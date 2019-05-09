package es.isst.demolab.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Partido implements Serializable{

	
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int Id;
	
	@Id
	private String Nombre;
	private String color;
	private String acronimo;
	
	public Partido() {

		
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAcronimo() {
		return acronimo;
	}
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}
	

}
