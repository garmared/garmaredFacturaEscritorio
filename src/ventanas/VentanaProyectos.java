package ventanas;

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
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import acciones.dto.ObjetoJComboBox;
import acciones.dto.ProyectosDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesClientesImpl;
import acciones.service.impl.AccionesCostesImpl;
import acciones.service.impl.AccionesEmpresasImpl;
import acciones.service.impl.AccionesProyectosImpl;
import acciones.service.impl.AccionesServiceImpl;
import javax.swing.SwingConstants;

public class VentanaProyectos {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private ProyectosDTO proyecto;
	private int idProyecto;
	
	private JDateChooser textFIni;
	private JDateChooser textFFin;
	private JDateChooser textFCierre;
	private JTextField textDescripcion;
	private JTextField textWeb;
	private JTextField textIban;
	private JTextField textObservaciones;
	private JTextField textImporte;
	private JTextField textMargen;

	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JLabel lblFechaCierre;
	private JLabel lblDescripcin;
	private JLabel lblWeb;
	private JLabel lblIban;
	private JLabel lblTipoCoste;
	private JLabel lblCliente;
	private JLabel lblObservaciones;
	private JLabel lblImporte;
	private JLabel lblMargen;
	
	private JComboBox comboCoste;
	private JComboBox comboCliente;
	
	private Boolean accion = false;
	AccionesClientesImpl accClientes = new AccionesClientesImpl();
	AccionesCostesImpl accCostes = new AccionesCostesImpl();
	AccionesEmpresasImpl accEmpresas = new AccionesEmpresasImpl();
	AccionesProyectosImpl accProyectos = new AccionesProyectosImpl();
	AccionesServiceImpl accService = new AccionesServiceImpl();
	private String dia,mes,ano,varFecha,fecha; 
	private SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
	private JButton btnConcepto;
	private JTextField textNombre;
	/**
	 * Create the application.
	 */
	public VentanaProyectos(ServiceDTO control) {
		sesionGlobal = control;
		initialize(control.getNombreEmpresa());
		if ((sesionGlobal.getNoPrincipal()=="S")&&(sesionGlobal.getIdentificador()!=0)) {
			llenaPantalla();
		}
	}

