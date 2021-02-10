package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import acciones.service.impl.AccionesClientesImpl;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesCostesImpl;
import acciones.service.impl.AccionesEmpresasImpl;
import acciones.service.impl.AccionesFacturaImpl;
import acciones.service.impl.AccionesProyectosImpl;
import acciones.service.impl.AccionesServiceImpl;
import estructuras.DatosFacturas;

public class VentanaIva {

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
	Color bg = new Color (192,192,192);
	DefaultTableModel modelo = new DefaultTableModel();
	private JTextField textIVAE, textIVAR, textBaseE, textBaseR;
	private Double sumaImporteE, sumaImporteR, sumaIVAE, sumaIVAR,varAux;
	/**
	 * Create the application.
	 */
	public VentanaIva(ServiceDTO control) {
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
		btnVolver.setBounds(159, 21, 89, 23);
		frame.getContentPane().add(btnVolver);
				
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacturasDTO paramConsulta = new FacturasDTO();
				JComboBox mes = llenaMeses();
				JComboBox anyo = llenaAnyos();
				Object [] mensaje= {
						"mes:", mes,
						"año:", anyo
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Búsqueda de Factura", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
					//las facturas emitidas no tienen proveedor
					paramConsulta.setAnyo((Integer) anyo.getSelectedItem());
					ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
					temporal = (ObjetoJComboBox) mes.getSelectedItem();
					paramConsulta.setMes(temporal.getNumero());
					paramConsulta.setIdEmpresa(sesionGlobal.getIdEmpresa());
					llenaListadoE(paramConsulta);
					llenaListadoR(paramConsulta);
					
				}
			}

		});

		btnBuscar.setBounds(42, 21, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
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
	
	private JComboBox llenaMeses() {
		JComboBox salida = new JComboBox();
		String[] meses = {"Enero", "Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Setiembre","Octubre","Noviembre","Diciembre"};
		
		for (int indice =0; indice < 12; indice ++) {
			ObjetoJComboBox temporal = new ObjetoJComboBox(indice+1,meses[indice]);
			salida.addItem(temporal);
		}
		
		return salida;
	}
	
	private DatosFacturas llenaJtable(ResultSet rs) {
		datosFact = new DatosFacturas();
		try {
			datosFact.setProyecto(accProyecto.buscaDescripcion(rs.getInt("id_proyecto"),sesionGlobal.getIdEmpresa()));
			datosFact.setConcepto(accConcepto.buscaDescripcion(rs.getInt("id_concepto")));
			datosFact.setCoste(accCostes.buscaDescripcion(rs.getInt("id_coste")));
			datosFact.setFecha(String.valueOf(rs.getInt("fecha")));
			datosFact.setFactura(String.valueOf(rs.getInt("id_factura")));
			datosFact.setVencimiento(String.valueOf(rs.getInt("vencimiento")));
			datosFact.setEstado(rs.getString("pagado"));
			datosFact.setBaseImpo(rs.getDouble("base_impo"));
			datosFact.setIva(rs.getDouble("IVA"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datosFact;
	}
	
	private void llenaListadoE(FacturasDTO paramConsulta) {
		//array con tantas filas cómo columnas queramos en el listado
		Connection connection = accService.getConexion();
		DefaultTableModel modelo = new DefaultTableModel();
		String consulta;
		consulta=accFactura.creaConsultaIvaE(paramConsulta);
												
		ResultSet rs = accService.getTabla(consulta, connection);
		modelo.setColumnIdentifiers(new Object[]{"identificador","Fecha","Vencimiento","Proyecto","Cliente","Estado","Base Imponible","IVA"});
		JTable table = new JTable(modelo);
		sumaImporteE = (double) 0;
		sumaIVAE = (double) 0;
		varAux = (double) 0;
		try {
				while (rs.next()) {
					datosFact = llenaJtable(rs);
					datosFact.setNombreCliente(accClientes.buscaNombre(rs.getInt("id_cliente")));
					modelo.addRow(new Object[] {rs.getInt("id_factura"),rs.getInt("fecha"), rs.getInt("vencimiento"), datosFact.getProyecto(), datosFact.getNombreCliente(), datosFact.getEstado()
							,rs.getDouble("base_impo"),rs.getDouble("IVA")});
					sumaImporteE = sumaImporteE + datosFact.getBaseImpo();
					varAux = (datosFact.getBaseImpo() * datosFact.getIva())/100;
					sumaIVAE = sumaIVAE + varAux;
				}
				table.setModel(modelo);
				if (table.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "Facturas no encontradas para esta selección");
				}else {
					sumaImporteE = Math.round(sumaImporteE*100.0)/100.0; //redondea a dos decimales
					sumaIVAE = Math.round(sumaIVAE*100.0)/100.0; //redondea a dos decimales
				}
				rs.close();
				connection.close();
		} catch (Exception e1) {System.out.println(e1);}
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(17, 75, 600, 500);
		frame.getContentPane().add(scrollPane);		
		
		JTextField lblBaseE = new JTextField("Total Emitidas:");
		lblBaseE.setBackground(bg);
		lblBaseE.setBounds(17, 580, 96, 20);
		
		frame.getContentPane().add(lblBaseE);
		JTextField lblIVAE = new JTextField("Total IVA:");
		lblIVAE.setBackground(bg);
		lblIVAE.setBounds(17, 600, 96, 20);
		frame.getContentPane().add(lblIVAE);
		
		textBaseE = new JTextField();
		textBaseE.setText(String.valueOf(sumaImporteE));
		textBaseE.setBounds(117, 580, 96, 20);
		frame.getContentPane().add(textBaseE);
		
		textIVAE = new JTextField();
		textIVAE.setText(String.valueOf(sumaIVAE));
		textIVAE.setBounds(117, 600, 96, 20);
		frame.getContentPane().add(textIVAE);
	}

	private void llenaListadoR(FacturasDTO paramConsulta) {
		//array con tantas filas cómo columnas queramos en el listado
		Connection connection = accService.getConexion();
		DefaultTableModel modelo = new DefaultTableModel();
		String consulta;
		consulta=accFactura.creaConsultaIvaR(paramConsulta);
												
		ResultSet rs = accService.getTabla(consulta, connection);
		modelo.setColumnIdentifiers(new Object[]{"identificador","Fecha","Vencimiento","Proyecto","Proveedor","Estado","Base Imponible","IVA"});
		JTable table = new JTable(modelo);
		sumaImporteR = (double) 0;
		sumaIVAR = (double) 0;
		varAux = (double) 0;
		try {
				while (rs.next()) {
					datosFact = llenaJtable(rs);
					datosFact.setProveedor(accProveedor.buscaNombre(rs.getInt("id_proveedor"), "P"));
					modelo.addRow(new Object[] {rs.getInt("id_factura"),rs.getInt("fecha"), rs.getInt("vencimiento"), datosFact.getProyecto(), datosFact.getNombreCliente(), datosFact.getEstado()
							,rs.getDouble("base_impo"),rs.getDouble("IVA")});
					sumaImporteR = sumaImporteR + rs.getDouble("base_impo");
					varAux = (datosFact.getBaseImpo() * datosFact.getIva())/100;
					sumaIVAR = sumaIVAR + varAux;
				}
				table.setModel(modelo);
				if (table.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "Facturas no encontradas para esta selección");
				}else {
					sumaImporteR = Math.round(sumaImporteR*100.0)/100.0; //redondea a dos decimales 
					sumaIVAR = Math.round(sumaIVAR*100.0)/100.0; //redondea a dos decimales
				}
				rs.close();
				connection.close();
		} catch (Exception e1) {System.out.println(e1);}
		

		JTextField lblBaseR = new JTextField("Total Recibidas:");
		lblBaseR.setBackground(bg);
		lblBaseR.setBounds(700, 580, 96, 20);
		frame.getContentPane().add(lblBaseR);
		
		textBaseR = new JTextField();
		textBaseR.setText(String.valueOf(sumaImporteR));
		textBaseR.setBounds(800, 580, 96, 20);
		frame.getContentPane().add(textBaseR);
		
		JTextField lblIVAR = new JTextField("Total IVA:");
		lblIVAR.setBackground(bg);
		lblIVAR.setBounds(700, 600, 96, 20);
		frame.getContentPane().add(lblIVAR);
		
		textIVAR = new JTextField();
		textIVAR.setBounds(800, 600, 96, 20);
		textIVAR.setText(String.valueOf(sumaIVAR));
		frame.getContentPane().add(textIVAR);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(700, 75, 600, 500);
		frame.getContentPane().add(scrollPane);
		
	}

}	

