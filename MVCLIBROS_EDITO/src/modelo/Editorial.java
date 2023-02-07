/**
 * 
 */
package modelo;

import java.util.Objects;

/**
 * @author David
 *
 */
public class Editorial {
	//variables que he obtenido de la tabla de MySQL
	private int codEditorial;
	private String nombre;
	private int anio;
	
	
	//constructor sin parámetros
	public Editorial() {
		this.nombre="";
		this.anio=2023;
	}
	//constructor con parámetros
	public Editorial(int codEditorial, String nombre, int anio) {
		super();
		this.codEditorial = codEditorial;
		this.nombre = nombre;
		this.anio = anio;
	}
	//getter y setter todos.
	public int getCodEditorial() {
		return codEditorial;
	}
	public void setCodEditorial(int codEditorial) {
		this.codEditorial = codEditorial;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAño() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	// estos dos métodos los usamos para comparar todos los datos generados con el codEditorial.
	@Override
	public int hashCode() {
		return Objects.hash(codEditorial);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editorial other = (Editorial) obj;
		return codEditorial == other.codEditorial;
		
	}
	@Override
	public String toString() {
		return this.codEditorial+" - "+this.nombre;
	}
	
	
}
