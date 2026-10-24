package org.daw.servlets.ej02_CRUD_categorias_teclados.categorias;

import java.util.ArrayList;
import java.util.List;


public class ControladorCategoria {
	
	private static final List<Categoria> categorias = new ArrayList<>();

    

    static {
        categorias.add(new Categoria(1, "Mecanico"));
        categorias.add(new Categoria(2, "Inalambrico"));
        categorias.add(new Categoria(3, "Membrana"));
    }

    /**
     * 
     * @return
     */
    public static List<Categoria> getLista() {
        return categorias;
    }

    /**
     * 
     * @return
     */
    private static int getMaxId() {
    	if (categorias.size() == 0) return 1;
    	else {
    		int maxId = categorias.getFirst().getId();
    		for (Categoria c : categorias) {
				if (c.getId() > maxId) maxId = c.getId();
			} 
    		return maxId;
    	}
    }

    /**
     * 
     * @param id
     * @return
     */
	public static Categoria getCategoria(int id) {
		for (Categoria c : ControladorCategoria.getLista()) {
			if (c.getId() == id) return c;
		}
		return null;
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static Categoria createCategoria(String descripcion) {
		Categoria c = new Categoria(getMaxId() + 1, descripcion);
		categorias.add(c);
		return c;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static boolean deleteCategoria(int id) {
		Categoria c = ControladorCategoria.getCategoria(id);
		if (c != null) {
			categorias.remove(c);
			return true;
		}
		return false;
	}
    

}
