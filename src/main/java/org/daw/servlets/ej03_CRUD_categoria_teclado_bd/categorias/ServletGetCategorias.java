package org.daw.servlets.ej03_CRUD_categoria_teclado_bd.categorias;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.JSONUtils;
import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.SuperServlet;


/**
 * Servlet implementation class ServletGetCategorias
 */
@WebServlet("/ej03_categorias")
public class ServletGetCategorias extends SuperServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int TAMANO_PAGINA_POR_DEFECTO = 10;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetCategorias() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Si se recibe un parámetro id, se consulta una categoría.
		String strId = request.getParameter("id");

		if (strId != null) {
			obtenerCategoriaPorId(strId, request, response);
			return;
		}

		// Obtener los parámetros de búsqueda y paginación.
		String nombre = request.getParameter("nombre");

		Integer pagina = obtenerParametroEntero(request, response, "pagina", 0);

		if (pagina == null) {
			return;
		}

		Integer tamanoPagina = obtenerParametroEntero(request, response, "tamanoPagina", TAMANO_PAGINA_POR_DEFECTO);

		if (tamanoPagina == null) {
			return;
		}

		// Validar los valores de paginación.
		if (pagina < 0 || tamanoPagina <= 0) {
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST,
					"La página debe ser mayor o igual que 0 " + "y tamanoPagina debe ser mayor que 0.");
			return;
		}

		// Buscar categorías en la base de datos.
		try {
			List<Categoria> categorias = ControladorCategoria.buscarCategorias(nombre, pagina, tamanoPagina);

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(JSONUtils.toJSON(categorias));

		} catch (SQLException e) {
			getServletContext().log("Error al consultar las categorías.", e);

			enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al consultar las categorías.");
		}
	}

	/**
	 * Obtiene un parámetro entero de la petición HTTP. Devuelve el valor por
	 * defecto si el parámetro no existe. Devuelve null y responde con HTTP 400 si
	 * no es un entero válido.
	 */
	private Integer obtenerParametroEntero(HttpServletRequest request, HttpServletResponse response,
			String nombreParametro, int valorPorDefecto) throws IOException {

		String valor = request.getParameter(nombreParametro);

		if (valor == null) {
			return valorPorDefecto;
		}

		try {
			return Integer.valueOf(valor.trim());

		} catch (NumberFormatException e) {
			enviarError(response, HttpServletResponse.SC_BAD_REQUEST,
					"El parámetro '" + nombreParametro + "' debe ser un número entero.");

			return null;
		}
	}

	/**
	 * Obtiene una categoría a partir de su identificador.
	 */
	private void obtenerCategoriaPorId(String strId, HttpServletRequest request, HttpServletResponse response) throws IOException {

		final Integer idCategoria = getIntParameterWithCheck(strId, request, response);
		if (idCategoria == null) return;
		

		try {
			Categoria categoria = ControladorCategoria.getCategoria(idCategoria);

			if (categoria == null) {
				enviarError(response, HttpServletResponse.SC_NOT_FOUND,
						"Categoría con id " + idCategoria + " no encontrada.");
				return;
			}

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(JSONUtils.toJSON(categoria));

		} catch (SQLException e) {
			getServletContext().log("Error al consultar la categoría " + idCategoria, e);

			enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al consultar la categoría.");
		}
	}


	
	
}
