package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Editorial;
import modelo.Libro;
import net.miginfocom.swing.MigLayout;

public class NuevoLibro extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtcodEdit;
	private JTextField txtnumPag;
	private Controlador controlador;
	private Editorial editorial;
	private JLabel lblTitulo;
	private JTextField textisbn;
	private JTextField textTitulo;
	private JTextField textanio;
	private JTextField textPrecio;
	private JTextField textCantidad;
	private JTextField textPrecioCD;
	
	private Libro libro;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NuevaEditorial frame = new NuevaEditorial();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public NuevoLibro() {
		setBounds(100, 100, 489, 358);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][]"));
		{
			lblTitulo = new JLabel("Insercion de Libros");
			lblTitulo.setOpaque(true);
			lblTitulo.setForeground(Color.WHITE);
			lblTitulo.setBackground(Color.BLACK);
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
			contentPanel.add(lblTitulo, "cell 0 0 2 1,growx");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Isbn:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_1, "cell 0 2,alignx trailing");
		}
		{
			textisbn = new JTextField();
			textisbn.setColumns(10);
			contentPanel.add(textisbn, "cell 1 2,growx");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Titulo:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_1, "cell 0 3,alignx trailing");
		}
		{
			textTitulo = new JTextField();
			textTitulo.setColumns(10);
			contentPanel.add(textTitulo, "cell 1 3,growx");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cod.Editorial");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_1, "cell 0 4,alignx trailing");
		}
		{
			txtcodEdit = new JTextField();
			contentPanel.add(txtcodEdit, "cell 1 4,growx");
			txtcodEdit.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Año:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_2, "cell 0 5,alignx trailing");
		}
		{
			textanio = new JTextField();
			textanio.setColumns(10);
			contentPanel.add(textanio, "cell 1 5,growx");
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Num.Pag:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_2, "cell 0 6,alignx right");
		}
		{
			txtnumPag = new JTextField();
			contentPanel.add(txtnumPag, "cell 1 6,growx");
			txtnumPag.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Precio:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_2, "cell 0 7,alignx trailing");
		}
		{
			textPrecio = new JTextField();
			textPrecio.setColumns(10);
			contentPanel.add(textPrecio, "cell 1 7,growx");
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Cantidad:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_2, "cell 0 9,alignx trailing");
		}
		{
			textCantidad = new JTextField();
			textCantidad.setColumns(10);
			contentPanel.add(textCantidad, "cell 1 9,growx");
		}
		{
			JLabel lblNewLabel_2 = new JLabel("PrecioCD:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_2, "cell 0 11,alignx trailing");
		}
		{
			textPrecioCD = new JTextField();
			textPrecioCD.setColumns(10);
			contentPanel.add(textPrecioCD, "cell 1 11,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						insertarLibro();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void insertarLibro() {
		try {
			String isbn = txtcodEdit.getText();
			String titulo= textTitulo.getText();
			int codEditorial= Integer.parseInt(txtcodEdit.getText());
			int anio = Integer.parseInt(textanio.getText());
			int num_pags =Integer.parseInt(txtnumPag.getText());
			float precio=Float.parseFloat(textPrecio.getText());
			int cantidad= Integer.parseInt(textCantidad.getText());
			float precioCD=Float.parseFloat(textPrecioCD.getText());
			//Editorial ed = new Editorial(0,nombre,año);
			Libro lib = new Libro(isbn, titulo, codEditorial, anio, num_pags, precio, cantidad, precioCD);//cojo el constructor con parámetros
			

			if (this.libro == null){//este if es porque significa que es una nueva
				controlador.insertarLibro(lib);
			}else {
				lib.setCodEditorial(this.libro.getCodEditorial());
				
				controlador.actualizarLibro(lib);
				
			}
		} catch (NumberFormatException e ) {
			JOptionPane.showMessageDialog(this, "Introduzca un año correcto");
		}
		
		
	}

	/**
	 * @param controlador el controlador a establecer
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setLibro(Libro lib) {
		libro = lib;
		if (lib!=null) {
			lblTitulo.setText("Actualizar el Libro");
			
			txtcodEdit.setText(editorial.getNombre());
			txtnumPag.setText(""+editorial.getAño());
		} else {
			lblTitulo.setText ("Insertar Libro");	
			txtcodEdit.setText("");
			txtnumPag.setText("");
		}
		///ahi arriba tengo que poner todos los datos en orden que se van a recoger
	}
	
	

}
