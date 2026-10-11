package org.daw.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ej01_libros/crear")
public class LibroPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/xml;charset=UTF-8");

		String nombre = request.getParameter("nombre");
		String anoEdicion = request.getParameter("anoEdicion");

		if (nombre == null || anoEdicion == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(XmlUtil.error("Faltan los parámetros nombre y anoEdicion."));
			return;
		}

		nombre = nombre.trim();
		if (nombre.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(XmlUtil.error("El nombre no puede estar vacío."));
			return;
		}

		try {
			int ano = Integer.parseInt(anoEdicion);

			if (ano < 0) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().println(XmlUtil.error("El año de edición no puede ser negativo."));
				return;
			}

			Libro libro = ControladorLibro.crear(nombre, ano);

			String xml = """
					<?xml version="1.0" encoding="UTF-8"?>
					<respuesta>
						<operacion>crear</operacion>
						<mensaje>Libro creado correctamente.</mensaje>
						%s
					</respuesta>
					""".formatted(XmlUtil.libro(libro)).strip();

			response.setStatus(HttpServletResponse.SC_CREATED);
			response.getWriter().println(xml);

		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(XmlUtil.error("El año de edición debe ser un número entero."));
		}
	}
}
