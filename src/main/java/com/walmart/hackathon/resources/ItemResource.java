package com.walmart.hackathon.resources;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.walmart.hackathon.model.Item;
import com.walmart.hackathon.model.ItemUserMapping;
import com.walmart.hackathon.persistence.ItemDao;
import com.walmart.hackathon.persistence.ItemUserDao;

@Path("/items")
public class ItemResource {

	ItemDao itemDao;
	ItemUserDao itemUserDao;
	@Inject
	public ItemResource(ItemDao itemDao,ItemUserDao itemUserDao ) {
		this.itemDao=itemDao;
		this.itemUserDao =itemUserDao;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Item> getItems(){
		List<Item> items =itemDao.findAll();
		return items;
	}
	
	 /**
     * Create new itemuser
     * @param item
     * @return new itemuser
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Item save(@Valid Item item) {
        return itemDao.save(item);
    }
    
    
    @GET
    @Path("itemuser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ItemUserMapping> getItems1(){
		List<ItemUserMapping> items =itemUserDao.findAll();
		return items;
	}
	
	 /**
     * Create new itemuser
     * @param item
     * @return new itemuser
     */
    @SuppressWarnings("unused")
	@POST
    @Path("itemuser")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public ItemUserMapping saveItemuser(@Valid ItemUserMapping itemUserMapping) {
    	ItemUserMapping itemUserMappingobj =null;
    	Integer availableQty =0;
    	Integer newAvailableQty = 0;
    	int sharedQuantity =0;
    	int availablereservedQuantity;
    	Integer groupId=null;
    	
    	try{ 
    	Item myitem=itemDao.findOne(itemUserMapping.getItemId());    	
    	List<ItemUserMapping> sharedItems = itemUserDao.getItemUsrDetails(itemUserMapping.getItemId(),ItemUserDao.STATUS_OPEN);
    	if(myitem.getPerItemQty()-itemUserMapping.getSelectedQuantity()>=0) {
    	if((sharedItems==null || sharedItems.isEmpty() )) {
    		itemUserMapping.setGroupId(1);
    		
    		newAvailableQty=myitem.getPerItemQty()-itemUserMapping.getSelectedQuantity();
    		itemUserMapping.setAvailableQty(newAvailableQty);
    		groupId = itemUserMapping.getGroupId();
    		itemUserMapping.setStatus(ItemUserDao.STATUS_OPEN);
    		itemUserMappingobj=itemUserDao.save(itemUserMapping);
    		
    	}else {
    		
    		for(ItemUserMapping sharedItem :sharedItems){
    			if(sharedItem.getAvailableQty()>=itemUserMapping.getSelectedQuantity()){
    				groupId=sharedItem.getGroupId();
    				availableQty = sharedItem.getAvailableQty();
    				
    			}
    		}
    		if(null==groupId){
    			List<Integer> maxGrpsharedItems=itemUserDao.getMaxGrpId(itemUserMapping.getItemId(), ItemUserDao.STATUS_OPEN);
    			int maxGrpsharedItemsGrpid =maxGrpsharedItems.get(0);
    			groupId = maxGrpsharedItemsGrpid+1;
    			itemUserMapping.setGroupId(groupId);
        		itemUserMapping.setStatus(ItemUserDao.STATUS_OPEN);
        		newAvailableQty = myitem.getPerItemQty()-itemUserMapping.getSelectedQuantity();
        		itemUserMapping.setAvailableQty(newAvailableQty);
        		itemUserMappingobj=itemUserDao.save(itemUserMapping);
    		}
    		else{
    		
    		itemUserMapping.setGroupId(groupId);
    		itemUserMapping.setStatus(ItemUserDao.STATUS_OPEN);
    		newAvailableQty = availableQty-itemUserMapping.getSelectedQuantity();
    		itemUserMapping.setAvailableQty(newAvailableQty);
    		itemUserMappingobj=itemUserDao.save(itemUserMapping);
    		itemUserDao.updateItemUsrDetails(newAvailableQty,itemUserMapping.getItemId(),groupId);
    		}
    		
    	}
		
		if(newAvailableQty==0){
			itemUserDao.updateGroupClosed(ItemUserDao.STATUS_CLOSED,itemUserMapping.getItemId(),groupId);
			
		}
    	}
    	
    	
    	//return itemUserMappingobj;
    }catch(Exception e) {
    	
    }
		return itemUserMappingobj;
    	
}
    
    @GET
	@Path("{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Item getItem(@PathParam("itemId") BigInteger itemId){
		
		return itemDao.findOne(itemId);
	
	}
}
