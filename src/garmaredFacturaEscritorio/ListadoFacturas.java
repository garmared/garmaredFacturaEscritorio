package garmaredFacturaEscritorio;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import acciones.dto.FacturasDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.accionesFacturaImpl;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class ListadoFacturas {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private FacturasDTO factura;
	accionesFacturaImpl accFactura = new accionesFacturaImpl();
	private JTable table;
	private String titColumna[];
	private String datoColumna[][];
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
				VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
			}
		});
		btnVolver.setBounds(318, 21, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		String datos[][] = {{"dato1/1","dato1/2","dato1/3","dato1/4","dato1/5","dato1/6","dato1/7","dato1/8"},
				{"dato2/1","dato2/2","dato2/3","dato2/4","dato2/5","dato2/6","dato2/7","dato2/8"},
				{"dato3/1","dato3/2","dato3/3","dato3/4","dato3/5","dato3/6","dato3/7","dato3/8"}	
		};
		
		String cabecera[] = {"Fecha","num Factura","Vencimiento","Proyecto","Cliente",
				"Concepto", "Coste", "Proveedor"
		};
		
		JTable table = new JTable(datos,cabecera);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(17, 75, 800, 800);
		frame.getContentPane().add(scrollPane);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField cliente = new JTextField();
				JTextField fecha = new JTextField();
				Object [] mensaje= {
						"Cliente:", cliente,
						"Fecha Inicio:", fecha
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Búsqueda de Factura", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
					if (cliente.getText() == "" || fecha.getText()== "") {
						
					}else {
						factura = new FacturasDTO();
						factura.setIdEmpresa(sesionGlobal.getIdEmpresa());
						factura.setCliente(Integer.valueOf(cliente.getText()));
						factura.setFecha(Integer.valueOf(fecha.getText()));
						factura = accFactura.buscaFactura(factura);
						if (factura.getIdFactura().equals(0)) {
							JOptionPane.showMessageDialog(null, "No se han encontrado datos para esta selección");	
						} else {
							initialize(sesionGlobal.getNombreEmpresa());
							llenaCamposListado(factura);
						}
					}
				}				
			
			}

			private void llenaCamposListado(FacturasDTO factura) {
				// TODO Auto-generated method stub
				
			}
		});

		btnBuscar.setBounds(42, 21, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}



}
