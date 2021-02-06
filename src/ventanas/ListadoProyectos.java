package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.opencsv.CSVWriter;
import com.toedter.calendar.JDateChooser;

import acciones.dto.FacturasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ProyectosDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesClientesImpl;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesCostesImpl;
import acciones.service.impl.AccionesEmpresasImpl;
import acciones.service.impl.AccionesFacturaImpl;
import acciones.service.impl.AccionesProyectosImpl;
import acciones.service.impl.AccionesServiceImpl;
import estructuras.DatosProyectos;

public class ListadoProyectos {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private FacturasDTO factura;

	AccionesFacturaImpl accFactura = new AccionesFacturaImpl();
	AccionesClientesImpl accClientes = new AccionesClientesImpl();
	AccionesServiceImpl accService = new AccionesServiceImpl();
	AccionesProyectosImpl accProyecto = new AccionesProyectosImpl();
	AccionesConceptosImpl accConcepto = new AccionesConceptosImpl();
	AccionesCostesImpl accCostes = new AccionesCostesImpl();
	AccionesEmpresasImpl accProveedor = new AccionesEmpresasImpl();
	DatosProyectos datos = new DatosProyectos();
	List<String[]> datosCsv = new ArrayList<String[]>();
	
	private JTable table;
	Table tablaPdf;
	DefaultTableModel modelo = new DefaultTableModel();
	/**
	 * Create the application.
	 */
	public ListadoProyectos(ServiceDTO control) {
		sesionGlobal = control;
		initialize(sesionGlobal.getNombreEmpresa());
		if (sesionGlobal.getNoPrincipal()=="S") {
			ProyectosDTO paramConsulta = llenaParamConsulta();
			llenaListado(paramConsulta);
		}
	}

