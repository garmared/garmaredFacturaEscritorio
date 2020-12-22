package acciones.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import acciones.controller.accionesUsuarios;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.UsuariosDTO;

//definicion de todas las acciones que tenemos en la aplicacion
public class accionesUsuariosImpl implements accionesUsuarios{

	@Override
	public Boolean grabarUsuario(UsuariosDTO entrada) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO usuarios VALUES(NULL,?,?,?,?,?,?)");
			stmt.setString(1, entrada.getUsuario());
			stmt.setString(2, entrada.getPassword());
			stmt.setString(3, entrada.getNombre());
			stmt.setString(4, entrada.getApellidos());
			stmt.setString(5, entrada.getEmail());
			stmt.setInt(6, entrada.getNivelSeguridad());
			stmt.executeUpdate();
			
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en grabarUsuario: "+ex.getMessage().toString());
			return false;
		}
	}

	public UsuariosDTO buscaUsuario(String nomBuscado) {
		UsuariosDTO salida = new UsuariosDTO();
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT * FROM usuarios WHERE usuario = '"+nomBuscado+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdUsuario(result.getInt("idUsuario"));
				salida.setUsuario(result.getString("usuario"));
				salida.setPassword(result.getString("password"));
				salida.setNombre(result.getString("nombre"));
				salida.setApellidos(result.getString("apellidos"));
				salida.setEmail(result.getString("email"));
				salida.setNivelSeguridad(result.getInt("nivelSeguridad"));
				return salida;
			} else {
				salida.setIdUsuario(0);
				return salida;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaUsuario: "+ex.getMessage().toString());
			return null;
		}	

	}

	public Boolean deleteUsuario(int idUsuario) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("DELETE FROM usuarios WHERE idUsuario = ?");
		    stmt.setInt(1,idUsuario);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en deleteUsuario: "+ex.getMessage().toString());
			return false;
		}
	}

	public Boolean updateUsuarios(UsuariosDTO usuarios) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE usuarios set usuario=?, password=?,nombre=?,apellidos=?,email=?,"
		    		+ "nivelSeguridad=? WHERE idUsuario = ?");
		    stmt.setString(1, usuarios.getUsuario());
		    stmt.setString(2,usuarios.getPassword());
		    stmt.setString(3,usuarios.getNombre());
		    stmt.setString(4,usuarios.getApellidos());
			stmt.setString(5,usuarios.getEmail());
			stmt.setInt(6,usuarios.getNivelSeguridad());
			stmt.setInt(7,usuarios.getIdUsuario());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en updateUsuarios: "+ex.getMessage().toString());
			return null;
		}
	}

	public ArrayList<ObjetoJComboBox> listaUsuarios() {
		ArrayList<ObjetoJComboBox> salida = new ArrayList<ObjetoJComboBox>();
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT * FROM usuarios ORDER by usuario";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				ObjetoJComboBox temporal;
				do {
					temporal = new ObjetoJComboBox(result.getInt("idUsuario"),result.getString("usuario"));
					salida.add(temporal);
				}
				while(result.next());
		
			} 
			return salida;
		}catch(Exception ex){
			System.out.println("Error en listaUsuarios: "+ex.getMessage().toString());
			return null;
		}		}

	public String buscaSeguridad(Integer nivelSeguridad) {
		String salida ="";
		switch (nivelSeguridad) {
			case 1: 
				salida = "desarrollo";
				break;
			case 2: 
				salida = "usuario";
				break;
		}
		return salida;
	}

	public Integer buscaIdSeguridad(String variable) {
		Integer salida = 0;
		switch (variable) {
			case "desarrollo":
				salida=1;
				break;
			case "usuario":
				salida=2;
				break;
		}
		return salida;
	}

}