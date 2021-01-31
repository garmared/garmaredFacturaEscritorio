package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import acciones.dto.CostesDTO;
import acciones.dto.FacturasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesCostesImpl;
import acciones.service.impl.AccionesEmpresasImpl;
import acciones.service.impl.AccionesFacturaImpl;
import acciones.service.impl.AccionesProyectosImpl;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class VentanaCostesIndirectos {

	private JFrame frame;
	private JTextField textImporte;
	private JTextField textDescripcion;
	static ServiceDTO sesionGlobal;
	private JComboBox comboProyecto;
	private JComboBox comboConcepto;
	private JComboBox comboCoste;
	AccionesCostesImpl accCostes = new AccionesCostesImpl();
	AccionesConceptosImpl accConceptos = new AccionesConceptosImpl();
	AccionesEmpresasImpl accEmpresas = new AccionesEmpresasImpl();
	AccionesProyectosImpl accProyecto = new AccionesProyectosImpl();
	Boolean accion = false;
	private CostesDTO costesInd;
	private int idCoste;
	
	
	public VentanaCostesIndirectos(ServiceDTO control) {
		sesionGlobal = control;
		initialize(control.getNombreEmpresa());
		if (sesionGlobal.getNoPrincipal()=="S") {
			llenaPantalla();
		}
	}

	private void llenaPantalla() {
		// TODO Auto-generated method stub
		costesInd = new CostesDTO();
		costesInd=accCostes.buscaCostesIndirectos(sesionGlobal.getIdentificador(),sesionGlobal.getIdEmpresa());
		llenaCamposPantalla(costesInd);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Costes indirectos de la empresa " + nombre);
		frame.setBounds(100, 100, 450, 485);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProyecto = new JLabel("Proyecto");
		lblProyecto.setBounds(27, 38, 96, 14);
		frame.getContentPane().add(lblProyecto);
		
		comboProyecto = new JComboBox();
		comboProyecto.setBounds(145, 34, 96, 22);
		frame.getContentPane().add(comboProyecto);
		comboProyecto.addItem("----");
		
		ArrayList<ObjetoJComboBox> proyectos = accProyecto.consultaProyectos(sesionGlobal.getIdEmpresa());
		
		if (proyectos != null) {
			for (var i = 0; i < proyectos.size(); i++) {
				comboProyecto.addItem(proyectos.get(i));
			}
		}
		
		comboConcepto = new JComboBox();
		comboConcepto.setBounds(145, 67, 96, 22);
		frame.getContentPane().add(comboConcepto);
		comboConcepto.addItem("----");
		ArrayList<ObjetoJComboBox> conceptos = accConceptos.consultaConceptos(sesionGlobal.getIdEmpresa());
		
		if (conceptos != null) {
			for (var i = 0; i < conceptos.size(); i++) {
				comboConcepto.addItem(conceptos.get(i));
			}
		}
	
		comboCoste = new JComboBox();
		comboCoste.setBounds(145, 100, 96, 22);
		frame.getContentPane().add(comboCoste);
		comboCoste.addItem("----");
		
		ArrayList<ObjetoJComboBox> costes = accCostes.consultaCostes(sesionGlobal.getIdEmpresa());
	
		if (costes != null) {
			for (var i = 0; i < costes.size(); i++) {
				comboCoste.addItem(costes.get(i));
			}
		}
		
			
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(27, 71, 96, 14);
		frame.getContentPane().add(lblConcepto);
		
		JLabel lblCoste = new JLabel("Coste");
		lblCoste.setBounds(27, 104, 48, 14);
		frame.getContentPane().add(lblCoste);
		
		JLabel lblImporte = new JLabel("Importe");
		lblImporte.setBounds(27, 136, 48, 14);
		frame.getContentPane().add(lblImporte);
		
		textImporte = new JTextField();
		textImporte.setColumns(10);
		textImporte.setBounds(145, 133, 96, 20);
		frame.getContentPane().add(textImporte);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(27, 174, 75, 14);
		frame.getContentPane().add(lblDescripcion);
		
		textDescripcion = new JTextField();
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(145, 171, 96, 20);
		frame.getContentPane().add(textDescripcion);
	
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
				if (sesionGlobal.getNoPrincipal()=="N") {
					VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
				}
			}
		});
		btnVolver.setBounds(564, 34, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiaPantalla();
			}
		});
		btnLimpiar.setBounds(564, 62, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox proyecto = new JComboBox();
				ArrayList<ObjetoJComboBox> cadena = accProyecto.consultaProyectos(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (var i = 0; i < cadena.size(); i++) {
						proyecto.addItem(cadena.get(i));
					}
				}
				JComboBox concepto = new JComboBox();
				cadena = accConceptos.consultaConceptos(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (var i = 0; i < cadena.size(); i++) {
						concepto.addItem(cadena.get(i));
					}
				}
				Object [] mensaje= {
						"Proyecto:", proyecto,
						"Concepto:", concepto
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Búsqueda de Costes indirectos", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION){
						CostesDTO varCostes = new CostesDTO();
						varCostes.setIdEmpresa(sesionGlobal.getIdEmpresa());
						ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
						temporal = (ObjetoJComboBox) proyecto.getSelectedItem();
						varCostes.setProyecto(temporal.getNumero());
						varCostes = accCostes.buscaCostesIndirectos(varCostes);
						if (varCostes.getIdCoste().equals(0)) {
							JOptionPane.showMessageDialog(null, "Coste no encontrado");	
						} else {
							limpiaPantalla();
							llenaCamposPantalla(varCostes);
						}
					
				}				
			
			}

		});
		btnBuscar.setBounds(564, 93, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		JButton btnAlta = new JButton("Alta Coste");
		btnAlta.setBounds(46, 416, 109, 23);
		frame.getContentPane().add(btnAlta);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
				//llenamos el DTO 
				CostesDTO entrada = new CostesDTO();
				entrada = llenaCamposDto();
				//grabamos los datos
				accion= accCostes.grabarCosteIndirecto(entrada);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Coste dado de alta correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error en el alta del coste");
				}
			}
		});
		
		JButton btnBaja = new JButton("Baja Coste");
		btnBaja.setBounds(181, 416, 114, 23);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar la factura?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					accion = accCostes.deleteCosteIndirecto(idCoste);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Factura borrada correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error en el borrado de la factura");
					}
				} else System.out.println("vale... no borro nada...");
				}
			});

		frame.getContentPane().add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar Coste");
		btnModificar.setBounds(327, 416, 135, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea modificar la factura?", "Confirmar modificación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					costesInd = llenaCamposDto();
					accion = accCostes.updateCosteIndirecto(costesInd);
					if (accion) {
						limpiaPantalla();
						JOptionPane.showMessageDialog(null, "Factura modificada correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "error al modificar la factura");
					}
				} else System.out.println("vale... no hago nada...");
			}
		});
		frame.getContentPane().add(btnModificar);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	

	private void limpiaPantalla() {
		textImporte.setText(" ");
		textDescripcion.setText(" ");
		comboConcepto.getModel().setSelectedItem("----");
		comboCoste.getModel().setSelectedItem("----");
		comboProyecto.getModel().setSelectedItem("----");		
	}
	
	private CostesDTO llenaCamposDto() {
		CostesDTO salida = new CostesDTO();
		salida.setIdCoste(idCoste);
		salida.setIdEmpresa(sesionGlobal.getIdEmpresa());
		salida.setDescripcion(textDescripcion.getText());
		String variable = (String) comboConcepto.getSelectedItem().toString();
		salida.setConcepto(accConceptos.buscaConcepto(variable));
		variable = (String) comboCoste.getSelectedItem().toString();
		salida.setTipoCoste(accCostes.buscaCoste(variable));
		variable = (String) comboProyecto.getSelectedItem().toString();
		salida.setProyecto(accProyecto.buscaProyecto(variable,sesionGlobal.getIdEmpresa()));
		salida.setImporte(Double.valueOf(textImporte.getText()));
		return salida;
	}
	
	private void llenaCamposPantalla(CostesDTO varCostes) {
		idCoste = varCostes.getIdCoste();
		textImporte.setText(String.valueOf(varCostes.getImporte()));
		textDescripcion.setText(varCostes.getDescripcion());
		String valorCombo = accConceptos.buscaDescripcion(varCostes.getConcepto());
		comboConcepto.getModel().setSelectedItem(valorCombo);
		valorCombo = accCostes.buscaDescripcion(varCostes.getTipoCoste());
		comboCoste.getModel().setSelectedItem(valorCombo);
		valorCombo = accProyecto.buscaDescripcion(varCostes.getProyecto(),sesionGlobal.getIdEmpresa());
		comboProyecto.getModel().setSelectedItem(valorCombo);
		
	}
}
