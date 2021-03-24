package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import acciones.dto.ClientesDTO;
import acciones.dto.FacturasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesClientesImpl;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesCostesImpl;
import acciones.service.impl.AccionesEmpresasImpl;
import acciones.service.impl.AccionesFacturaImpl;
import acciones.service.impl.AccionesProyectosImpl;
import plantillas.FacturaEmitida;

public class VentanaFacturasE extends JFrame {

	private JFrame frame;
	private JDateChooser textVencimiento;
	private JTextField textIrpf;
	private JTextField textDescuento;
	private JTextField textIban;
	private JTextField textTasa;
	private JTextField textBaseImponible;
	private JTextField textIva;
	private JDateChooser textFecha;
	
	ClientesDTO cliente;

	Boolean accion = false;
	static ServiceDTO sesionGlobal;
	private int idFactura;
	private FacturasDTO factura;
	private JComboBox comboProyecto;
	private JComboBox comboCliente;
	private JComboBox comboConcepto;
	private JComboBox comboCoste;
	private JComboBox comboPagado;
	AccionesCostesImpl accCostes = new AccionesCostesImpl();
	AccionesConceptosImpl accConceptos = new AccionesConceptosImpl();
	AccionesEmpresasImpl accEmpresas = new AccionesEmpresasImpl();
	AccionesFacturaImpl accFactura = new AccionesFacturaImpl();
	AccionesProyectosImpl accProyecto = new AccionesProyectosImpl();
	AccionesClientesImpl accClientes = new AccionesClientesImpl();
	/**
	 * Create the application.
	 */
	public VentanaFacturasE(ServiceDTO control) {
		sesionGlobal = control;
		initialize(sesionGlobal.getNombreEmpresa());
		if ((sesionGlobal.getNoPrincipal()=="S" &&(sesionGlobal.getIdentificador()!=0))) {
			llenaPantalla();
		}
	}

	private void llenaPantalla() {
		// TODO Auto-generated method stub
		factura = new FacturasDTO();
		factura=accFactura.buscaFactura(sesionGlobal.getIdentificador(),sesionGlobal.getIdEmpresa());
		llenaCamposPantalla(factura);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Facturas emitidas de la empresa " + nombre);
		frame.setBounds(100, 100, 450, 485);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(10, 42, 48, 14);
		frame.getContentPane().add(lblFecha);
		
		JLabel lblVencimiento = new JLabel("Vencimiento");
		lblVencimiento.setBounds(10, 70, 75, 14);
		frame.getContentPane().add(lblVencimiento);
		
		textFecha = new JDateChooser();
		textFecha.setBounds(128, 36, 96, 20);
		frame.getContentPane().add(textFecha);
		
		textVencimiento = new JDateChooser();
		textVencimiento.setBounds(128, 64, 96, 20);
		frame.getContentPane().add(textVencimiento);
		
		JLabel lblProyecto = new JLabel("Proyecto");
		lblProyecto.setBounds(10, 101, 96, 14);
		frame.getContentPane().add(lblProyecto);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 129, 48, 14);
		frame.getContentPane().add(lblCliente);
		
		comboProyecto = new JComboBox();
		comboProyecto.setBounds(128, 95, 96, 22);
		frame.getContentPane().add(comboProyecto);
		comboProyecto.addItem("----");
		
		ArrayList<ObjetoJComboBox> proyectos = accProyecto.consultaProyectos(sesionGlobal.getIdEmpresa());
		
		if (proyectos != null) {
			for (int i = 0; i < proyectos.size(); i++) {
				comboProyecto.addItem(proyectos.get(i));
			}
		}
		
		comboCliente = new JComboBox();
		comboCliente.setBounds(128, 122, 96, 22);
		frame.getContentPane().add(comboCliente);
		comboCliente.addItem("----");
		
		ArrayList<ObjetoJComboBox> clientes = accClientes.consultaClientes(sesionGlobal.getIdEmpresa());
		
		if (clientes != null) {
			for (int i = 0; i < clientes.size(); i++) {
				comboCliente.addItem(clientes.get(i));
			}
		}
		
