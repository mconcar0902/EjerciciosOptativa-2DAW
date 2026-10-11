package org.daw.servlets;

import java.util.List;


import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 */
@WebServlet("/ej01_libros")
public class LibroGetServlet  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/xml;charset=UTF-8");

        String idParam = request.getParameter("id");
        
        if (idParam == null) {
			
        	List<Libro> libros = ControladorLibro.listar();
        	
        	StringBuilder xml = new StringBuilder();
        	
        	xml.append("""
        			<?xml version="1.0" encoding="UTF-8"?>
        			<libros>
        			""");
        	
        	for (Libro libro : libros) {
				xml.append(XmlUtil.libro(libro));
			}
        	
        	xml.append("""
					</libros>
					""");
        	
        	response.setStatus(HttpServletResponse.SC_OK);
        	response.getWriter().print(xml);
        	return;
		}
        
        try {
			int id = Integer.parseInt(idParam);
			
			Libro libro = ControladorLibro.buscar(id);
			
			if (libro == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);

				response.getWriter().print(XmlUtil.error("Libro no encontrado."));

				return;
			}
			
			String xml = """
					<?xml version="1.0" encoding="UTF-8"?>
        			%s
					""".formatted(XmlUtil.libro(libro));
			
			response.setStatus(HttpServletResponse.SC_OK);
        	response.getWriter().print(xml);
        	
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(XmlUtil.error("El parámetro id debe ser un número entero."));
		}
        
	}
}