	private ProyectosDTO llenaParamConsulta() {
		ProyectosDTO salida = new ProyectosDTO();
		salida.setEmpresa(sesionGlobal.getIdEmpresa());
		salida.setCliente(sesionGlobal.getInt1());
		salida.setDescripcion(sesionGlobal.getChar1());
		salida.setFechaIni(sesionGlobal.getInt2());
		salida.setFechaFin(sesionGlobal.getInt3());	
		salida.setFechaCierre(sesionGlobal.getInt4());
		return salida;
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Listado de proyectos de la empresa " + nombre);
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
				ProyectosDTO paramConsulta = new ProyectosDTO();
				JComboBox cliente = new JComboBox();
				ArrayList<ObjetoJComboBox> cadena = accClientes.consultaClientes(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (var i = 0; i < cadena.size(); i++) {
						cliente.addItem(cadena.get(i));
					}
					cliente.addItem(new ObjetoJComboBox(0, "Todos"));
				}
				JDateChooser fechaIni = new JDateChooser();
				JDateChooser fechaFin = new JDateChooser();
				JDateChooser fechaCierre = new JDateChooser();
				JTextField descripcion= new JTextField();
				Object [] mensaje= {
						"Cliente:", cliente,
						"Nombre:", descripcion,
						"Fecha Inicio:", fechaIni,
						"Fecha Fin:", fechaFin,
						"Fecha Cierre:", fechaCierre,
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Búsqueda de proyectos", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
						ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
						temporal = (ObjetoJComboBox) cliente.getSelectedItem();
						paramConsulta.setCliente(temporal.getNumero());	
						paramConsulta.setDescripcion(descripcion.getText());
						paramConsulta.setFechaIni(Integer.valueOf(accService.obtenerFecha(fechaIni)));
						paramConsulta.setFechaFin(Integer.valueOf(accService.obtenerFecha(fechaFin)));
						paramConsulta.setFechaCierre(Integer.valueOf(accService.obtenerFecha(fechaCierre)));
						paramConsulta.setEmpresa(sesionGlobal.getIdEmpresa());
						
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

	protected void llenaListado(ProyectosDTO paramConsulta) {
		Connection connection = accService.getConexion();
		DefaultTableModel modelo = new DefaultTableModel();
		
		String consulta;
		consulta=accProyecto.creaConsulta(paramConsulta);
												
		ResultSet rs = accService.getTabla(consulta, connection);
		modelo.setColumnIdentifiers(new Object[]{"identificador","Descripción","Fecha Inicio","Fecha Fin","Fecha Cierre","Cliente", "Coste", "Importe","Margen"});
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
					frame.dispose();
					VentanaProyectos ventana = new VentanaProyectos(sesionGlobal);
				} else {JOptionPane.showMessageDialog(null, "Selecciona una única fila");}
			}
		});
		
		try {
				tablaPdf = new Table(9);
				tablaPdf = llenaCabecera();
				int indice= 0;
				while (rs.next()) {
					datos = llenaJtable(rs);
					modelo.addRow(new Object[] {rs.getInt("id_proyecto"), rs.getString("descripcion"), rs.getInt("fecha_ini"), rs.getInt("fecha_fin"),rs.getInt("fecha_cierre"),
							datos.getCliente(),datos.getCoste(),rs.getInt("importe"),rs.getInt("margen")});
					llenaTablaPdf(datos);	
					llenaDatosCsv(datos,indice);
					indice++;
				}
				table.setModel(modelo);
				if (table.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "Proveedores no encontrados para esta selección");
				}
				rs.close();
				connection.close();
		} catch (Exception e1) {System.out.println(e1);}
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(17, 75, 800, 800);
		frame.getContentPane().add(scrollPane);

	}

	private void creaPdf(Table tabla) {
		String nomFichero = JOptionPane.showInputDialog("Escribe el nombre del fichero a generar");
		PdfDocument pdf=null;
		try {
			pdf = new PdfDocument(new PdfWriter("pdf/proyectos/"+nomFichero+".pdf"));
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
		String nomFichero = ("csv/proyectos/"+JOptionPane.showInputDialog("Escribe el nombre del fichero a generar")+".csv");
		try {
			CSVWriter csv = new CSVWriter(new FileWriter(nomFichero));
			String [] cabecera = {"identificador","Descripción","Fecha Inicio","Fecha Fin","Fecha Cierre","Cliente", "Coste", "Importe","Margen"};
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
		
		tablaPdf.addHeaderCell("Clave");
		tablaPdf.addHeaderCell("Descripción");
		tablaPdf.addHeaderCell("Fecha Inicio");
		tablaPdf.addHeaderCell("Fecha Fin");
		tablaPdf.addHeaderCell("Fecha Cierre");
		tablaPdf.addHeaderCell("Cliente");
		tablaPdf.addHeaderCell("Coste");
		tablaPdf.addHeaderCell("Importe");
		tablaPdf.addHeaderCell("Margen");
				
		return tablaPdf;
	}	
	
	private void llenaTablaPdf(DatosProyectos datos) {
		tablaPdf.addCell(String.valueOf(datos.getIdentificador()));
		tablaPdf.addCell(datos.getDescripcion());
		tablaPdf.addCell(datos.getFechaIni());
		tablaPdf.addCell(datos.getFechaFin());
		tablaPdf.addCell(datos.getFechaCierre());
		tablaPdf.addCell(datos.getCliente());
		tablaPdf.addCell(datos.getCoste());
		tablaPdf.addCell(String.valueOf(datos.getImporte()));
		tablaPdf.addCell(String.valueOf(datos.getMargen()));
	}
	
	private DatosProyectos llenaJtable(ResultSet rs) {
		datos = new DatosProyectos();
		try {
			datos.setCliente(accClientes.buscaNombre(rs.getInt("id_cliente")));
			datos.setCoste(accCostes.buscaDescripcion(rs.getInt("Tipo_coste")));
			datos.setDescripcion(rs.getString("descripcion"));
			datos.setFechaCierre(String.valueOf(rs.getInt("fecha_cierre")));
			datos.setFechaFin(String.valueOf(rs.getInt("fecha_fin")));
			datos.setFechaIni(String.valueOf(rs.getInt("fecha_ini")));
			datos.setIdentificador(rs.getInt("id_proyecto"));
			datos.setImporte(rs.getBigDecimal("importe"));
			datos.setMargen(rs.getBigDecimal("margen"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}

	private void llenaDatosCsv(DatosProyectos datos, int indice) {
		String[] dato = {String.valueOf(datos.getIdentificador()),datos.getDescripcion(),datos.getFechaIni(),datos.getFechaFin(),datos.getFechaCierre(),
				datos.getCliente(),datos.getCoste(),String.valueOf(datos.getImporte()),String.valueOf(datos.getMargen())};
		datosCsv.add(indice,dato);
	}
	
	protected void guardaConsulta(ProyectosDTO paramConsulta) {
		sesionGlobal.setInt1(paramConsulta.getCliente());
		sesionGlobal.setInt2(paramConsulta.getFechaIni());
		sesionGlobal.setInt3(paramConsulta.getFechaFin());
		sesionGlobal.setInt4(paramConsulta.getFechaCierre());
		sesionGlobal.setChar1(paramConsulta.getDescripcion());
	}
}
