package garmaredFacturaEscritorio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import acciones.dto.ServiceDTO;

public class VentanaPrincipal implements ActionListener{
	JFrame ventana;
	JMenu opcion1, opcion2,opcion3;
	JMenuItem Sopcion1, Sopcion2, Sopcion3, Sopcion4,Sopcion5,Sopcion6,Sopcion7,Sopcion8,Sopcion9;
	//JMenuItem sub1,sub2,sub3;
	JMenuBar menubar;
	ServiceDTO control;
	static int sesion; //variable para pasar el tipo de sesion que estamos utilizando (gestion o usuario) al resto de pantallas.
	
	public void CrearMenu() {
		menubar = new JMenuBar();
		
		opcion1 = new JMenu("Ficheros");
		opcion2 = new JMenu("Gesti\u00F3n");
		opcion3 = new JMenu("Administraci\u00F3n");
		
		Sopcion1 = new JMenuItem("Clientes");
		Sopcion2 = new JMenuItem("Proveedores");
		Sopcion3 = new JMenuItem("Empresas");
		Sopcion4 = new JMenuItem("Costes");
		Sopcion5 = new JMenuItem("Proyectos");
		Sopcion6 = new JMenuItem("Facturas");
		Sopcion7 = new JMenuItem("Cambio sesi\u00F3n");
		Sopcion9 = new JMenuItem("Conceptos");
		

		menubar.add(opcion1);
		menubar.add(opcion2);
		menubar.add(opcion3);
		
		opcion1.add(Sopcion1);
		opcion1.add(Sopcion2);
		opcion1.add(Sopcion4);
		
		opcion2.add(Sopcion5);
		opcion2.add(Sopcion6);
				
		opcion3.add(Sopcion3);
		opcion3.add(Sopcion7);
		opcion3.add(Sopcion9);
		
		Sopcion1.addActionListener(this);
		Sopcion2.addActionListener(this);
		Sopcion3.addActionListener(this);
		Sopcion4.addActionListener(this);
		Sopcion5.addActionListener(this);
		Sopcion6.addActionListener(this);
		Sopcion7.addActionListener(this);
		Sopcion9.addActionListener(this);
		
	}
	
	public void CrearMenuAdministracion() {
		CrearMenu();
				
		Sopcion8 = new JMenuItem("Usuarios");
					
		opcion3.add(Sopcion7);
		opcion3.add(Sopcion8);
		
		Sopcion7.addActionListener(this);
		Sopcion8.addActionListener(this);
		
	}	
	
	
	public void CrearVentana() {
		ventana = new JFrame("Garmared Factura 1.0");
		ventana.getContentPane().setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));
		//ventana.setSize(300, 300);
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH); //para que la pantalla se ejecute completa.
		ventana.setVisible(true);
		ventana.setJMenuBar(menubar);
		ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
	}
		
	public VentanaPrincipal(ServiceDTO entrada) {
		control = entrada;
		if (entrada.getAcceso() == 1) {
			CrearMenuAdministracion();
		} else {
			CrearMenu();
		}
		CrearVentana();
		
	}

	@Override
	public void actionPerformed(ActionEvent A) {
		// aqui añadimos las acciones de clik de las opciones.
		String opcion = A.getActionCommand();
		switch (opcion) {
			case "Clientes":
				ventana.setVisible(false);
				VentaClientes clientes = new VentaClientes(control);
				break;
			case "Proveedores":
				ventana.setVisible(false);
				VentanaProveedores proveedores = new VentanaProveedores(control);
				break;
			case "Empresas":
				ventana.setVisible(false);
				VentanaEmpresas empresas = new VentanaEmpresas(control);
				break;
			case "Costes":
				ventana.setVisible(false);
				VentanaCostes costes = new VentanaCostes(control);
				break;
			case "Proyectos":
				ventana.setVisible(false);
				VentanaProyectos proyecto = new VentanaProyectos(control);
				break;
			case "Facturas":
				ventana.setVisible(false);
				VentanaFacturas facturas = new VentanaFacturas(control);
				break;
			case "Conceptos":
				ventana.setVisible(false);
				VentanaConceptos conceptos = new VentanaConceptos(control);
				break;
			case "Usuarios":
				ventana.setVisible(false);
				VentanaUsuarios users = new VentanaUsuarios(control);
				break;
			case "Cambio sesi\u00F3n":
				ventana.setVisible(false);
				break;
		}
	}
	}

