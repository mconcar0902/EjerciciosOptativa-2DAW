package org.daw.servlets;

public class Libro {

	private int id;
	private String nombre;
	private int añoEdicion;
	
	
	public Libro(int id, String nombre, int añoEdicion) {
		this.id = id;
		this.nombre = nombre;
		this.añoEdicion = añoEdicion;
	}

///////////////////////////////////////////////////////////////////////////////////////////

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getAñoEdicion() {
		return añoEdicion;
	}
	
	public void setAñoEdicion(int añoEdicion) {
		this.añoEdicion = añoEdicion;
	}
}