		comboConcepto = new JComboBox();
		comboConcepto.setBounds(128, 150, 96, 22);
		frame.getContentPane().add(comboConcepto);
		comboConcepto.addItem("----");
		ArrayList<ObjetoJComboBox> conceptos = accConceptos.consultaConceptos(sesionGlobal.getIdEmpresa());
		
		if (conceptos != null) {
			for (int i = 0; i < conceptos.size(); i++) {
				comboConcepto.addItem(conceptos.get(i));
			}
		}
	
		comboCoste = new JComboBox();
		comboCoste.setBounds(128, 176, 96, 22);
		frame.getContentPane().add(comboCoste);
		comboCoste.addItem("----");
		
		ArrayList<ObjetoJComboBox> costes = accCostes.consultaCostes(sesionGlobal.getIdEmpresa());
	
		if (costes != null) {
			for (int i = 0; i < costes.size(); i++) {
				comboCoste.addItem(costes.get(i));
			}
		}
		
			
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(10, 157, 96, 14);
		frame.getContentPane().add(lblConcepto);
		
		JLabel lblCoste = new JLabel("Coste");
		lblCoste.setBounds(10, 182, 48, 14);
		frame.getContentPane().add(lblCoste);
		
		JLabel lblIrpf = new JLabel("IRPF");
		lblIrpf.setBounds(10, 215, 48, 14);
		frame.getContentPane().add(lblIrpf);
		
		textIrpf = new JTextField();
		textIrpf.setColumns(10);
		textIrpf.setBounds(128, 209, 96, 20);
		frame.getContentPane().add(textIrpf);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setBounds(10, 245, 75, 14);
		frame.getContentPane().add(lblDescuento);
		
		textDescuento = new JTextField();
		textDescuento.setColumns(10);
		textDescuento.setBounds(128, 239, 96, 20);
		frame.getContentPane().add(textDescuento);
		
		JLabel lblIban = new JLabel("IBAN");
		lblIban.setBounds(10, 273, 75, 14);
		frame.getContentPane().add(lblIban);
		
		textIban = new JTextField();
		textIban.setColumns(10);
		textIban.setBounds(128, 267, 96, 20);
		frame.getContentPane().add(textIban);
		
		JLabel lblTasa = new JLabel("Tasa");
		lblTasa.setBounds(10, 360, 42, 14);
		frame.getContentPane().add(lblTasa);
		
		textTasa = new JTextField();
		textTasa.setColumns(10);
		textTasa.setBounds(128, 357, 96, 20);
		frame.getContentPane().add(textTasa);
		
		JLabel lblBaseImponible = new JLabel("Base Imponible");
		lblBaseImponible.setBounds(10, 304, 96, 14);
		frame.getContentPane().add(lblBaseImponible);
		
		textBaseImponible = new JTextField();
		textBaseImponible.setColumns(10);
		textBaseImponible.setBounds(128, 298, 96, 20);
		frame.getContentPane().add(textBaseImponible);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(10, 332, 75, 14);
		frame.getContentPane().add(lblIva);
		
		textIva = new JTextField();
		textIva.setColumns(10);
		textIva.setBounds(128, 326, 96, 20);
		frame.getContentPane().add(textIva);
		
		JLabel lblPagado = new JLabel("Pagado");
		lblPagado.setBounds(276, 268, 48, 14);
		frame.getContentPane().add(lblPagado);
		
		comboPagado = new JComboBox();
		comboPagado.setBounds(328, 264, 96, 23);
		frame.getContentPane().add(comboPagado);
		comboPagado.addItem("----");
		comboPagado.addItem("Abono");
		comboPagado.addItem("Pendiente");
		comboPagado.addItem("Pagado");
		
		final JLabel lblError = new JLabel("");
		lblError.setBounds(10, 375, 414, 14);
		frame.getContentPane().add(lblError);
		
