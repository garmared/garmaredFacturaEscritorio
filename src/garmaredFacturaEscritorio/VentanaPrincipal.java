package garmaredFacturaEscritorio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import acciones.dto.ObjetoJComboBox;
import acciones.dto.ServiceDTO;
import acciones.service.impl.accionesEmpresasImpl;
import acciones.service.impl.accionesUsuariosImpl;

public class VentanaPrincipal implements ActionListener{
	JFrame ventana;
	JMenu opcion1, opcion2,opcion3,opcion4;
	JMenuItem Sopcion1, Sopcion2, Sopcion3, Sopcion4,Sopcion5,Sopcion6,Sopcion7,Sopcion8,Sopcion9,Sopcion10;
	JMenuItem sub1,sub2,sub3;
	JMenuBar menubar;
	ServiceDTO control;
	accionesEmpresasImpl accEmpresas = new accionesEmpresasImpl();
	static int sesion; //variable para pasar el tipo de sesion que estamos utilizando (gestion o usuario) al resto de pantallas.
	
	public void CrearMenu() {
		menubar = new JMenuBar();
		
		opcion1 = new JMenu("Ficheros");
		opcion1.setMnemonic(KeyEvent.VK_F);
		opcion2 = new JMenu("Gesti\u00F3n");
		opcion2.setMnemonic(KeyEvent.VK_G);
		opcion3 = new JMenu("Administraci\u00F3n");
		opcion3.setMnemonic(KeyEvent.VK_A);
		opcion4 = new JMenu("Facturas");
		opcion4.setMnemonic(KeyEvent.VK_F);
		
		Sopcion1 = new JMenuItem("Clientes");
		Sopcion1.setMnemonic(KeyEvent.VK_C);
		Sopcion2 = new JMenuItem("Proveedores");
		Sopcion2.setMnemonic(KeyEvent.VK_P);
		Sopcion3 = new JMenuItem("Gesti\u00F3n Empresas");
		Sopcion3.setMnemonic(KeyEvent.VK_G);
		Sopcion4 = new JMenuItem("Costes");
		Sopcion4.setMnemonic(KeyEvent.VK_O);
		Sopcion5 = new JMenuItem("Proyectos");
		Sopcion5.setMnemonic(KeyEvent.VK_R);
		Sopcion7 = new JMenuItem("Cambio sesi\u00F3n");
		Sopcion7.setMnemonic(KeyEvent.VK_M);
		Sopcion9 = new JMenuItem("Conceptos");
		Sopcion9.setMnemonic(KeyEvent.VK_N);
		Sopcion10 = new JMenuItem("Selecci\u00F3n empresa");
		Sopcion10.setMnemonic(KeyEvent.VK_S);
		
		sub1 = new JMenuItem("Gesti\u00F3n Factura");
		sub2 = new JMenuItem("Listado Facturas");
		

		menubar.add(opcion1);
		menubar.add(opcion2);
		menubar.add(opcion3);
		
		opcion1.add(Sopcion1);
		opcion1.add(Sopcion2);
		opcion1.add(Sopcion4);
		
		opcion2.add(Sopcion5);
		opcion2.add(opcion4);
				
		opcion3.add(Sopcion10);
		opcion3.add(Sopcion7);
		opcion3.add(Sopcion9);
		
		opcion4.add(sub1);
		opcion4.add(sub2);
		
		Sopcion1.addActionListener(this);
		Sopcion2.addActionListener(this);
		Sopcion4.addActionListener(this);
		Sopcion5.addActionListener(this);
		Sopcion7.addActionListener(this);
		Sopcion9.addActionListener(this);
		Sopcion10.addActionListener(this);
		
		sub1.addActionListener(this);
		sub2.addActionListener(this);
		
	}
	
	public void CrearMenuAdministracion() {
		CrearMenu();
				
		Sopcion8 = new JMenuItem("Usuarios");
		Sopcion8.setMnemonic(KeyEvent.VK_U);
					
		opcion3.add(Sopcion3);
		opcion3.add(Sopcion7);
		opcion3.add(Sopcion8);
		
		Sopcion3.addActionListener(this);
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
		if (entrada.getNombreEmpresa() == null) {
			seleccionEmpresa(entrada);
		}
		
		if (entrada.getAcceso() == 1) {
			CrearMenuAdministracion();
		} else {
			CrearMenu();
		}
		CrearVentana();
		
	}

	private void seleccionEmpresa(ServiceDTO entrada) {
		//mostramos las empresas disponibles para seleccionar la que se quiere utilizar para trabajar
		ArrayList<ObjetoJComboBox> cadena = accEmpresas.listadoEmpresas();
		Object nomBuscado = JOptionPane.showInputDialog(null,"Selecciona la empresa con la que deseas operar:","",JOptionPane.PLAIN_MESSAGE,null,cadena.toArray(),null);
		JOptionPane.showMessageDialog(null, nomBuscado, "A partir de ahora trabajaremos con ", JOptionPane.INFORMATION_MESSAGE);
		entrada.setNombreEmpresa(nomBuscado.toString());
		entrada.setIdEmpresa(accEmpresas.buscaId(nomBuscado.toString(), "E"));
		//System.out.println(entrada.getIdEmpresa());
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
			case "Costes":
				ventana.setVisible(false);
				VentanaCostes costes = new VentanaCostes(control);
				break;
			case "Proyectos":
				ventana.setVisible(false);
				VentanaProyectos proyecto = new VentanaProyectos(control);
				break;
			case "Gesti\u00F3n Factura":
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
			case "Selecci\u00F3n empresa":
				seleccionEmpresa(control);
				break;
			case "Gesti\u00F3n Empresas":
				ventana.setVisible(false);
				VentanaEmpresas empresas = new VentanaEmpresas(control);
				break;
			case "Listado Facturas":
				ventana.setVisible(false);
				ListadoFacturas listadoFacturas = new ListadoFacturas(control);
				break;
		}
	}
	}

