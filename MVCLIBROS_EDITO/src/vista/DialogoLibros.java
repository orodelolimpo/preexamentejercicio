package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Editorial;
import modelo.Libro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DialogoLibros extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Controlador controlador;


	/**
	 * Create the dialog.
	 */
	public DialogoLibros() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow][]"));
		{
			JLabel lblNewLabel = new JLabel("Listado de libros:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblNewLabel, "cell 0 0");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 1,grow");
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Isbn", "Titulo", "Cod.Editorial", "A\u00F1o", "N\u00FAm. P\u00E1ginas", "Precio", "Cantidad", "PrecioCD"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, Integer.class, Integer.class, Integer.class, Float.class, Integer.class, Float.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(0).setPreferredWidth(99);
				scrollPane.setViewportView(table);
			}
		}
		{
			{
				JPanel panel = new JPanel();
				contentPanel.add(panel, "cell 0 2,grow");
				panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					JButton btnNewButton_1 = new JButton("Modificar");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							llamarActualizar();
						}
					});
					{
						JButton btnEliminar = new JButton("Eliminar");
						btnEliminar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								llamarEliminar();
							}
						});
						panel.add(btnEliminar);
					}
					panel.add(btnNewButton_1);
					btnNewButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				JButton btnNewButton = new JButton("Cerrar");
				panel.add(btnNewButton);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
			}
		}
	}
	
	protected void llamarEliminar() {
		int fila=table.getSelectedRow();
		if (fila ==-1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una editorial");
		return; //para ahorrarme el else pongo return; y que continúe con lo de abajo.
			
		}
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();//lo recoge directamente
		int codEditorial = (int) modelo.getValueAt(fila, 0);//me coge la fila y hace un casting a int
		controlador.eliminarEditorial(codEditorial);	
		
	}

	protected void llamarActualizar() {
		int fila=table.getSelectedRow();
		if (fila ==-1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un libro");
		return; //para ahorrarme el else pongo return; y que continúe con lo de abajo.
			
		}
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();//lo recoge directamente
		String isbn = (String) modelo.getValueAt(fila, 0);//me coge la fila y hace un casting a int
		
		controlador.mostrarActualizarLibro(isbn);
		
	}

	public void setListaLibros(ArrayList<Libro> lista) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for (Libro libros : lista) {
			Object [] fila = { //así relleno la lista
					libros.getIsbn(),libros.getTitulo(),libros.getCodEditorial(),libros.getAnio(), 					libros.getNum_pags(),libros.getPrecio(),libros.getCantidad(),libros.getPrecioCD()
			};
			modelo.addRow(fila);
		}
	}//este código es el mismo de las ventanas anteriores solo que uso unalistas

	/**
	 * @param controlador el controlador a establecer
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	

}
