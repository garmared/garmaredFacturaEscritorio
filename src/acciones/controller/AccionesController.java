package acciones.controller;

import java.sql.*;

import acciones.service.impl.AccionesServiceImpl;

public class AccionesController {
	
	@SuppressWarnings("deprecation")
	public static Integer controlLogin(String usuario, String password) {
	// gestion de acceso a la aplicacion	
		int nivelSeguridad = 0;
		AccionesServiceImpl accService = new AccionesServiceImpl();
		try{
			Connection connection=accService.getConexion();						
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
