package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import acciones.dto.ObjetoJComboBox;
import acciones.dto.ServiceDTO;
import acciones.dto.UsuariosDTO;
import acciones.service.impl.AccionesUsuariosImpl;
import javax.swing.JComboBox;

public class VentanaUsuarios {

	private JFrame frame;
	private JTextField textUser;
	private JTextField textPassword;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textMail;
	private JComboBox comboBoxSeguridad;
	
	static ServiceDTO sesionGlobal;
	int idUsuario;
	UsuariosDTO usuarios;
	Boolean accion = false;
	AccionesUsuariosImpl accUsuarios = new AccionesUsuariosImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuarios window = new VentanaUsuarios(sesionGlobal);
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
	public VentanaUsuarios(ServiceDTO control) {
		sesionGlobal = control;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Usuarios");
		frame.setBounds(100, 100, 450, 332);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textUser = new JTextField();
		textUser.setBounds(210, 46, 96, 20);
		frame.getContentPane().add(textUser);
		textUser.setColumns(10);
		
		JLabel lblUser = new JLabel("Usuario");
		lblUser.setBounds(69, 49, 48, 14);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(69, 80, 48, 14);
		frame.getContentPane().add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(210, 77, 96, 20);
		frame.getContentPane().add(textPassword);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(69, 108, 48, 14);
		frame.getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(210, 105, 96, 20);
		frame.getContentPane().add(textNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(69, 136, 48, 14);
		frame.getContentPane().add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(210, 133, 96, 20);
		frame.getContentPane().add(textApellidos);
		
		JLabel lblMail = new JLabel("eMail");
		lblMail.setBounds(69, 166, 48, 14);
		frame.getContentPane().add(lblMail);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(210, 163, 96, 20);
		frame.getContentPane().add(textMail);
		
		JLabel lblNivelSegur = new JLabel("Nivel Seguridad");
		lblNivelSegur.setBounds(69, 194, 89, 14);
		frame.getContentPane().add(lblNivelSegur);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(69, 268, 189, 14);
		frame.getContentPane().add(lblError);
		
		comboBoxSeguridad = new JComboBox();
		comboBoxSeguridad.setBounds(210, 194, 96, 22);
		frame.getContentPane().add(comboBoxSeguridad);
		comboBoxSeguridad.addItem("----");
		comboBoxSeguridad.addItem("desarrollo");
		comboBoxSeguridad.addItem("usuario");
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario, password, nombre, apellidos, email;
				Integer nivelSeguridad;
				//llenamos el DTO de usuarios
				usuarios = llenaCamposDto();
				//grabamos los datos
				accion= accUsuarios.grabarUsuario(usuarios);
				if (accion) {
					lblError.setText("Usuario dada de alta correctamente");
				}else {
					lblError.setText("Error en el alta de usuario");
				}
				
			}
		});
		btnAlta.setBounds(69, 222, 89, 23);
		frame.getContentPane().add(btnAlta);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el empleado?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					accion = accUsuarios.deleteUsuario(idUsuario);
					if (accion) {
						initialize();
						JOptionPane.showMessageDialog(null, "Usuario borrada correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado del usuario");
					}
				} else System.out.println("vale... no borro nada...");
			}
		});

		btnBaja.setBounds(192, 222, 89, 23);
		frame.getContentPane().add(btnBaja);
		
		JButton btnModificar = new JButton("Modificaci\u00F3n");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar el usuario?", "Confirmar modificación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					usuarios = llenaCamposDto();
					accion = accUsuarios.updateUsuarios(usuarios);
					if (accion) {
						initialize();
						JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar el usuario");
					}
				} else System.out.println("vale... no hago nada...");
			}

		});

		btnModificar.setBounds(321, 222, 103, 23);
		frame.getContentPane().add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
			}
		});
		btnVolver.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialize();
			}
		});
		btnLimpiar.setBounds(335, 45, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ObjetoJComboBox> cadena = accUsuarios.listaUsuarios();
				Object nomBuscado = JOptionPane.showInputDialog(null,"Seleccione una opción","",JOptionPane.PLAIN_MESSAGE,null,cadena.toArray(),null);
				if (nomBuscado != null){
					if (nomBuscado == "" ) {
						JOptionPane.showMessageDialog(null, "Usuario no encontrado");
					}else {
						usuarios = new UsuariosDTO();
						usuarios = accUsuarios.buscaUsuario(nomBuscado.toString());
						if (usuarios.getIdUsuario().equals(0)) {
							JOptionPane.showMessageDialog(null, "Usuario no encontrado");	
						} else {
							initialize();
							llenaCamposPantalla(usuarios);
							lblError.setText("");
						}
					}
				}				
			}
		});
		btnBuscar.setBounds(335, 76, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	private void llenaCamposPantalla(UsuariosDTO entrada) {
		//llenamos los campos de pantalla con el DTO de clientes
		idUsuario = entrada.getIdUsuario();
		textNombre.setText(entrada.getNombre());
		textUser.setText(entrada.getUsuario());
		textApellidos.setText(entrada.getApellidos());
		textMail.setText(entrada.getEmail());
		String valorSeguridad = accUsuarios.buscaSeguridad(entrada.getNivelSeguridad());
		comboBoxSeguridad.getModel().setSelectedItem(valorSeguridad);
		textPassword.setText(entrada.getPassword());
	}
	
	private UsuariosDTO llenaCamposDto() {
		usuarios = new UsuariosDTO();
		usuarios.setApellidos(textApellidos.getText());
		usuarios.setEmail(textMail.getText());
		usuarios.setIdUsuario(idUsuario);
		String variable = (String) comboBoxSeguridad.getSelectedItem().toString();
		usuarios.setNivelSeguridad(accUsuarios.buscaIdSeguridad(variable));
		usuarios.setNombre(textNombre.getText());
		usuarios.setPassword(textPassword.getText());
		usuarios.setUsuario(textUser.getText());
		return usuarios;
	}	
}
