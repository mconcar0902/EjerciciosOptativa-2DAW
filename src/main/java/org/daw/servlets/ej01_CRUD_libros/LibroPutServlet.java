package org.daw.servlets.ej01_CRUD_libros;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ej01_libros/modificar")
public class LibroPutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/xml;charset=UTF-8");

		String idParam = request.getParameter("id");

		if (idParam == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(XmlUtil.error("Debe indicar el parámetro id."));
			return;
		}

		try {
			int id = Integer.parseInt(idParam);

			String nombre = request.getParameter("nombre");
			String anoEdicion = request.getParameter("anoEdicion");

			if (nombre != null) {
				nombre = nombre.trim();
				if (nombre.isEmpty()) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getWriter().println(XmlUtil.error("El nombre no puede estar vacío."));
					return;
				}
			}

			Integer ano = null;
			if (anoEdicion != null) {
				ano = Integer.valueOf(anoEdicion);
				if (ano < 0) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getWriter().println(XmlUtil.error("El año de edición no puede ser negativo."));
					return;
				}
			}

			Libro libro = ControladorLibro.modificar(id, nombre, ano);

			if (libro == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println(XmlUtil.error("Libro no encontrado."));
				return;
			}

			String xml = """
					<?xml version="1.0" encoding="UTF-8"?>
					<respuesta>
						<operacion>modificar</operacion>
						<mensaje>Libro modificado correctamente.</mensaje>
						%s
					</respuesta>
					""".formatted(XmlUtil.libro(libro)).strip();

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(xml);

		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(XmlUtil.error("El id y el año de edición tienen que ser números enteros."));
		}
	}
}