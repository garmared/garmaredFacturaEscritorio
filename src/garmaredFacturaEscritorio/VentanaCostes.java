package garmaredFacturaEscritorio;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import acciones.dto.CostesDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.accionesCostesImpl;

public class VentanaCostes {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	
	private JTextField textCoste;
	private Boolean accion;
	private CostesDTO costes;
	private int idCoste;
	accionesCostesImpl accCostes = new accionesCostesImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (sesionGlobal != null) {
						VentanaCostes window = new VentanaCostes(sesionGlobal);
						window.frame.setVisible(true);
					} 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaCostes(ServiceDTO control) {
		sesionGlobal = control;
		//initialize(control.getNombreEmpresa());
		if (control.getNombreEmpresa()!= null) {
			initialize(control.getNombreEmpresa());
		}else initialize("empresa");
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Costes de la empresa " + nombre);
		frame.setBounds(100, 100, 450, 300);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textCoste = new JTextField();
		textCoste.setBounds(191, 92, 96, 20);
		frame.getContentPane().add(textCoste);
		textCoste.setColumns(10);
		
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(41, 148, 383, 14);
		frame.getContentPane().add(lblError);
		
		JLabel lblNewLabel = new JLabel("Coste");
		lblNewLabel.setBounds(88, 95, 48, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAltaCoste = new JButton("Alta coste");
		btnAltaCoste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String coste;
				accion = false;
				//llenamos el DTO de costes
				costes = new CostesDTO();
				costes.setDescripcion(textCoste.getText());
				costes.setIdEmpresa(sesionGlobal.getIdEmpresa());
				//grabamos los datos.
				accion= accCostes.grabarCoste(costes);
				if (accion) {
					JOptionPane.showMessageDialog(null, "Coste dado de alta correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error en el alta de costes");
				}
			}
		});
		btnAltaCoste.setBounds(51, 173, 89, 23);
		frame.getContentPane().add(btnAltaCoste);
		
		JButton btnBaja = new JButton("Baja Coste");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el coste?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					accion = accCostes.deleteCoste(idCoste);
					if (accion) {
						initialize(sesionGlobal.getNombreEmpresa());
						JOptionPane.showMessageDialog(null, "Coste borrado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado del coste");
					}
				} else System.out.println("vale... no borro nada...");
			}
		});


		btnBaja.setBounds(178, 173, 89, 23);
		frame.getContentPane().add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar Coste");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar el coste?", "Confirmar modificación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					costes.setDescripcion(textCoste.getText());
					costes.setIdCoste(idCoste);
					
					accion = accCostes.updateCoste(costes);
					if (accion) {
						initialize(sesionGlobal.getNombreEmpresa());
						JOptionPane.showMessageDialog(null, "Coste modificado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar el coste");
					}
				} else System.out.println("vale... no hago nada...");
			}
		});
		
		btnModificar.setBounds(299, 173, 125, 23);
		frame.getContentPane().add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
			}
		});
		btnVolver.setBounds(317, 34, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialize(sesionGlobal.getNombreEmpresa());
			}
		});
		btnLimpiar.setBounds(317, 68, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buscado = JOptionPane.showInputDialog("Escribe el coste a buscar");
				if (buscado != null){
					if (buscado != "" && !(buscado.isEmpty())) {
						costes = new CostesDTO();
						costes.setDescripcion(buscado);
						costes.setIdEmpresa(sesionGlobal.getIdEmpresa());
						costes = accCostes.buscaCoste(costes);
						if (costes.getIdCoste().equals(0)) {
							JOptionPane.showMessageDialog(null, "Coste no encontrado");	
						} else {
							initialize(sesionGlobal.getNombreEmpresa());
							textCoste.setText(costes.getDescripcion());
							idCoste=costes.getIdCoste();
						}
					}
				}				
			}
		});
		btnBuscar.setBounds(317, 103, 89, 23);
		frame.getContentPane().add(btnBuscar);

		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}
