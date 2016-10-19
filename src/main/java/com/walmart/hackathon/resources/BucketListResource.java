package com.walmart.hackathon.resources;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.walmart.hackathon.model.BucketList;
import com.walmart.hackathon.model.HackUser;
import com.walmart.hackathon.model.Item;
import com.walmart.hackathon.persistence.BucketListDao;
import com.walmart.hackathon.persistence.ItemDao;
import com.walmart.hackathon.persistence.UserDao;

@Path("/bucketLists")
public class BucketListResource {
	
	BucketListDao bucketListDao;
	ItemDao itemDao;
	UserDao  userDao;
	
	@Inject
	public BucketListResource(BucketListDao bucketListDao,ItemDao itemDao,UserDao userDao) {
		this.bucketListDao=bucketListDao;
		this.itemDao=itemDao;
		this.userDao=userDao;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<BucketList> getBucketLists(@QueryParam("userId") BigInteger userId,@QueryParam("groupId") BigInteger groupId){
		List<BucketList> bucketLists =null;
		
		if(null!=userId && null!=groupId){
			bucketLists=bucketListDao.findByGroupAndUser(groupId,userId);
		}else if(null!=groupId){
			bucketLists=bucketListDao.fingByGroup(groupId);
		}
		else{
			bucketLists = new ArrayList<>();
		}
		for(BucketList bl:bucketLists){
			Item item =itemDao.findOne(bl.getItemId());
			HackUser hackUser =userDao.findOne(bl.getUserId());
			bl.setItem(item);
			bl.setHackUser(hackUser);
		}
		return bucketLists;
	}
	
/**
     * Create new USER
     * @param user
     * @return new user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public BucketList save(@Valid BucketList bucketList) {
    	bucketList.setStatus("open");
    return bucketListDao.save(bucketList);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public BucketList update(@Valid BucketList bucketList) {
    return bucketListDao.save(bucketList);
    }
   
}
