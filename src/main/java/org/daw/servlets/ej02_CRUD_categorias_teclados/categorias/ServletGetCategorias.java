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
 * Servlet implementation class ServletGetCategorias
 */
@WebServlet("/ej02_categorias")
public class ServletGetCategorias extends SuperServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetCategorias() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strId = request.getParameter("id");
		
		if (strId == null) {	
			response.setStatus(HttpServletResponse.SC_OK);
        	response.getWriter().print(JSONUtils.toJSON(ControladorCategoria.getLista()));
        	
		} else {
			Integer idCategoria = getIntParameterWithCheck("id", request, response);
			if (idCategoria == null) return;
			
			Categoria cat = ControladorCategoria.getCategoria(idCategoria);
			
			if (cat != null) {
	        	response.setStatus(HttpServletResponse.SC_OK);
	        	response.getWriter().print(JSONUtils.toJSON(cat));
			}
			else {
				response.getWriter().print(JSONUtils.toJSON(new Error("Categoria con 'id'='"+idCategoria+"' no encontrado.")));
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
			
		}
	}

	
}
