package org.daw.servlets.ej03_CRUD_categoria_teclado_bd.teclados;

public class Teclado {
	
	private int id;
	private String color;
	private String idioma;
	private int idCategoria;
	
	public Teclado() {
		super();
	}
	
	public Teclado(int id, String color, String idioma, int idCategoria) {
		super();
		this.id = id;
		this.color = color;
		this.idioma = idioma;
		this.idCategoria = idCategoria;
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

}
