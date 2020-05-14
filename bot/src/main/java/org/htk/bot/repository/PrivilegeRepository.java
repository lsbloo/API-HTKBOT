package org.htk.bot.repository;

import javax.transaction.Transactional;

import org.htk.bot.models.Privilege;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Integer>{
	
	
	
	@Query(value = "select * from privilege where name=:name" , nativeQuery=true)
	Privilege findPrivilegeByNameParam(@Param("name") String name);
	
	@Transactional
	@Modifying
	@Query(value="insert into privilege(id,name) values (:id,:name)" , nativeQuery=true)
	void insertPrivilege(@Param("id") Integer id , @Param("name") String name);

	
	
}