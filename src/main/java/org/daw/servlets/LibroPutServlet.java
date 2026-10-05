package org.daw.servlets;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ej01_libros/modificar")
public class LibroPutServlet extends HttpServlet{

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=UTF-8");

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            response.getWriter().println("Debe indicar el parámetro id.");
            return;
        }

        int id = Integer.parseInt(idParam);

        Libro libro = ControladorLibro.buscar(id);

        if (libro == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("Libro no encontrado.");
            return;
        }

        String nombre = request.getParameter("nombre");
        String anoEdicion = request.getParameter("anoEdicion");

        if (nombre != null) {
            libro.setNombre(nombre);
        }

        if (anoEdicion != null) {
            int ano = Integer.parseInt(anoEdicion);
            libro.setAñoEdicion(ano);
        }

        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println("Libro modificado correctamente.");

        response.getWriter().println("ID: " + libro.getId());
        response.getWriter().println("Nombre: " + libro.getNombre());
        response.getWriter().println("Año de Edicion: " + libro.getAñoEdicion());
	}

	

}
