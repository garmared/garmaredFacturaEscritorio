package garmaredFacturaEscritorio;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import acciones.dto.ObjetoJComboBox;
import acciones.dto.ProyectosDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.accionesClientesImpl;
import acciones.service.impl.accionesCostesImpl;
import acciones.service.impl.accionesEmpresasImpl;
import acciones.service.impl.accionesProyectosImpl;

public class VentanaProyectos {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private ProyectosDTO proyecto;
	private int idProyecto;
	
	private JTextField textFIni;
	private JTextField textFFin;
	private JTextField textFCierre;
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
	accionesClientesImpl accClientes = new accionesClientesImpl();
	accionesCostesImpl accCostes = new accionesCostesImpl();
	accionesEmpresasImpl accEmpresas = new accionesEmpresasImpl();
	accionesProyectosImpl accProyectos = new accionesProyectosImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaProyectos window = new VentanaProyectos(sesionGlobal);
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
	public VentanaProyectos(ServiceDTO control) {
		sesionGlobal = control;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Proyecto");
		frame.setBounds(100, 100, 575, 498);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setBounds(40, 91, 72, 14);
		frame.getContentPane().add(lblFechaInicio);
		
		textFIni = new JTextField();
		textFIni.setBounds(133, 88, 96, 20);
		frame.getContentPane().add(textFIni);
		textFIni.setColumns(10);
		
		lblFechaFin = new JLabel("Fecha fin");
		lblFechaFin.setBounds(40, 119, 72, 14);
		frame.getContentPane().add(lblFechaFin);
		
		textFFin = new JTextField();
		textFFin.setColumns(10);
		textFFin.setBounds(133, 116, 96, 20);
		frame.getContentPane().add(textFFin);
		
		lblFechaCierre = new JLabel("Fecha cierre");
		lblFechaCierre.setBounds(256, 119, 72, 14);
		frame.getContentPane().add(lblFechaCierre);
		
		textFCierre = new JTextField();
		textFCierre.setColumns(10);
		textFCierre.setBounds(333, 116, 96, 20);
		frame.getContentPane().add(textFCierre);
		
		lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(256, 208, 72, 14);
		frame.getContentPane().add(lblDescripcin);
		
		textDescripcion = new JTextField();
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(333, 211, 96, 104);
		frame.getContentPane().add(textDescripcion);
		
		lblWeb = new JLabel("Web");
		lblWeb.setBounds(40, 147, 72, 14);
		frame.getContentPane().add(lblWeb);
		
		textWeb = new JTextField();
		textWeb.setColumns(10);
		textWeb.setBounds(133, 144, 96, 20);
		frame.getContentPane().add(textWeb);
		
		lblIban = new JLabel("IBAN");
		lblIban.setBounds(40, 208, 72, 14);
		frame.getContentPane().add(lblIban);
		
		textIban = new JTextField();
		textIban.setColumns(10);
		textIban.setBounds(133, 205, 96, 20);
		frame.getContentPane().add(textIban);
		
		lblTipoCoste = new JLabel("Tipo Coste");
		lblTipoCoste.setBounds(40, 172, 48, 14);
		frame.getContentPane().add(lblTipoCoste);
		
		comboCoste = new JComboBox();
		comboCoste.setBounds(133, 172, 170, 22);
		frame.getContentPane().add(comboCoste);
		comboCoste.addItem("----");
		
		ArrayList<ObjetoJComboBox> costes = accCostes.consultaCostes(sesionGlobal.getIdEmpresa());
	
		if (costes !=null) {
			for (var i = 0; i < costes.size(); i++) {
				comboCoste.addItem(costes.get(i));
			}
		}
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(40, 323, 48, 14);
		frame.getContentPane().add(lblCliente);
		
		comboCliente = new JComboBox();
		comboCliente.setBounds(133, 319, 170, 22);
		frame.getContentPane().add(comboCliente);
		comboCliente.addItem("----");
		
		ArrayList<ObjetoJComboBox> clientes = accClientes.consultaClientes(sesionGlobal.getIdEmpresa());
			
		if (clientes != null) {
			for (var i = 0; i < clientes.size(); i++) {
				comboCliente.addItem(clientes.get(i));
			}
		}
		
		textObservaciones = new JTextField();
		textObservaciones.setColumns(10);
		textObservaciones.setBounds(133, 233, 96, 20);
		frame.getContentPane().add(textObservaciones);
		
		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(40, 236, 72, 14);
		frame.getContentPane().add(lblObservaciones);
		
		textImporte = new JTextField();
		textImporte.setColumns(10);
		textImporte.setBounds(133, 264, 96, 20);
		frame.getContentPane().add(textImporte);
		
		lblImporte = new JLabel("Importe");
		lblImporte.setBounds(40, 267, 72, 14);
		frame.getContentPane().add(lblImporte);
		
		textMargen = new JTextField();
		textMargen.setColumns(10);
		textMargen.setBounds(133, 295, 96, 20);
		frame.getContentPane().add(textMargen);
		
		lblMargen = new JLabel("Margen");
		lblMargen.setBounds(40, 298, 72, 14);
		frame.getContentPane().add(lblMargen);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(29, 317, 427, 14);
		frame.getContentPane().add(lblError);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
			}
		});
		btnVolver.setBounds(446, 23, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnAlta = new JButton("Alta Proyecto");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
				//llenamos el DTO de proyectos
				ProyectosDTO entrada = new ProyectosDTO();
				entrada.setDescripcion(textDescripcion.getText()); 
				entrada.setWeb(textWeb.getText());
				entrada.setIban(textIban.getText());
				entrada.setObservaciones(textObservaciones.getText());
				entrada.setEmpresa(sesionGlobal.getIdEmpresa());
				temporal = (ObjetoJComboBox) comboCoste.getSelectedItem();
				entrada.setCoste(temporal.getNumero());
				entrada.setFechaIni(Integer.valueOf(textFIni.getText()));
				entrada.setFechaFin(Integer.valueOf(textFFin.getText()));
				entrada.setFechaCierre(Integer.valueOf(textFCierre.getText()));
				temporal = (ObjetoJComboBox) comboCliente.getSelectedItem();
				entrada.setCliente(temporal.getNumero());
				entrada.setImporte(Double.valueOf(textImporte.getText()));
				entrada.setMargen(Double.valueOf(textMargen.getText()));
				//grabamos los datos. 
				accion= accProyectos.grabarProyectos(entrada);
				if (accion) {
					JOptionPane.showMessageDialog(null, "Proyecto dado de alta");
				}else {
					JOptionPane.showMessageDialog(null, "Error en el alta del proyecto");
				}
			}
		});
		btnAlta.setBounds(54, 406, 109, 23);
		frame.getContentPane().add(btnAlta);
		
		JButton btnBaja = new JButton("Baja Proyecto");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el proyecto?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					accion = accProyectos.deleteProyecto(idProyecto);
					if (accion) {
						initialize();
						JOptionPane.showMessageDialog(null, "Proyecto borrado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado del proyecto");
					}
				} else System.out.println("vale... no borro nada...");
			}

		});
		btnBaja.setBounds(189, 406, 114, 23);
		frame.getContentPane().add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar Proyecto");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar el proyecto?", "Confirmar modificación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					proyecto = llenaCamposDto();
					accion = accProyectos.updateProyectos(proyecto);
					if (accion) {
						initialize();
						JOptionPane.showMessageDialog(null, "Proyecto modificado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar el proyecto");
					}
				} else System.out.println("vale... no hago nada...");
			}
		});
		
		btnModificar.setBounds(335, 406, 135, 23);
		frame.getContentPane().add(btnModificar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialize();
			}
		});
		btnLimpiar.setBounds(446, 51, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField cliente = new JTextField();
				JTextField fecha = new JTextField();
				Object [] mensaje= {
						"Cliente:", cliente,
						"Fecha Inicio:", fecha
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Búsqueda de Proyecto", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
					if (cliente.getText() == "" || fecha.getText()== "") {
						activaCampos();
					}else {
						proyecto = new ProyectosDTO();
						int numCliente = accClientes.buscaCliente(cliente.getText());
						proyecto.setCliente(numCliente);
						proyecto.setFechaIni(Integer.valueOf(fecha.getText()));
						proyecto = accProyectos.buscaProyecto(proyecto);
						if (proyecto.getIdProyecto().equals(0)) {
							JOptionPane.showMessageDialog(null, "Proyecto no encontrado");	
						} else {
							initialize();
							llenaCamposPantalla(proyecto);
							lblError.setText("");
						}
					}
				}				

			}
		});
		btnBuscar.setBounds(446, 82, 89, 23);
		frame.getContentPane().add(btnBuscar);
	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	protected ProyectosDTO llenaCamposDto() {
		//llenamos el DTO de clientes con los datos de pantalla
		proyecto = new ProyectosDTO();
		
		proyecto.setIdProyecto(idProyecto);
		proyecto.setDescripcion(textDescripcion.getText());
		proyecto.setFechaCierre(Integer.valueOf(textFCierre.getText()));
		proyecto.setFechaFin(Integer.valueOf(textFFin.getText()));
		proyecto.setFechaIni(Integer.valueOf(textFIni.getText()));	
		proyecto.setIban(textIban.getText());
		proyecto.setImporte(Integer.valueOf(textImporte.getText()));
		proyecto.setMargen(Integer.valueOf(textMargen.getText()));
		proyecto.setObservaciones(textObservaciones.getText());
		proyecto.setWeb(textWeb.getText());
		
		String variable = (String) comboCliente.getSelectedItem().toString();
		proyecto.setCliente(accClientes.buscaCliente(variable));
		variable = (String) comboCoste.getSelectedItem().toString();
		proyecto.setCoste(accCostes.buscaIdCoste(variable));
		proyecto.setEmpresa(sesionGlobal.getIdEmpresa());
		
		
		return proyecto;
	}

	public void activaCampos() {
		//mostramos todos los campos y etiquetas de pantalla
		textDescripcion.setVisible(true);
		textFCierre.setVisible(true);
		textFFin.setVisible(true);
		textFIni.setVisible(true);
		textIban.setVisible(true);
		textImporte.setVisible(true);
		textMargen.setVisible(true);
		textObservaciones.setVisible(true);
		textWeb.setVisible(true);
		
		lblCliente.setVisible(true);
		lblDescripcin.setVisible(true);
		lblFechaCierre.setVisible(true);
		lblFechaFin.setVisible(true);
		lblFechaInicio.setVisible(true);
		lblIban.setVisible(true);
		lblImporte.setVisible(true);
		lblMargen.setVisible(true);
		lblWeb.setVisible(true);
		lblTipoCoste.setVisible(true);
		lblObservaciones.setVisible(true);
	}

	private void llenaCamposPantalla(ProyectosDTO entrada) {
		//llenamos los campos de pantalla con el DTO de proyectos
		idProyecto = entrada.getIdProyecto();
		textDescripcion.setText(entrada.getDescripcion());
		textFCierre.setText(String.valueOf(entrada.getFechaCierre()));
		textFFin.setText(String.valueOf(entrada.getFechaFin()));
		textFIni.setText(String.valueOf(entrada.getFechaIni()));
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
}
