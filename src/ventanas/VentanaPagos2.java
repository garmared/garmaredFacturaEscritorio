package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import acciones.dto.ConceptosDTO;
import acciones.dto.FacturasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.PagosDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesConstantesImpl;
import acciones.service.impl.AccionesFacturaImpl;
import acciones.service.impl.AccionesPagosImpl;
import acciones.service.impl.AccionesServiceImpl;
import ventanas.pruebas.TABLE_MODEL;

public class VentanaPagos2 extends JFrame {

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private Boolean accion;
	private PagosDTO pagos;
	private int idPago;
	JComboBox modaPago;
	AccionesPagosImpl accPagos = new AccionesPagosImpl();
	AccionesServiceImpl accService = new AccionesServiceImpl();
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	AccionesConstantesImpl accConstantes = new AccionesConstantesImpl();
	
    class TABLE_MODEL extends DefaultTableModel{
        public static final String INITIAL_VALUE = "";
        public TABLE_MODEL(){
            this.addColumn(COLUMN1);
            this.addColumn(COLUMN2);
            this.addColumn(COLUMN3);
            this.addColumn(COLUMN4);
            this.addColumn(COLUMN5);	         
        }
        public static final String COLUMN1="Vencimiento";
        public static final String COLUMN2="Fecha Pago";
        public static final String COLUMN3="Moda Pago";
        public static final String COLUMN4="Importe";
        public static final String COLUMN5="Observaciones";
        public void addData(String a,String b, String c, String d){
            final Object rowData[]={a,b,INITIAL_VALUE,c,d};
            this.addRow(rowData);
        }
    }
    TABLE_MODEL model=new TABLE_MODEL();

	public VentanaPagos2(ServiceDTO control) {
		sesionGlobal = control;
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Gestión pagos de la factura " + sesionGlobal.getIdentificador() + " de la empresa " + sesionGlobal.getNombreEmpresa());
		frame.setBounds(100, 100, 950, 900);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		montaTabla();
		table = new JTable(modelo);
		table.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (table.getSelectedColumn()==4) {
					if (e.getKeyCode()== KeyEvent.VK_TAB) {
						modaPago = new JComboBox();
						llenaModaPago();
						modelo.addRow(new Object[] {"","",modaPago,"",""});
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 45, 497, 200);
		frame.getContentPane().add(scrollPane);		
		
		JButton btnAlta = new JButton("Grabar");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<PagosDTO> entrada = new ArrayList<PagosDTO>();
				accion = false;
				//grabamos los datos.
				int elementos = modelo.getRowCount()-1;
				String campo = "";
				for (int i = 0; i < elementos; i++) {
					pagos = new PagosDTO();
					pagos.setIdEmpresa(sesionGlobal.getIdEmpresa());
					pagos.setIdProyecto(sesionGlobal.getIdentificador());
					pagos.setEstado("");
					campo = String.valueOf(table.getValueAt(i,0));
					if (campo !=" " && campo != "") {
						pagos.setVencimiento(Integer.parseInt(String.valueOf(modelo.getValueAt(i,0))));
						} else {pagos.setVencimiento(0);}
					campo = String.valueOf(table.getValueAt(i,1));
					if (campo !=" " && campo != ""){
						pagos.setFecha(Integer.parseInt(String.valueOf(modelo.getValueAt(i,1))));
					} else {pagos.setFecha(0);}
					campo = String.valueOf(table.getValueAt(i,2));
					if (campo !=" " && campo != ""){
						pagos.setTipo(campo);
					} else { pagos.setTipo("");}
					campo = String.valueOf(table.getValueAt(i,3));
					if (campo !=" " && campo != ""){
						pagos.setImporte(Double.parseDouble(String.valueOf(modelo.getValueAt(i,3))));
					} else { pagos.setTipo("");}
					campo = String.valueOf(table.getValueAt(i,4));
					if (campo !=" " && campo != ""){
						pagos.setObservaciones(campo);
					} else { pagos.setObservaciones("");}
					entrada.add(pagos);
				}
 				accion= accPagos.grabarPagos(entrada);
				if (accion) {
					limpiaPantalla();
					JOptionPane.showMessageDialog(null, "Pagos dados de alta correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error con el alta de los pagos");
				}
			}
		});
		btnAlta.setBounds(23, 11, 80, 23);
		frame.getContentPane().add(btnAlta);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //esto cierra la ventana
			}
		});
		btnVolver.setBounds(212, 11, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiaPantalla();
			}
		});
		btnLimpiar.setBounds(311, 11, 89, 23);
		frame.getContentPane().add(btnLimpiar);
		
	
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				int confirmado = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el concepto "+table.getValueAt(fila, 0)+"?", "Confirmar borrado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.OK_OPTION == confirmado) {
					modelo.removeRow(fila);
					modaPago = new JComboBox();
					llenaModaPago();
					modelo.addRow(new Object[] {"","",modaPago,"",""});
				}
			}
		});
		btnEliminar.setBounds(113, 11, 89, 23);
		frame.getContentPane().add(btnEliminar);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	private void montaTabla() {
		Connection connection = accService.getConexion();
		String consulta;
		PagosDTO paramConsulta = new PagosDTO();
		paramConsulta.setIdEmpresa(sesionGlobal.getIdEmpresa());
		paramConsulta.setTipo("P");
		consulta=accPagos.creaConsultaPagos(paramConsulta);
												
		ResultSet rs = accService.getTabla(consulta, connection);
		
		modelo.setColumnIdentifiers(new Object[]{"Vencimiento","Fecha Pago","Moda Pago","Importe","Observaciones"});
		try {
			modaPago = new JComboBox();
			llenaModaPago();
			while (rs.next()) {
				modaPago.getModel().setSelectedItem(accConstantes.nombreConstante("MODP",Integer.valueOf(rs.getString("tipo"))));;
				modelo.addRow(new Object[] {rs.getInt("vencimiento"),rs.getInt("fecha"),rs.getString("tipo"),rs.getDouble("importe"),rs.getString("observaciones")});
			}
			modelo.addRow(new Object[] {"","",modaPago,"",""});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	protected void limpiaPantalla() {
		int filas = table.getRowCount();
		filas --;
		for (int i = filas; i >= 0; i--) {
			modelo.removeRow(i);
		}
		montaTabla();
	}
	
	private void llenaModaPago() {
		ArrayList<ObjetoJComboBox> listaModaPago = accConstantes.consultaConstante("MODP");
		if (listaModaPago !=null) {
			for (int i = 0; i < listaModaPago.size(); i++) {
				modaPago.addItem(listaModaPago.get(i));
			}
		}
	}
}
