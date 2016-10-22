package com.walmart.hackathon.model;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	BigInteger itemId;
	
	@NotNull
	@Column(name="item_name")
	String itemName;
	
	@NotNull
	@Column(name="gtin")
	String gtin;
	
	@NotNull
	@Column(name="price")
	Float price;
	
	@NotNull
	@Column(name="per_item_qty")
	Integer perItemQty;
	
	@NotNull
	@Column(name="total_qty")
	Integer totalQty;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getPerItemQty() {
		return perItemQty;
	}

	public void setPerItemQty(Integer perItemQty) {
		this.perItemQty = perItemQty;
	}

	public Integer getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}

	public BigInteger getItemId() {
		return itemId;
	}
	
	
}
