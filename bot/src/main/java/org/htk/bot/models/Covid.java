package org.htk.bot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="covid19_brasil")
public class Covid {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	
	@Column(name="regiao", length=255)
	private String regiao;
	
	@Column(name="estado", length=100)
	private String estado;
	
	@Column(name="data", length=100)
	private String data;
	
	@Column(name="casosacumulados", length=100)
	private String casosAcumulados;
	
	@Column(name="casosnovos", length=100)
	private String casosNovos;
	
	@Column(name="obitosnovos", length=100)
	private String obitosnovos;
	
	@Column(name="obitosacumulados", length=100)
	private String obitosAcumulados;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getCasosAcumulados() {
		return casosAcumulados;
	}

	public void setCasosAcumulados(String casosAcumulados) {
		this.casosAcumulados = casosAcumulados;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	
	
	
	
}
