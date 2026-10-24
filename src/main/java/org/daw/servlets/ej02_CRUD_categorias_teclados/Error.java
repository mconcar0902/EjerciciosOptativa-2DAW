package org.daw.servlets.ej02_CRUD_categorias_teclados;

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