	private void llenaPantalla() {
		// TODO Auto-generated method stub
		proyecto = new ProyectosDTO();
		proyecto=accProyectos.buscaProyecto(sesionGlobal.getIdentificador(),sesionGlobal.getIdEmpresa());
		llenaCamposPantalla(proyecto);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Proyecto de la empresa " + nombre);
		frame.setBounds(100, 100, 575, 498);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setBounds(40, 32, 72, 14);
		frame.getContentPane().add(lblFechaInicio);
		
		textFIni = new JDateChooser();
		textFIni.setBounds(133, 29, 109, 20);
		frame.getContentPane().add(textFIni);
		
		lblFechaFin = new JLabel("Fecha fin");
		lblFechaFin.setBounds(40, 60, 72, 14);
		frame.getContentPane().add(lblFechaFin);
		
		textFFin = new JDateChooser();
		textFFin.setBounds(133, 57, 109, 20);
		frame.getContentPane().add(textFFin);
		
		lblFechaCierre = new JLabel("Fecha cierre");
		lblFechaCierre.setBounds(256, 60, 72, 14);
		frame.getContentPane().add(lblFechaCierre);
		
		textFCierre = new JDateChooser();
		textFCierre.setBounds(333, 57, 113, 20);
		frame.getContentPane().add(textFCierre);
		
		lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(40, 313, 72, 14);
		frame.getContentPane().add(lblDescripcin);
		
		textDescripcion = new JTextField();
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(117, 316, 418, 79);
		frame.getContentPane().add(textDescripcion);
		
		lblWeb = new JLabel("Web");
		lblWeb.setBounds(40, 156, 72, 14);
		frame.getContentPane().add(lblWeb);
		
		textWeb = new JTextField();
		textWeb.setColumns(10);
		textWeb.setBounds(133, 153, 96, 20);
		frame.getContentPane().add(textWeb);
		
		lblIban = new JLabel("IBAN");
		lblIban.setBounds(40, 211, 72, 14);
		frame.getContentPane().add(lblIban);
		
		textIban = new JTextField();
		textIban.setColumns(10);
		textIban.setBounds(133, 208, 96, 20);
		frame.getContentPane().add(textIban);
		
		lblTipoCoste = new JLabel("Tipo Coste");
		lblTipoCoste.setBounds(40, 123, 72, 14);
		frame.getContentPane().add(lblTipoCoste);
		
		comboCoste = new JComboBox();
		comboCoste.setBounds(133, 123, 170, 22);
		frame.getContentPane().add(comboCoste);
		comboCoste.addItem("----");
		
		ArrayList<ObjetoJComboBox> costes = accCostes.consultaCostes(sesionGlobal.getIdEmpresa());
	
		if (costes !=null) {
			for (int i = 0; i < costes.size(); i++) {
				comboCoste.addItem(costes.get(i));
			}
		}
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(40, 274, 48, 14);
		frame.getContentPane().add(lblCliente);
		
		comboCliente = new JComboBox();
		comboCliente.setBounds(133, 270, 170, 22);
		frame.getContentPane().add(comboCliente);
		comboCliente.addItem("----");
		
		ArrayList<ObjetoJComboBox> clientes = accClientes.consultaClientes(sesionGlobal.getIdEmpresa());
			
		if (clientes != null) {
			for (int i = 0; i < clientes.size(); i++) {
				comboCliente.addItem(clientes.get(i));
			}
		}
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(40, 95, 72, 14);
		frame.getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(133, 92, 296, 20);
		frame.getContentPane().add(textNombre);
		
		textObservaciones = new JTextField();
		textObservaciones.setColumns(10);
		textObservaciones.setBounds(133, 184, 96, 20);
		frame.getContentPane().add(textObservaciones);
		
		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(40, 187, 72, 14);
		frame.getContentPane().add(lblObservaciones);
		
		textImporte = new JTextField();
		textImporte.setColumns(10);
		textImporte.setBounds(133, 239, 96, 20);
		frame.getContentPane().add(textImporte);
		
		lblImporte = new JLabel("Importe");
		lblImporte.setBounds(40, 242, 72, 14);
		frame.getContentPane().add(lblImporte);
		
		textMargen = new JTextField();
		textMargen.setColumns(10);
		textMargen.setBounds(350, 239, 96, 20);
		frame.getContentPane().add(textMargen);
		
		lblMargen = new JLabel("Margen");
		lblMargen.setBounds(257, 242, 72, 14);
		frame.getContentPane().add(lblMargen);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
				accService.abrirVentanaPrincipal(sesionGlobal);
				if (sesionGlobal.getNoPrincipal()=="S") {
					ListadoProyectos listado = new ListadoProyectos(sesionGlobal);
				}
			}
		});
		btnVolver.setBounds(460, 29, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnAlta = new JButton("Alta Proyecto");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
				//llenamos el DTO de proyectos
				ProyectosDTO entrada = new ProyectosDTO();
				entrada.setNombre(textNombre.getText());
				entrada.setDescripcion(textDescripcion.getText()); 
				entrada.setWeb(textWeb.getText());
				entrada.setIban(textIban.getText());
				entrada.setObservaciones(textObservaciones.getText());
				entrada.setEmpresa(sesionGlobal.getIdEmpresa());
				dia = Integer.toString(textFIni.getCalendar().get(Calendar.DAY_OF_MONTH));
				if (textFIni.getCalendar().get(Calendar.DAY_OF_MONTH)<10) {
					dia = ("0"+dia);
				}
				mes = Integer.toString(textFIni.getCalendar().get(Calendar.MONTH)+1);
				if (textFIni.getCalendar().get(Calendar.MONTH)+1<10) {
					mes = ("0"+mes);
				}
				ano = Integer.toString(textFIni.getCalendar().get(Calendar.YEAR));
				varFecha = (ano+mes+dia);
				entrada.setFechaIni(Integer.valueOf(varFecha));
				dia = Integer.toString(textFFin.getCalendar().get(Calendar.DAY_OF_MONTH));
				if (textFFin.getCalendar().get(Calendar.DAY_OF_MONTH)<10) {
					dia = ("0"+dia);
				}
				mes = Integer.toString(textFFin.getCalendar().get(Calendar.MONTH)+1);
				if (textFFin.getCalendar().get(Calendar.MONTH)+1<10) {
					mes = ("0"+mes);
				}
				ano = Integer.toString(textFFin.getCalendar().get(Calendar.YEAR));
				varFecha = (ano+mes+dia);
				entrada.setFechaFin(Integer.valueOf(varFecha));
				dia = Integer.toString(textFCierre.getCalendar().get(Calendar.DAY_OF_MONTH));
				if (textFCierre.getCalendar().get(Calendar.DAY_OF_MONTH)<10) {
					dia = ("0"+dia);
				}
				mes = Integer.toString(textFCierre.getCalendar().get(Calendar.MONTH)+1);
				if (textFCierre.getCalendar().get(Calendar.MONTH)+1<10) {
					mes = ("0"+mes);
				}
				ano = Integer.toString(textFCierre.getCalendar().get(Calendar.YEAR));
				varFecha = (ano+mes+dia);
				entrada.setFechaCierre(Integer.valueOf(varFecha));
				String variable = (String) comboCliente.getSelectedItem().toString();
				entrada.setCliente(accClientes.buscaCliente(variable, sesionGlobal.getIdEmpresa()));
				variable = (String) comboCoste.getSelectedItem().toString();
				entrada.setCoste(accCostes.buscaCoste(variable));
				
				entrada.setImporte(Double.valueOf(textImporte.getText()));
				entrada.setMargen(Double.valueOf(textMargen.getText()));
				//grabamos los datos. 
				accion= accProyectos.grabarProyectos(entrada);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Proyecto dado de alta");
				}else {
					JOptionPane.showMessageDialog(null, "Error en el alta del proyecto");
				}
			}
		});
		btnAlta.setBounds(21, 406, 142, 23);
		frame.getContentPane().add(btnAlta);
		
		JButton btnBaja = new JButton("Baja Proyecto");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el proyecto?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					accion = accProyectos.deleteProyecto(idProyecto);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Proyecto borrado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado del proyecto");
					}
				} else System.out.println("vale... no borro nada...");
			}

		});
		btnBaja.setBounds(189, 406, 139, 23);
		frame.getContentPane().add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar Proyecto");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar el proyecto?", "Confirmar modificaci�n", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					proyecto = llenaCamposDto();
					accion = accProyectos.updateProyectos(proyecto);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Proyecto modificado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar el proyecto");
					}
				} else System.out.println("vale... no hago nada...");
			}
		});
		
		btnModificar.setBounds(389, 406, 135, 23);
		frame.getContentPane().add(btnModificar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiaPantalla();
			}


		});
		btnLimpiar.setBounds(460, 57, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
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
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "B�squeda de Proyecto", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
						proyecto = new ProyectosDTO();
						ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
						temporal = (ObjetoJComboBox) cliente.getSelectedItem();
						proyecto.setCliente(temporal.getNumero());
												
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
						
						proyecto.setFechaIni(Integer.valueOf(varFecha));
						proyecto = accProyectos.buscaProyecto(proyecto);
						if (proyecto.getIdProyecto().equals(0)) {
							JOptionPane.showMessageDialog(null, "Proyecto no encontrado");	
						} else {
							limpiaPantalla();
							llenaCamposPantalla(proyecto);
							
						}
					}
				}				
		});
		btnBuscar.setBounds(460, 88, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		btnConcepto = new JButton("Conceptos");
		if (sesionGlobal.getIdentificador() > 0) {
			btnConcepto.setEnabled(true);
		}else {btnConcepto.setEnabled(false);}
		
		btnConcepto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaConceptosProyecto ventana = new VentanaConceptosProyecto(sesionGlobal);
			}
		});
		btnConcepto.setBounds(389, 164, 109, 23);
		frame.getContentPane().add(btnConcepto);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	protected ProyectosDTO llenaCamposDto() {
		//llenamos el DTO de clientes con los datos de pantalla
		proyecto = new ProyectosDTO();
		
		proyecto.setIdProyecto(idProyecto);
		proyecto.setDescripcion(textDescripcion.getText());
		proyecto.setNombre(textNombre.getText());
		dia = Integer.toString(textFCierre.getCalendar().get(Calendar.DAY_OF_MONTH));
		if (textFCierre.getCalendar().get(Calendar.DAY_OF_MONTH)<10) {
			dia = ("0"+dia);
		}
		mes = Integer.toString(textFCierre.getCalendar().get(Calendar.MONTH)+1);
		if (textFCierre.getCalendar().get(Calendar.MONTH)+1<10) {
			mes = ("0"+mes);
		}
		ano = Integer.toString(textFCierre.getCalendar().get(Calendar.YEAR));
		varFecha = (ano+mes+dia);
		proyecto.setFechaCierre(Integer.valueOf(varFecha));
		dia = Integer.toString(textFFin.getCalendar().get(Calendar.DAY_OF_MONTH));
		if (textFFin.getCalendar().get(Calendar.DAY_OF_MONTH)<10) {
			dia = ("0"+dia);
		}
		mes = Integer.toString(textFFin.getCalendar().get(Calendar.MONTH)+1);
		if (textFFin.getCalendar().get(Calendar.MONTH)+1<10) {
			mes = ("0"+mes);
		}
		ano = Integer.toString(textFFin.getCalendar().get(Calendar.YEAR));
		varFecha = (ano+mes+dia);
		proyecto.setFechaFin(Integer.valueOf(varFecha));
		dia = Integer.toString(textFIni.getCalendar().get(Calendar.DAY_OF_MONTH));
		if (textFIni.getCalendar().get(Calendar.DAY_OF_MONTH)<10) {
			dia = ("0"+dia);
		}
		mes = Integer.toString(textFIni.getCalendar().get(Calendar.MONTH)+1);
		if (textFIni.getCalendar().get(Calendar.MONTH)+1<10) {
			mes = ("0"+mes);
		}
		ano = Integer.toString(textFIni.getCalendar().get(Calendar.YEAR));
		varFecha = (ano+mes+dia);
		proyecto.setFechaIni(Integer.valueOf(varFecha));	
		proyecto.setIban(textIban.getText());
		proyecto.setImporte(Double.valueOf(textImporte.getText()));
		proyecto.setMargen(Double.valueOf(textMargen.getText()));
		proyecto.setObservaciones(textObservaciones.getText());
		proyecto.setWeb(textWeb.getText());
		
		String variable = (String) comboCliente.getSelectedItem().toString();
		proyecto.setCliente(accClientes.buscaCliente(variable, sesionGlobal.getIdEmpresa()));
		variable = (String) comboCoste.getSelectedItem().toString();
		proyecto.setCoste(accCostes.buscaIdCoste(variable));
		proyecto.setEmpresa(sesionGlobal.getIdEmpresa());
		
		
		return proyecto;
	}

	private void llenaCamposPantalla(ProyectosDTO entrada) {
		//llenamos los campos de pantalla con el DTO de proyectos
		idProyecto = entrada.getIdProyecto();
		textDescripcion.setText(entrada.getDescripcion());
		textNombre.setText(entrada.getNombre());
		fecha = String.valueOf(entrada.getFechaCierre());
		try {
			textFCierre.setDate(formato.parse(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fecha = String.valueOf(entrada.getFechaFin());
		try {
			textFFin.setDate(formato.parse(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fecha = String.valueOf(entrada.getFechaIni());
		try {
			textFIni.setDate(formato.parse(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textIban.setText(entrada.getIban());
		textImporte.setText(String.valueOf(entrada.getImporte()));
		textMargen.setText(String.valueOf(entrada.getMargen()));
		textObservaciones.setText(entrada.getObservaciones());
		textWeb.setText(entrada.getWeb());
		
		String valorCombo = accCostes.buscaNombre(entrada.getCoste());
		comboCoste.getModel().setSelectedItem(valorCombo);
		
		valorCombo = accClientes.buscaNombre(entrada.getCliente());
		comboCliente.getModel().setSelectedItem(valorCombo);
		
		
	}
	private void limpiaPantalla() {
		// TODO Auto-generated method stub
		textDescripcion.setText(" ");
		textNombre.setText(" ");
		textFCierre.setDate(null);
		textFFin.setDate(null);
		textFIni.setDate(null);
		textIban.setText(" ");
		textImporte.setText(" ");
		textMargen.setText(" ");
		textObservaciones.setText(" ");
		textWeb.setText(" ");
		sesionGlobal.setIdentificador(0);
	}
}
