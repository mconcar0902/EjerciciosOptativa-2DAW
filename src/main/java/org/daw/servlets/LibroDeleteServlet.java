package org.daw.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ej01_libros/eliminar")
public class LibroDeleteServlet  extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        response.setContentType("text/plain;charset=UTF-8");

        String idParam = request.getParameter("id");
        
        if (idParam == null) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            response.getWriter().println("Debe indicar el parámetro id.");

            return;
        }

        int id = Integer.parseInt(idParam);
        boolean eliminado =
                ControladorLibro.eliminar(id);

        if (!eliminado) {

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            response.getWriter().println("Libro no encontrado.");

            return;
        }

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

	
}
