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
 * Servlet implementation class ServletPostCategorias
 */
@WebServlet("/ej03_categorias/crear")
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
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "El parametro 'descripcion' no puede estar vacio");
			return;
		}
		
		Categoria c = null;
		try {
			c = ControladorCategoria.createCategoria(strDesc);
		} catch (SQLException e) {
			e.printStackTrace();
			enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Ha habido un error al crear la categoria.");
			return;
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(c));
		
	}

}
