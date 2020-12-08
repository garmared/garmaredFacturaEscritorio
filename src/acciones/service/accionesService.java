package acciones.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//definicion de todas las acciones que tenemos en la aplicacion
public interface accionesService{
	
	public static Boolean grabarUsuarios(String usuario, String password, String nombre, String apellidos, String email,int nivelSeguridad) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
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