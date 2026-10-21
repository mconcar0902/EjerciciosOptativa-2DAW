package org.daw.servlets.ej02_CRUD_categorias_teclados;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ServletPutCategorias
 */
@WebServlet("/ej02_categorias/modificar")
public class ServletPutCategorias extends HttpServlet {
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
			response.getWriter().print(JSONUtils.errorToJSON("Se necesita un parametro 'descripcion'"));
			return;
		}
		
		String strId = request.getParameter("id");
		if (strId == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.errorToJSON("Se necesita un parametro 'id'"));
			return;
		}
		
		int id = -1;
		
		try {
			id = Integer.parseInt(strId);
			
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.errorToJSON("El parametro 'id' debe ser un numero entero"));
			return;
		}
		
		Categoria c = ControladorCategoria.getCategoria(id);
		if (c == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.errorToJSON("El valor 'id' no corresponde con ninguna categoria."));
			return;
		}
		
		c.setDescripcion(strDes);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.categoriaToJSON(c));
	}

}
