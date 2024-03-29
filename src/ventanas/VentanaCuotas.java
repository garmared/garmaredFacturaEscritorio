package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import acciones.dto.ConceptosDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesServiceImpl;

public class VentanaCuotas {

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
					VentanaCuotas window = new VentanaCuotas(sesionGlobal);
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
	public VentanaCuotas(ServiceDTO control) {
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
		table.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (table.getSelectedColumn()==2) {
					if (e.getKeyCode()== KeyEvent.VK_TAB) {
						modelo.addRow(new Object[] {"",""});
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
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
				String campo = "";
				for (int i = 0; i < elementos; i++) {
					campo = String.valueOf(table.getValueAt(i,0));
					if (campo !=" " && campo != "") {
						conceptos = new ConceptosDTO();
						conceptos.setIdEmpresa(sesionGlobal.getIdEmpresa());
						conceptos.setNombre(String.valueOf(table.getValueAt(i,0)));
						campo = String.valueOf(table.getValueAt(i,1));
						if (campo !=" " && campo != ""){
							conceptos.setImporte(Double.parseDouble(String.valueOf(modelo.getValueAt(i,1))));
						} else {
							conceptos.setImporte(0);
						}
						campo = String.valueOf(table.getValueAt(i,2));
						if (campo !=" " && campo != ""){
							conceptos.setIva(Double.parseDouble(String.valueOf(modelo.getValueAt(i,2))));
						} else {
							conceptos.setIva(0);							
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
		
	
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el concepto "+table.getValueAt(fila, 0)+"?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					modelo.removeRow(fila);
					modelo.addRow(new Object[] {"", "", ""});
				}
			}
		});
		btnEliminar.setBounds(113, 11, 89, 23);
		frame.getContentPane().add(btnEliminar);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
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
			modelo.addRow(new Object[] {"", "", ""});
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
