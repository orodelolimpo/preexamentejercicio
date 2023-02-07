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
import modelo.Editorial;

/**
 * @author David
 * Clase que implementa un CRUD de la base batos
 * (Create, Read, update y delete)
 */
public class EditorialDAO {

	private ConexionBD conexion;
	
    public EditorialDAO() {
        this.conexion = new ConexionBD();
    }
    //metodo de la clase DAO que recupera todas las editoriales de la base de datos y las añade a un arralist de editoriales.

    public ArrayList<Editorial> obtenerEditoriales() { //recojo todas las editoriales, impor arrayList y editorial
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();//defino objeto Connection llamad con. obtenemos objeto connection para conectar a la base de datos.
		Statement consulta = null;
		ResultSet resultado = null;//recoge los datos
		ArrayList<Editorial> lista = new ArrayList<Editorial>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from editoriales");
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			while(resultado.next()) {
				int codEditorial = resultado.getInt("codeditorial");
				String nombre = resultado.getString("nombre");
				int anio = resultado.getInt("anio");
				
				Editorial ed = new Editorial(codEditorial, nombre,anio);//instancio elobjeto
				lista.add(ed);//lo añado en la lista ed
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


    public Editorial obtenerEditorial(int codEditorial) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Editorial ed=null; //aqui no necesito una lista, sólo una editorial ed
		
		try {
			consulta = con.prepareStatement("select * from editoriales "
					+ "where codEditorial = ?");//es diferene a la de arriba, y no paso valor sino ?
			consulta.setInt(1, codEditorial); //setInt la primera? es 1, las 2 ? es codEditorial
			resultado = consulta.executeQuery();
			
	
			
			//hago if porque con solo necestio un dato, el while es un bucle  codEditorial es clave primaria no va a dar problema
			if (resultado.next()) {//el next salta a la siguiente fila para seguir buscando
				String nombre = resultado.getString("nombre");
				int año = resultado.getInt("anio");
				
				ed = new Editorial(codEditorial, nombre,año);// no devuelve un array pero si un ed
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
		return ed;
    }

//insertamos una nueva
    public int insertarEditorial(Editorial editorial) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;//coge este tipo
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("INSERT INTO editoriales (nombre,anio)"
					+ " VALUES (?,?) ");
			
			consulta.setString(1, editorial.getNombre());
			consulta.setInt(2, editorial.getAño());
			resultado=consulta.executeUpdate(); // llamo a execute UPdate porque voy a insertar

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
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

    public int actualizarEditorial(Editorial editorial) { //modifica ed cambiando nombre y año
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("UPDATE biblioteca.editoriales"
					+ "SET nombre = ?, año = ?"
					+ "WHERE codEditorial = ?");
			
			consulta.setString(1, editorial.getNombre());
			consulta.setInt(2, editorial.getAño());
			consulta.setInt(3, editorial.getCodEditorial());
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

    public int eliminarEditorial(int codEditorial) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("DELETE FROM `biblioteca`.`editoriales`\r\n"
					+ "WHERE codEditorial = ?");
			
			consulta.setInt(1, codEditorial);//porque solo depende de codEditorial
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
//   Public ArrayList<Editorial> obtenerEditorialesAPartir(int anioDesde) { //recojo todas las editoriales, impor arrayList y editorial
////    	// Obtenemos una conexion a la base de datos.
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
//		}
//		return lista;
//    }
    public ArrayList<Editorial> obtenerEditorialesApartirde(int anio) { //recojo todas las editoriales, impor arrayList y editorial
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();//defino objeto Connection llamad con. obtenemos objeto connection para conectar a la base de datos.
		PreparedStatement consulta = null;
		ResultSet resultado = null;//recoge los datos
		ArrayList<Editorial> lista = new ArrayList<Editorial>();
		
		try {
			consulta = con.prepareStatement("select * from editoriales where anio=?");
			consulta.setInt(1, anio);//aquí se prepara la query
			resultado = consulta.executeQuery();
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			if(resultado.next()) {
				int codEditorial = resultado.getInt("codeditorial");
				String nombre = resultado.getString("nombre");
				int  anio = resultado.getInt("anio");
				
				Editorial ed = new Editorial(codEditorial, nombre,anio);//instancio elobjeto
				lista.add(ed);//lo añado en la lista ed
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
}
