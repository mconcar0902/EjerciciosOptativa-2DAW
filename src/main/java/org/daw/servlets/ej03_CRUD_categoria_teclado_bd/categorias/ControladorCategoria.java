package org.daw.servlets.ej03_CRUD_categoria_teclado_bd.categorias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class ControladorCategoria {
	
	private static final String URL = "jdbc:mysql://localhost:3311/categorias_teclados";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "1234";
	
	/**
	 * 
	 * @param nombre
	 * @param pagina
	 * @param tamanoPagina
	 * @return
	 * @throws SQLException
	 */
	public static List<Categoria> buscarCategorias(String nombre, int pagina, int tamanoPagina) throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}
		
		List<Categoria> categorias = new ArrayList<>();

		String sql = """
				SELECT id, descripcion
				FROM categoria
				WHERE LOWER(descripcion) LIKE LOWER(?)
				ORDER BY id
				LIMIT ? OFFSET ?
				""";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			// Filtro contains ignorecase.
			ps.setString(1, "%" + (nombre == null ? "" : nombre.trim()) + "%");

			// Paginación.
			ps.setInt(2, tamanoPagina);
			ps.setInt(3, pagina * tamanoPagina);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Categoria categoria = new Categoria();

					categoria.setId(rs.getInt("id"));
					categoria.setDescripcion(rs.getString("descripcion"));

					categorias.add(categoria);
				}
			}
		}

		return categorias;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static int getMaxId() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}
		
		String sql = """
				SELECT MAX(id) FROM categoria
				""";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {
			    if (rs.next()) {
			        return rs.getInt(1);
			    }
			    return 0;
			}
		}
	}
	
    
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static Categoria getCategoria(int id) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}

		String sql = """
				SELECT id, descripcion
				FROM categoria
				WHERE id = ?
				""";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Categoria categoria = new Categoria();

					categoria.setId(rs.getInt("id"));
					categoria.setDescripcion(rs.getString("descripcion"));

					return categoria;
				}
			}
		}

		return null;
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 * @throws SQLException
	 */
	public static Categoria createCategoria(String descripcion) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}

		String sql = """
				INSERT INTO categoria(descripcion) values(?)
				""";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, descripcion);
	        ps.executeUpdate();

	        try (ResultSet rs = ps.getGeneratedKeys()) {
	            if (rs.next()) {
	                Categoria categoria = new Categoria();
	                categoria.setId(rs.getInt(1));
	                categoria.setDescripcion(descripcion);
	                return categoria;
	            }
	        }
	    }
	    return null;
	}

	/**
	 * 
	 * @param id
	 * @param descripcion
	 * @return
	 * @throws SQLException
	 */
	public static Categoria modifyCategoria(int id, String descripcion) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}

		String sql = "UPDATE categoria SET descripcion=? WHERE id=?;";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setString(1, descripcion);
			ps.setInt(2, id);
	        ps.executeUpdate();

	        return getCategoria(id);
	    }
	}

	/**
	 * 
	 * @param idCategoria
	 * @throws SQLException
	 */
	public static void deleteCategoria(Integer idCategoria) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}
		
		String sql = "DELETE FROM categoria WHERE id=?;";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, idCategoria);
	        ps.executeUpdate();
	        
	    }
	}
}
