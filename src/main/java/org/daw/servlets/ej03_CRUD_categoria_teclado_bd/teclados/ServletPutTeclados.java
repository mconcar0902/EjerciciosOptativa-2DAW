package org.daw.servlets.ej03_CRUD_categoria_teclado_bd.teclados;

import java.io.IOException;
import java.sql.SQLException;

import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.JSONUtils;
import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.SuperServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ServletPutTeclados
 */
@WebServlet("/ej03_teclados/modificar")
public class ServletPutTeclados extends SuperServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletPutTeclados() {
        super();
    }

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParam = request.getParameter("id");		// Compruebas si tiene contenido el parametro 'id'
		if (idParam == null) {
			enviarError(response,HttpServletResponse.SC_BAD_REQUEST,"El parametro 'id' no puede estar vacio");
			return;
		}
		Integer id = getIntParameterWithCheck("id", request, response);		// Compruebas si es un int
		if (id == null) return;
		
		Teclado t = null;
		try {
			t = ControladorTeclado.getTeclado(id);
		} catch (SQLException e) {
			e.printStackTrace();
			enviarError(response,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error al buscar el teclado");
		}
		
		if (t == null) {
			enviarError(response,HttpServletResponse.SC_NOT_FOUND,"El teclado 'id'='"+ id+"' no se ha encontrado");
			return;
		}
		
		String colorParam = request.getParameter("color");
		String idiomaParam = request.getParameter("idioma");
		String idCatParam = request.getParameter("idCategoria");
		
		
		if (colorParam == null && idiomaParam == null && idCatParam == null) {	// Compruebas si tiene contenido algun parametro 
			enviarError(response,HttpServletResponse.SC_BAD_REQUEST,"Debes introducir algun parametro con valor para modificar");
		
		} else {
			if (idCatParam != null) { // Compruebas si el parametro tiene valor
				
				Integer idCat = getIntParameterWithCheck("idCategoria", request, response);	
				if (idCat != null) {	// Compruebas si es un int y sino manda un mensaje de error
					
					if (ControladorTeclado.checkIdCategoria(idCat)) t.setIdCategoria(idCat);	// Compruebas si esta la categoria y modifica
					else {
						enviarError(response,HttpServletResponse.SC_NOT_FOUND,"La 'idCategoria'='"+idCat+"' no se ha encontrado");
						return;
					}
				} else return;
			}
			
			if (colorParam != null) t.setColor(colorParam);		// Compruebas si no es null y modificas el 'color'
			if (idiomaParam != null) t.setIdioma(idiomaParam);	// Compruebas si no es null y modificas el 'idioma'
			
			try {
				ControladorTeclado.modifyTeclado(t.getId(), t.getColor(), t.getIdioma(), t.getIdCategoria());
			} catch (SQLException e) {
				e.printStackTrace();
				enviarError(response,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error al modificar el teclado");
			}
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(JSONUtils.toJSON(t));
		}
		
	}

}
