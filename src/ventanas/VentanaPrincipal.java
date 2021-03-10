package ventanas;

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
import acciones.service.impl.AccionesEmpresasImpl;

public class VentanaPrincipal implements ActionListener{
	JFrame ventana;
	JMenu opcion1, opcion2,opcion3,opcion4,opcion5,opcion6,opcion7;
	JMenuItem Sopcion1, Sopcion2, Sopcion3, Sopcion4,Sopcion5,Sopcion6,Sopcion7,Sopcion8,Sopcion9,Sopcion10;
	JMenuItem sub1,sub2,sub3,sub4,sub5,sub6,sub7,sub8,sub9;
	JMenuBar menubar;
	ServiceDTO control;
	AccionesEmpresasImpl accEmpresas = new AccionesEmpresasImpl();
	static int sesion; //variable para pasar el tipo de sesion que estamos utilizando (gestion o usuario) al resto de pantallas.
	
	public void CrearMenu(ServiceDTO entrada) {
		menubar = new JMenuBar();
		
		montaGestion(entrada);
		montaProyectos();
		montaFacturas();
		montaClientesProveedores();
		//montaClientes();
				
	}
	
	private void montaGestion(ServiceDTO entrada) {
		//menu con datos de gestion de la aplicacion
		//primer nivel
		opcion1 = new JMenu("Gesti\u00F3n");
		opcion1.setMnemonic(KeyEvent.VK_G);
		//segundo nivel
		Sopcion1 = new JMenu("Empresas");
		Sopcion1.setMnemonic(KeyEvent.VK_E);
		Sopcion2 = new JMenuItem("Conceptos");
		Sopcion2.setMnemonic(KeyEvent.VK_C);
		Sopcion3 = new JMenu("Costes");
		Sopcion3.setMnemonic(KeyEvent.VK_O);
		if (entrada.getAcceso() == 1) {
			Sopcion4 = new JMenuItem("Usuarios");
			Sopcion4.setMnemonic(KeyEvent.VK_U);
		}
		Sopcion5 = new JMenuItem("Cambio sesi\u00F3n");
		Sopcion5.setMnemonic(KeyEvent.VK_M);
		Sopcion6 = new JMenuItem("Salir");
		Sopcion6.setMnemonic(KeyEvent.VK_S);
		//tercer nivel
		sub1 = new JMenuItem("Gesti\u00F3n Empresas");
		sub1.setMnemonic(KeyEvent.VK_G);
		sub2 = new JMenuItem("Selecci\u00F3n empresa");
		sub2.setMnemonic(KeyEvent.VK_S);
		sub3 = new JMenuItem("Tipos de costes");
		sub4 = new JMenu("Costes indirectos");
		//cuarto nivel
		sub5 = new JMenuItem("Gesti\u00F3n costes indirectos");
		sub6 = new JMenuItem("Listado costes indirectos");
		

		sub4.add(sub5);
		sub4.add(sub6);
		Sopcion1.add(sub1);
		Sopcion1.add(sub2);
		Sopcion3.add(sub3);
		Sopcion3.add(sub4);
		
		opcion1.add(Sopcion1);
		opcion1.add(Sopcion2);
		opcion1.add(Sopcion3);
		
		if (entrada.getAcceso() == 1) {
			opcion1.add(Sopcion4);
		}
		opcion1.add(Sopcion5);
		opcion1.add(Sopcion6);

		
		sub1.addActionListener(this);
		sub2.addActionListener(this);
		sub3.addActionListener(this);
		sub5.addActionListener(this);
		sub6.addActionListener(this);
		Sopcion2.addActionListener(this);
		Sopcion3.addActionListener(this);
		if (entrada.getAcceso() == 1) {
			Sopcion4.addActionListener(this);
		}
		Sopcion5.addActionListener(this);
		Sopcion6.addActionListener(this);
		menubar.add(opcion1);
	}
	
	private void montaProyectos() {
		//primer nivel
		opcion1 = new JMenu("Proyectos");
		opcion1.setMnemonic(KeyEvent.VK_Y);
		//segundo nivel
		Sopcion1 = new JMenuItem("Gesti\u00F3n Proyectos");
		Sopcion1.setMnemonic(KeyEvent.VK_G);
		Sopcion2 = new JMenuItem("Listado Proyectos");
		Sopcion2.setMnemonic(KeyEvent.VK_L);
		
		opcion1.add(Sopcion1);
		opcion1.add(Sopcion2);
		
		Sopcion1.addActionListener(this);
		Sopcion2.addActionListener(this);
		
		menubar.add(opcion1);
	}

