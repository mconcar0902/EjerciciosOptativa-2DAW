package org.daw.servlets.ej03_CRUD_categoria_teclado_bd;

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
