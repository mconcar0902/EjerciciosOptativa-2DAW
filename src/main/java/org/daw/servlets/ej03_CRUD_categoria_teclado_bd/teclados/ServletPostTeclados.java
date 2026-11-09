package org.daw.servlets.ej03_CRUD_categoria_teclado_bd.teclados;

import java.io.IOException;
import java.sql.SQLException;

import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.JSONUtils;
import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.SuperServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletPostTeclados
 */
@WebServlet("/ej03_teclados/crear")
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
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "El parametro 'color' no puede estar vacio");
			return;
		}
		
		String idiomaParam = request.getParameter("idioma");	// Compruebas si tiene contenido el parametro 'Idioma'
		if (idiomaParam == null) {
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "El parametro 'idioma' no puede estar vacio");
			return;
		}
		
		String idCatParam = request.getParameter("idCategoria");		// Compruebas si tiene contenido el parametro 'idCategoria'
		if (idCatParam == null) {
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "El parametro 'idCategoria' no puede estar vacio");
			return;
		}
		
		Integer idCat = getIntParameterWithCheck("idCategoria", request, response);  // Compruebas si es un int
		if (idCat == null) return;
		
		boolean idCatValido = ControladorTeclado.checkIdCategoria(idCat);
		if(!idCatValido) {
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "El parametro 'idCategoria' tiene que existir");
			return;
		}
		
		Teclado t = null;
		try {
			t = ControladorTeclado.createTeclado(colorParam,idiomaParam,idCat);
		} catch (SQLException e) {
			e.printStackTrace();
			enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Ha habido un error al crear el teclado.");
			return;
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(t));
	}

}
