package acciones.controller;

import java.sql.*;

public class accionesController {

	@SuppressWarnings("deprecation")
	public static Integer controlLogin(String usuario, String password) {
	// gestion de acceso a la aplicacion	
		int nivelSeguridad = 0;
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
			Statement stmt = connection.createStatement();
			ResultSet rset;						
			String peticion = "SELECT nivelSeguridad FROM usuarios WHERE usuario = '"+usuario+"' AND password='"+password+"'";
			rset = stmt.executeQuery(peticion);
			while(rset.next()){							
				nivelSeguridad = rset.getInt("nivelSeguridad");
			}
			stmt.close();
			connection.close();
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
		}
		return (nivelSeguridad);
	}
	
}
