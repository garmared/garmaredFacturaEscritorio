package ventanas;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.opencsv.CSVWriter;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import acciones.dto.CostesDTO;
import acciones.dto.FacturasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesClientesImpl;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesCostesImpl;
import acciones.service.impl.AccionesEmpresasImpl;
import acciones.service.impl.AccionesFacturaImpl;
import acciones.service.impl.AccionesProyectosImpl;
import acciones.service.impl.AccionesServiceImpl;
import estructuras.DatosFacturas;

public class ListadoFacturasE {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private FacturasDTO factura;

	AccionesFacturaImpl accFactura = new AccionesFacturaImpl();
	AccionesClientesImpl accClientes = new AccionesClientesImpl();
	AccionesServiceImpl accService = new AccionesServiceImpl();
	AccionesProyectosImpl accProyecto = new AccionesProyectosImpl();
	AccionesConceptosImpl accConcepto = new AccionesConceptosImpl();
	AccionesCostesImpl accCostes = new AccionesCostesImpl();
	DatosFacturas datosFact = new DatosFacturas();
	List<String[]> datosCsv = new ArrayList<String[]>();
	
	private JTable table;
	Table tablaPdf;
	DefaultTableModel modelo = new DefaultTableModel();
	/**
	 * Create the application.
	 */
	public ListadoFacturasE(ServiceDTO control) {
		sesionGlobal = control;
		initialize(sesionGlobal.getNombreEmpresa());
		if (sesionGlobal.getNoPrincipal()=="S") {
			FacturasDTO paramConsulta = llenaParamConsulta();
			llenaListado(paramConsulta);
		}
	}

