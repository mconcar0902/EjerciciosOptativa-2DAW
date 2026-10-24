package org.daw.servlets.ej02_CRUD_categorias_teclados.teclados;

import java.util.ArrayList;
import java.util.List;

import org.daw.servlets.ej02_CRUD_categorias_teclados.categorias.Categoria;
import org.daw.servlets.ej02_CRUD_categorias_teclados.categorias.ControladorCategoria;

public class ControladorTeclado {

	private static final List<Teclado> teclados = new ArrayList<>();
	
	static {
		teclados.add(new Teclado(1, "Azul", "QWERTY", 1));
		teclados.add(new Teclado(2, "Negro", "QWERTY", 3));
		teclados.add(new Teclado(3, "Rojo", "AZERTY", 2));
	}

	/**
	 * 
	 * @return
	 */
	public static List<Teclado> getLista() {
        return teclados;
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Teclado getTeclado(int id) {
		for (Teclado t : teclados) {
			if (id == t.getId()) return t;
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	private static int getMaxId() {
		if (teclados.size() == 0) return 1;
		else {
			int idMax = teclados.getFirst().getId();
			for (Teclado t : teclados) {
				if (t.getId() > idMax) idMax = t.getId();
			}
			return idMax;
		}
	}
	
	/**
	 * 
	 * @param color
	 * @param idioma
	 * @param idCategoria
	 * @return
	 */
	public static Teclado createTeclado(String color, String idioma, int idCategoria) {
		Teclado t = new Teclado(getMaxId() + 1 , color, idioma, idCategoria);
		teclados.add(t);
		return t;
	}
	
	/**
	 * 
	 * @param idCat
	 * @return
	 */
	public static boolean checkIdCategoria(int idCat) {
		Categoria c = ControladorCategoria.getCategoria(idCat);
		if (c == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static boolean deleteTeclado(int id) {
		Teclado t = getTeclado(id);
		if (t != null) {
			teclados.remove(t);
			return true;
		}
		return false;
	}
	
}
