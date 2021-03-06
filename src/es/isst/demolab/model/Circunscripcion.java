package es.isst.demolab.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Circunscripcion implements Serializable {

	
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int Id;
	
	@Id
	private String Nombre;
	private int NElectores;
	private int NMaxEscanos;
	private String colorCircunscripcion;
	
	public Circunscripcion () {
		
	}
	
//	public int getId() {
//		return Id;
//	}
//	public void setId(int id) {
//		Id = id;
//	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getNElectores() {
		return NElectores;
	}
	public void setNElectores(int nElectores) {
		NElectores = nElectores;
	}
	public int getNMaxEscanos() {
		return NMaxEscanos;
	}
	public void setNMaxEscanos(int nMaxEscanos) {
		NMaxEscanos = nMaxEscanos;
	}

	public String getColorCircunscripcion() {
		return colorCircunscripcion;
	}

	public void setColorCircunscripcion(String colorCircunscripcion) {
		this.colorCircunscripcion = colorCircunscripcion;
	}
}
