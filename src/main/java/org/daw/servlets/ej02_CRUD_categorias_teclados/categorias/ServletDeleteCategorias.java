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
 * Servlet implementation class ServletDeleteCategorias
 */
@WebServlet("/ej02_categorias/eliminar")
public class ServletDeleteCategorias extends SuperServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteCategorias() {
        super();
    }

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strId = request.getParameter("id");
		
		if (strId == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("Es necesario introducir el parametro 'id'")));
			return;
		} else {
			
			Integer idCategoria = getIntParameterWithCheck("id", request, response);
			if (idCategoria == null) return;
			
			if (ControladorCategoria.deleteCategoria(idCategoria)) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print(JSONUtils.toJSON(new Error("La categoria con el 'id = "+ idCategoria +"' eliminado con exito")));
				return;
			}
			else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().print(JSONUtils.toJSON(new Error("Parametro 'id' no encontrado.")));
				return;
			}
		}
	}

}
