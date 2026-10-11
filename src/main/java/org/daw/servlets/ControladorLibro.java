package org.daw.servlets;

import java.util.ArrayList;
import java.util.List;

public class ControladorLibro {

	private static final List<Libro> libros = new ArrayList<>();

	private static int siguienteId = 4;

	static {
		libros.add(new Libro(1, "El Quijote", 2015));
		libros.add(new Libro(2, "La Odisea", 2006));
		libros.add(new Libro(3, "El Capitan Alatriste", 2025));
	}

	/**
	 * 
	 * @return
	 */
	public static synchronized List<Libro> listar() {
		return new ArrayList<>(libros);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static synchronized Libro buscar(int id) {
		for (Libro libro : libros) {
			if (libro.getId() == id) {
				return libro;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param nombre
	 * @param anoEdicion
	 * @return
	 */
	public static synchronized Libro crear(String nombre, int anoEdicion) {
		Libro libro = new Libro(siguienteId++, nombre, anoEdicion);
		libros.add(libro);
		return libro;
	}

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param anoEdicion
	 * @return
	 */
	public static synchronized Libro modificar(int id, String nombre, Integer anoEdicion) {
		Libro libro = buscar(id);
		if (libro == null) {
			return null;
		}
		if (nombre != null) {
			libro.setNombre(nombre);
		}
		if (anoEdicion != null) {
			libro.setAnoEdicion(anoEdicion);
		}
		return libro;
	}

	/**
	 * 
	 * @param id
	 * @returnz
	 */
	public static synchronized boolean eliminar(int id) {
		Libro libro = buscar(id);
		if (libro == null) {
			return false;
		}
		libros.remove(libro);
		return true;
	}
}