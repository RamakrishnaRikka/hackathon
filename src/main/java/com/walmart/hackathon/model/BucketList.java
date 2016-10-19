package com.walmart.hackathon.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class BucketList {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	BigInteger bucketListID;
	
	@NotNull
	@Column(name = "item_id")
	BigInteger itemId;
	
	
	@Column(name="status")
	String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	@JsonProperty
	Item item;
	
	@Transient
	@JsonProperty
	HackUser hackUser;
	
	
	public HackUser getHackUser() {
		return hackUser;
	}

	public void setHackUser(HackUser hackUser) {
		this.hackUser = hackUser;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	@NotNull
	@Column(name = "item_qty")
	Double itemQty;
	
	@NotNull
	@Column(name = "user_id")
	BigInteger userId;
	
	@NotNull
	@Column(name = "group_id")
	BigInteger userGroupId;

	public BigInteger getBucketListID() {
		return bucketListID;
	}

	public void setBucketListID(BigInteger bucketListID) {
		this.bucketListID = bucketListID;
	}

	public BigInteger getItemId() {
		return itemId;
	}

	public void setItemId(BigInteger itemId) {
		this.itemId = itemId;
	}

	public Double getItemQty() {
		return itemQty;
	}

	public void setItemQty(Double itemQty) {
		this.itemQty = itemQty;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(BigInteger userGroupId) {
		this.userGroupId = userGroupId;
	}
	
	
}
