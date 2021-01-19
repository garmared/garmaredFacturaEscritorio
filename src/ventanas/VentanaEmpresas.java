package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import acciones.dto.EmpresasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesEmpresasImpl;


public class VentanaEmpresas {

	private JFrame frame;
	private JTextField textCif;
	private JTextField textNombre;
	private JTextField textDireccion;
	private JTextField textPoblacion;
	private JTextField textProvincia;
	private JTextField textTelefono1;
	private JTextField textTelefono2;
	private JTextField textTelefono3;
	private JTextField textPersonaContact;
	private JTextField textMail;
	private JTextField textWeb;
	private JTextField textIban;
	private JTextField textObserv;
	private JTextField textCP;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnSi;
	AccionesEmpresasImpl accEmpresas = new AccionesEmpresasImpl();
	
	private JComboBox comboCoste;
	
	private JLabel lblCif;
	private JLabel lblNombre;
	private JLabel lblDireccin;
	private JLabel lblPoblacin;
	private JLabel lblProvincia;
	private JLabel lblTelfono;
	private JLabel lblMvil;
	private JLabel lblMvil1;
	private JLabel lblPersonaContacto;
	private JLabel lblCorreo;
	private JLabel lblWeb;
	private JLabel lblObservaciones;
	private JLabel lblIban;
	private JLabel lblCP;
	private JLabel lblActivo;
	
	static ServiceDTO sesionGlobal;
	private Boolean accion;
	private EmpresasDTO empresas;
	private int idEmpresa;
	private ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEmpresas window = new VentanaEmpresas(sesionGlobal);
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
	public VentanaEmpresas(ServiceDTO control) {
		sesionGlobal = control;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Empresa");
		frame.setBounds(100, 100, 540, 485);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textCif = new JTextField();
		textCif.setBounds(116, 8, 96, 20);
		frame.getContentPane().add(textCif);
		textCif.setColumns(10);
				
		JLabel lblCif = new JLabel("CIF");
		lblCif.setBounds(10, 14, 48, 14);
		frame.getContentPane().add(lblCif);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 42, 48, 14);
		frame.getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(116, 36, 96, 20);
		frame.getContentPane().add(textNombre);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(10, 70, 75, 14);
		frame.getContentPane().add(lblDireccin);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(116, 64, 96, 20);
		frame.getContentPane().add(textDireccion);
		
		JLabel lblPoblacin = new JLabel("Poblaci\u00F3n");
		lblPoblacin.setBounds(10, 101, 75, 14);
		frame.getContentPane().add(lblPoblacin);
		
