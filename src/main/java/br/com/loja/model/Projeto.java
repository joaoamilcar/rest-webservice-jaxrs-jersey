package br.com.loja.model;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Projeto {
	
	private long id;
	private String nome;
	private int anoDeInicio;
	
	public Projeto() {
		// TODO Auto-generated constructor stub
	}
	
	public Projeto(long id, String nome, int anoDeInicio) {
		this.id = id;
		this.nome = nome;
		this.anoDeInicio = anoDeInicio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public int getAnoDeInicio() {
		return anoDeInicio;
	}
	
	public String toXML() {
		return new XStream().toXML(this);
	}
	
	public String toJson( ) {
		return new Gson().toJson(this);
	}
	
}
