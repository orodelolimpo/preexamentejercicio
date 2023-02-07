package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import net.miginfocom.swing.MigLayout;

public class VentanaPpal extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;



	/**
	 * Create the frame.
	 */
	public VentanaPpal() {
		
		setTitle("Base de datos de Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][][grow][]", "[][][][][][][]"));
		
		JButton btnMostrarEd = new JButton("Mostrar Editorial");
		btnMostrarEd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarEditoriales();
			}
		});
		contentPane.add(btnMostrarEd, "cell 1 1,growx");
		
		JButton btnInsertar = new JButton("Insertar Editorial");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarNuevaEditorial();
			}
		});
		
		JButton btnMostrarL = new JButton("Mostrar Libro");
		contentPane.add(btnMostrarL, "cell 3 1,growx");
		contentPane.add(btnInsertar, "cell 1 3,growx");
		
		JButton btnInsertLibro = new JButton("Insertar Libro");
		contentPane.add(btnInsertLibro, "cell 3 3,growx");
	}



	/**
	 * @param controlador el controlador a establecer
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	

}
