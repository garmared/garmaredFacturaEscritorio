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
	}
	
	private void montaGestion(ServiceDTO entrada) {
		//menu con datos de gestion de la aplicacion
		//primer nivel
		opcion1 = new JMenu("Gesti\u00F3n");
		opcion1.setMnemonic(KeyEvent.VK_G);
		//segundo nivel
		Sopcion1 = new JMenu("Empresas");
		Sopcion1.setMnemonic(KeyEvent.VK_E);

		if (entrada.getAcceso() == 1) {
			Sopcion4 = new JMenuItem("Usuarios");
			Sopcion4.setMnemonic(KeyEvent.VK_U);
			Sopcion7 = new JMenuItem("Gesti\u00F3n Constantes");
			Sopcion7.setMnemonic(KeyEvent.VK_C);
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
		
		Sopcion1.add(sub1);
		Sopcion1.add(sub2);
				
		opcion1.add(Sopcion1);
		
		if (entrada.getAcceso() == 1) {
			opcion1.add(Sopcion4);
			opcion1.add(Sopcion7);
		}
		opcion1.add(Sopcion5);
		opcion1.add(Sopcion6);

		
		sub1.addActionListener(this);
		sub2.addActionListener(this);
		
				
		if (entrada.getAcceso() == 1) {
			Sopcion4.addActionListener(this);
			Sopcion7.addActionListener(this);
		}
		Sopcion5.addActionListener(this);
		Sopcion6.addActionListener(this);
		menubar.add(opcion1);
	}
	
	private void montaProyectos() {
		//primer nivel
		opcion1 = new JMenu("Proyectos");
		opcion1.setMnemonic(KeyEvent.VK_P);
		//segundo nivel
		Sopcion2 = new JMenuItem("Proyectos");
		Sopcion2.setMnemonic(KeyEvent.VK_P);
		Sopcion3 = new JMenuItem("Conceptos");
		Sopcion3.setMnemonic(KeyEvent.VK_C);
		Sopcion4 = new JMenu("Costes");
		Sopcion4.setMnemonic(KeyEvent.VK_O);
		// tercer nivel
		sub3 = new JMenuItem("Tipos de costes");
		sub6 = new JMenuItem("Costes indirectos");

		Sopcion4.add(sub3);
		Sopcion4.add(sub6);
		opcion1.add(Sopcion2);
		opcion1.add(Sopcion3);
		opcion1.add(Sopcion4);
		
		Sopcion2.addActionListener(this);
		Sopcion3.addActionListener(this);
		Sopcion4.addActionListener(this);
		sub3.addActionListener(this);
		sub6.addActionListener(this);
		
		menubar.add(opcion1);
	}

	private void montaFacturas() {
		//primer nivel
		opcion1 = new JMenu("Facturas");
		opcion1.setMnemonic(KeyEvent.VK_F);
		//segundo nivel
		Sopcion1 = new JMenuItem("Emitidas");
		Sopcion1.setMnemonic(KeyEvent.VK_E);
		Sopcion2 = new JMenuItem("Recibidas");
		Sopcion2.setMnemonic(KeyEvent.VK_R);
		Sopcion3 = new JMenuItem("IVA");
		Sopcion3.setMnemonic(KeyEvent.VK_I);
		Sopcion4 = new JMenuItem("Modelo 347");
		Sopcion4.setMnemonic(KeyEvent.VK_M);
		
		
		opcion1.add(Sopcion1);
		opcion1.add(Sopcion2);
		opcion1.add(Sopcion3);
		opcion1.add(Sopcion4);
		
		Sopcion1.addActionListener(this);
		Sopcion2.addActionListener(this);
		Sopcion3.addActionListener(this);
		Sopcion4.addActionListener(this);
		
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
		// aqui añadimos las acciones de clik de las opciones.
		String opcion = A.getActionCommand();
		switch (opcion) {
			case "Clientes":
				ventana.dispose();
				ListadoClientes listadoClientes = new ListadoClientes(control);
				break;
			case "Proveedores":
				ventana.dispose();
				ListadoProveedores listadoProveedores = new ListadoProveedores(control);
				break;				
			case "Tipos de costes":
				sub4 = new JMenuItem("Gesti\u00F3n costes indirectos");
				ventana.dispose();
				VentanaCostes costes = new VentanaCostes(control);
				break;
			case "Costes indirectos":
				ventana.dispose();
				ListadoCostesIndirectos listadoIndirectos = new ListadoCostesIndirectos(control);
				break;
			case "Proyectos":
				ventana.dispose();
				ListadoProyectos listadoProyectos = new ListadoProyectos(control);
				break;
			case "Emitidas":
				ventana.dispose();
				ListadoFacturasE listadoFacturas = new ListadoFacturasE(control);
				break;
			case "Recibidas":
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
			case "Gesti\u00F3n Constantes":
				ventana.dispose();
				VentanaConstantes constantes = new VentanaConstantes(control);
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

