package com.walmart.hackathon.resources;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.walmart.hackathon.model.HackUser;
import com.walmart.hackathon.model.UserGroup;
import com.walmart.hackathon.model.GroupUserMapping;
import com.walmart.hackathon.persistence.GroupDao;
import com.walmart.hackathon.persistence.GroupUserMappingDao;
import com.walmart.hackathon.persistence.UserDao;

@Path("groups")
public class GroupResource {
	
	GroupDao groupDao;
	GroupUserMappingDao guMappingDao;
	UserDao userDao;
	@Inject
	public GroupResource(GroupDao groupDao,GroupUserMappingDao guMappingDao,UserDao userDao) {
		this.groupDao=groupDao;
		this.guMappingDao=guMappingDao;
		this.userDao=userDao;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<UserGroup> getGroups(@QueryParam("userId") BigInteger userId){
		List<UserGroup> groups=null;
		if(null!=userId){
			groups = groupDao.findGroupsOfUser(userId);
		}else{
			 groups= groupDao.findAll();
		}
		for(UserGroup ug:groups){
			ug.setMembers(userDao.findUsersByGroup(ug.getGroupId()));
			
		}
		return groups;
	}
	
	@GET
	@Path("{groupId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<GroupUserMapping> getGroup(){
		List<GroupUserMapping> group= guMappingDao.findAll();
		for(GroupUserMapping gum:group){
			HackUser user= userDao.findOne(gum.getUserId());
			gum.setHackUser(user); 
		}
		return group;
	}
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserGroup save(@Valid UserGroup group) {
		UserGroup gp= groupDao.save(group);
		GroupUserMapping gum =new GroupUserMapping();
		gum.setGroupId(gp.getGroupId());
		gum.setUserId(gp.getCreatedBy());
		guMappingDao.save(gum);
		return gp;
        
    }
	
	@POST
	@Path("user")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public GroupUserMapping addUserToGroup(@Valid GroupUserMapping gum){
		guMappingDao.save(gum);
		return gum;
	}
	
}
