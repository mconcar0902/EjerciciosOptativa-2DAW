package org.daw.servlets.ej03_CRUD_categoria_teclado_bd.teclados;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.categorias.Categoria;
import org.daw.servlets.ej03_CRUD_categoria_teclado_bd.categorias.ControladorCategoria;


public class ControladorTeclado {

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
	public static List<Teclado> buscarTeclados(String nombre, int pagina, int tamanoPagina) throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}
		
		List<Teclado> teclados = new ArrayList<>();

		String sql = """
				SELECT id, LOWER(color) AS color, LOWER(idioma) AS idioma, idCategoria
				FROM teclado
				ORDER BY id
				LIMIT ? OFFSET ?
				""";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			// Paginación.
			ps.setInt(1, tamanoPagina);
			ps.setInt(2, pagina * tamanoPagina);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Teclado teclado = new Teclado();

					teclado.setId(rs.getInt("id"));
					teclado.setColor(rs.getString("color"));
					teclado.setIdioma(rs.getString("idioma"));
					teclado.setIdCategoria(rs.getInt("idCategoria"));

					teclados.add(teclado);
				}
			}
		}

		return teclados;
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
				SELECT MAX(id) FROM teclado
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
	public static Teclado getTeclado(int id) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}

		String sql = """
				SELECT id, color, idioma, idCategoria
				FROM teclado
				WHERE id = ?
				""";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Teclado teclado = new Teclado();

					teclado.setId(rs.getInt("id"));
					teclado.setColor(rs.getString("color"));
					teclado.setIdioma(rs.getString("idioma"));
					teclado.setIdCategoria(rs.getInt("idCategoria"));

					return teclado;
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
	public static Teclado createTeclado(String color, String idioma, int idCategoria) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}

		String sql = """
				INSERT INTO teclado(color,idioma,idCategoria) values(?,?,?)
				""";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, color);
			ps.setString(2, idioma);
			ps.setInt(3, idCategoria);
	        ps.executeUpdate();

        	Teclado teclado = getTeclado(getMaxId());
			return teclado;
        }
	}

	/**
	 * 
	 * @param id
	 * @param descripcion
	 * @return
	 * @throws SQLException
	 */
	public static Teclado modifyTeclado(int id, String color, String idioma, int idCategoria) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}

		String sql = "UPDATE teclado SET color=?, idioma=?, idCategoria=? WHERE id=?;";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setString(1, color);
			ps.setString(2, idioma);
			ps.setInt(3, idCategoria);
			ps.setInt(4, id);
	        ps.executeUpdate();

	        return getTeclado(id);
	    }
	}

	/**
	 * 
	 * @param idCategoria
	 * @throws SQLException
	 */
	public static void deleteTeclado(Integer id) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
			e.printStackTrace();
		}
		
		String sql = "DELETE FROM teclado WHERE id=?;";

		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
				PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);
	        ps.executeUpdate();
	        
	    }
	}

	/**
	 * 
	 * @param idCat
	 * @return
	 */
	public static boolean checkIdCategoria(Integer idCat) {
		Categoria c = null;
		try {
			c = ControladorCategoria.getCategoria(idCat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (c != null) return true;
		
		return false;
	}
}
