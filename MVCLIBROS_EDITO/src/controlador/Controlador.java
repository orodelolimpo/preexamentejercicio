/**
 * 
 */
package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.EditorialDAO;
import dao.LibroDAO;
import modelo.Editorial;
import modelo.Libro;
import vista.DialogoEditoriales;
import vista.DialogoLibros;
import vista.NuevoEditorial;
import vista.NuevoLibro;
import vista.VentanaPpal;

/**
 * @author David
 *
 */
public class Controlador {

	// VEntanas del sistema
	private VentanaPpal ventanaPpal;
	private DialogoEditoriales dialogoEditoriales;
	private NuevoEditorial NuevaEditorial;
	private NuevoLibro nuevoLibro;
	private DialogoLibros dialogoLibros;
	
	// Objetos DAO o CRUD de la base de datos para acceso
	private EditorialDAO editorialDAO;
	private LibroDAO libroDAO;
	
	public Controlador() {
		// Creamos las ventanas de la aplicaci�n
		this.ventanaPpal = new VentanaPpal();
		dialogoEditoriales = new DialogoEditoriales();
		this.NuevaEditorial = new NuevoEditorial();
		this.nuevoLibro= new NuevoLibro();
		this.dialogoLibros =new DialogoLibros();
		this.libroDAO = new LibroDAO();
		
		// Dando acceso al controlador desde las vistas.  EStablecemos controladores vistas
		ventanaPpal.setControlador(this);
		dialogoEditoriales.setControlador(this);
		NuevaEditorial.setControlador(this);
		nuevoLibro.setControlador(this);
		
		// Creamos los objetos DAO
		editorialDAO = new EditorialDAO();
		libroDAO = new LibroDAO();
	}
	
	public void inciarPrograma() {

		ventanaPpal.setVisible(true);
	}
	
	public void mostrarEditoriales() {
		ArrayList<Editorial> lista = editorialDAO.obtenerEditoriales();//
		this.dialogoEditoriales.setListaEditoriales(lista);//método que aún no existe al crearse aquí.Me lleva el error a dialogo Editoriarles de paquete vista.
		dialogoEditoriales.setVisible(true);
	}
	
	public void mostrarNuevaEditorial() {
		NuevaEditorial.setEditorial(null);
		NuevaEditorial.setVisible(true);
	}
//	
	
	public void insertarEditorial(Editorial ed) {
		int res=editorialDAO.insertarEditorial(ed);
		if (res==0) {
			JOptionPane.showMessageDialog(NuevaEditorial, "Error no se ha podido insertar");
		} else {
			JOptionPane.showMessageDialog(NuevaEditorial, "Editorial a�adido correctamente.");
			NuevaEditorial.setVisible(false);
		}
	}

	public void mostrarActualizarEditorial(int codEditorial) {
		Editorial e = editorialDAO.obtenerEditorial(codEditorial);
		NuevaEditorial.setEditorial(e);
		NuevaEditorial.setVisible(true);
	}

	public void actualizarEditorial(Editorial ed) {
		int res=editorialDAO.actualizarEditorial(ed);
		if (res==0) {
			JOptionPane.showMessageDialog(NuevaEditorial, "Error no se ha podido actualizar");
		} else {
			JOptionPane.showMessageDialog(NuevaEditorial, "Editorial actualizado correctamente.");
			NuevaEditorial.setVisible(false);
		}
		mostrarEditoriales();
	}

	public void eliminarEditorial(int codEditorial) {
	int res= editorialDAO.eliminarEditorial(codEditorial);
	if (res==0) {
		JOptionPane.showMessageDialog(NuevaEditorial, "No se ha actualizadeo correctamente.");
		this.NuevaEditorial.setVisible(false);
		
	} else {
		JOptionPane.showMessageDialog(NuevaEditorial, "Editorial borrada correctamente.");
		NuevaEditorial.setVisible(false);
	}
		mostrarEditoriales();
	}
	
	
	
	public void mostrarLibros() {
		ArrayList<Libro> lista = libroDAO.obtenerLibros();//
		this.dialogoLibros.setListaLibros(lista);//método que aún no existe al crearse aquí.Me lleva el error a dialogo Editoriarles de paquete vista.
		dialogoLibros.setVisible(true);
	}
}
