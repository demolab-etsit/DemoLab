package es.isst.demolab.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Elec_Circ_Part implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Partido partido;
	private Circunscripcion circunscripcion;
	private Eleccion eleccion;
	private int nvotos;
	private String nvotosString;
	private int nEscanos;
	private String nEscanosString;
	
	public String getnEscanosString() {
		return nEscanosString;
	}

	public void setnEscanosString(String nEscanosString) {
		this.nEscanosString = nEscanosString;
	}

	public int getNEscanos() {
		return nEscanos;
	}

	public void setNEscanos(int nEscanos) {
		this.setnEscanosString(String.valueOf(nEscanos));
		this.nEscanos = nEscanos;
	}

	public Elec_Circ_Part() {
		this.nEscanos=0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Circunscripcion getCircunscripcion() {
		return circunscripcion;
	}

	public void setCircunscripcion(Circunscripcion circunscripcion) {
		this.circunscripcion = circunscripcion;
	}

	public Eleccion getEleccion() {
		return eleccion;
	}

	public void setEleccion(Eleccion eleccion) {
		this.eleccion = eleccion;
	}

	public int getNVotos() {
		return nvotos;
	}

	public void setNVotos(int nvotos) {
		this.setNvotosString(String.valueOf(nvotos));
		this.nvotos = nvotos;
	}

	public String getNvotosString() {
		return nvotosString;
	}

	public void setNvotosString(String nvotosString) {
		this.nvotosString = nvotosString;
	}

	public void addEscanos() {
		this.setNEscanos(this.getNEscanos() + 1);
	}

	
	
	
	
	
	
	
	
	
}
