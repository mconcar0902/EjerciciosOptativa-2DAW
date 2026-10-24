package org.daw.servlets.ej02_CRUD_categorias_teclados;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 
	 * @param o
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String toJSON(Object o) throws JsonProcessingException {	
		return mapper.writeValueAsString(o);
	}
}
