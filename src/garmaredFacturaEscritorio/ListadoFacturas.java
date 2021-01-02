package garmaredFacturaEscritorio;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import acciones.dto.FacturasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ServiceDTO;
import acciones.service.impl.accionesClientesImpl;
import acciones.service.impl.accionesFacturaImpl;
import acciones.service.impl.accionesServiceImpl;

public class ListadoFacturas {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private FacturasDTO factura;
	accionesFacturaImpl accFactura = new accionesFacturaImpl();
	accionesClientesImpl accClientes = new accionesClientesImpl();
	accionesServiceImpl accService = new accionesServiceImpl();
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoFacturas window = new ListadoFacturas(sesionGlobal);
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
	public ListadoFacturas(ServiceDTO control) {
		sesionGlobal = control;
		initialize(sesionGlobal.getNombreEmpresa());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Listado de facturas de la empresa " + nombre);
		frame.setBounds(100, 100, 850, 900);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
				VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
			}
		});
		btnVolver.setBounds(318, 21, 89, 23);
		frame.getContentPane().add(btnVolver);
				
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cliente = new JComboBox();
				ArrayList<ObjetoJComboBox> cadena = accClientes.consultaClientes(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (var i = 0; i < cadena.size(); i++) {
						cliente.addItem(cadena.get(i));
					}
					cliente.addItem(new ObjetoJComboBox(0, "Todos"));
				}
				JTextField fecha = new JTextField();
				Object [] mensaje= {
						"Cliente:", cliente,
						"Fecha Inicio:", fecha
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Búsqueda de Factura", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
					if (fecha.getText()!= "") {
						int empresa = 0;
						int fentrada = 0;
						int idCliente = 0;
						
						empresa = sesionGlobal.getIdEmpresa();
						ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
						temporal = (ObjetoJComboBox) cliente.getSelectedItem();
						idCliente = temporal.getNumero();			
						fentrada = Integer.valueOf(fecha.getText());
						//array con tantas filas cómo columnas queramos en el listado
						Connection connection = accService.getConexion();
						DefaultTableModel modelo = new DefaultTableModel();
						String consulta;
						if (idCliente==0) {
							consulta = "select fecha, id_factura, vencimiento, id_proyecto, id_cliente, id_concepto, id_coste, id_proveedor from factura WHERE fecha = '"+fentrada+"' AND id_empresa = "+empresa+"";
							
						} else {
							consulta = "select fecha, id_factura, vencimiento, id_proyecto, id_cliente, id_concepto, id_coste, id_proveedor from factura WHERE id_cliente = '"+idCliente+"' AND fecha = '"+fentrada+"' AND id_empresa = "+empresa+"";
						}
						ResultSet rs = accService.getTabla(consulta, connection);
						modelo.setColumnIdentifiers(new Object[]{"Fecha","num Factura","Vencimiento","Proyecto","Cliente","Concepto", "Coste", "Proveedor"});
						
						JTable table = new JTable(modelo);
						try {
								while (rs.next()) {
									modelo.addRow(new Object[] {rs.getInt("fecha"), rs.getInt("id_factura"), rs.getInt("vencimiento"), rs.getInt("id_proyecto"), rs.getInt("id_cliente"), rs.getInt("id_concepto"),
										rs.getInt("id_coste"),rs.getInt("id_proveedor")});
								}
								table.setModel(modelo);
								if (table.getRowCount()==0) {
									JOptionPane.showMessageDialog(null, "Facturas no encontradas para esta selección");
								}
								rs.close();
								connection.close();
						} catch (Exception e1) {System.out.println(e1);}
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(17, 75, 800, 800);
						frame.getContentPane().add(scrollPane);
					}
				}
			}				
		});

		btnBuscar.setBounds(42, 21, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}



}
