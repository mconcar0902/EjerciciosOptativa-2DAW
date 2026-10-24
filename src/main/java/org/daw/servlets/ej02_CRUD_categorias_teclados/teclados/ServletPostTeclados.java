package org.daw.servlets.ej02_CRUD_categorias_teclados.teclados;

import java.io.IOException;

import org.daw.servlets.ej02_CRUD_categorias_teclados.Error;
import org.daw.servlets.ej02_CRUD_categorias_teclados.JSONUtils;
import org.daw.servlets.ej02_CRUD_categorias_teclados.SuperServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletPostTeclados
 */
@WebServlet("/ej02_teclados/crear")
public class ServletPostTeclados extends SuperServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletPostTeclados() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String colorParam = request.getParameter("color");		// Compruebas si tiene contenido el parametro 'Color'
		if (colorParam == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("El parametro 'color' no puede estar vacio")));
			return;
		}
		
		String idiomaParam = request.getParameter("idioma");	// Compruebas si tiene contenido el parametro 'Idioma'
		if (idiomaParam == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("El parametro 'idioma' no puede estar vacio")));
			return;
		}
		
		String idCatParam = request.getParameter("idCategoria");		// Compruebas si tiene contenido el parametro 'idCategoria'
		if (idCatParam == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("El parametro 'idCategoria' no puede estar vacio")));
			return;
		}
		
		Integer idCat = getIntParameterWithCheck("idCategoria", request, response);  // Compruebas si es un int
		
		if (!ControladorTeclado.checkIdCategoria(idCat)) {		// Comprueba si el 'idCategoria' existe 
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El 'idCategoria'='"+idCat+"' no se ha encontrado")));
			return;
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(ControladorTeclado.createTeclado(colorParam, idiomaParam, idCat)));
	}

}
