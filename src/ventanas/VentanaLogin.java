package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesServiceImpl;


public class VentanaLogin {

	private JFrame frame;
	private JPasswordField textPassword;
	private JTextField textUser;
	JButton btnAcceder;
	AccionesServiceImpl accService = new AccionesServiceImpl();
	ServiceDTO control = new ServiceDTO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
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
	public VentanaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		control = accService.obtenerVentanaActual();		
		frame = new JFrame("Acceso a Garmared Factura");
		frame.setBounds(100, 100, control.getAnchoVentana()/3, control.getAltoVentana()/3);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(97, 52, 66, 14);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblDeAcceso = new JLabel("Clave de acceso :");
		lblDeAcceso.setBounds(58, 91, 105, 23);
		frame.getContentPane().add(lblDeAcceso);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(184, 92, 96, 20);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		textUser = new JTextField();
		textUser.setColumns(10);
		textUser.setBounds(184, 49, 96, 20);
		frame.getContentPane().add(textUser);
		
		JLabel labelError = new JLabel("");
		labelError.setBounds(46, 236, 201, 14);
		frame.getContentPane().add(labelError);
		
		EventosTecladoRaton teclado = new EventosTecladoRaton();
		btnAcceder = new JButton("Acceder");
		btnAcceder.addKeyListener(teclado);
		btnAcceder.addActionListener(teclado);
			
		btnAcceder.setBounds(308, 137, 89, 23);
		frame.getContentPane().add(btnAcceder);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	private class EventosTecladoRaton extends java.awt.event.KeyAdapter implements java.awt.event.ActionListener{

		private static final int VK_ENTER = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			String usuario = textUser.getText();
			String password = textPassword.getText();
			//Integer acceso = 0;
			
			if ((usuario.isEmpty() == true) || (password.isEmpty() == true)) {
				JOptionPane.showMessageDialog(null, "Usuraio o clave no informados");
			}else {
				control.setAcceso(accService.controlLogin(usuario, password));
				if (control.getAcceso() > 0) {
					textUser.setText(" ");
					textPassword.setText(null);
					VentanaPrincipal menu = new VentanaPrincipal(control);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario no autorizado");
				}
			}
			
		}
		
		public void keyPressed(java.awt.event.KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) btnAcceder.doClick();
		}
		
	}
}
