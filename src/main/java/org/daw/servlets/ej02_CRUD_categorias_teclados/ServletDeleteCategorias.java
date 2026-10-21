package org.daw.servlets.ej02_CRUD_categorias_teclados;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ServletDeleteCategorias
 */
@WebServlet("/ej02_categorias/eliminar")
public class ServletDeleteCategorias extends HttpServlet {
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
			response.getWriter().print(JSONUtils.errorToJSON("Es necesario introducir el parametro 'id'"));
			return;
		} else {
			int id = -1;
			try {
				id = Integer.parseInt(strId);
				
			} catch (NumberFormatException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().print(JSONUtils.errorToJSON("El parametro 'id' debe ser un numero entero"));
				return;
			}
			
			if (ControladorCategoria.deleteCategoria(id)) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print("{\"mensaje\":\"La categoria con el 'id = "+ id +"' eliminado con exito\"}");
				return;
			}
			else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().print(JSONUtils.errorToJSON("Parametro 'id' no encontrado."));
				return;
			}
		}
	}

}
