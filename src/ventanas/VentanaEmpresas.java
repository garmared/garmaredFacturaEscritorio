package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	private JTextField textNif;
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
	
	private JLabel lblNif;
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
	private JTextField textRegCivil;
	private JTextField textTomo;
	private JTextField textFolio;
	private JTextField textHoja;
	private JTextField textInscripcion;
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
		frame.setBounds(100, 100, 540, 800);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
		textNif = new JTextField();
		textNif.setBounds(116, 8, 96, 20);
		frame.getContentPane().add(textNif);
		textNif.setColumns(10);
				
		JLabel lblNif = new JLabel("Nif");
		lblNif.setBounds(10, 14, 48, 14);
		frame.getContentPane().add(lblNif);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 42, 48, 14);
		frame.getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(116, 36, 266, 20);
		frame.getContentPane().add(textNombre);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(10, 70, 75, 14);
		frame.getContentPane().add(lblDireccin);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(116, 64, 266, 20);
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
		textMail.setBounds(116, 233, 228, 20);
		frame.getContentPane().add(textMail);
		
		JLabel lblWeb = new JLabel("Web");
		lblWeb.setBounds(10, 263, 48, 14);
		frame.getContentPane().add(lblWeb);
		
		textWeb = new JTextField();
		textWeb.setColumns(10);
		textWeb.setBounds(116, 263, 228, 20);
		frame.getContentPane().add(textWeb);
		
		JLabel lblIban = new JLabel("IBAN");
		lblIban.setBounds(10, 298, 75, 14);
		frame.getContentPane().add(lblIban);
		
		textIban = new JTextField();
		textIban.setColumns(10);
		textIban.setBounds(116, 298, 187, 20);
		frame.getContentPane().add(textIban);
		
		JLabel lblActivo = new JLabel("Activo");
		lblActivo.setBounds(239, 437, 48, 14);
		frame.getContentPane().add(lblActivo);
		JLabel lblCP = new JLabel("C\u00F3digo Postal");
		lblCP.setBounds(255, 129, 89, 14);
		frame.getContentPane().add(lblCP);
		
		textCP = new JTextField();
		textCP.setColumns(10);
		textCP.setBounds(354, 123, 96, 20);
		frame.getContentPane().add(textCP);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(10, 326, 96, 14);
		frame.getContentPane().add(lblObservaciones);
		
		textObserv = new JTextField();
		textObserv.setColumns(10);
		textObserv.setBounds(116, 326, 349, 33);
		frame.getContentPane().add(textObserv);

		rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setBounds(293, 432, 44, 23);
		frame.getContentPane().add(rdbtnSi);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(338, 432, 44, 23);
		rdbtnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNo.setSelected(false);
				rdbtnSi.setSelected(true);
			}
		});				
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSi.setSelected(false);
				rdbtnNo.setSelected(true);
			}
		});
		frame.getContentPane().add(rdbtnNo);
		
		JLabel lblRegistroCivil = new JLabel("Regsitro Civil");
		lblRegistroCivil.setBounds(10, 373, 96, 14);
		frame.getContentPane().add(lblRegistroCivil);
		
		textRegCivil = new JTextField();
		textRegCivil.setColumns(10);
		textRegCivil.setBounds(116, 373, 96, 20);
		frame.getContentPane().add(textRegCivil);
		
		JLabel lblTomo = new JLabel("Tomo");
		lblTomo.setBounds(239, 373, 53, 14);
		frame.getContentPane().add(lblTomo);
		
		textTomo = new JTextField();
		textTomo.setColumns(10);
		textTomo.setBounds(302, 370, 96, 20);
		frame.getContentPane().add(textTomo);
		
		JLabel lblFolio = new JLabel("Folio");
		lblFolio.setBounds(10, 404, 96, 14);
		frame.getContentPane().add(lblFolio);
		
		textFolio = new JTextField();
		textFolio.setColumns(10);
		textFolio.setBounds(116, 404, 96, 20);
		frame.getContentPane().add(textFolio);
		
		JLabel lblHoja = new JLabel("Hoja");
		lblHoja.setBounds(239, 404, 44, 14);
		frame.getContentPane().add(lblHoja);
		
		textHoja = new JTextField();
		textHoja.setColumns(10);
		textHoja.setBounds(305, 401, 96, 20);
		frame.getContentPane().add(textHoja);
		
		JLabel lblInscripcion = new JLabel("Inscripci\u00F3n");
		lblInscripcion.setBounds(10, 435, 96, 14);
		frame.getContentPane().add(lblInscripcion);
		
		textInscripcion = new JTextField();
		textInscripcion.setColumns(10);
		textInscripcion.setBounds(116, 435, 96, 20);
		frame.getContentPane().add(textInscripcion);
		
		JButton btnAltaEmpresa = new JButton("Alta Empresa");
		btnAltaEmpresa.setBounds(38, 506, 124, 23);
		frame.getContentPane().add(btnAltaEmpresa);
		btnAltaEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//llenamos el DTO de empresas
				empresas = llenaCamposDto();
				//grabamos los datos. Es igual que en proveedor pero el TIPO es "E" de empresas.
				accion= accEmpresas.grabarEmpresas(empresas);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Empresa dada de alta correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error en el alta de empresa");
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
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Empresa borrada correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado de la empresa");
					}
				} else System.out.println("vale... no borro nada...");
			}
		});

		btnBaja.setBounds(172, 506, 123, 23);
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

		btnModificar.setBounds(305, 506, 160, 23);
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
		separator.setBounds(433, 351, -422, -7);
		separator.setVisible(true);
		frame.getContentPane().add(separator);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiaPantalla();
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
						
					}else {
						empresas = new EmpresasDTO();
						empresas.setIdEmpresa(0);
						empresas.setNombre(nomBuscado);
						empresas.setTipo("E");
						empresas = accEmpresas.buscaEmpresa(empresas);
						if (empresas.getIdEmpresa().equals(0)) {
							JOptionPane.showMessageDialog(null, "Empresa no encontrada");	
						} else {
							initialize();
							llenaCamposPantalla(empresas);
						}
					}
				}				
			}
		});

		btnBuscar.setBounds(425, 79, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	
	private void llenaCamposPantalla(EmpresasDTO entrada) {
		//llenamos los campos de pantalla con el DTO de clientes
		idEmpresa = entrada.getIdEmpresa();
		textNombre.setText(entrada.getNombre());
		textNif.setText(entrada.getNif());
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
			rdbtnNo.setSelected(false);
			rdbtnSi.setSelected(true);
		} else {
			rdbtnNo.setSelected(true);
			rdbtnSi.setSelected(false);
		}
		textRegCivil.setText(entrada.getRegMercantil());
		textTomo.setText(String.valueOf(entrada.getTomo()));
		textFolio.setText(String.valueOf(entrada.getFolio()));
		textHoja.setText(String.valueOf(entrada.getHoja()));
		textInscripcion.setText(String.valueOf(entrada.getInscripcion()));
	}
	
	private  EmpresasDTO llenaCamposDto(){
		//llenamos el DTO de clientes con los datos de pantalla
		empresas = new EmpresasDTO();
		empresas.setIdEmpresa(idEmpresa);
		empresas.setDireccion(textDireccion.getText());
		empresas.setTipo("E");
		empresas.setNif(textNif.getText());
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
		
		empresas.setRegMercantil(textRegCivil.getText());
		empresas.setTomo(Integer.valueOf(textTomo.getText()));
		empresas.setFolio(Integer.valueOf(textFolio.getText()));
		empresas.setHoja(Integer.valueOf(textHoja.getText()));
		empresas.setInscripcion(Integer.valueOf(textInscripcion.getText()));
				
		return empresas;
	}
	
	private void limpiaPantalla() {
		textNombre.setText(" ");
		textNif.setText(" ");
		textDireccion.setText(" ");
		textPoblacion.setText(" ");
		textProvincia.setText(" ");
		textTelefono1.setText(" ");
		textTelefono2.setText(" ");
		textTelefono3.setText(" ");
		textPersonaContact.setText(" ");
		textWeb.setText(" ");
		textMail.setText(" ");
		textIban.setText(" ");
		textObserv.setText(" ");
		textCP.setText(" ");
		rdbtnSi.setSelected(false);
		rdbtnNo.setSelected(false);
		textRegCivil.setText(" ");
		textTomo.setText(" ");
		textFolio.setText(" ");
		textHoja.setText(" ");
		textInscripcion.setText(" ");
	}
}
