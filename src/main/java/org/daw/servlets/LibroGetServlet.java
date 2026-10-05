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

        response.setContentType("text/html;charset=UTF-8");

        response.setStatus(HttpServletResponse.SC_OK);

        String idParam = request.getParameter("id");

        response.getWriter().println("""
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <title>Libros</title>
            </head>
            <body>
                <h1>Libros</h1>
            """);
        
        if (idParam != null) {
			int id = Integer.parseInt(idParam);
			
			Libro libro = ControladorLibro.buscar(id);
			
			if (libro == null) { // Si no lo encuentra
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // Mensaje 404
				response.getWriter().println("<p>Libro no encontrado.</p>");
				
			} else {
				response.getWriter().println("<h2>"+ libro.getNombre() +"</h2>");
				response.getWriter().println("<p>Id: "+ libro.getId() +"</p>");
				response.getWriter().println("<p>Año Edicion: "+ libro.getAñoEdicion() +"</p>");
			}
		} else {
			List<Libro> libros =  ControladorLibro.listar();
			
			response.getWriter().println("<ul>");

            for (Libro libro : libros) {

                response.getWriter().println(
                    "<li>" +
                    libro.getId() +
                    " - " +
                    libro.getNombre() +
                    " año edicion " +
                    libro.getAñoEdicion() +
                    "</li>"
                );
            }
            response.getWriter().println("</ul>");
        }

        response.getWriter().println("""
            </body>
            </html>
            """);
	}

	
	
	

}