	private void llenaListado(FacturasDTO paramConsulta) {
		//array con tantas filas cómo columnas queramos en el listado
		Connection connection = accService.getConexion();
		DefaultTableModel modelo = new DefaultTableModel();
		String consulta;
		consulta=accFactura.creaConsultaEmitida(paramConsulta);
												
		ResultSet rs = accService.getTabla(consulta, connection);
		modelo.setColumnIdentifiers(new Object[]{"identificador","Fecha","Vencimiento","Proyecto","Cliente","Concepto", "Coste","Estado"});
		JTable table = new JTable(modelo);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				sesionGlobal.setNoPrincipal("S");
				DefaultTableModel modeloAux = (DefaultTableModel) table.getModel();
				if (table.getSelectedRow() !=-1) {
					int codigo = (int) modeloAux.getValueAt(table.getSelectedRow(), 0);
					sesionGlobal.setIdentificador(codigo);
					frame.dispose(); // esto cierra la ventana
					VentanaFacturasE ventana = new VentanaFacturasE(sesionGlobal);
				} else {JOptionPane.showMessageDialog(null, "Selecciona una única fila");}
			}
		});
		try {
				tablaPdf = new Table(8);
				tablaPdf = llenaCabecera();
				int indice= 0;
				while (rs.next()) {
					datosFact = llenaJtable(rs);
					modelo.addRow(new Object[] {rs.getInt("id_factura"),rs.getInt("fecha"), rs.getInt("vencimiento"), datosFact.getProyecto(), datosFact.getNombreCliente(), datosFact.getConcepto(),datosFact.getCoste(),datosFact.getEstado()});
					llenaTablaPdf(datosFact);	
					llenaDatosCsv(datosFact,indice);
					indice++;
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

	private FacturasDTO llenaParamConsulta() {
		FacturasDTO salida = new FacturasDTO();
		salida.setCliente(sesionGlobal.getInt1());
		salida.setPagado(sesionGlobal.getChar1());
		salida.setCoste(sesionGlobal.getInt2());
		salida.setFecha(sesionGlobal.getInt3());
		salida.setEmpresa(sesionGlobal.getInt4());	
		return salida;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Listado de facturas emitidas de la empresa " + nombre);
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
		btnVolver.setBounds(529, 21, 89, 23);
		frame.getContentPane().add(btnVolver);
				
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacturasDTO paramConsulta = new FacturasDTO();
				JComboBox cliente = new JComboBox();
				ArrayList<ObjetoJComboBox> cadena = accClientes.consultaClientes(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (var i = 0; i < cadena.size(); i++) {
						cliente.addItem(cadena.get(i));
					}
					cliente.addItem(new ObjetoJComboBox(0, "Todos"));
				}
				String proyecto="";
				
				JComboBox coste = new JComboBox();
				cadena = accCostes.consultaCostes(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (var i = 0; i < cadena.size(); i++) {
						coste.addItem(cadena.get(i));
					}
					coste.addItem(new ObjetoJComboBox(0, "Todos"));
				}
				JComboBox estadoFactura = new JComboBox();
				estadoFactura.addItem("Abono");
				estadoFactura.addItem("Pendiente");
				estadoFactura.addItem("Pagado");
				estadoFactura.addItem("Todos");
				
				JDateChooser fecha = new JDateChooser();
				Object [] mensaje= {
						"Cliente:", cliente,
						"Tipo Coste:", coste,
						"Estado Factura:", estadoFactura,
						"Fecha Inicio:", fecha
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Búsqueda de Factura", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
						//las facturas emitidas no tienen proveedor
						paramConsulta.setProveedor(0);
						paramConsulta.setEmpresa(sesionGlobal.getIdEmpresa());
						ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
						temporal = (ObjetoJComboBox) cliente.getSelectedItem();
						paramConsulta.setCliente(temporal.getNumero());			
						paramConsulta.setFecha(Integer.valueOf(accService.obtenerFecha(fecha)));
						temporal = (ObjetoJComboBox) coste.getSelectedItem();
						paramConsulta.setCoste(temporal.getNumero());
						paramConsulta.setPagado((String) estadoFactura.getSelectedItem());
						guardaConsulta(paramConsulta);
						llenaListado(paramConsulta);
				}
			}

		});

		btnBuscar.setBounds(42, 21, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		JButton btnExportarAPdf = new JButton("Exportar a PDF");
		btnExportarAPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creaPdf(tablaPdf);
			}
		});
		btnExportarAPdf.setBounds(173, 21, 134, 23);
		frame.getContentPane().add(btnExportarAPdf);
		
		JButton btnExportarExcel = new JButton("Exportar a Excel");
		btnExportarExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCsv();
			}
		});
		btnExportarExcel.setBounds(364, 21, 134, 23);
		frame.getContentPane().add(btnExportarExcel);
		
	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	protected void guardaConsulta(FacturasDTO paramConsulta) {
		sesionGlobal.setInt1(paramConsulta.getCliente());
		sesionGlobal.setInt2(paramConsulta.getCoste());
		sesionGlobal.setInt3(paramConsulta.getFecha());
		sesionGlobal.setInt4(paramConsulta.getEmpresa());
		sesionGlobal.setChar1(paramConsulta.getPagado());
	}

	private void creaPdf(Table tabla) {
		String nomFichero = JOptionPane.showInputDialog("Escribe el nombre del fichero a generar");
		PdfDocument pdf=null;
		try {
			pdf = new PdfDocument(new PdfWriter("pdf/factura/"+nomFichero+".pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al exportar a PDF");
		}
		
		Document documento = new Document(pdf);
		documento.add(tabla);
		documento.close();
		
		JOptionPane.showMessageDialog(null, "Fichero "+nomFichero+" creado correctamente");
	}
	
	private void crearCsv() {
		String nomFichero = ("csv/factura/"+JOptionPane.showInputDialog("Escribe el nombre del fichero a generar")+".csv");
		try {
			CSVWriter csv = new CSVWriter(new FileWriter(nomFichero));
			String [] cabecera = {"Factura", "Fecha", "Vencimiento", "Proyecto","Cliente","Concepto","Coste","Estado"};
			csv.writeNext(cabecera);
			csv.writeAll(datosCsv);
			csv.close();
			JOptionPane.showMessageDialog(null, "Fichero "+nomFichero+" creado correctamente");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			        
	}
	
	private Table llenaCabecera() {
		
		tablaPdf.addHeaderCell("Factura");
		tablaPdf.addHeaderCell("Fecha");
		tablaPdf.addHeaderCell("Vencimiento");
		tablaPdf.addHeaderCell("Proyecto");
		tablaPdf.addHeaderCell("Cliente");
		tablaPdf.addHeaderCell("Concepto");
		tablaPdf.addHeaderCell("Coste");
		tablaPdf.addHeaderCell("Estado");
		
		return tablaPdf;
	}	
	
	private void llenaTablaPdf(DatosFacturas datosFact) {
		tablaPdf.addCell(datosFact.getFactura());
		tablaPdf.addCell(datosFact.getFecha());
		tablaPdf.addCell(datosFact.getVencimiento());
		tablaPdf.addCell(datosFact.getProyecto());
		tablaPdf.addCell(datosFact.getNombreCliente());
		tablaPdf.addCell(datosFact.getConcepto());
		tablaPdf.addCell(datosFact.getCoste());
		tablaPdf.addCell(datosFact.getEstado());
	}
	
	private DatosFacturas llenaJtable(ResultSet rs) {
		datosFact = new DatosFacturas();
		try {
			datosFact.setProyecto(accProyecto.buscaDescripcion(rs.getInt("id_proyecto"),sesionGlobal.getIdEmpresa()));
			datosFact.setNombreCliente(accClientes.buscaNombre(rs.getInt("id_cliente")));
			datosFact.setConcepto(accConcepto.buscaDescripcion(rs.getInt("id_concepto")));
			datosFact.setCoste(accCostes.buscaDescripcion(rs.getInt("id_coste")));
			datosFact.setFecha(String.valueOf(rs.getInt("fecha")));
			datosFact.setFactura(String.valueOf(rs.getInt("id_factura")));
			datosFact.setVencimiento(String.valueOf(rs.getInt("vencimiento")));
			datosFact.setEstado(rs.getString("pagado"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datosFact;
	}

	private void llenaDatosCsv(DatosFacturas datosFact, int indice) {
		String[] dato = {datosFact.getFactura(), datosFact.getFecha(), datosFact.getVencimiento(),datosFact.getProyecto(), datosFact.getNombreCliente(), 
					datosFact.getConcepto(), datosFact.getCoste(), datosFact.getEstado()};
		datosCsv.add(indice,dato);
	}
}	

