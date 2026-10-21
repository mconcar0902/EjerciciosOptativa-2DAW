package org.daw.servlets.ej02_CRUD_categorias_teclados;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ServletGetCategorias
 */
@WebServlet("/ej02_categorias")
public class ServletGetCategorias extends HttpServlet {
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
		StringBuffer sb = new StringBuffer();
		
		String strId = request.getParameter("id");
		
		if (strId == null) {	
			response.setStatus(HttpServletResponse.SC_OK);
        	response.getWriter().print(JSONUtils.categoriaToJSONArray(ControladorCategoria.getLista()));
        	return;
        	
		} else {
			int id = 0;
			try {
				id = Integer.parseInt(strId);
				
			} catch (NumberFormatException e) {
				sb.append(JSONUtils.errorToJSON("Se ha introducido un id no valido."));
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        	response.getWriter().print(sb);
	        	return;
			}

			Categoria cat = ControladorCategoria.getCategoria(id);
			
			if (cat == null) {
				sb.append(JSONUtils.errorToJSON("Parametro 'id' no encontrado."));
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        	response.getWriter().print(sb);
	        	return;
			
			}
			
			sb.append(JSONUtils.categoriaToJSON(cat)); 
        	response.setStatus(HttpServletResponse.SC_OK);
        	response.getWriter().print(sb);
        	return;
		}
	}

	
}
