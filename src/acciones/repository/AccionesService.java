package acciones.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import acciones.service.impl.AccionesServiceImpl;

//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesService{
	AccionesServiceImpl accService = new AccionesServiceImpl();
	public Boolean grabarUsuarios(String usuario, String password, String nombre, String apellidos, String email,int nivelSeguridad) {
		try{
			Connection connection=accService.getConexion();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO usuarios VALUES(NULL,?,?,?,?,?,?)");
			stmt.setString(1, usuario);
			stmt.setString(2, password);
			stmt.setString(3, nombre);
			stmt.setString(4, apellidos);
			stmt.setString(5, email);
			stmt.setInt(6, nivelSeguridad);
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
	}
}