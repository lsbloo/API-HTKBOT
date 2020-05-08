package org.htk.bot.pojo.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.htk.bot.models.Covid;

public class CovidDTO {

	@NotNull
	private String regiao;
	@NotNull
	private String estado;
	@NotNull
	private String data;
	
	private String casosAcumulados;
	
	@NotNull
	private String casosNovos;
	
	private String obitosnovos;
	
	private String obitosAcumulados;
	public String getRegiao() {
		return regiao;
	}


	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getCasosAcumulados() {
		return casosAcumulados;
	}


	public void setCasosAcumulados(String casosAcumulados) {
		this.casosAcumulados = casosAcumulados;
	}


	public String getCasosNovos() {
		return casosNovos;
	}


	public void setCasosNovos(String casosNovos) {
		this.casosNovos = casosNovos;
	}


	public String getObitosnovos() {
		return obitosnovos;
	}


	public void setObitosnovos(String obitosnovos) {
		this.obitosnovos = obitosnovos;
	}


	public String getObitosAcumulados() {
		return obitosAcumulados;
	}


	public void setObitosAcumulados(String obitosAcumulados) {
		this.obitosAcumulados = obitosAcumulados;
	}
	
	
	public CovidDTO(String regiao,String estado,String data,String casosAcumulados,String casosNovos,String obitosNovos,String obitosAcumulados) {
		setRegiao(regiao);
		setEstado(estado);
		setData(data);
		setCasosAcumulados(casosAcumulados);
		setCasosNovos(casosNovos);
		setObitosnovos(obitosNovos);
		setObitosAcumulados(obitosAcumulados);
		
		
	}
	
	public Covid createCovid() {
		return new Covid();
	}
}
