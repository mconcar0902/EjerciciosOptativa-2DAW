package org.daw.servlets.ej03_CRUD_categoria_teclado_bd;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SuperServlet extends HttpServlet {
	
	protected Integer getIntParameterWithCheck (String paramN, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParam = request.getParameter(paramN);

		try {
			return Integer.parseInt(strParam);

		} catch (NumberFormatException e) {
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'id' debe ser un número entero.");
			
		}
		return null; 
	}

	
	protected void enviarError(HttpServletResponse response, int codigoEstado, String mensaje) throws IOException {

		response.setStatus(codigoEstado);
		response.getWriter().print(JSONUtils.toJSON(new Error(mensaje)));
	}
	
}
