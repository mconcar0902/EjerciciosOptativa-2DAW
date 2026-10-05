package org.daw.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ej01_libros/crear")
public class LibroPostServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=UTF-8");
		 
        String nombre = request.getParameter("nombre");
        String anoEdicion = request.getParameter("anoEdicion");

        if (nombre == null || anoEdicion == null) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            response.getWriter().println("Faltan los parámetros nombre y año de edicion.");
            return;
        }

        int ano = Integer.parseInt(anoEdicion);

        Libro libro = ControladorLibro.crear(nombre, ano);

        response.setStatus(HttpServletResponse.SC_CREATED);

        response.getWriter().println("Libro creado correctamente.");

        response.getWriter().println("ID asignado: " + libro.getId());
	}
	
	

}
