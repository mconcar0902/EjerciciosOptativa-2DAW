package org.daw.servlets.ej02_CRUD_categorias_teclados.categorias;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.daw.servlets.ej02_CRUD_categorias_teclados.Error;
import org.daw.servlets.ej02_CRUD_categorias_teclados.JSONUtils;
import org.daw.servlets.ej02_CRUD_categorias_teclados.SuperServlet;

/**
 * Servlet implementation class ServletPostCategorias
 */
@WebServlet("/ej02_categorias/crear")
public class ServletPostCategorias extends SuperServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPostCategorias() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strDesc = request.getParameter("descripcion");
		if (strDesc == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("El parametro 'descripcion' no puede estar vacio")));
			return;
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(ControladorCategoria.createCategoria(strDesc)));
		
	}

}
