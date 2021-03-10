package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.layout.element.Table;

import acciones.dto.ConceptosDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesServiceImpl;

public class VentanaConceptosProyecto {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private Boolean accion;
	private ConceptosDTO conceptos;
	private int idConcepto;
	AccionesConceptosImpl accConceptos = new AccionesConceptosImpl();
	AccionesServiceImpl accService = new AccionesServiceImpl();
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	private JTable table_1;
	private JTable table_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConceptosProyecto window = new VentanaConceptosProyecto(sesionGlobal);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaConceptosProyecto(ServiceDTO control) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Conceptos del proyecto.");
		frame.setBounds(100, 100, 950, 900);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		montaTabla();
		JButton btnAlta = new JButton("Alta concepto");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accion = false;
				//grabamos los datos.
				conceptos = new ConceptosDTO();
				conceptos.setIdEmpresa(sesionGlobal.getIdEmpresa());
				accion= accConceptos.grabarConcepto(conceptos);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Concepto dado de alta correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error con el alta del concepto");
				}
			}
		});
		btnAlta.setBounds(23, 11, 127, 23);
		frame.getContentPane().add(btnAlta);
		
		JButton btnBaja = new JButton("Baja Concepto");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el concepto?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					accion = accConceptos.deleteConcepto(idConcepto);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Concepto borrado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado del concepto");
					}
				} else System.out.println("vale... no borro nada...");
			}
		});

		btnBaja.setBounds(193, 11, 127, 23);
		frame.getContentPane().add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar Concepto");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar el concepto?", "Confirmar modificación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					conceptos.setIdConcepto(idConcepto);
					accion = accConceptos.updateConcepto(conceptos);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Concepto modificado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar el concepto");
					}
				} else System.out.println("vale... no hago nada...");
			}
		});
		btnModificar.setBounds(361, 11, 159, 23);
		frame.getContentPane().add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
			}
		});
		btnVolver.setBounds(512, 119, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiaPantalla();
			}
		});
		btnLimpiar.setBounds(512, 153, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
		JButton btnMs = new JButton("M\u00E1s");
		btnMs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.addRow(new Object[] {" ", " "," "});
			}
		});
		btnMs.setBounds(490, 222, 51, 23);
		frame.getContentPane().add(btnMs);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.setBounds(98, 299, 301, 147);
		frame.getContentPane().add(table_2);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	//https://www.youtube.com/watch?v=jPfKFm2Yfow
	private void montaTabla() {
		modelo.setColumnIdentifiers(new Object[]{"Nombre","Importe",""});
		table = new JTable(modelo);
		modelo.addRow(new Object[] {" ", " "," "});
		modelo.addRow(new Object[] {" ", " "," "});
		modelo.addRow(new Object[] {" ", " "," "});
		modelo.addRow(new Object[] {" ", " "," "});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 45, 452, 200);
		frame.getContentPane().add(scrollPane);		
		
	}

	protected void limpiaPantalla() {
		// TODO Auto-generated method stub
	}
}
