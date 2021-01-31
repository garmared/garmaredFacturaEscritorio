package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import acciones.dto.CostesDTO;
import acciones.dto.FacturasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesClientesImpl;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesCostesImpl;
import acciones.service.impl.AccionesEmpresasImpl;
import acciones.service.impl.AccionesFacturaImpl;
import acciones.service.impl.AccionesProyectosImpl;
import acciones.service.impl.AccionesServiceImpl;
import estructuras.DatosCostes;

public class ListadoCostesIndirectos extends JFrame {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private FacturasDTO factura;
	private DatosCostes datos;

	AccionesFacturaImpl accFactura = new AccionesFacturaImpl();
	AccionesClientesImpl accClientes = new AccionesClientesImpl();
	AccionesServiceImpl accService = new AccionesServiceImpl();
	AccionesProyectosImpl accProyecto = new AccionesProyectosImpl();
	AccionesConceptosImpl accConcepto = new AccionesConceptosImpl();
	AccionesCostesImpl accCostes = new AccionesCostesImpl();
	AccionesEmpresasImpl accProveedor = new AccionesEmpresasImpl();
	

	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoCostesIndirectos window = new ListadoCostesIndirectos(sesionGlobal);
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
	public ListadoCostesIndirectos(ServiceDTO control) {
		sesionGlobal = control;
		initialize(sesionGlobal.getNombreEmpresa());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nombre) {
		frame = new JFrame("Listado de costes indirecots de la empresa " + nombre);
		frame.setBounds(100, 100, 850, 900);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); // esto cierra la ventana
				VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
			}
		});
		btnVolver.setBounds(529, 21, 89, 23);
		frame.getContentPane().add(btnVolver);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CostesDTO paramConsulta = new CostesDTO();
				JComboBox proyecto = new JComboBox();
				ArrayList<ObjetoJComboBox> cadena = accProyecto.consultaProyectos(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (var i = 0; i < cadena.size(); i++) {
						proyecto.addItem(cadena.get(i));
					}
					proyecto.addItem(new ObjetoJComboBox(0, "Todos"));
				}
				JComboBox concepto = new JComboBox();
				cadena = accConcepto.consultaConceptos(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (var i = 0; i < cadena.size(); i++) {
						concepto.addItem(cadena.get(i));
					}
					concepto.addItem(new ObjetoJComboBox(0, "Todos"));
				}
				JComboBox tipoCoste = new JComboBox();
				cadena = accCostes.consultaCostes(sesionGlobal.getIdEmpresa());
				if (cadena != null) {
					for (var i = 0; i < cadena.size(); i++) {
						tipoCoste.addItem(cadena.get(i));
					}
					tipoCoste.addItem(new ObjetoJComboBox(0, "Todos"));
				}
				Object[] mensaje = { "Tipo Coste:", tipoCoste, "Proyecto:", proyecto, "Concepto:", concepto };
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Búsqueda de cliente",
						JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION) {
					ObjetoJComboBox temporal = new ObjetoJComboBox(0,"");
					temporal = (ObjetoJComboBox) tipoCoste.getSelectedItem();
					paramConsulta.setTipoCoste((temporal.getNumero()));
					temporal = (ObjetoJComboBox) proyecto.getSelectedItem();
					paramConsulta.setProyecto((temporal.getNumero()));
					temporal = (ObjetoJComboBox) concepto.getSelectedItem();
					paramConsulta.setConcepto((temporal.getNumero()));
					paramConsulta.setIdEmpresa(sesionGlobal.getIdEmpresa());
					Connection connection = accService.getConexion();
					DefaultTableModel modelo = new DefaultTableModel();
					String consulta;
					consulta = accCostes.creaConsulta(paramConsulta);

					ResultSet rs = accService.getTabla(consulta, connection);
					modelo.setColumnIdentifiers(new Object[] { "identificador", "Tipo Coste", "Proyecto", "Concepto","Importe", "Descripción"});
					table = new JTable(modelo);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							sesionGlobal.setNoPrincipal("S");
							DefaultTableModel modeloAux = (DefaultTableModel) table.getModel();
							if (table.getSelectedRow() != -1) {
								int codigo = (int) modeloAux.getValueAt(table.getSelectedRow(), 0);
								sesionGlobal.setIdentificador(codigo);
								VentanaCostesIndirectos ventana = new VentanaCostesIndirectos(sesionGlobal);
							} else {
								JOptionPane.showMessageDialog(null, "Selecciona una única fila");
							}
						}
					});
					try {
						while (rs.next()) {
							datos = llenaJtable(rs);
							modelo.addRow(new Object[] {datos.getIdentificador(), datos.getTipoCoste(), datos.getProyecto(), datos.getConcepto(), datos.getImporte(), datos.getDescripcion() });
						}
						table.setModel(modelo);
						if (table.getRowCount() == 0) {
							JOptionPane.showMessageDialog(null, "Costes indirectos no encontrados para esta selección");
						}
						rs.close();
						connection.close();
					} catch (Exception e1) {
						System.out.println(e1);
					}
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.setBounds(17, 75, 800, 800);
					frame.getContentPane().add(scrollPane);

				}
			}

		});

		btnBuscar.setBounds(42, 21, 89, 23);
		frame.getContentPane().add(btnBuscar);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	private DatosCostes llenaJtable(ResultSet rs) {
		datos = new DatosCostes();
		try {
			datos.setConcepto(accConcepto.buscaDescripcion(rs.getInt("id_concepto")));
			datos.setDescripcion(rs.getString("descripcion"));
			datos.setIdentificador(rs.getInt("id_costeInd"));
			datos.setImporte(rs.getDouble("importe"));
			datos.setTipoCoste(accCostes.buscaNombre(rs.getInt("id_coste")));
			datos.setProyecto(accProyecto.buscaDescripcion(rs.getInt("id_proyecto"),sesionGlobal.getIdEmpresa()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}

}
