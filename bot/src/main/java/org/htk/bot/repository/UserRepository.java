package org.htk.bot.repository;

import org.htk.bot.models.UserHtk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserHtk,Integer>{
	
	
	
	@Query(value="select * from userhtk where username=:u", nativeQuery=true)
	UserHtk validateExistenceUser(@Param("u") String username) throws NullPointerException;

}
