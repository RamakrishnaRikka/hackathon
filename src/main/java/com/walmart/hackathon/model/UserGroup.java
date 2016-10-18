package com.walmart.hackathon.model;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserGroup {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	BigInteger groupId;
	
	@NotNull
	@Column(name = "group_name")
	String groupName;
	
	@NotNull
	@Column(name = "created_by")
	BigInteger createdBy;
	
	@JsonProperty
	@Transient
	List<HackUser> members;

	public List<HackUser> getMembers() {
		return members;
	}

	public void setMembers(List<HackUser> members) {
		this.members = members;
	}

	public BigInteger getGroupId() {
		return groupId;
	}

	public void setGroupId(BigInteger groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public BigInteger getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}
	
	
}
