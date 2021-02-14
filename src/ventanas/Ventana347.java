package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.opencsv.CSVWriter;

import acciones.dto.FacturasDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesClientesImpl;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesCostesImpl;
import acciones.service.impl.AccionesEmpresasImpl;
import acciones.service.impl.AccionesFacturaImpl;
import acciones.service.impl.AccionesProyectosImpl;
import acciones.service.impl.AccionesServiceImpl;
import estructuras.DatosClientes;
import estructuras.DatosFacturas;

public class Ventana347 {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	AccionesFacturaImpl accFactura = new AccionesFacturaImpl();
	AccionesClientesImpl accClientes = new AccionesClientesImpl();
	AccionesServiceImpl accService = new AccionesServiceImpl();
	AccionesProyectosImpl accProyecto = new AccionesProyectosImpl();
	AccionesEmpresasImpl accProveedor = new AccionesEmpresasImpl();
	AccionesConceptosImpl accConcepto = new AccionesConceptosImpl();
	AccionesCostesImpl accCostes = new AccionesCostesImpl();
	DatosFacturas datosFact = new DatosFacturas();
	DefaultTableModel modelo = new DefaultTableModel();
	List<String[]> datosCsv = new ArrayList<String[]>();
	Table tablaPdf;
	/**
	 * Create the application.
	 */
	public Ventana347(ServiceDTO control) {
		sesionGlobal = control;
		initialize(sesionGlobal.getNombreEmpresa());
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Cálculo del IVA para la empresa " + nombre);
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
				JComboBox anyo = llenaAnyos();
				Object [] mensaje= {
						"año:", anyo
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Búsqueda de Factura", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
					//las facturas emitidas no tienen proveedor
					paramConsulta.setAnyo((Integer) anyo.getSelectedItem());
					paramConsulta.setIdEmpresa(sesionGlobal.getIdEmpresa());
					llenaListadoR(paramConsulta);
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

	private void creaPdf(Table tabla) {
		String nomFichero = JOptionPane.showInputDialog("Escribe el nombre del fichero a generar");
		PdfDocument pdf=null;
		try {
			pdf = new PdfDocument(new PdfWriter("pdf/347/"+nomFichero+".pdf"));
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
		String nomFichero = ("csv/347/"+JOptionPane.showInputDialog("Escribe el nombre del fichero a generar")+".csv");
		try {
			CSVWriter csv = new CSVWriter(new FileWriter(nomFichero));
			String [] cabecera = {"Proveedor","Base Imponible","Descuento"};
			csv.writeNext(cabecera);
			csv.writeAll(datosCsv);
			csv.close();
			JOptionPane.showMessageDialog(null, "Fichero "+nomFichero+" creado correctamente");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			        
	}

	protected JComboBox llenaAnyos() {
		JComboBox salida = new JComboBox();
		Connection connection = accService.getConexion();
		String consulta;
		consulta=accFactura.creaConsultaAnyos(sesionGlobal.getIdEmpresa());
		ResultSet rs = accService.getTabla(consulta, connection);
		try {
			while (rs.next()) {
				salida.addItem(rs.getInt("anyoFecha"));
			}
			rs.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return salida;
	}
	
	private DatosFacturas llenaJtable(ResultSet rs) {
		datosFact = new DatosFacturas();
		try {
			datosFact.setProveedor(accProveedor.buscaNombre(rs.getInt("id_proveedor"), "P"));
			Double suma = Math.round(rs.getDouble("base_impo")*100.0)/100.0; //redondea a dos decimales
			datosFact.setBaseImpo(suma);
			suma = Math.round(rs.getDouble("descuento")*100.0)/100.0; //redondea a dos decimales
			datosFact.setDescuento(suma);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datosFact;
	}
	
	private void llenaListadoR(FacturasDTO paramConsulta) {
		//array con tantas filas cómo columnas queramos en el listado
		Connection connection = accService.getConexion();
		DefaultTableModel modelo = new DefaultTableModel();
		String consulta;
		consulta=accFactura.creaConsulta347R(paramConsulta);
												
		ResultSet rs = accService.getTabla(consulta, connection);
		modelo.setColumnIdentifiers(new Object[]{"Proveedor","Base Imponible","Descuento"});
		JTable table = new JTable(modelo);
		Double suma1,suma2;
		try {
			tablaPdf = new Table(3);
			tablaPdf = llenaCabecera();
			int indice= 0;
			while (rs.next()) {
				datosFact = llenaJtable(rs);
				modelo.addRow(new Object[] {datosFact.getProveedor(), rs.getDouble("base_impo"),rs.getDouble("descuento")});
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
		scrollPane.setBounds(17, 75, 800, 700);
		frame.getContentPane().add(scrollPane);
		
	}
	
	private Table llenaCabecera() {
		
		tablaPdf.addHeaderCell("Proveedor");
		tablaPdf.addHeaderCell("Base Imponible");
		tablaPdf.addHeaderCell("Descuento");
		
		return tablaPdf;
	}
	
	private void llenaDatosCsv(DatosFacturas datos, int indice) {
		String[] dato = {datos.getProveedor(),String.valueOf(datos.getBaseImpo()),String.valueOf(datos.getDescuento())};
		datosCsv.add(indice,dato);
	}

	private void llenaTablaPdf(DatosFacturas datos) {
		tablaPdf.addCell(datos.getProveedor());
		tablaPdf.addCell(String.valueOf(datos.getBaseImpo()));
		tablaPdf.addCell(String.valueOf(datos.getDescuento()));
	}
}	

