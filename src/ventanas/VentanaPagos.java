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

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
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

public class VentanaPagos extends JFrame {
	AccionesServiceImpl accService = new AccionesServiceImpl();
	AccionesConstantesImpl accConstantes = new AccionesConstantesImpl();
	AccionesPagosImpl accPagos = new AccionesPagosImpl();
	static ServiceDTO sesionGlobal;
    JTable table = null;
    JScrollPane scrollPane=null;
    
    class TABLE_MODEL extends DefaultTableModel{
        public static final String INITIAL_VALUE = "seleccionar";
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
        public void addData(int a,int b,String c,Double d, String e){
        	final Object rowData[]={a,b,c,d,e};
            this.addRow(rowData);
        }
        public void initialData(){
        	final Object rowData[]={0,0,INITIAL_VALUE,0,""};
            this.addRow(rowData);
        }
    }
    
    TABLE_MODEL model=new TABLE_MODEL();
    JComboBox modaPago = new JComboBox();
    
    public VentanaPagos(){
    	llenaModoPago();
        DefaultCellEditor defaultCellEditor=new DefaultCellEditor(modaPago);
        table=new JTable(model);
        table.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (table.getSelectedColumn()==4) {
					if (e.getKeyCode()== KeyEvent.VK_TAB) {
						model.initialData();
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
        table.getColumnModel().getColumn(2).setCellEditor(defaultCellEditor);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(212, 11, 89, 23);
        btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
        this.getContentPane().add(btnVolver);
        
        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(23, 45, 497, 200);
        this.getContentPane().add(scrollPane);
        this.setTitle("Gestión de pagos de la factura " + sesionGlobal.getIdentificador() + " de la empresa " + sesionGlobal.getNombreEmpresa());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        this.getContentPane().setLayout(null);
        llenaTabla();
    }
    protected void volver() {
    	this.setVisible(false);
		this.dispose(); //esto cierra la ventana		
	}
	private void llenaModoPago() {
		ArrayList<ObjetoJComboBox> listaModaPago = accConstantes.consultaConstante("MODP");
		if (listaModaPago !=null) {
			modaPago.addItem("seleccionar");
			for (int i = 0; i < listaModaPago.size(); i++) {
				modaPago.addItem(listaModaPago.get(i));
			}
		}
	}
	public void llenaTabla(){
		Connection connection = accService.getConexion();
		String consulta;
		PagosDTO paramConsulta = new PagosDTO();
		paramConsulta.setIdEmpresa(sesionGlobal.getIdEmpresa());
		paramConsulta.setTipo("P");
		consulta=accPagos.creaConsultaPagos(paramConsulta);
												
		ResultSet rs = accService.getTabla(consulta, connection);
		
		try {
			while (rs.next()) {
				modaPago.getModel().setSelectedItem(accConstantes.nombreConstante("MODP",Integer.valueOf(rs.getString("tipo"))));;
				model.addData(rs.getInt("vencimiento"),rs.getInt("fecha"),accConstantes.nombreConstante("MODP",rs.getInt("tipo")),rs.getDouble("importe"),rs.getString("observaciones"));
			}
			model.initialData();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public VentanaPagos(ServiceDTO control) {
		sesionGlobal = control;
		new VentanaPagos().setVisible(true);
	}

}
