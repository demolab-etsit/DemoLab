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
	private Circunscripcion Circunscripcion;
	private Eleccion eleccion;
	
	
	private int NVotos;
	private int NEscanos;
	
	public int getNEscanos() {
		return NEscanos;
	}

	public void setNEscanos(int nEscanos) {
		NEscanos = nEscanos;
	}

	public Elec_Circ_Part() {
		
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
		return Circunscripcion;
	}

	public void setCircunscripcion(Circunscripcion circunscripcion) {
		Circunscripcion = circunscripcion;
	}

	public Eleccion getEleccion() {
		return eleccion;
	}

	public void setEleccion(Eleccion eleccion) {
		this.eleccion = eleccion;
	}

	public int getNVotos() {
		return NVotos;
	}

	public void setNVotos(int nVotos) {
		NVotos = nVotos;
	}

	
	
	
	
	
	
	
	
	
}
