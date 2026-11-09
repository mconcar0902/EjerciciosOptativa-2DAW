package org.daw.servlets.ej03_CRUD_categoria_teclado_bd.teclados;

import java.io.IOException;
import java.sql.SQLException;

import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.Error;
import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.JSONUtils;
import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.SuperServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ServletDeleteTeclados
 */
@WebServlet("/ej03_teclados/eliminar")
public class ServletDeleteTeclados extends SuperServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletDeleteTeclados() {
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
			
		Integer idTeclado = getIntParameterWithCheck("id", request, response);
		if (idTeclado == null) return;
		
		Teclado t;
		try {
			t = ControladorTeclado.getTeclado(idTeclado);
		} catch (SQLException e) {
			enviarError(response,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error al buscar el valor 'id' en el servidor.");
			return;
		}
		
		if (t == null) {
			enviarError(response,HttpServletResponse.SC_NOT_FOUND,"El valor 'id' no corresponde con ninguna teclado.");
			return;
		} 
		
		
		try {
			ControladorTeclado.deleteTeclado(idTeclado);
		} catch (SQLException e) {
			enviarError(response,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error al eliminar el teclado.");
			return;
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtils.toJSON(new Error("El teclado con el 'id = "+ idTeclado +"' eliminado con exito")));
		
	}

}
