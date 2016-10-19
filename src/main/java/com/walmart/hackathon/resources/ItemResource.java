package com.walmart.hackathon.resources;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.walmart.hackathon.model.Item;
import com.walmart.hackathon.persistence.ItemDao;

@Path("/items")
public class ItemResource {

	ItemDao itemDao;
	
	@Inject
	public ItemResource(ItemDao itemDao) {
		this.itemDao=itemDao;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Item> getItems(){
		List<Item> items =itemDao.findAll();
		return items;
	}
	
	@GET
	@Path("{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Item getItem(@PathParam("itemId") BigInteger itemd){
		return itemDao.findOne(itemd);
	
	}
	
	 /**
     * Create new ITEM
     * @param item
     * @return new item
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Item save(@Valid Item item) {
        return itemDao.save(item);
    }
}
