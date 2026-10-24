package org.daw.servlets.ej02_CRUD_categorias_teclados.teclados;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.daw.servlets.ej02_CRUD_categorias_teclados.Error;
import org.daw.servlets.ej02_CRUD_categorias_teclados.JSONUtils;
import org.daw.servlets.ej02_CRUD_categorias_teclados.SuperServlet;

/**
 * Servlet implementation class ServletDeleteTeclados
 */
@WebServlet("/ej02_teclados/eliminar")
public class ServletDeleteTeclados extends SuperServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletDeleteTeclados() {
        super();
    }

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParam = request.getParameter("id");
		if (idParam == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("El parametro 'id' no puede estar vacio")));
			return;
		}
		
		Integer id = getIntParameterWithCheck("id", request, response);	
		if (id == null) return;
		
		if (ControladorTeclado.deleteTeclado(id)) {		// Comprueba si existe ese id devuelve true o false
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(JSONUtils.toJSON(new Error("El teclado con 'id'='"+id+"' eliminado con exito")));
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El teclado con 'id'='"+id+"' no encontrado")));
		}
	}

}
