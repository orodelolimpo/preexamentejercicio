/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Libro;

/**
 * @author David
 * Clase que implementa un CRUD de la base batos
 * (Create, Read, update y delete)
 */
public class LibroDAO {

	private ConexionBD conexion;
	
    public LibroDAO() {
        this.conexion = new ConexionBD();
    }
    //metodo de la clase DAO que recupera todas las editoriales de la base de datos y las añade a un arralist de editoriales.

    public ArrayList<Libro> obtenerLibros() { //recojo todas las editoriales, impor arrayList y editorial
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();//defino objeto Connection llamad con. obtenemos objeto connection para conectar a la base de datos.
		Statement consulta = null;
		ResultSet resultado = null;//recoge los datos
		ArrayList<Libro> lista = new ArrayList<Libro>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from libros");
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			while(resultado.next()) {
				String isbn=resultado.getString("isbn");
				String titulo = resultado.getString("titulo");
				int codEditorial = resultado.getInt("codeditorial");
				int anio = resultado.getInt("anio");
				int num_pags=resultado.getInt("num_pags");
				float precio=resultado.getFloat("precio");
				int cantidad=resultado.getInt("cantidad");
				float precioCD=resultado.getFloat("precioCD");
				
				Libro lib = new Libro(isbn,titulo,codEditorial,anio,num_pags,precio,cantidad,precioCD);//instancio el objeto constructor
				lista.add(lib);//lo añado en la lista ed
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return lista;
    }


    public Libro obtenerLibro(String isbn) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Libro lib=null; //aqui no necesito una lista, sólo una editorial ed
		
		try {
			consulta = con.prepareStatement("select * from libros "
					+ "where isbn = '?'");//es diferene a la de arriba, y no paso valor sino ?
			consulta.setString(1, isbn); //setInt la primera? es 1, las 2 ? es codEditorial
			resultado = consulta.executeQuery();
			
	
			
			//hago if porque con solo necestio un dato, el while es un bucle  codEditorial es clave primaria no va a dar problema
			if (resultado.next()) {//el next salta a la siguiente fila para seguir buscando
			
				String titulo = resultado.getString("titulo");
				int codEditorial = resultado.getInt("codeditorial");
				int anio = resultado.getInt("anio");
				int num_pags=resultado.getInt("num_pags");
				float precio=resultado.getFloat("precio");
				int cantidad=resultado.getInt("cantidad");
				float precioCD=resultado.getFloat("precioCD");
				
				lib = new Libro(isbn,titulo,codEditorial,anio,num_pags,precio,cantidad,precioCD);// no devuelve un array pero si un ed
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return lib;
    }

//insertamos una nueva
    public int insertarLibro(Libro libro) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;//coge este tipo
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("INSERT INTO libros (isbn,titulo,codEditorial,anio,num_pags,precio,cantidad,precioCD)"
					+ " VALUES (?,?,?,?,?,?,?,?) ");
			
			consulta.setString(1, libro.getIsbn());
			consulta.setString(2, libro.getTitulo());
			consulta.setInt(3, libro.getCodEditorial());
			consulta.setInt(4,libro.getAnio());
			consulta.setInt(5,libro.getNum_pags());
			consulta.setFloat(6,libro.getPrecio());
			consulta.setInt(7, libro.getCantidad());
			consulta.setFloat(8, libro.getPrecioCD());
			resultado=consulta.executeUpdate(); // llamo a execute UPdate porque voy a insertar

		} catch (SQLException e) {
			System.out.println("Error al realizar la operación: "+e.getMessage());
		} finally {
			try {
				consulta.close();//este no tiene un resultado.close sino consulta
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }

    public int actualizarLibro(Libro libro) { //modifica ed cambiando nombre y año
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("UPDATE biblioteca.libros"
					+ "SET titulo = ?,CodEditorial=?, anio = ?,Num_pags=?,precio =?,cantidad=?, precioCD=?"
					+ "WHERE isbn = ?"
					);
			
			consulta.setString(1, libro.getTitulo());
			consulta.setInt(2, libro.getCodEditorial());
			consulta.setInt(3,libro.getAnio() );
			consulta.setInt(4,libro.getNum_pags());
			consulta.setFloat(5,libro.getPrecio());
			consulta.setInt(6, libro.getCantidad());
			consulta.setFloat(7,libro.getPrecioCD());
			consulta.setString(8, libro.getIsbn());
			resultado=consulta.executeUpdate();
			
			//System.out.println(consulta); lo hace para controlar  por esto se genera el toString

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualizacion: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }
//método que  borra editorial basada en parámetros

    public int eliminarLibro(String isbn) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("DELETE FROM `biblioteca`.libros"
					+ "WHERE isbn = ?");
			
			consulta.setString(1, isbn);//porque solo depende de codEditorial
			resultado=consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualizacion: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }
//////////ejemplo Barbara
//    Public ArrayList<Editorial> obtenerEditorialesAPartir(int anioDesde) { //recojo todas las editoriales, impor arrayList y editorial
//    	// Obtenemos una conexion a la base de datos.
//		Connection con = conexion.getConexion();//defino objeto Connection llamad con. obtenemos objeto connection para conectar a la base de datos.
//		Statement consulta = null;
//		ResultSet resultado = null;//recoge los datos
//		ArrayList<Editorial> lista = new ArrayList<Editorial>();
//		
//		try {
//			consulta = con.prepareStatement();
//			resultado = consulta.executeQuery("select * from editoriales");
//			consulta.SetINt()
//			// Bucle para recorrer todas las filas que devuelve la consulta
//			while(resultado.next()) {
//				int codEditorial = resultado.getInt("codeditorial");
//				String nombre = resultado.getString("nombre");
//				int anio = resultado.getInt("anio");
//				
//				Editorial ed = new Editorial(codEditorial, nombre,anio);//instancio elobjeto
//				lista.add(ed);//lo añado en la lista ed
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("Error al realizar la consulta: "+e.getMessage());
//		} finally {
//			try {
//				resultado.close();
//				consulta.close();
//				conexion.desconectar();
//			} catch (SQLException e) {
//				System.out.println("Error al liberar recursos: "+e.getMessage());
//			} catch (Exception e) {
//				
//			}
////		}
//		return lista;
//    }
}
