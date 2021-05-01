package plantillas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import acciones.controller.imprimir;
import acciones.dto.ClientesDTO;
import acciones.dto.ConceptosDTO;
import acciones.dto.EmpresasDTO;
import acciones.dto.FacturasDTO;
import acciones.dto.ProyectosDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesClientesImpl;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesConstantesImpl;
import acciones.service.impl.AccionesEmpresasImpl;
import acciones.service.impl.AccionesFacturaImpl;
import acciones.service.impl.AccionesProyectosImpl;

public class FacturaEmitida extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblFactura;
	static ServiceDTO sesionGlobal;
	EmpresasDTO empresa;
	FacturasDTO factura;
	ClientesDTO cliente;
	AccionesEmpresasImpl accEmpresas = new AccionesEmpresasImpl();
	AccionesClientesImpl accClientes = new AccionesClientesImpl();
	AccionesFacturaImpl accFacturas = new AccionesFacturaImpl();
	AccionesProyectosImpl accProyestos = new AccionesProyectosImpl();
	AccionesConceptosImpl accConceptos = new AccionesConceptosImpl();
	AccionesConstantesImpl accConstantes = new AccionesConstantesImpl();
	
	private JTextField textEmpresa;
	private JTextField textDireccion;
	private JTextField textCiudad;
	private JTextField textProvincia;
	private JTextField textTelefono;
	private JTextField textMovil;
	private JTextField textMail;
	private JPanel panel_1;
	private JTextField textCliente;
	private JTextField textDireccionCli;
	private JTextField textPoblacionCli;
	private JTextField textNifCli;
	private JTextField textFactura;
	private JTextField textFechaFactura;
	private JTextField textObra;
	private JTextField textMercantil;
	private JTextField textNif;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	public FacturaEmitida(ServiceDTO control) {
		sesionGlobal = control;
		final JFrame frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(0,10,1000,900);
		EmpresasDTO empresa = new EmpresasDTO();
		cliente = new ClientesDTO();
		factura = new FacturasDTO();
		ProyectosDTO proyecto = new ProyectosDTO();
		
		
		empresa=obtenerDatosEmpresa(sesionGlobal.getIdEmpresa());
		cliente=obtenerDatosCliente(sesionGlobal.getInt5());
		factura=obtenerDatosFactura(sesionGlobal.getIdentificador());
		proyecto=obtenerDatosProyecto(factura.getProyecto());
		
		panel = new JPanel();
		panel.setBounds(0, 11, 459, 750);
		panel.setLayout(null);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mandarImprimir(panel);
			}
		});
		btnImprimir.setBounds(540, 346, 141, 21);
		
		frame.getContentPane().add(panel);
		
		textDireccion = new JTextField();
		textDireccion.setText((String) null);
		textDireccion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textDireccion.setColumns(10);
		textDireccion.setBounds(0, 40, 231, 20);
		textDireccion.setText("C/"+empresa.getDireccion()+"");
		panel.add(textDireccion);
		
		textCiudad = new JTextField();
		textCiudad.setText((String) null);
		textCiudad.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textCiudad.setColumns(10);
		textCiudad.setBounds(0, 63, 231, 20);
		textCiudad.setText(String.valueOf(empresa.getCp())+"-"+empresa.getPoblacion());
		panel.add(textCiudad);
		
		textProvincia = new JTextField();
		textProvincia.setText((String) null);
		textProvincia.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textProvincia.setColumns(10);
		textProvincia.setBounds(0, 86, 231, 20);
		textProvincia.setText(empresa.getProvincia());
		panel.add(textProvincia);
		
		JPanel panel_empresa = new JPanel();
		panel_empresa.setBackground(Color.LIGHT_GRAY);
		panel_empresa.setBounds(0, 0, 449, 110);
		panel.add(panel_empresa);
		panel_empresa.setLayout(null);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(240, 40, 209, 20);
		panel_empresa.add(textTelefono);
		textTelefono.setText((String) null);
		textTelefono.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textTelefono.setColumns(10);
		textTelefono.setText("Teléfono : "+String.valueOf(empresa.getTelefono1())+"");
		
		textEmpresa = new JTextField();
		textEmpresa.setBounds(0, 3, 449, 33);
		panel_empresa.add(textEmpresa);
		textEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textEmpresa.setColumns(10);
		textEmpresa.setText(empresa.getNombre());
		
		textMovil = new JTextField();
		textMovil.setBounds(240, 63, 209, 20);
		panel_empresa.add(textMovil);
		textMovil.setText((String) null);
		textMovil.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textMovil.setColumns(10);
		textMovil.setText("Teléfono auxiliar : "+String.valueOf(empresa.getTelefono2())+"");
		
		textMail = new JTextField();
		textMail.setBounds(240, 86, 209, 20);
		panel_empresa.add(textMail);
		textMail.setText((String) null);
		textMail.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textMail.setColumns(10);
		textMail.setText("Correo electrónico : "+empresa.getMail()+"");
		
		panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), null));
		panel_1.setBounds(161, 117, 289, 115);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textCliente = new JTextField();
		textCliente.setBounds(10, 11, 274, 20);
		panel_1.add(textCliente);
		textCliente.setText(cliente.getNombre());
		textCliente.setColumns(10);
		
		textDireccionCli = new JTextField();
		textDireccionCli.setColumns(10);
		textDireccionCli.setBounds(10, 36, 274, 20);
		textDireccionCli.setText("C/ "+cliente.getDireccion()+"");
		panel_1.add(textDireccionCli);
		
		textPoblacionCli = new JTextField();
		textPoblacionCli.setColumns(10);
		textPoblacionCli.setBounds(10, 61, 274, 20);
		textPoblacionCli.setText(cliente.getCp()+" - "+cliente.getPoblacion());
		panel_1.add(textPoblacionCli);
		
		textNifCli = new JTextField();
		textNifCli.setColumns(10);
		textNifCli.setBounds(10, 84, 274, 20);
		textNifCli.setText("NIF "+cliente.getNif()+"");
		panel_1.add(textNifCli);
		
		textFactura = new JTextField();
		textFactura.setColumns(10);
		textFactura.setBounds(0, 117, 141, 20);
		textFactura.setText("Número : "+factura.getIdFactura()+"");
		panel.add(textFactura);
		
		textFechaFactura = new JTextField();
		textFechaFactura.setColumns(10);
		textFechaFactura.setBounds(0, 143, 141, 20);
		String fecha = String.valueOf(factura.getFecha());
		char[] digitos = fecha.toCharArray();
		textFechaFactura.setText("Fecha : "+digitos[6]+digitos[7]+"/"+digitos[4]+digitos[5]+"/"+digitos[0]+digitos[1]+digitos[2]+digitos[3]+"");
		panel.add(textFechaFactura);
		
		JLabel lblNewLabel = new JLabel("FACTURA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(0, 174, 99, 29);
		panel.add(lblNewLabel);
		
		textObra = new JTextField();
		textObra.setColumns(10);
		textObra.setBounds(0, 238, 449, 20);
		textObra.setText("Obra : "+proyecto.getNombre()+"");
		panel.add(textObra);
		
		textNif = new JTextField();
		textNif.setBounds(0, 673, 449, 20);
		panel.add(textNif);
		textNif.setText("NIF : "+empresa.getNif()+"");
		textNif.setColumns(10);
		
		llenaTextoFijo();
		
		
		Double total = llenaImportes();
		llenaPago(total);
				
		JTextArea textDescripcion = new JTextArea();
		textDescripcion.setBounds(0, 263, 449, 60);
		textDescripcion.append("Descripción");
		textDescripcion.append(System.getProperty("line.separator"));
		textDescripcion.append(proyecto.getDescripcion());
		panel.add(textDescripcion);
		
		llenaConceptos();
		
		
		frame.getContentPane().add(btnImprimir);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnVolver.setBounds(540, 394, 141, 21);
		frame.getContentPane().add(btnVolver);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	private Double llenaImportes() {
		//me obliga a definir la columna y a entrar los títulos como row. No sé pq
		DefaultTableModel modelo2 = new DefaultTableModel();

		modelo2.addColumn("Base Imponible");
		modelo2.addColumn("IVA");
		modelo2.addColumn("Total Factura");
		String [] indetificador2 = new String[5];
		indetificador2[0] = "Base Imponible";
		indetificador2[1] = "IVA";
		indetificador2[2] = "Total Factura";
		int ind = 3;
		Double importeIRPF = (double) 0;
		Double importeTasa = (double) 0;
		if (factura.getIrpf()>0) {
			modelo2.addColumn("IRPF");
			indetificador2[ind] = "IRPF";
			ind++;
			importeIRPF = factura.getIrpf();
		}
		if (factura.getTasa()>0) {
			modelo2.addColumn("Tasa");
			indetificador2[ind] = "Tasa";
			ind++;
			importeTasa = factura.getTasa();
		}
		
		Double importeIva = factura.getBaseImpo() * (factura.getIva()/100);
		Double importeTotal = factura.getBaseImpo() + importeIva + importeIRPF + importeTasa;
		Double importePagar = importeTotal + importeIva + importeIRPF + importeTasa;
		modelo2.addRow(indetificador2);
		
		Double valores[] = new Double[5];
		valores[0] = (double) Math.round((factura.getBaseImpo()*100.0)/100.0);
		valores[1] = (double) Math.round((importeIva*100.0)/100.0);
		valores[2] = (double) Math.round((importeTotal*100.0)/100.0);
		ind=3;
		if (factura.getIrpf()>0) {
			valores[ind] = (double) Math.round((importeIRPF*100.0)/100.0);
			ind++;
		}
		if (factura.getTasa()>0) {
			valores[ind] = (double) Math.round((importeTasa*100.0)/100.0);
			ind++;
		}
		modelo2.addRow(valores);
		
		table_1 = new JTable(modelo2);
		//table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		resizeColumnWidth(table_1);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setBounds(0, 506, 449, 36);
		panel.add(table_1);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		return importePagar;
			
	}
	
	private void resizeColumnWidth(JTable table) {
	    //Se obtiene el modelo de la columna
	    TableColumnModel columnModel = table.getColumnModel();
	    //Se obtiene el total de las columnas
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        //Establecemos un valor minimo para el ancho de la columna
	        int width = 150; //Min Width
	        //Obtenemos el numero de filas de la tabla
	        for (int row = 0; row < table.getRowCount(); row++) {
	            //Obtenemos el renderizador de la tabla
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            //Creamos un objeto para preparar el renderer
	            Component comp = table.prepareRenderer(renderer, row, column);
	            //Establecemos el width segun el valor maximo del ancho de la columna
	            width = Math.max(comp.getPreferredSize().width + 1, width);

	        }
	        //Se establece una condicion para no sobrepasar el valor de 300
	        //Esto es Opcional
	        if (width > 300) {
	            width = 300;
	        }
	        //Se establece el ancho de la columna
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}

	private void llenaPago(Double total) {
		
		DefaultTableModel modelo = new DefaultTableModel();
		Object [] identificador1 = new Object[]{"Numero de cuenta", "Forma de pago", "Vencimiento", "TOTAL a pagar"};
		modelo.setColumnIdentifiers(identificador1);
		String fecha = String.valueOf(factura.getVencimiento());
		char[] digitos = fecha.toCharArray();
		String vencimiento = ""+digitos[6]+digitos[7]+"/"+digitos[4]+digitos[5]+"/"+digitos[0]+digitos[1]+digitos[2]+digitos[3];
		modelo.addRow(identificador1);
		String modoPago = accConstantes.nombreConstante("MODP", Integer.valueOf(cliente.getFp()));
		modelo.addRow(new Object [] {empresa.getIban(),modoPago,vencimiento,(double) Math.round((total*100.0)/100.0)});
		table = new JTable(modelo);
		table.setModel(modelo);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(0, 542, 449, 36);
		
		panel.add(table);
		
	}

	private void llenaConceptos() {
		ArrayList<ConceptosDTO> concepto = new ArrayList();
		concepto=obtenerConceptosProyecto(factura.getProyecto(),sesionGlobal.getIdEmpresa());

		DefaultTableModel modelo3 = new DefaultTableModel();
		Object[] identificador3 = new Object[]{"Concepto", "Importe"};
		modelo3.setColumnIdentifiers(identificador3);
		modelo3.addRow(identificador3);
		if (concepto != null) {
			Double importe=null;
			for (int i = 0; i < concepto.size(); i++) {
				importe = (double) Math.round((concepto.get(i).getImporte()*100.0)/100.0);
				modelo3.addRow(new Object[] {concepto.get(i).getDescripcion(), importe});
			}
		}
		
		table_2 = new JTable(modelo3);
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.setBounds(0, 334, 449, 161);
		panel.add(table_2);
		
		
	}

	private void llenaTextoFijo() {
		
		JTextArea textMerca = new JTextArea();
		textMerca.setFont(new Font("Monospaced", Font.PLAIN, 6));
		textMerca.setBounds(0, 582, 455, 86);
		textMerca.append("De conformidad con lo que establece la Ley Orgánica 15/1999 de Protección de Datos de Carácter Personal, le");
		textMerca.append(System.getProperty("line.separator"));		
		textMerca.append("informamos que sus datos personales serán incluidos dentre de un fichero automatizado bajo responsabilidad de" );
		textMerca.append(System.getProperty("line.separator"));
		textMerca.append(empresa.getNombre()+", con la finalidad de poder atender los compromisos derivados de la");
		textMerca.append(System.getProperty("line.separator"));
		textMerca.append("relación que mantenemos con usted. Puede ejercer sus derechos de acceso, cancleación, rectificación y oposición");
		textMerca.append(System.getProperty("line.separator"));
		textMerca.append("mediante un escrito a la dirección "+empresa.getDireccion()+".");
		textMerca.append(System.getProperty("line.separator"));
		textMerca.append("Si en el plazo de 30 días no nos comunica lo contrario, entenderemos que los datos no han sido modificados, que");
		textMerca.append(System.getProperty("line.separator"));
		textMerca.append("se compremete a notificarnos cualquier variación y que tenemos el consentimiento para utilizarlos a fin de poder");
		textMerca.append(System.getProperty("line.separator"));
		textMerca.append("fidelizar la relación entre partes.");
		textMerca.append(System.getProperty("line.separator"));
		textMerca.append(System.getProperty("line.separator"));
		textMerca.append("Inscrita en el Registro Mercantil de "+empresa.getRegMercantil()+" Tomo "+empresa.getTomo()+", Folio "+empresa.getFolio()+" Hoja "+empresa.getHoja()+", Inscripción "+empresa.getInscripcion()+"");
		panel.add(textMerca);
		
	}

	private ArrayList<ConceptosDTO> obtenerConceptosProyecto(Integer proyecto, Integer empresa) {
		ArrayList<ConceptosDTO> salida = accConceptos.buscaConceptos(proyecto,empresa);
		return salida;
	}

	private ProyectosDTO obtenerDatosProyecto(Integer proyecto) {
		ProyectosDTO salida = new ProyectosDTO();
		salida = accProyestos.buscaProyecto(proyecto, sesionGlobal.getIdEmpresa());
		return salida;
	}

	protected void mandarImprimir(JPanel panel2) {
		PrinterJob	tarea = PrinterJob.getPrinterJob();
		PageFormat format = tarea.pageDialog(tarea.defaultPage());
		tarea.setPrintable(new imprimir(panel2),format);
		if (tarea.printDialog()) {
			try {						
				tarea.print();
			} catch (Exception PrinterException) {
				PrinterException.printStackTrace();}
		}
		
	}

	private FacturasDTO obtenerDatosFactura(Integer int1) {
		FacturasDTO factura = new FacturasDTO();
		factura = accFacturas.buscaFactura(int1,sesionGlobal.getIdEmpresa());
		return factura;	
	}

	private ClientesDTO obtenerDatosCliente(Integer int2) {
		ClientesDTO cliente = new ClientesDTO();
		cliente = accClientes.buscaCliente(int2, sesionGlobal.getIdEmpresa());
		return cliente;
		
	}

	private EmpresasDTO obtenerDatosEmpresa(Integer idEmpresa) {
		empresa = new EmpresasDTO();
		empresa.setIdEmpresa(idEmpresa);
		empresa.setTipo("E");
		empresa = accEmpresas.buscaEmpresaId(empresa);
		return empresa;
	}
}
