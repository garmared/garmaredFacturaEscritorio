package garmaredFacturaEscritorio;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import acciones.dto.ConceptosDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesConceptosImpl;

public class VentanaConceptos {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private JTextField textConcepto;
	private Boolean accion;
	private ConceptosDTO conceptos;
	private int idConcepto;
	AccionesConceptosImpl accConceptos = new AccionesConceptosImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConceptos window = new VentanaConceptos(sesionGlobal);
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
	public VentanaConceptos(ServiceDTO control) {
		sesionGlobal = control;
		initialize(control.getNombreEmpresa());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Conceptos de la empresa " + nombre);
		frame.setBounds(100, 100, 450, 300);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textConcepto = new JTextField();
		textConcepto.setBounds(109, 92, 96, 20);
		frame.getContentPane().add(textConcepto);
		textConcepto.setColumns(10);
		
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(41, 148, 383, 14);
		frame.getContentPane().add(lblError);
		
		JLabel lblNewLabel = new JLabel("Concepto");
		lblNewLabel.setBounds(51, 95, 48, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAlta = new JButton("Alta concepto");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accion = false;
				//grabamos los datos.
				conceptos = new ConceptosDTO();
				conceptos.setDescripcion(textConcepto.getText());
				conceptos.setIdEmpresa(sesionGlobal.getIdEmpresa());
				accion= accConceptos.grabarConcepto(conceptos);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Concepto dado de alta correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error con el alta del concepto");
				}
			}
		});
		btnAlta.setBounds(51, 173, 89, 23);
		frame.getContentPane().add(btnAlta);
		
		JButton btnBaja = new JButton("Baja Concepto");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el concepto?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					accion = accConceptos.deleteConcepto(idConcepto);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Concepto borrado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado del concepto");
					}
				} else System.out.println("vale... no borro nada...");
			}
		});

		btnBaja.setBounds(164, 173, 103, 23);
		frame.getContentPane().add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar Concepto");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar el concepto?", "Confirmar modificación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					conceptos.setDescripcion(textConcepto.getText());
					conceptos.setIdConcepto(idConcepto);
					accion = accConceptos.updateConcepto(conceptos);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Concepto modificado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar el concepto");
					}
				} else System.out.println("vale... no hago nada...");
			}
		});
		btnModificar.setBounds(287, 173, 137, 23);
		frame.getContentPane().add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
				VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
			}
		});
		btnVolver.setBounds(317, 34, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiaPantalla();
			}
		});
		btnLimpiar.setBounds(317, 68, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buscado = JOptionPane.showInputDialog("Escribe el concepto a buscar");
				if (buscado != null){
					if (buscado != "" && !(buscado.isEmpty())) {
						conceptos = new ConceptosDTO();
						conceptos.setDescripcion(buscado);
						conceptos.setIdEmpresa(sesionGlobal.getIdEmpresa());
						conceptos = accConceptos.buscaConcepto(conceptos);
						if (conceptos.getIdConcepto().equals(0)) {
							JOptionPane.showMessageDialog(null, "Concepto no encontrado");	
						} else {
							limpiaPantalla();
							textConcepto.setText(conceptos.getDescripcion());
							idConcepto=conceptos.getIdConcepto();
						}
					}
				}				
			}
		});
		btnBuscar.setBounds(318, 102, 89, 23);
		frame.getContentPane().add(btnBuscar);

		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	protected void limpiaPantalla() {
		// TODO Auto-generated method stub
		textConcepto.setText(" ");;
	}
}
