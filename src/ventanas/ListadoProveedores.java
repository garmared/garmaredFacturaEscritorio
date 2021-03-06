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
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.opencsv.CSVWriter;

import acciones.dto.ClientesDTO;
import acciones.dto.EmpresasDTO;
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

public class ListadoProveedores {

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
	DatosClientes datos = new DatosClientes();
	List<String[]> datosCsv = new ArrayList<String[]>();
	
	private JTable table;
	Table tablaPdf;
	DefaultTableModel modelo = new DefaultTableModel();
	/**
	 * Create the application.
	 */
	public ListadoProveedores(ServiceDTO control) {
		sesionGlobal = control;
		initialize(sesionGlobal.getNombreEmpresa());
		if (sesionGlobal.getNoPrincipal()=="S") {
			EmpresasDTO paramConsulta = llenaParamConsulta();
			llenaListado(paramConsulta);
		} else {consutlaInicial();}
	}

	private void consutlaInicial() {
		EmpresasDTO salida = new EmpresasDTO();
		salida.setActivo("Todos");
		salida.setPoblacion("Todos");
		salida.setIdEmpresa(sesionGlobal.getIdEmpresa());	
		
		guardaConsulta(salida);
		llenaListado(salida);	
		
	}

	private void llenaListado(EmpresasDTO paramConsulta) {
		Connection connection = accService.getConexion();
		DefaultTableModel modelo = new DefaultTableModel();
		String consulta;
		consulta=accProveedor.creaConsulta(paramConsulta);
												
		ResultSet rs = accService.getTabla(consulta, connection);
		modelo.setColumnIdentifiers(new Object[]{"identificador","Nombre","CIF","Direccion","Poblacion","CP", "Telefono", "Persona de Contacto","mail","web","activo"});
		final JTable table = new JTable(modelo);
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
					VentanaProveedores ventana = new VentanaProveedores(sesionGlobal);
				} else {JOptionPane.showMessageDialog(null, "Selecciona una �nica fila");}
			}
		});
		try {
				tablaPdf = new Table(11);
				tablaPdf = llenaCabecera();
				int indice= 0;
				while (rs.next()) {
					datos = llenaJtable(rs);
					modelo.addRow(new Object[] {rs.getInt("empresa"), rs.getString("Nombre"), rs.getString("CIF"), rs.getString("Direccion"),rs.getString("Poblacion"),
							rs.getInt("CP"),rs.getInt("telefono1"),rs.getString("Persona_contacto"),rs.getString("mail"),rs.getString("web"),rs.getString("activo"),});
					llenaTablaPdf(datos);	
					llenaDatosCsv(datos,indice);
					indice++;
				}
				table.setModel(modelo);
				if (table.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "Proveedores no encontrados para esta selecci�n");
				}
				rs.close();
				connection.close();
		} catch (Exception e1) {System.out.println(e1);}
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(17, 75, 800, 800);
		frame.getContentPane().add(scrollPane);
	
	}

	private EmpresasDTO llenaParamConsulta() {
		EmpresasDTO salida = new EmpresasDTO();
		salida.setActivo(sesionGlobal.getChar1());
		salida.setPoblacion(sesionGlobal.getChar2());
		salida.setEmpresa(sesionGlobal.getInt1());	
		salida.setIdEmpresa(sesionGlobal.getInt2());
		return salida;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Listado de proveedores de la empresa " + nombre);
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
				EmpresasDTO paramConsulta = new EmpresasDTO();
				JComboBox poblacion = new JComboBox();
				ArrayList<String> cadena = accProveedor.consultaPoblacion(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (int i = 0; i < cadena.size(); i++) {
						poblacion.addItem(cadena.get(i));
					}
					poblacion.addItem("Todos");
				}
				JComboBox activo = new JComboBox();
				activo.addItem("S�");
				activo.addItem("No");
				activo.addItem("Todos");
				Object [] mensaje= {
						"Poblaci�n:", poblacion,
						"Activo:", activo
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "B�squeda de proveedor", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
						String temporal = (String) poblacion.getSelectedItem();
						paramConsulta.setPoblacion(temporal);
						temporal = (String) activo.getSelectedItem();
						if (temporal == "S�") {paramConsulta.setActivo("S");}
						if (temporal == "No") {paramConsulta.setActivo("N");}
						if (temporal == "Todos") {paramConsulta.setActivo("Todos");}
						paramConsulta.setIdEmpresa(sesionGlobal.getIdEmpresa());
						guardaConsulta(paramConsulta);
						llenaListado(paramConsulta);
				}
			}

		});

		btnBuscar.setBounds(142, 21, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		JButton btnExportarAPdf = new JButton("Exportar a PDF");
		btnExportarAPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creaPdf(tablaPdf);
			}
		});
		btnExportarAPdf.setBounds(241, 21, 134, 23);
		frame.getContentPane().add(btnExportarAPdf);
		
		JButton btnExportarExcel = new JButton("Exportar a Excel");
		btnExportarExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCsv();
			}
		});
		btnExportarExcel.setBounds(385, 21, 134, 23);
		frame.getContentPane().add(btnExportarExcel);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sesionGlobal.setIdentificador(0);
				sesionGlobal.setNoPrincipal("S");
				frame.dispose(); // esto cierra la ventana
				VentanaProveedores ventana = new VentanaProveedores(sesionGlobal);
			}
		});
		btnAlta.setBounds(42, 21, 89, 23);
		frame.getContentPane().add(btnAlta);
		
	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	protected void guardaConsulta(EmpresasDTO paramConsulta) {
		sesionGlobal.setChar1(paramConsulta.getActivo());
		sesionGlobal.setChar2(paramConsulta.getPoblacion());
		sesionGlobal.setInt1(paramConsulta.getEmpresa());
		sesionGlobal.setInt2(paramConsulta.getIdEmpresa());
	}
	
	private void creaPdf(Table tabla) {
		String nomFichero = JOptionPane.showInputDialog("Escribe el nombre del fichero a generar");
		PdfDocument pdf=null;
		try {
			pdf = new PdfDocument(new PdfWriter("pdf/proveedores/"+nomFichero+".pdf"));
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
		String nomFichero = ("csv/proveedores/"+JOptionPane.showInputDialog("Escribe el nombre del fichero a generar")+".csv");
		try {
			CSVWriter csv = new CSVWriter(new FileWriter(nomFichero));
			String [] cabecera = {"Clave", "Nombre", "Cif", "Direcci�n","Poblaci�n","C�digo Postal","Tel�fono","Persona de Contacto","Correo electr�nico","P�gina Web","Activo"};
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
		tablaPdf.addHeaderCell("Nombre");
		tablaPdf.addHeaderCell("CIF");
		tablaPdf.addHeaderCell("Direccion");
		tablaPdf.addHeaderCell("Poblacion");
		tablaPdf.addHeaderCell("C�digo Postal");
		tablaPdf.addHeaderCell("Tel�fono");
		tablaPdf.addHeaderCell("Persona de contacto");
		tablaPdf.addHeaderCell("Correo electr�nico");
		tablaPdf.addHeaderCell("P�gina web");
		tablaPdf.addHeaderCell("Activo");
		
		return tablaPdf;
	}	
	
	private void llenaTablaPdf(DatosClientes datos) {
		tablaPdf.addCell(String.valueOf(datos.getIdentificador()));
		tablaPdf.addCell(datos.getNombre());
		tablaPdf.addCell(datos.getCif());
		tablaPdf.addCell(datos.getDireccion());
		tablaPdf.addCell(datos.getPoblacion());
		tablaPdf.addCell(String.valueOf(datos.getcPostal()));
		tablaPdf.addCell(String.valueOf(datos.getTelefono()));
		tablaPdf.addCell(datos.getPersonaContacto());
		tablaPdf.addCell(datos.getMail());
		tablaPdf.addCell(datos.getWeb());
		tablaPdf.addCell(datos.getActivo());
	}
	
	private DatosClientes llenaJtable(ResultSet rs) {
		datos = new DatosClientes();
		try {
			datos.setActivo(rs.getString("activo"));
			datos.setCif(rs.getString("CIF"));
			datos.setcPostal(rs.getInt("CP"));
			datos.setDireccion(rs.getString("Direccion"));
			datos.setIdentificador(rs.getInt("empresa"));
			datos.setMail(rs.getString("mail"));
			datos.setNombre(rs.getString("Nombre"));
			datos.setPersonaContacto(rs.getString("Persona_contacto"));
			datos.setPoblacion(rs.getString("Poblacion"));
			datos.setTelefono(rs.getInt("telefono1"));
			datos.setWeb(rs.getString("web"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}

	private void llenaDatosCsv(DatosClientes datos, int indice) {
		String[] dato = {String.valueOf(datos.getIdentificador()),datos.getNombre(),datos.getCif(),datos.getDireccion(),datos.getPoblacion(),String.valueOf(datos.getcPostal()),
				String.valueOf(datos.getTelefono()),datos.getPersonaContacto(),datos.getMail(),datos.getWeb(),datos.getActivo()};
		datosCsv.add(indice,dato);
	}	
}