		JButton btnAltaFactura = new JButton("Alta Factura");
		btnAltaFactura.setBounds(37, 400, 105, 23);
		frame.getContentPane().add(btnAltaFactura);
		btnAltaFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
				//llenamos el DTO de factura
				FacturasDTO entrada = new FacturasDTO();
				entrada = llenaCamposDto();
				//grabamos los datos
				accion= accFactura.grabarFactura(entrada);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Factura dada de alta correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error en el alta de factura");
				}
			}
		});
		
		
		JButton btnBaja = new JButton("Baja Factura");
		btnBaja.setBounds(152, 400, 117, 23);
		btnBaja.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar la factura?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (JOptionPane.OK_OPTION == confirmado) {
				accion = accFactura.deleteFactura(idFactura);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Factura borrada correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error en el borrado de la factura");
				}
			} else System.out.println("vale... no borro nada...");
			}
		});

		frame.getContentPane().add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar Factura");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar la factura?", "Confirmar modificación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					factura = llenaCamposDto();
					accion = accFactura.updateFactura(factura);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Factura modificada correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar la factura");
					}
				} else System.out.println("vale... no hago nada...");
			}
		});

		btnModificar.setBounds(276, 400, 148, 23);
		frame.getContentPane().add(btnModificar);
		

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
				if (sesionGlobal.getNoPrincipal()=="N") {
					VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
				}else {ListadoFacturasE listado = new ListadoFacturasE(sesionGlobal);}
			}
		});
		btnNewButton.setBounds(335, 21, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(433, 375, -422, -7);
		separator.setVisible(true);
		frame.getContentPane().add(separator);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiaPantalla();
			}
		});
		btnLimpiar.setBounds(335, 55, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		

		JButton btnPrint = new JButton("Vista preliminar");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sesionGlobal.setInt1(factura.getCliente());
				FacturaEmitida ventana = new FacturaEmitida(sesionGlobal);
			}
		});
		btnPrint.setBounds(289, 125, 135, 23);
		frame.getContentPane().add(btnPrint);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cliente = new JComboBox();
				ArrayList<ObjetoJComboBox> cadena = accClientes.consultaClientes(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (int i = 0; i < cadena.size(); i++) {
						cliente.addItem(cadena.get(i));
					}
				}
				JDateChooser fecha = new JDateChooser();
				Object [] mensaje= {
						"Cliente:", cliente,
						"Fecha Inicio:", fecha
				};
				//Object opcion = JOptionPane.showInputDialog(null,"Seleccione una opción","",JOptionPane.PLAIN_MESSAGE,null,cliente.toArray(),null);
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Búsqueda de Factura", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
						factura = new FacturasDTO();
						factura.setIdEmpresa(sesionGlobal.getIdEmpresa());
						ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
						temporal = (ObjetoJComboBox) cliente.getSelectedItem();
						factura.setCliente(temporal.getNumero());
						String dia = Integer.toString(fecha.getCalendar().get(Calendar.DAY_OF_MONTH));
						if (fecha.getCalendar().get(Calendar.DAY_OF_MONTH)<10) {
							dia = ("0"+dia);
						}
						String mes = Integer.toString(fecha.getCalendar().get(Calendar.MONTH)+1);
						if (fecha.getCalendar().get(Calendar.MONTH)+1<10) {
							mes = ("0"+mes);
						}
						String ano = Integer.toString(fecha.getCalendar().get(Calendar.YEAR));
						String varFecha = (ano+mes+dia);
						factura.setFecha(Integer.valueOf(varFecha));
						factura = accFactura.buscaFactura(factura);
						if (factura.getIdFactura().equals(0)) {
							JOptionPane.showMessageDialog(null, "Factura no encontrada");	
						} else {
							limpiaPantalla();
							llenaCamposPantalla(factura);
							lblError.setText("");
						}
					
				}				
			
			}
		});
		btnBuscar.setBounds(335, 89, 89, 23);
		frame.getContentPane().add(btnBuscar);
					
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	private void llenaCamposPantalla(FacturasDTO entrada) {
		//llenamos los campos de pantalla con el DTO de clientes
		idFactura = entrada.getIdFactura();
		textBaseImponible.setText(String.valueOf(entrada.getBaseImpo()));
		textDescuento.setText(String.valueOf(entrada.getDescuento()));
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		String fecha = String.valueOf(entrada.getFecha());
		try {
			textFecha.setDate(formato.parse(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textIban.setText(entrada.getIban());
		textIrpf.setText(String.valueOf(entrada.getIrpf()));
		textIva.setText(String.valueOf(entrada.getIva()));
		textTasa.setText(String.valueOf(entrada.getTasa()));
		
		fecha = String.valueOf(entrada.getVencimiento());
		try {
			textVencimiento.setDate(formato.parse(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		/*String valorCombo = accEmpresas.buscaNombre(entrada.getEmpresa(),"E");
		comboEmpresa.getModel().setSelectedItem(valorCombo);*/
		String valorCombo = accClientes.buscaNombre(entrada.getCliente());
		comboCliente.getModel().setSelectedItem(valorCombo);
		valorCombo = accConceptos.buscaDescripcion(entrada.getConcepto());
		comboConcepto.getModel().setSelectedItem(valorCombo);
		valorCombo = accCostes.buscaDescripcion(entrada.getCoste());
		comboCoste.getModel().setSelectedItem(valorCombo);
		valorCombo = accProyecto.buscaDescripcion(entrada.getProyecto(),sesionGlobal.getIdEmpresa());
		comboProyecto.getModel().setSelectedItem(valorCombo);
		comboPagado.getModel().setSelectedItem(entrada.getPagado());
	}
	
	private  FacturasDTO llenaCamposDto(){
		//llenamos el DTO de clientes con los datos de pantalla
		factura = new FacturasDTO();
		//en las facturas emitidas no hay proveedor
		factura.setProveedor(0);
		factura.setBaseImpo(Double.valueOf(textBaseImponible.getText()));
		factura.setDescuento(Double.valueOf(textDescuento.getText()));
		String dia = Integer.toString(textFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
		if (textFecha.getCalendar().get(Calendar.DAY_OF_MONTH)<10) {
			dia = ("0"+dia);
		}
		String mes = Integer.toString(textFecha.getCalendar().get(Calendar.MONTH)+1);
		if (textFecha.getCalendar().get(Calendar.MONTH)+1<10) {
			mes = ("0"+mes);
		}
		String ano = Integer.toString(textFecha.getCalendar().get(Calendar.YEAR));
		String varFecha = (ano+mes+dia);
		factura.setFecha(Integer.valueOf(varFecha));
		
		factura.setIban(textIban.getText());
		factura.setIdFactura(idFactura);
		factura.setIrpf(Double.valueOf(textIrpf.getText()));
		factura.setIva(Double.valueOf(textIva.getText()));
		factura.setTasa(Double.valueOf(textTasa.getText()));
		
		dia = Integer.toString(textVencimiento.getCalendar().get(Calendar.DAY_OF_MONTH));
		if (textVencimiento.getCalendar().get(Calendar.DAY_OF_MONTH)<10) {
			dia = ("0"+dia);
		}
		mes = Integer.toString(textVencimiento.getCalendar().get(Calendar.MONTH)+1);
		if (textVencimiento.getCalendar().get(Calendar.MONTH)+1<10) {
			mes = ("0"+mes);
		}
		ano = Integer.toString(textVencimiento.getCalendar().get(Calendar.YEAR));
		varFecha = (ano+mes+dia);
		factura.setVencimiento(Integer.valueOf(varFecha));				
		
		String variable = (String) comboCliente.getSelectedItem().toString();
		factura.setCliente(accClientes.buscaCliente(variable));
		
		variable = (String) comboConcepto.getSelectedItem().toString();
		factura.setConcepto(accConceptos.buscaConcepto(variable));
		
		variable = (String) comboCoste.getSelectedItem().toString();
		factura.setCoste(accCostes.buscaCoste(variable));
		
		factura.setEmpresa(sesionGlobal.getIdEmpresa());

		variable = (String) comboProyecto.getSelectedItem().toString();
		factura.setProyecto(accProyecto.buscaProyecto(variable,sesionGlobal.getIdEmpresa()));
		
		factura.setPagado(comboPagado.getSelectedItem().toString());
				
		return factura;
	}
	
	private void limpiaPantalla() {
		textBaseImponible.setText(" ");
		textDescuento.setText(" ");
		textFecha.setDate(null);
		textIban.setText(" ");
		textIrpf.setText(" ");
		textIva.setText(" ");
		textTasa.setText(" ");
		textVencimiento.setDate(null);
		comboCliente.getModel().setSelectedItem("----");
		comboConcepto.getModel().setSelectedItem("----");
		comboCoste.getModel().setSelectedItem("----");
		comboProyecto.getModel().setSelectedItem("----");
		comboPagado.getModel().setSelectedItem("----");
	}
}
