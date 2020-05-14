package org.htk.bot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.htk.bot.models.Covid;
import java.util.List;



@Repository
public interface CovidRepository extends CrudRepository<Covid,Integer>{
	
	
	
	/**
	 * 
	 * @return
	 */
	@Query(value= "select * from covid19_brasil",nativeQuery=true)
	List<Covid> getAllCovid();
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	@Query(value="select * from covid19_brasil where data=:datax",nativeQuery=true)
	List<Covid> getCovidByData(@Param("datax") String datax);
	
	/**
	 * 
	 * @param datax
	 * @param regiaox
	 * @return
	 */
	@Query(value="select * from covid19_brasil where data=:datax and regiao=:regiaox",nativeQuery=true)
	List<Covid> getCovidByDataRegiao(@Param("datax") String datax, @Param("regiaox") String regiaox);
	

}
