package com.walmart.hackathon.persistence;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.walmart.hackathon.model.ItemUserMapping;

@Transactional
public interface ItemUserDao extends JpaRepository<ItemUserMapping,Integer>{
	public static String STATUS_CLOSED = "CLOSED";
	public static String STATUS_OPEN = "OPEN";
	
	
	@Query("select i from ItemUserMapping i where i.itemId=?1 and i.status=?2")
	public List<ItemUserMapping>  getItemUsrDetails(BigInteger itemId,String status);

	@Modifying
	@Query("update  ItemUserMapping i set i.status =?1 where i.itemId=?2 and i.groupId=?3")
	public void updateGroupClosed(String status ,BigInteger itemId, Integer groupId);
	
	@Modifying
	@Query("update  ItemUserMapping i set i.availableQty =?1 where i.itemId=?2 and i.groupId=?3")
	public void updateItemUsrDetails(Integer availableQty ,BigInteger itemId, Integer groupId);
	@Query("SELECT MAX(groupId) FROM ItemUserMapping WHERE itemId=?1 GROUP BY itemId ,groupId ORDER BY groupId DESC ")
	public List<Integer> getMaxGrpId(BigInteger itemId);
	@Query("select i from ItemUserMapping i where i.itemId=?1")
	public List<ItemUserMapping> getItemUsrDetailsbyId(BigInteger itemIdShared);

}
