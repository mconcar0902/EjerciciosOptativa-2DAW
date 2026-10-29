package org.daw.servlets.ej03_CRUD_categoria_teclado_bd;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

public class Error {

	private String mensaje;
	
	public Error() {
		super();
	}

	public Error(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////////

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
