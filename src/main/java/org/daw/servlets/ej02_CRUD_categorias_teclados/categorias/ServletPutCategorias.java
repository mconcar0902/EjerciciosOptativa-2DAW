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
 * Servlet implementation class ServletPutCategorias
 */
@WebServlet("/ej02_categorias/modificar")
public class ServletPutCategorias extends SuperServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPutCategorias() {
        super();
    }

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strDes = request.getParameter("descripcion");
		if (strDes == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("Se necesita un parametro 'descripcion'")));
			return;
		}
		
		String strId = request.getParameter("id");
		if (strId == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("Se necesita un parametro 'id'")));
			return;
		}
		
		Integer idCategoria = getIntParameterWithCheck("id", request, response);
		if (idCategoria == null) return;
		
		Categoria c = ControladorCategoria.getCategoria(idCategoria);
		
		if (c == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El valor 'id' no corresponde con ninguna categoria.")));
			
		} else {
			c.setDescripcion(strDes);
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(JSONUtils.toJSON(c));
		}
		
	}

}
