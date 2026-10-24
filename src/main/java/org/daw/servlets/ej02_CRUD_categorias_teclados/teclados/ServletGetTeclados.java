package org.daw.servlets.ej02_CRUD_categorias_teclados.teclados;

import java.io.IOException;

import org.daw.servlets.ej02_CRUD_categorias_teclados.Error;
import org.daw.servlets.ej02_CRUD_categorias_teclados.JSONUtils;
import org.daw.servlets.ej02_CRUD_categorias_teclados.SuperServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletGetTeclados
 */
@WebServlet("/ej02_teclados")
public class ServletGetTeclados extends SuperServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletGetTeclados() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strid = request.getParameter("id");
		
		if (strid == null) {	// Comprueba si hay parametro id si no hay muestra todos los teclados
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(JSONUtils.toJSON(ControladorTeclado.getLista()));
			
		} else {
			Integer idTeclado = getIntParameterWithCheck("id", request, response);	// Comprueba si es un int sino salta un error
			if (idTeclado == null) return;
			
			Teclado t = ControladorTeclado.getTeclado(idTeclado);
			
			if (t != null) {	// Comprueba si hay teclado con ese id
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print(JSONUtils.toJSON(t));
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().print(JSONUtils.toJSON(new Error("Teclado con 'id'='"+idTeclado+"' no encontrado")));
			}
		}
	}

}
