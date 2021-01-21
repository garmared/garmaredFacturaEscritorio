package acciones.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import com.toedter.calendar.JDateChooser;

import acciones.controller.AccionesServiceController;
import acciones.dto.ServiceDTO;
import ventanas.VentanaPrincipal;

public class AccionesServiceImpl implements AccionesServiceController{
	@Override
	public ServiceDTO obtenerVentanaActual() {
		ServiceDTO salida = new ServiceDTO(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height, java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		return salida;
	}

	private static String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
	
	public Integer controlLogin(String usuario, String password) {
		// gestion de acceso a la aplicacion	
			int nivelSeguridad = 0;
			try{
				Connection connection= getConexion();
				Statement stmt = connection.createStatement();
				String peticion = "SELECT nivelSeguridad FROM usuarios WHERE usuario = '"+usuario+"' AND password='"+password+"'";
				ResultSet rset = getTabla(peticion, connection);
				while(rset.next()){							
					nivelSeguridad = rset.getInt("nivelSeguridad");
				}
				stmt.close();
				connection.close();
			}catch(Exception ex){
				System.out.println("Error en controlLogin: "+ex.getMessage().toString());
			}
			return (nivelSeguridad);
		}
		

	@Override
	public Connection getConexion() {
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
		}catch(Exception ex){
			System.out.println("Error en conexion a base de datos: "+ex.getMessage().toString());
		}
		return connection;
	}

	@Override
	public ResultSet getTabla(String Consulta, Connection connection) {
		Statement st;
		ResultSet datos=null;
		try {
			st=connection.createStatement();
			datos=st.executeQuery(Consulta);
		} catch(Exception e) {System.out.println(e.toString());}
		return datos;
	}


	@Override
	public String obtenerFecha(JDateChooser fecha) {
		String salida; 
		if (fecha.getCalendar()==null) {
			return salida = "0";
		}else {
			String dia = Integer.toString(fecha.getCalendar().get(Calendar.DAY_OF_MONTH));
			if (fecha.getCalendar().get(Calendar.DAY_OF_MONTH)<10) {
				dia = ("0"+dia);
			}
			String mes = Integer.toString(fecha.getCalendar().get(Calendar.MONTH)+1);
			if (fecha.getCalendar().get(Calendar.MONTH)+1<10) {
				mes = ("0"+mes);
			}
			String ano = Integer.toString(fecha.getCalendar().get(Calendar.YEAR));
			return salida = (ano+mes+dia);	
		}
	}


	@Override
	public void abrirVentanaPrincipal(ServiceDTO sesionGlobal) {
		// TODO Auto-generated method stub
		if (sesionGlobal.getNoPrincipal()=="N") {
			VentanaPrincipal ventana = new VentanaPrincipal(sesionGlobal);
		}
	}	
	
}