	private void montaFacturas() {
		//primer nivel
		opcion1 = new JMenu("Facturas");
		opcion1.setMnemonic(KeyEvent.VK_F);
		//segundo nivel
		Sopcion1 = new JMenu("Emitidas");
		Sopcion1.setMnemonic(KeyEvent.VK_E);
		Sopcion2 = new JMenu("Recibidas");
		Sopcion2.setMnemonic(KeyEvent.VK_R);
		Sopcion3 = new JMenuItem("IVA");
		Sopcion3.setMnemonic(KeyEvent.VK_I);
		Sopcion4 = new JMenuItem("Modelo 347");
		Sopcion4.setMnemonic(KeyEvent.VK_M);
		//tercer nivel
		sub1 = new JMenuItem("Gesti\u00F3n Emitidas");
		sub2 = new JMenuItem("Listado Emitidas");
		sub3 = new JMenuItem("Gesti\u00F3n Recibidas");
		sub4 = new JMenuItem("Listado Recibidas");
		
		
		Sopcion1.add(sub1);
		Sopcion1.add(sub2);
		Sopcion2.add(sub3);
		Sopcion2.add(sub4);
		opcion1.add(Sopcion1);
		opcion1.add(Sopcion2);
		opcion1.add(Sopcion3);
		opcion1.add(Sopcion4);
		
		sub1.addActionListener(this);
		sub2.addActionListener(this);
		sub3.addActionListener(this);
		sub4.addActionListener(this);
		Sopcion3.addActionListener(this);
		Sopcion4.addActionListener(this);
		
		menubar.add(opcion1);
	}	

	private void montaClientes() {
		//primer nivel
		opcion1 = new JMenu("Clientes");
		opcion1.setMnemonic(KeyEvent.VK_C);
		//segundo nivel
		sub1 = new JMenuItem("Clientes");
		sub1.setMnemonic(KeyEvent.VK_C);
		
		sub1.addActionListener(this);
		opcion1.add(sub1);
		menubar.add(opcion1);
	}

	private void montaClientesProveedores() {
		//primer nivel
		opcion1 = new JMenu("Clientes y Proveedores");
		opcion1.setMnemonic(KeyEvent.VK_C);
		//segundo nivel
		sub1 = new JMenuItem("Clientes");
		sub2 = new JMenuItem("Proveedores");
				
		
		opcion1.add(sub1);
		opcion1.add(sub2);

		sub1.addActionListener(this);
		sub2.addActionListener(this);
		menubar.add(opcion1);

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
		control.setNoPrincipal("N");
		if (entrada.getNombreEmpresa() == null) {
			seleccionEmpresa(entrada);
		}
		
		CrearMenu(control);
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

	public void actionPerformed(ActionEvent A) {
		// aqui a�adimos las acciones de clik de las opciones.
		String opcion = A.getActionCommand();
		switch (opcion) {
			/*case "Gesti\u00F3n Clientes":
				ventana.dispose();
				VentanaClientes clientes = new VentanaClientes(control);
				break;*/
			case "Clientes":
				ventana.dispose();
				ListadoClientes listadoClientes = new ListadoClientes(control);
				break;
			/*case "Gesti\u00F3n Proveedores":
				ventana.dispose();
				VentanaProveedores proveedores = new VentanaProveedores(control);
				break;*/
			case "Proveedores":
				ventana.dispose();
				ListadoProveedores listadoProveedores = new ListadoProveedores(control);
				break;				
			case "Tipos de costes":
				sub4 = new JMenuItem("Gesti\u00F3n costes indirectos");
				ventana.dispose();
				VentanaCostes costes = new VentanaCostes(control);
				break;
			case "Gesti\u00F3n costes indirectos":
				ventana.dispose();
				VentanaCostesIndirectos costesIndirectos = new VentanaCostesIndirectos(control);
				break;
			case "Listado costes indirectos":
				ventana.dispose();
				ListadoCostesIndirectos listadoIndirectos = new ListadoCostesIndirectos(control);
				break;
			case "Gesti\u00F3n Proyectos":
				ventana.dispose();
				VentanaProyectos proyecto = new VentanaProyectos(control);
				break;
			case "Listado Proyectos":
				ventana.dispose();
				ListadoProyectos listadoProyectos = new ListadoProyectos(control);
				break;
			case "Gesti\u00F3n Emitidas":
				ventana.dispose();
				VentanaFacturasE facturas = new VentanaFacturasE(control);
				break;
			case "Listado Emitidas":
				ventana.dispose();
				ListadoFacturasE listadoFacturas = new ListadoFacturasE(control);
				break;
			case "Gesti\u00F3n Recibidas":
				ventana.dispose();
				VentanaFacturasR facturasR = new VentanaFacturasR(control);
				break;
			case "Listado Recibidas":
				ventana.dispose();
				ListadoFacturasR listadoFacturasR = new ListadoFacturasR(control);
				break;
			case "Conceptos":
				ventana.dispose();
				VentanaConceptos conceptos = new VentanaConceptos(control);
				break;
			case "Usuarios":
				ventana.dispose();
				VentanaUsuarios users = new VentanaUsuarios(control);
				break;
			case "Cambio sesi\u00F3n":
				ventana.dispose();
				break;
			case "Selecci\u00F3n empresa":
				seleccionEmpresa(control);
				break;
			case "Gesti\u00F3n Empresas":
				ventana.dispose();
				VentanaEmpresas empresas = new VentanaEmpresas(control);
				break;
			case "IVA":
				ventana.dispose();
				VentanaIva iva = new VentanaIva(control);
				break;
			case "Modelo 347":
				ventana.dispose();
				Ventana347 modelo347 = new Ventana347(control);
				break;				
			case "Salir":
				System.exit(0);
		}
	}
	}

