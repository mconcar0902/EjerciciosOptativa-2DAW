package org.daw.servlets.ej02_CRUD_categorias_teclados;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SuperServlet extends HttpServlet {
	
	protected Integer getIntParameterWithCheck (String paramN, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParam = request.getParameter(paramN);
		if (strParam == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("Parámetro no incluido: '" + paramN + "'")));
			return null;
		}
		
		try {
			return Integer.parseInt(strParam);
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("Parámetro '" + paramN + "': '" + strParam + "' no es un valor entero")));
		}
		
		return null;
	}

}
