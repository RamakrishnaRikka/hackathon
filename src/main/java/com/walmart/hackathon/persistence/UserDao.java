package com.walmart.hackathon.persistence;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.walmart.hackathon.model.HackUser;

public interface UserDao extends JpaRepository<HackUser,BigInteger>{
	@Query("select u from HackUser u where u.email=?1")
	public HackUser findUsersByEmail(String  email);

}
