package org.daw.servlets.ej02_CRUD_categorias_teclados;

import java.util.List;

public class JSONUtils {

	/**
	 * 
	 * @param mensaje
	 * @return
	 */
	public static String errorToJSON (String mensaje) {
		return "{\"error\":\"" + mensaje + "\"}";
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public static String categoriaToJSON(Categoria c) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("{\"id\":"+c.getId()+",");
		sb.append("\"descripcion\":\""+c.getDescripcion()+"\"},");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param categorias
	 * @return
	 */
	public static String categoriaToJSONArray(List<Categoria> categorias) {
    	StringBuffer sb = new StringBuffer();
    	
    	sb.append("[");
		
		for (Categoria categoria : categorias) {
			sb.append(categoriaToJSON(categoria) + ",");
		}
		String strResponse = sb.toString();
		strResponse = strResponse.substring(0,sb.toString().length()-1);
		return strResponse +=  "]";
    }
}
