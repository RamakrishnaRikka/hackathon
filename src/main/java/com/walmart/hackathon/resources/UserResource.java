package com.walmart.hackathon.resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.walmart.hackathon.model.HackUser;
import com.walmart.hackathon.model.HackUserLogin;
import com.walmart.hackathon.persistence.UserDao;

@Path("/users")
public class UserResource {
	
	
	UserDao userDao;
	
	@Inject
	public UserResource(UserDao userDao) {
		this.userDao=userDao;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<HackUser> getUsers(){
		List<HackUser> users =userDao.findAll();
		return users;
	}
	
	 /**
     * Create new USER
     * @param user
     * @return new user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public HackUser save(@Valid HackUser user) {
        return userDao.save(user);
    }
    @POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public HackUser getUser(HackUserLogin hackUserLogin){
		HackUser user =userDao.findUsersByEmail(hackUserLogin.getUserName());
		if(null==user){
			user = new HackUser();
			user.setValid(false);
		}
		if(null != user && hackUserLogin.getPassword().equals(user.getPassword())){
			user.setValid(true);
		}
		else{
			user.setValid(false);
		}
		if(null!=user){
			user.setPassword("iamafool");
		}
		
		return user;
	}
}
