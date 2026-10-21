package org.daw.servlets.ej02_CRUD_categorias_teclados;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ServletPostCategorias
 */
@WebServlet("/ej02_categorias/crear")
public class ServletPostCategorias extends HttpServlet {
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
			response.getWriter().print(JSONUtils.errorToJSON("El parametro 'descripcion' no es valido"));
			return;
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.categoriaToJSON(ControladorCategoria.createCategoria(strDesc)));
		
	}

}
