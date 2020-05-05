package org.htk.bot.controller;

import org.htk.bot.services.ServiceCovid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import org.htk.bot.models.Covid;
import org.htk.bot.pojo.MessengerResponse;


@RestController
@Api(value="Covid")
public class CovidBrasilController {
	
	
	@Autowired
	private ServiceCovid serviceCovid;
	
	@ApiOperation(value="retorna uma lista de todos os dados da base")
	@GetMapping(value = "/covids",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getAllCovid(){
		List<Covid> list = this.serviceCovid.getAllCovid();
		if (list.size() != 0){
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
		}
		MessengerResponse mensageiro = new MessengerResponse("400", "NÃO EXISTE DADOS NA BASE.");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensageiro);
	}
	
	
	@ApiOperation(value="retorna dados sobre covid19. parametros data da ocorrencia e regiao. Ex: data=2020-05-01 & regiao=Nordeste")
	@GetMapping(value="/covid/data",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getCovidByData(@RequestParam(value="data") String data, @RequestParam(value="regiao") String regiao){
		
		
		if(!regiao.isEmpty() && data != null) {
			List<Covid> list= this.serviceCovid.getCovidByDataRegiao(data, regiao);
			if(list.size() != 0) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
			}else {
				MessengerResponse mensageiro = new MessengerResponse("404 - ", "NÃO FOI POSSIVEL ENCONTRAR DADOS COM ESTES PARAMETROS");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageiro);
			}
		}
		
	  if(data.isEmpty() && regiao.isEmpty()){
		  MessengerResponse mensageiro = new MessengerResponse("403", "Nenhuma Passagem de Parametro.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageiro);
	  }
	  
	  if(regiao.isEmpty() && data != null) {
			  List<Covid> list = this.serviceCovid.getCovidByData(data);
			  if(list.size() != 0) {
					return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
				}else {
					MessengerResponse mensageiro = new MessengerResponse("404 - ", "NÃO FOI POSSIVEL ENCONTRAR DADOS COM A DATA INFORMADA");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageiro);
			}
	  	}
	  
	  return null;
	}
	
	
	

}
