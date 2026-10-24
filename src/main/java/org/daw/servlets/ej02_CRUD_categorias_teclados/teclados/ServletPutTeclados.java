package org.daw.servlets.ej02_CRUD_categorias_teclados.teclados;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.daw.servlets.ej02_CRUD_categorias_teclados.Error;
import org.daw.servlets.ej02_CRUD_categorias_teclados.JSONUtils;
import org.daw.servlets.ej02_CRUD_categorias_teclados.SuperServlet;


/**
 * Servlet implementation class ServletPutTeclados
 */
@WebServlet("/ej02_teclados/modificar")
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
		
		String idParam = request.getParameter("id");		// Compruebas si tiene contenido el parametro 'Color'
		if (idParam == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("El parametro 'id' no puede estar vacio")));
			return;
		}
		Integer id = getIntParameterWithCheck("id", request, response);		// Compruebas si es un int
		if (id == null) return;
		
		Teclado t = ControladorTeclado.getTeclado(id);		// Compruebas si se encuentra el teclado
		if (t == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print(JSONUtils.toJSON(new Error("El parametro 'id'='"+ id+"' no se ha encontrado")));
			return;
		}
		
		String colorParam = request.getParameter("color");
		String idiomaParam = request.getParameter("idioma");
		String idCatParam = request.getParameter("idCategoria");
		
		
		if (colorParam == null && idiomaParam == null && idCatParam == null) {	// Compruebas si tiene contenido algun parametro 
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error("Debes introducir algun parametro con valor para modificar")));
			
		} else {
			if (idCatParam != null) { // Compruebas si el parametro tiene valor
				
				Integer idCat = getIntParameterWithCheck("idCategoria", request, response);	
				if (idCat != null) {	// Compruebas si es un int y sino manda un mensaje de error
					
					if (ControladorTeclado.checkIdCategoria(idCat)) t.setIdCategoria(idCat);	// Compruebas si esta la categoria y modifica
					else {
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
						response.getWriter().print(JSONUtils.toJSON(new Error("La 'idCategoria'='"+idCat+"' no se ha encontrado")));
						return;
					}
				} else return;
			}
			
			if (colorParam != null) t.setColor(colorParam);		// Compruebas si no es null y modificas el 'color'
			if (idiomaParam != null) t.setIdioma(idiomaParam);	// Compruebas si no es null y modificas el 'idioma'
			
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(JSONUtils.toJSON(t));
		}
		
		
	}

}
