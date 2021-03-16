package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		sesionGlobal = control;
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
		table = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 45, 497, 200);
		frame.getContentPane().add(scrollPane);		
		
		JButton btnAlta = new JButton("Grabar");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ConceptosDTO> entrada = new ArrayList<ConceptosDTO>();
				accion = false;
				//grabamos los datos.
				int elementos = modelo.getRowCount();
				for (int i = 0; i < elementos; i++) {
					if (table.getValueAt(i, 0)!=" ") {
						conceptos = new ConceptosDTO();
						conceptos.setIdEmpresa(sesionGlobal.getIdEmpresa());
						conceptos.setNombre(String.valueOf(table.getValueAt(i,0)));
						if (table.getValueAt(i,1)== " "){
							conceptos.setImporte(0);
						} else {
							conceptos.setImporte(Double.parseDouble(String.valueOf(modelo.getValueAt(i,1))));
						}
						if (table.getValueAt(i,2)== " "){
							conceptos.setIva(0);
						} else {
							conceptos.setIva(Double.parseDouble(String.valueOf(modelo.getValueAt(i,2))));
						}
						conceptos.setIdProyecto(sesionGlobal.getIdentificador());
						entrada.add(conceptos);
					}
				}
				accion= accConceptos.grabarConceptoProyecto(entrada);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Conceptos dados de alta correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error con el alta de conceptos");
				}
			}
		});
		btnAlta.setBounds(23, 11, 80, 23);
		frame.getContentPane().add(btnAlta);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
			}
		});
		btnVolver.setBounds(212, 11, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiaPantalla();
			}
		});
		btnLimpiar.setBounds(311, 11, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
		JButton btnMs = new JButton("M\u00E1s");
		btnMs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.addRow(new Object[] {" ", " "});
			}
		});
		btnMs.setBounds(410, 11, 67, 23);
		frame.getContentPane().add(btnMs);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el concepto "+table.getValueAt(fila, 0)+"?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					modelo.removeRow(fila);
					modelo.addRow(new Object[] {" ", " ", " "});
				}
			}
		});
		btnEliminar.setBounds(113, 11, 89, 23);
		frame.getContentPane().add(btnEliminar);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	//https://www.youtube.com/watch?v=jPfKFm2Yfow
	private void montaTabla() {
		Connection connection = accService.getConexion();
		String consulta;
		ConceptosDTO paramConsulta = new ConceptosDTO();
		paramConsulta.setIdEmpresa(sesionGlobal.getIdEmpresa());
		paramConsulta.setIdProyecto(sesionGlobal.getIdentificador());
		consulta=accConceptos.creaConsulta(paramConsulta);
												
		ResultSet rs = accService.getTabla(consulta, connection);
		
		modelo.setColumnIdentifiers(new Object[]{"Nombre","Importe","IVA"});
		try {
			while (rs.next()) {
				modelo.addRow(new Object[] {rs.getString("idConcepto"),rs.getDouble("importe"),rs.getDouble("iva")});
			}
			modelo.addRow(new Object[] {" ", " ", " "});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	protected void limpiaPantalla() {
		int filas = table.getRowCount();
		filas --;
		for (int i = filas; i >= 0; i--) {
			modelo.removeRow(i);
		}
		montaTabla();
	}
}
