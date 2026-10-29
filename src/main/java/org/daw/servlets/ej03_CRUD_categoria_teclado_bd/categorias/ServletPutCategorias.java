package org.daw.servlets.ej03_CRUD_categoria_teclado_bd.categorias;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.JSONUtils;
import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.SuperServlet;


/**
 * Servlet implementation class ServletPutCategorias
 */
@WebServlet("/ej03_categorias/modificar")
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
			enviarError(response,HttpServletResponse.SC_BAD_REQUEST,"Se necesita un parametro 'descripcion'");
			return;
		}
		
		String strId = request.getParameter("id");
		if (strId == null) {
			enviarError(response,HttpServletResponse.SC_BAD_REQUEST,"Se necesita un parametro 'id'");
			return;
		}
		
		Integer idCategoria = getIntParameterWithCheck("id", request, response);
		if (idCategoria == null) return;
		
		Categoria c;
		try {
			c = ControladorCategoria.getCategoria(idCategoria);
		} catch (SQLException e) {
			e.printStackTrace();
			enviarError(response,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error al buscar el valor 'id' en el servidor.");
			return;
		}
		
		if (c == null) {
			enviarError(response,HttpServletResponse.SC_NOT_FOUND,"El valor 'id' no corresponde con ninguna categoria.");
			
		} else {
			try {
				c = ControladorCategoria.modifyCategoria(idCategoria, strDes);
			} catch (SQLException e) {
				e.printStackTrace();
				enviarError(response,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error al modificar la categoria.");
				return;
			}
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(JSONUtils.toJSON(c));
		}
		
	}

}
