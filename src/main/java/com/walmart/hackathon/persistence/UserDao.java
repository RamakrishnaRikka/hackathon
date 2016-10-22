package com.walmart.hackathon.persistence;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walmart.hackathon.model.HackUser;

public interface UserDao extends JpaRepository<HackUser,BigInteger>{

}