		textPoblacion = new JTextField();
		textPoblacion.setColumns(10);
		textPoblacion.setBounds(116, 95, 96, 20);
		frame.getContentPane().add(textPoblacion);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 129, 75, 14);
		frame.getContentPane().add(lblProvincia);
		
		textProvincia = new JTextField();
		textProvincia.setColumns(10);
		textProvincia.setBounds(116, 123, 96, 20);
		frame.getContentPane().add(textProvincia);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(10, 157, 75, 14);
		frame.getContentPane().add(lblTelfono);
		
		textTelefono1 = new JTextField();
		textTelefono1.setColumns(10);
		textTelefono1.setBounds(116, 151, 96, 20);
		frame.getContentPane().add(textTelefono1);
		
		JLabel lblMvil = new JLabel("M\u00F3vil 1");
		lblMvil.setBounds(10, 179, 48, 14);
		frame.getContentPane().add(lblMvil);
		
		textTelefono2 = new JTextField();
		textTelefono2.setColumns(10);
		textTelefono2.setBounds(116, 176, 96, 20);
		frame.getContentPane().add(textTelefono2);
		
		JLabel lblMvil_1 = new JLabel("M\u00F3vil 2");
		lblMvil_1.setBounds(255, 179, 48, 14);
		frame.getContentPane().add(lblMvil_1);
		
		textTelefono3 = new JTextField();
		textTelefono3.setColumns(10);
		textTelefono3.setBounds(354, 176, 96, 20);
		frame.getContentPane().add(textTelefono3);
		
		JLabel lblPersonaContacto = new JLabel("Persona Contacto");
		lblPersonaContacto.setBounds(10, 205, 105, 14);
		frame.getContentPane().add(lblPersonaContacto);
		
		textPersonaContact = new JTextField();
		textPersonaContact.setColumns(10);
		textPersonaContact.setBounds(116, 202, 96, 20);
		frame.getContentPane().add(textPersonaContact);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(10, 233, 48, 14);
		frame.getContentPane().add(lblCorreo);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(116, 233, 96, 20);
		frame.getContentPane().add(textMail);
		
		JLabel lblWeb = new JLabel("Web");
		lblWeb.setBounds(10, 263, 48, 14);
		frame.getContentPane().add(lblWeb);
		
		textWeb = new JTextField();
		textWeb.setColumns(10);
		textWeb.setBounds(116, 263, 96, 20);
		frame.getContentPane().add(textWeb);
		
		
		JLabel lblTipoCoste = new JLabel("Tipo de coste");
		lblTipoCoste.setBounds(10, 291, 96, 14);
		frame.getContentPane().add(lblTipoCoste);
		
		JLabel lblIban = new JLabel("IBAN");
		lblIban.setBounds(10, 322, 75, 14);
		frame.getContentPane().add(lblIban);
		
		textIban = new JTextField();
		textIban.setColumns(10);
		textIban.setBounds(116, 322, 96, 20);
		frame.getContentPane().add(textIban);
		
		JLabel lblActivo = new JLabel("Activo");
		lblActivo.setBounds(255, 323, 48, 14);
		frame.getContentPane().add(lblActivo);
		JLabel lblCP = new JLabel("C\u00F3digo Postal");
		lblCP.setBounds(255, 129, 89, 14);
		frame.getContentPane().add(lblCP);
		
		textCP = new JTextField();
		textCP.setColumns(10);
		textCP.setBounds(354, 123, 96, 20);
		frame.getContentPane().add(textCP);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(10, 350, 96, 14);
		frame.getContentPane().add(lblObservaciones);
		
		textObserv = new JTextField();
		textObserv.setColumns(10);
		textObserv.setBounds(116, 350, 96, 20);
		frame.getContentPane().add(textObserv);

		rdbtnNo = new JRadioButton("No");
		
		rdbtnNo.setBounds(354, 318, 44, 23);
				
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNo.setSelected(false);
				rdbtnSi.setSelected(true);
			}
		});
		frame.getContentPane().add(rdbtnNo);
		
	
		JLabel lblError = new JLabel("");
		lblError.setBounds(10, 375, 414, 14);
		frame.getContentPane().add(lblError);
		
		JButton btnAltaEmpresa = new JButton("Alta Empresa");
		btnAltaEmpresa.setBounds(37, 400, 124, 23);
		frame.getContentPane().add(btnAltaEmpresa);
		btnAltaEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre, cif, direccion, poblacion, provincia, personaContacto, mail, web, observaciones,activo,iban;
				Integer cp, telefono1, telefono2, telefono3,tipoCoste;
				//Boolean accion = false;
				//llenamos el DTO de empresas
				EmpresasDTO entrada = new EmpresasDTO();
				if (rdbtnNo.isSelected() == true) {
					entrada.setActivo("S");
				} else {entrada.setActivo("N");}
				
				entrada.setCif(textCif.getText());
				entrada.setCp(Integer.valueOf(textCP.getText()));
				entrada.setDireccion(textDireccion.getText());
				temporal = (ObjetoJComboBox) comboCoste.getSelectedItem();
				entrada.setTipoCoste(temporal.getNumero());
				entrada.setMail(textMail.getText());
				entrada.setIban(textIban.getText());
				entrada.setNombre(textNombre.getText());
				entrada.setObservaciones(textObserv.getText());
				entrada.setPersonaContacto(textPersonaContact.getText());
				entrada.setPoblacion(textPoblacion.getText());
				entrada.setProvincia(textProvincia.getText());
				entrada.setTelefono1(Integer.valueOf(textTelefono1.getText()));
				entrada.setTelefono2(Integer.valueOf(textTelefono2.getText()));
				entrada.setTelefono3(Integer.valueOf(textTelefono3.getText()));
				entrada.setWeb(textWeb.getText());
				entrada.setTipo("E");
				
				//grabamos los datos. Es igual que en proveedor pero el TIPO es "E" de empresas.
				accion= accEmpresas.grabarEmpresas(entrada);
				if (accion) {
					lblError.setText("Empresa dada de alta correctamente");
				}else {
					lblError.setText("Error en el alta de empresa");
				}
			}
		});
		
		
		JButton btnBaja = new JButton("Baja Empresa");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar la empresa?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					accion = accEmpresas.deleteEmpresa(idEmpresa);
					if (accion) {
						initialize();
						JOptionPane.showMessageDialog(null, "Empresa borrada correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado de la empresa");
					}
				} else System.out.println("vale... no borro nada...");
			}
		});

		btnBaja.setBounds(171, 400, 123, 23);
		frame.getContentPane().add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar Empresa");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar la empresa?", "Confirmar modificación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					empresas = llenaCamposDto();
					accion = accEmpresas.updateEmpresas(empresas);
					if (accion) {
						initialize();
						JOptionPane.showMessageDialog(null, "Empresa modificado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar la empresa");
					}
				} else System.out.println("vale... no hago nada...");
			}
		});

		btnModificar.setBounds(304, 400, 160, 23);
		frame.getContentPane().add(btnModificar);
		

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
				VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
			}
		});
		btnNewButton.setBounds(425, 11, 89, 23);
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
				initialize();
			}
		});
		btnLimpiar.setBounds(425, 45, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomBuscado = JOptionPane.showInputDialog("Escribe la empresa a buscar");
				if (nomBuscado != null){
					if (nomBuscado == "" || nomBuscado.isEmpty()) {
						activaCampos();
					}else {
						empresas = new EmpresasDTO();
						empresas.setidEmpresa(0);
						empresas.setNombre(nomBuscado);
						empresas.setTipo("E");
						empresas = accEmpresas.buscaEmpresa(empresas);
						if (empresas.getidEmpresa().equals(0)) {
							JOptionPane.showMessageDialog(null, "Empresa no encontrada");	
						} else {
							initialize();
							llenaCamposPantalla(empresas);
							lblError.setText("");
						}
					}
				}				
			}
		});

		btnBuscar.setBounds(425, 79, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		JRadioButton rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setBounds(309, 318, 44, 23);
		frame.getContentPane().add(rdbtnSi);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public void ocultarCampos() {
		//ocultamos todos los campos y etiquetas de pantalla excepto el NOMBRE
		textCif.setVisible(false);
		textDireccion.setVisible(false);
		textPoblacion.setVisible(false);
		textProvincia.setVisible(false);
		textTelefono1.setVisible(false);
		textTelefono2.setVisible(false);
		textTelefono3.setVisible(false);
		textPersonaContact.setVisible(false);
		textWeb.setVisible(false);
		textMail.setVisible(false);
		comboCoste.setVisible(false);
		textIban.setVisible(false);
		textObserv.setVisible(false);
		textCP.setVisible(false);
		lblCif.setVisible(false);
		lblDireccin.setVisible(false);
		lblPoblacin.setVisible(false);
		lblProvincia.setVisible(false);
		lblTelfono.setVisible(false);
		lblMvil.setVisible(false);
		lblMvil1.setVisible(false);
		lblPersonaContacto.setVisible(false);
		lblCorreo.setVisible(false);
		lblWeb.setVisible(false);
		lblIban.setVisible(false);
		lblObservaciones.setVisible(false);
		lblCP.setVisible(false);
		lblActivo.setVisible(false);
		rdbtnNo.setVisible(false);
		rdbtnNo.setVisible(false);
	}
	
	public void activaCampos() {
		//mostramos todos los campos y etiquetas de pantalla
		textCif.setVisible(true);
		textDireccion.setVisible(true);
		textPoblacion.setVisible(true);
		textProvincia.setVisible(true);
		textTelefono1.setVisible(true);
		textTelefono2.setVisible(true);
		textTelefono3.setVisible(true);
		textPersonaContact.setVisible(true);
		textWeb.setVisible(true);
		textMail.setVisible(true);
		textIban.setVisible(true);
		textObserv.setVisible(true);
		textCP.setVisible(true);
		lblCif.setVisible(true);
		lblDireccin.setVisible(true);
		lblPoblacin.setVisible(true);
		lblProvincia.setVisible(true);
		lblTelfono.setVisible(true);
		lblMvil.setVisible(true);
		lblMvil1.setVisible(true);
		lblPersonaContacto.setVisible(true);
		lblCorreo.setVisible(true);
		lblWeb.setVisible(true);
		lblObservaciones.setVisible(true);
		lblIban.setVisible(true);
		lblActivo.setVisible(true);
		rdbtnSi.setVisible(true);
		rdbtnNo.setVisible(true);
	}


	private void llenaCamposPantalla(EmpresasDTO entrada) {
		//llenamos los campos de pantalla con el DTO de clientes
		idEmpresa = entrada.getidEmpresa();
		textNombre.setText(entrada.getNombre());
		textCif.setText(entrada.getCif());
		textDireccion.setText(entrada.getDireccion());
		textPoblacion.setText(entrada.getPoblacion());
		textProvincia.setText(entrada.getProvincia());
		textTelefono1.setText(String.valueOf(entrada.getTelefono1()));
		textTelefono2.setText(String.valueOf(entrada.getTelefono2()));
		textTelefono3.setText(String.valueOf(entrada.getTelefono3()));
		textPersonaContact.setText(entrada.getPersonaContacto());
		textWeb.setText(entrada.getWeb());
		textMail.setText(entrada.getMail());
		
		textIban.setText(entrada.getIban());
		textObserv.setText(entrada.getObservaciones());
		textCP.setText(String.valueOf(entrada.getCp()));
		if (entrada.getActivo().equals("S")) {
			rdbtnSi.setSelected(false);
			rdbtnSi.setSelected(true);
		} else {
			rdbtnNo.setSelected(true);
			rdbtnNo.setSelected(false);
		}
	}
	
	private  EmpresasDTO llenaCamposDto(){
		//llenamos el DTO de clientes con los datos de pantalla
		empresas = new EmpresasDTO();
		empresas.setidEmpresa(idEmpresa);
		empresas.setDireccion(textDireccion.getText());
		empresas.setTipo("E");
		empresas.setCif(textCif.getText());
		empresas.setNombre(textNombre.getText());
		empresas.setPoblacion(textPoblacion.getText());
		empresas.setProvincia(textProvincia.getText());
		empresas.setCp(Integer.valueOf(textCP.getText()));
		empresas.setTelefono1(Integer.valueOf(textTelefono1.getText()));
		empresas.setTelefono2(Integer.valueOf(textTelefono2.getText()));
		empresas.setTelefono3(Integer.valueOf(textTelefono3.getText()));
		empresas.setPersonaContacto(textPersonaContact.getText());
		empresas.setMail(textMail.getText());
		empresas.setWeb(textWeb.getText());
		
		empresas.setIban(textIban.getText());
		empresas.setObservaciones(textObserv.getText());
		if (rdbtnSi.isSelected() == true) {
			empresas.setActivo("S");
		} else {empresas.setActivo("N");}
				
		return empresas;
	}
}
