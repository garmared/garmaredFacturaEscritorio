package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import acciones.dto.ConceptosDTO;
import acciones.dto.ConstantesDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesConstantesImpl;
import acciones.service.impl.AccionesServiceImpl;

public class VentanaConstantes {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private JComboBox textTipoReg;
	private Boolean accion;
	private ConstantesDTO constantes;
	private int idConcepto;
	AccionesConstantesImpl accConstantes = new AccionesConstantesImpl();
	AccionesServiceImpl accService = new AccionesServiceImpl();
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Create the application.
	 */
	public VentanaConstantes(ServiceDTO control) {
		sesionGlobal = control;
		initialize(control.getNombreEmpresa());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Gestión Contantes");
		frame.setBounds(100, 100, 700, 700);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblTipoReg = new JLabel("Tipo constante");
		lblTipoReg.setBounds(41, 37, 96, 14);
		frame.getContentPane().add(lblTipoReg);
		
		JButton btnAlta = new JButton("Alta constante");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accion = false;
				ArrayList<ConstantesDTO> entrada= llenaModelo();
				accion= accConstantes.grabarConstante(entrada);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Constante dada de alta correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error con el alta de la constante");
				}
			}
		});
		btnAlta.setBounds(28, 469, 122, 23);
		frame.getContentPane().add(btnAlta);
		
		JButton btnBaja = new JButton("Baja Constante");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el concepto?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					accion = accConstantes.deleteConstante((String) textTipoReg.getSelectedItem());
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Constante borrado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado de la constabte");
					}
				} else System.out.println("vale... no borro nada...");
			}
		});

		btnBaja.setBounds(160, 469, 125, 23);
		frame.getContentPane().add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar Constante");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar la constante?", "Confirmar modificación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					ArrayList<ConstantesDTO> entrada = llenaModelo();;
					accion = accConstantes.updateConstante(entrada);
					if (accion){
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Concepto modificado correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar el concepto");
					}
				} else System.out.println("vale... no hago nada...");
			}
		});
		btnModificar.setBounds(295, 469, 166, 23);
		frame.getContentPane().add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
				accService.abrirVentanaPrincipal(sesionGlobal);
			}
		});
		btnVolver.setBounds(502, 33, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiaPantalla();
			}
		});
		btnLimpiar.setBounds(502, 67, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
	
		JButton btnAltaTipo = new JButton("Alta Tipo");
		btnAltaTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tipoConstante = new JTextField();
				Object [] mensaje= {
						"Tipo de Constante :", tipoConstante
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Introduce el nuevo tipo de constante", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
					textTipoReg.addItem(tipoConstante.getText());
				}
			}
		});
		btnAltaTipo.setBounds(502, 101, 89, 23);
		frame.getContentPane().add(btnAltaTipo);
		
		
		modelo.setColumnIdentifiers(new Object[] {"Código", "Nombre"});
		modelo.addRow(new Object[] {"", ""});
		table = new JTable();
	
		table.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (table.getSelectedColumn()==1) {
					if (e.getKeyCode()== KeyEvent.VK_TAB) {
						modelo.addRow(new Object[] {"",""});
					}
				}				
			}

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(28, 64, 456, 383);
		table.setModel(modelo);
		frame.getContentPane().add(scrollPane);

		textTipoReg = new JComboBox();
		textTipoReg.setBounds(160, 33, 96, 20);
		frame.getContentPane().add(textTipoReg);
		textTipoReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llenaTabla();
			}
		});
		llenaTipoReg();
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}


	protected  ArrayList<ConstantesDTO> llenaModelo() {
		int elementos = modelo.getRowCount();
		//grabamos los datos.
		ArrayList<ConstantesDTO> entrada = new ArrayList<ConstantesDTO>();
		String campo="";
		int valor=0;
		for (int i = 0; i< elementos; i++) {
			constantes = new ConstantesDTO();
			constantes.setTipoReg((String) textTipoReg.getSelectedItem());
			campo = String.valueOf(modelo.getValueAt(i,1));
			if (campo !=" " && campo != "") {
				constantes.setNombre(String.valueOf(modelo.getValueAt(i, 1)));
			} else {
				break;
			}
			campo = String.valueOf(modelo.getValueAt(i,0));
			if (campo !=" " && campo != "") {
				constantes.setCodigo(Integer.parseInt(campo));
			}else {
				break;
			}
			entrada.add(constantes);
		}		
		return entrada;
	}

	protected void llenaTabla() {
		ArrayList<ConstantesDTO> lista = accConstantes.buscaConstantes((String) textTipoReg.getSelectedItem());
		limpiaTabla();
		if(lista != null) {
			for (int i=0; i<lista.size();i++) {
				ConstantesDTO dato = lista.get(i);
				modelo.addRow(new Object[]{dato.getCodigo(),dato.getNombre()});
			}
			modelo.addRow(new Object[] {"",""});
		}else {modelo.addRow(new Object[] {"",""});}
		
		
	}

	private void limpiaTabla() {
		int filas = table.getRowCount();
		filas --;
		for (int i = filas; i >= 0; i--) {
			modelo.removeRow(i);
		}		
	}

	private void llenaTipoReg() {
		textTipoReg.removeAllItems();
		textTipoReg.addItem("----");
		ArrayList<String> tipos = accConstantes.buscaTipos();
		if (tipos != null) {
			for (int i = 0; i < tipos.size(); i++) {
				textTipoReg.addItem(tipos.get(i));
			}
		}
	}

	protected void limpiaPantalla() {
		limpiaTabla();
		modelo.addRow(new Object[] {"",""});
		llenaTipoReg();
	}
}
