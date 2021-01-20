package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import acciones.dto.ClientesDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesClientesImpl;


public class VentanaClientes {

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
	private JTextField textFP;
	private JTextField textDiaPago;
	private JTextField textMP;
	private JTextField textObserv;
	private JTextField textCP;

	private JRadioButton rdbtnSi;
	private JRadioButton rdbtnNo;
	
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
	private JLabel lblFPago;
	private JLabel lblWeb;
	private JLabel lblDiaPago;
	private JLabel lblObservaciones;
	private JLabel lblModalidadDePago;
	private JLabel lblCP;
	private JLabel lblActivo;
	private JLabel lblError;
	
	ClientesDTO cliente;
	ServiceDTO control;
	
	static ServiceDTO sesionGlobal;
	private Integer idCliente;
	private Boolean accion = false;
	AccionesClientesImpl accClientes = new AccionesClientesImpl();
	/**
	 * Create the application.
	 */
	public VentanaClientes(ServiceDTO sesion) {
		sesionGlobal = sesion;
		initialize(sesion.getNombreEmpresa());
		if (sesionGlobal.getNoPrincipal()=="S") {
			llenaPantalla();
		}
	}

	private void llenaPantalla() {
		// TODO Auto-generated method stub
		cliente = new ClientesDTO();
		cliente=accClientes.buscaCliente(sesionGlobal.getIdentificador(),sesionGlobal.getIdEmpresa());
		llenaCamposPantalla(cliente);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Clientes de la empresa " + nombre);
		frame.setBounds(100, 100, 450, 485);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblCif = new JLabel("CIF");
		textCif = new JTextField();
		textCif.setBounds(95, 11, 96, 20);
		frame.getContentPane().add(textCif);
		textCif.setColumns(10);
				
		lblCif.setBounds(10, 14, 48, 14);
		frame.getContentPane().add(lblCif);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 42, 48, 14);
		frame.getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(95, 39, 96, 20);
		frame.getContentPane().add(textNombre);
		
		lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(10, 70, 48, 14);
		frame.getContentPane().add(lblDireccin);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(95, 67, 96, 20);
		frame.getContentPane().add(textDireccion);
		
		lblPoblacin = new JLabel("Poblaci\u00F3n");
		lblPoblacin.setBounds(10, 101, 48, 14);
		frame.getContentPane().add(lblPoblacin);
		
		textPoblacion = new JTextField();
		textPoblacion.setColumns(10);
		textPoblacion.setBounds(95, 98, 96, 20);
		frame.getContentPane().add(textPoblacion);
		
		lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 129, 48, 14);
		frame.getContentPane().add(lblProvincia);
		
		textProvincia = new JTextField();
		textProvincia.setColumns(10);
		textProvincia.setBounds(95, 126, 96, 20);
		frame.getContentPane().add(textProvincia);
		
		lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(10, 157, 48, 14);
		frame.getContentPane().add(lblTelfono);
		
		textTelefono1 = new JTextField();
		textTelefono1.setColumns(10);
		textTelefono1.setBounds(95, 154, 96, 20);
		frame.getContentPane().add(textTelefono1);
		
		lblMvil = new JLabel("M\u00F3vil 1");
		lblMvil.setBounds(10, 179, 48, 14);
		frame.getContentPane().add(lblMvil);
		
		textTelefono2 = new JTextField();
		textTelefono2.setColumns(10);
		textTelefono2.setBounds(95, 176, 96, 20);
		frame.getContentPane().add(textTelefono2);
		
		lblMvil1 = new JLabel("M\u00F3vil 2");
		lblMvil1.setBounds(241, 179, 48, 14);
		frame.getContentPane().add(lblMvil1);
		
		textTelefono3 = new JTextField();
		textTelefono3.setColumns(10);
		textTelefono3.setBounds(316, 176, 96, 20);
		frame.getContentPane().add(textTelefono3);
		
		lblPersonaContacto = new JLabel("Persona Contacto");
		lblPersonaContacto.setBounds(10, 205, 75, 14);
		frame.getContentPane().add(lblPersonaContacto);
		
		textPersonaContact = new JTextField();
		textPersonaContact.setColumns(10);
		textPersonaContact.setBounds(95, 202, 96, 20);
		frame.getContentPane().add(textPersonaContact);
		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(10, 233, 48, 14);
		frame.getContentPane().add(lblCorreo);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(95, 230, 96, 20);
		frame.getContentPane().add(textMail);
		
		lblWeb = new JLabel("Web");
		lblWeb.setBounds(10, 263, 48, 14);
		frame.getContentPane().add(lblWeb);
		
		textWeb = new JTextField();
		textWeb.setColumns(10);
		textWeb.setBounds(95, 260, 96, 20);
		frame.getContentPane().add(textWeb);
		
		lblFPago = new JLabel("Forma de Pago");
		lblFPago.setBounds(10, 291, 75, 14);
		frame.getContentPane().add(lblFPago);
		
		textFP = new JTextField();
		textFP.setColumns(10);
		textFP.setBounds(95, 288, 96, 20);
		frame.getContentPane().add(textFP);
		
		lblDiaPago = new JLabel("D\u00EDa de pago");
		lblDiaPago.setBounds(241, 291, 77, 14);
		frame.getContentPane().add(lblDiaPago);
		
		textDiaPago = new JTextField();
		textDiaPago.setColumns(10);
		textDiaPago.setBounds(328, 288, 96, 20);
		frame.getContentPane().add(textDiaPago);
		
		lblModalidadDePago = new JLabel("Modalidad de pago");
		lblModalidadDePago.setBounds(10, 322, 75, 14);
		frame.getContentPane().add(lblModalidadDePago);
		
		textMP = new JTextField();
		textMP.setColumns(10);
		textMP.setBounds(95, 319, 96, 20);
		frame.getContentPane().add(textMP);
		
		lblActivo = new JLabel("Activo");
		lblActivo.setBounds(243, 319, 48, 14);
		frame.getContentPane().add(lblActivo);
		lblCP = new JLabel("C\u00F3digo Postal");
		lblCP.setBounds(231, 154, 48, 14);
		frame.getContentPane().add(lblCP);
		
		textCP = new JTextField();
		textCP.setColumns(10);
		textCP.setBounds(316, 151, 96, 20);
		frame.getContentPane().add(textCP);
		
		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(10, 350, 75, 14);
		frame.getContentPane().add(lblObservaciones);
		
		textObserv = new JTextField();
		textObserv.setColumns(10);
		textObserv.setBounds(95, 347, 96, 20);
		frame.getContentPane().add(textObserv);
		
		rdbtnSi = new JRadioButton("Si");
		rdbtnNo = new JRadioButton("No");
		
		rdbtnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNo.setSelected(false);
				rdbtnSi.setSelected(true);
			}
		});
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNo.setSelected(true);
				rdbtnSi.setSelected(false);
			}
		});
		
		rdbtnSi.setBounds(285, 318, 48, 23);
		frame.getContentPane().add(rdbtnSi);
		
		rdbtnNo.setBounds(338, 318, 48, 23);
		frame.getContentPane().add(rdbtnNo);
		
		lblError = new JLabel("");
		lblError.setBounds(10, 375, 414, 14);
		frame.getContentPane().add(lblError);
		
		JButton btnAltaCliente = new JButton("Alta Cliente");
		btnAltaCliente.setBounds(37, 400, 105, 23);
		frame.getContentPane().add(btnAltaCliente);
		btnAltaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//llenamos el DTO de clientes
				cliente=llenaCamposDto();
				//grabamos los datos
				accion= accClientes.grabarCliente(cliente);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Cliente dado de alta correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error en el alta de cliente");
				}
			}
		});
		
		
		JButton btnBajaCliente = new JButton("Baja Cliente");
		btnBajaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el cliente?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					accion = accClientes.deleteCliente(idCliente);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Cliente borrado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado del cliente");
					}
				} else System.out.println("vale... no borro nada...");
			}
		});
		btnBajaCliente.setBounds(152, 400, 105, 23);
		frame.getContentPane().add(btnBajaCliente);
		
		JButton btnModificarCliente = new JButton("Modificar Cliente");
		//mirar si se puede añandir parametro con {}
		btnModificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar el cliente?", "Confirmar modificación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					cliente=llenaCamposDto();
					accion = accClientes.updateCliente(cliente);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar el cliente");
					}
				} else System.out.println("vale... no hago nada...");
			}
		});
		btnModificarCliente.setBounds(269, 400, 115, 23);
		frame.getContentPane().add(btnModificarCliente);
		

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
				if (sesionGlobal.getNoPrincipal()=="N") {
				VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
				}
			}
		});
		btnNewButton.setBounds(335, 21, 89, 23);
		frame.getContentPane().add(btnNewButton,BorderLayout.EAST);
		
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
		frame.getContentPane().add(btnLimpiar,BorderLayout.EAST);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomBuscado = JOptionPane.showInputDialog("Escribe el cliente a buscar");
				if (nomBuscado != null){
					if (nomBuscado == "" || nomBuscado.isEmpty()) {
						activaCampos();
					}else {
						cliente = new ClientesDTO();
						cliente.setIdEmpresa(sesionGlobal.getIdEmpresa());
						cliente.setIdCliente(0);
						cliente.setNombre(nomBuscado);
						cliente = accClientes.buscaCliente(cliente);
						if (cliente.getIdCliente().equals(0)) {
							JOptionPane.showMessageDialog(null, "Cliente no encontrado");	
						} else {
							limpiaPantalla();
							llenaCamposPantalla(cliente);
							lblError.setText("");
						}
					}
				}				
			}
		});
		btnBuscar.setBounds(335, 89, 89, 23);
		frame.getContentPane().add(btnBuscar,BorderLayout.EAST);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public void ocultarCampos() {
		//ocultamos todos los campos y etiquetas de pantalla excepto el NOMBRE
		lblError.setText("");
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
		textFP.setVisible(false);
		textDiaPago.setVisible(false);
		textMP.setVisible(false);
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
		lblFPago.setVisible(false);
		lblWeb.setVisible(false);
		lblDiaPago.setVisible(false);
		lblObservaciones.setVisible(false);
		lblModalidadDePago.setVisible(false);
		lblCP.setVisible(false);
		lblActivo.setVisible(false);
		rdbtnSi.setVisible(false);
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
		textFP.setVisible(true);
		textDiaPago.setVisible(true);
		textMP.setVisible(true);
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
		lblFPago.setVisible(true);
		lblWeb.setVisible(true);
		lblDiaPago.setVisible(true);
		lblObservaciones.setVisible(true);
		lblModalidadDePago.setVisible(true);
		lblCP.setVisible(true);
		lblActivo.setVisible(true);
		rdbtnSi.setVisible(true);
		rdbtnNo.setVisible(true);
	}
	
	private void llenaCamposPantalla(ClientesDTO entrada) {
		//llenamos los campos de pantalla con el DTO de clientes
		idCliente = entrada.getIdCliente();
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
		textFP.setText(entrada.getFp());
		textDiaPago.setText(String.valueOf(entrada.getDiaPago()));
		textMP.setText(entrada.getModaPago());
		textObserv.setText(entrada.getObservaciones());
		textCP.setText(String.valueOf(entrada.getCp()));
		if (entrada.getActivo().equals("S")) {
			rdbtnNo.setSelected(false);
			rdbtnSi.setSelected(true);
		} else {
			rdbtnNo.setSelected(true);
			rdbtnSi.setSelected(false);
		}
	}
	
	private  ClientesDTO llenaCamposDto(){
		//llenamos el DTO de clientes con los datos de pantalla
		cliente = new ClientesDTO();
		cliente.setIdEmpresa(sesionGlobal.getIdEmpresa());
		cliente.setIdCliente(idCliente);
		cliente.setDireccion(textDireccion.getText());
		cliente.setCif(textCif.getText());
		cliente.setNombre(textNombre.getText());
		cliente.setPoblacion(textPoblacion.getText());
		cliente.setProvincia(textProvincia.getText());
		cliente.setCp(Integer.valueOf(textCP.getText()));
		cliente.setTelefono1(Integer.valueOf(textTelefono1.getText()));
		cliente.setTelefono2(Integer.valueOf(textTelefono2.getText()));
		cliente.setTelefono3(Integer.valueOf(textTelefono3.getText()));
		cliente.setPersonaContacto(textPersonaContact.getText());
		cliente.setMail(textMail.getText());
		cliente.setWeb(textWeb.getText());
		cliente.setFp(textFP.getText());
		cliente.setDiaPago(Integer.valueOf(textDiaPago.getText()));
		cliente.setModaPago(textMP.getText());
		cliente.setObservaciones(textObserv.getText());
		if (rdbtnSi.isSelected() == true) {
			cliente.setActivo("S");
		} else {cliente.setActivo("N");}
				
		return cliente;
	}
	
	private void limpiaPantalla() {
		textCif.setText(" ");
		textNombre.setText(" ");
		textDireccion.setText(" ");
		textPoblacion.setText(" ");
		textProvincia.setText(" ");
		textTelefono1.setText(" ");
		textTelefono2.setText(" ");
		textTelefono3.setText(" ");
		textPersonaContact.setText(" ");
		textMail.setText(" ");
		textWeb.setText(" ");
		textFP.setText(" ");
		textDiaPago.setText(" ");
		textMP.setText(" ");
		textObserv.setText(" ");
		textCP.setText(" ");
		rdbtnNo.setSelected(false);
		rdbtnSi.setSelected(false);
	}

}
