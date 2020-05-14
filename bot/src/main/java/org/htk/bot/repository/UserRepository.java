package org.htk.bot.repository;

import javax.transaction.Transactional;

import org.htk.bot.models.UserHtk;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserHtk,Integer>{
	
	
	
	@Query(value="select * from userhtk where username=:u", nativeQuery=true)
	UserHtk validateExistenceUser(@Param("u") String username) throws NullPointerException;

	@Modifying
	@Transactional
	@Query(value="insert into user_role (usuarios_id,role_id) values (usuarios_id,role_id)",nativeQuery = true)
	void insertRelationUser(@Param("usuarios_id") Long usuarios_id , @Param("role_id") Long role_id);
}

