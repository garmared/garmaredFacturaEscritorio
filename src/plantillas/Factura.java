package plantillas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import acciones.controller.imprimir;
import acciones.dto.ConceptosDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesServiceImpl;

public class Factura extends javax.swing.JFrame{

	private JFrame frame;
	static ServiceDTO sesionGlobal;
	private Boolean accion;
	private ConceptosDTO conceptos;
	private int idConcepto;
	AccionesConceptosImpl accConceptos = new AccionesConceptosImpl();
	AccionesServiceImpl accService = new AccionesServiceImpl();
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Factura window = new Factura();
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
	public Factura() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame("Conceptos de la empresa ");
		frame.setBounds(100, 100, 700, 700);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("FACTURA");
		lblNewLabel.setBounds(257, 5, 76, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Factura de la empresa X n\u00FAmero 123123");
		lblNewLabel_1.setBounds(115, 120, 339, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("columna 1");
		modelo.addColumn("columna 2");
		modelo.addRow(new Object[] {"11","22"});
		modelo.addRow(new Object[] {"11","22"});
		modelo.addRow(new Object[] {"11","22"});
		JTable tabla = new JTable(modelo);
		tabla.setModel(modelo);
		JScrollPane algo = new JScrollPane(tabla);
		frame.getContentPane().add(algo);
		
		btnNewButton = new JButton("Imprimir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mandarImprimir(frame);
				
			}
		});
		btnNewButton.setBounds(231, 178, 89, 23);
		frame.getContentPane().add(btnNewButton);

		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	public void mandarImprimir(JFrame frame2) {
		PrinterJob	tarea = PrinterJob.getPrinterJob();
		PageFormat format = tarea.pageDialog(tarea.defaultPage());
		tarea.setPrintable(new imprimir(frame2),format);
		if (tarea.printDialog()) {
			try {						
				tarea.print();
			} catch (Exception PrinterException) {

				PrinterException.printStackTrace();}
		}
		
	}
}
