
package org.daw.servlets.ej01_CRUD_libros;

public class Libro {

	private int id;
	private String nombre;
	private int anoEdicion;

	public Libro(int id, String nombre, int anoEdicion) {
		this.id = id;
		this.nombre = nombre;
		this.anoEdicion = anoEdicion;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////

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

	public int getAnoEdicion() {
		return anoEdicion;
	}

	public void setAnoEdicion(int anoEdicion) {
		this.anoEdicion = anoEdicion;
	}
}