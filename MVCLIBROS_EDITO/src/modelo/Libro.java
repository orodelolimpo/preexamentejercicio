/**
 * 
 */
package modelo;

import java.util.Objects;

/**
 * @author David
 *
 */
public class Libro {
	//variables que he obtenido de la tabla de MySQL
	private String isbn;
	private String titulo;
	private int codEditorial;
	private int anio;
	private int num_pags;
	private float precio;
	private int cantidad;
	private float precioCD;
	
	
	//constructor sin parámetros
	public Libro() {
		this.isbn="";
		this.titulo="";
	}
	//constructor con parámetros
	
	public Libro(String isbn, String titulo, int codEditorial, int anio, int num_pags, float precio,
			int cantidad, float precioCD) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.codEditorial = codEditorial;
		this.anio = anio;
		this.num_pags = num_pags;
		this.precio = precio;
		this.cantidad = cantidad;
		this.precioCD = precioCD;


	}
	//getter y setter todos.
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getCodEditorial() {
		return codEditorial;
	}

	public void setCodEditorial(int codEditorial) {
		this.codEditorial = codEditorial;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getNum_pags() {
		return num_pags;
	}

	public void setNum_pags(int num_pags) {
		this.num_pags = num_pags;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioCD() {
		return precioCD;
	}

	public void setPrecioCD(float precioCD) {
		this.precioCD = precioCD;
	}

	// estos dos métodos los usamos para comparar todos los datos generados con el codEditorial.
		
		
	

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(isbn, other.isbn);
	}
	 //to String

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", codEditorial=" + codEditorial + ", anio=" + anio
				+ ", num_pags=" + num_pags + ", precio=" + precio + ", cantidad=" + cantidad + ", precioCD=" + precioCD
				+ "]";
	}
	
	
}
