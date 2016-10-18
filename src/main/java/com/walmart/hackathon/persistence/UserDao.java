package com.walmart.hackathon.persistence;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.walmart.hackathon.model.HackUser;

public interface UserDao extends JpaRepository<HackUser,BigInteger>{
	
	@Query("select u from HackUser u where u.id in(select gu.userId from GroupUserMapping gu where gu.groupId=?1)")
	public List<HackUser> findUsersByGroup(BigInteger groupId);
}
