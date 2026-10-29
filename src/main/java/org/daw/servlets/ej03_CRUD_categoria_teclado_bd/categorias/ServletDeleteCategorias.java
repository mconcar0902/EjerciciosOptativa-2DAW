package org.daw.servlets.ej03_CRUD_categoria_teclado_bd.categorias;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.Error;
import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.JSONUtils;
import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.SuperServlet;


/**
 * Servlet implementation class ServletDeleteCategorias
 */
@WebServlet("/ej03_categorias/eliminar")
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
			enviarError(response,HttpServletResponse.SC_BAD_REQUEST,"Es necesario introducir el parametro 'id'");
			return;
		}
			
		Integer idCategoria = getIntParameterWithCheck("id", request, response);
		if (idCategoria == null) return;
		
		Categoria c;
		try {
			c = ControladorCategoria.getCategoria(idCategoria);
		} catch (SQLException e) {
			enviarError(response,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error al buscar el valor 'id' en el servidor.");
			return;
		}
		
		if (c == null) {
			enviarError(response,HttpServletResponse.SC_NOT_FOUND,"El valor 'id' no corresponde con ninguna categoria.");
			return;
		} 
		
		
		try {
			ControladorCategoria.deleteCategoria(idCategoria);
		} catch (SQLException e) {
			enviarError(response,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error al eliminar la categoria.");
			return;
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(new Error("La categoria con el 'id = "+ idCategoria +"' eliminado con exito")));
		
	}

}
