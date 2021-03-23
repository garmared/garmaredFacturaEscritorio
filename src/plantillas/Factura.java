package plantillas;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import acciones.controller.accionesPrint;
import acciones.dto.ConceptosDTO;
import acciones.dto.ServiceDTO;
import acciones.service.impl.AccionesConceptosImpl;
import acciones.service.impl.AccionesServiceImpl;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;

public class Factura extends javax.swing.JFrame implements Printable {

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
		tarea.setPrintable(this,format);
		if (tarea.printDialog()) {
			try {						
				tarea.print();
			} catch (Exception PrinterException) {

				PrinterException.printStackTrace();}
		}
		
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex == 0) {
			Graphics2D impresion = (Graphics2D) graphics;
			impresion.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			this.paint(impresion);
			impresion.drawString("hola", 100, 100);
			return PAGE_EXISTS;
		} else return NO_SUCH_PAGE;
	}

	
}
