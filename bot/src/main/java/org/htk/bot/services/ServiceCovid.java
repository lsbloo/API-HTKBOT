package org.htk.bot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.htk.bot.repository.CovidRepository;
import org.htk.bot.models.Covid;


import java.util.List;


@Service
public class ServiceCovid {
	
	
	
	private CovidRepository covidRepository;
	
	
	@Autowired
	public ServiceCovid(CovidRepository covidRepository) {
		this.covidRepository=covidRepository;
	}
	
	
	public List<Covid> getAllCovid(){
		return this.covidRepository.getAllCovid();
	}

	public List<Covid> getCovidByData(String data){
		return this.covidRepository.getCovidByData(data);
	}
	
	
	
	public List<Covid> getCovidByDataRegiao(String data,String regiao){
		return this.covidRepository.getCovidByDataRegiao(data,regiao);
	}
	
}
