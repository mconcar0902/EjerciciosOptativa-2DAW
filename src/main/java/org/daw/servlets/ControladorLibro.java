package org.daw.servlets;

import java.util.ArrayList;
import java.util.List;

public class ControladorLibro {

	private static final List<Libro> libros = new ArrayList<>();
	
	private static int siguienteId = 4;
	
	static {
		libros.add(new Libro(1,"El Quijote",2015));
		libros.add(new Libro(2,"La Odisea",2006));
		libros.add(new Libro(3,"El Capitan Alatriste",2025));
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Libro> listar() {
		return libros;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Libro buscar(int id) {
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
	 * @param añoEdicion
	 * @return
	 */
	public static Libro crear(String nombre, int añoEdicion) {
		Libro libro = new Libro(siguienteId++, nombre, añoEdicion);
		
		libros.add(libro);
		return libro;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static boolean eliminar(int id) {
		Libro libro = buscar(id);
		
		if (libro == null) {
			return false;
		}
		
		libros.remove(libro);
		
		return true;
	}
}
