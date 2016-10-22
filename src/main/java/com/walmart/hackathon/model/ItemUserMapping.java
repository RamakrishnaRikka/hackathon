package com.walmart.hackathon.model;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ItemUserMapping {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	BigInteger itemUserMapingId;
	
	
	@NotNull
	@Column(name = "item_Id")
	BigInteger itemId;

	@NotNull
	@Column(name = "user_id")
	BigInteger userId;
	
	@NotNull
	@Column(name = "selected_quantity")
	Integer selectedQuantity;
	
	@NotNull
	@Column(name = "user_date")
	Date userDate;
	
	
	
	@Column(name = "group_id")
	Integer groupId;
	
	
	
	@Column(name = "status")
	String status;
	
	
	@Column(name = "available_qty")
	Integer availableQty;


	public BigInteger getItemId() {
		return itemId;
	}


	public void setItemId(BigInteger itemId) {
		this.itemId = itemId;
	}


	public BigInteger getUserId() {
		return userId;
	}


	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}


	public Integer getSelectedQuantity() {
		return selectedQuantity;
	}


	public void setSelectedQuantity(Integer selectedQuantity) {
		this.selectedQuantity = selectedQuantity;
	}


	public Date getUserDate() {
		return userDate;
	}


	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}


	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getAvailableQty() {
		return availableQty;
	}


	public void setAvailableQty(Integer availableQty) {
		this.availableQty = availableQty;
	}


	public BigInteger getItemUserMapingId() {
		return itemUserMapingId;
	}

	
}
