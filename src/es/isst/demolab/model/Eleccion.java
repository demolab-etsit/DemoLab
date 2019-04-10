package es.isst.demolab.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Eleccion implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Date fecha;
	private TipoElecciones tipo;
	//private String FechaString;
	
	public Eleccion(){
		
	}
	
	//Getters Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getFechaString() {
//		return FechaString;
//	}
	
//	public void setFechaString(String FechaString) {
//		this.FechaString = FechaString;
//	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoElecciones getTipo() {
		return tipo;
	}

	public void setTipo(TipoElecciones tipo) {
		this.tipo = tipo;
	}

}
