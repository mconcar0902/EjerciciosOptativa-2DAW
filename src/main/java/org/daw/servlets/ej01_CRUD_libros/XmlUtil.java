package org.daw.servlets.ej01_CRUD_libros;

public class XmlUtil {

	private XmlUtil() {
	}

	/**
	 * 
	 * @param texto
	 * @return
	 */
	public static String escapar(String texto) {
		
		if (texto == null) {
			return "";
		}
		
		return texto
				.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
	}
	
	/**
	 * 
	 * @param libro
	 * @return
	 */
	public static String libro(Libro libro) {

		return """
				<libro>
					<id>%d</id>
					<nombre>%s</nombre>
					<anoEdicion>%d</anoEdicion>
				</libro>
				""".formatted(libro.getId(), escapar(libro.getNombre()), libro.getAnoEdicion());
	}
	
	/**
	 * 
	 * @param mensaje
	 * @return
	 */
	public static String error(String mensaje) {
		
		return """
				<?xml version="1.0" encoding="UTF-8"?>
				<error>
					<mensaje>%s</mensaje>
				</error>
				""".formatted(escapar(mensaje));
	}
	
}
