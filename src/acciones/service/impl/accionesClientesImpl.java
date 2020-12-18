package acciones.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.controller.accionesClientes;
import acciones.dto.ClientesDTO;
import acciones.dto.ObjetoJComboBox;

//definicion de todas las acciones que tenemos en la aplicacion
public class accionesClientesImpl implements accionesClientes{
	public Boolean grabarCliente(ClientesDTO entrada) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("INSERT INTO clientes VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, entrada.getCif());
			stmt.setString(2,entrada.getNombre());
			stmt.setString(3,entrada.getDireccion());
			stmt.setString(4,entrada.getPoblacion());
			stmt.setString(5,entrada.getProvincia());
			stmt.setInt(6,entrada.getCp());
			stmt.setInt(7,entrada.getTelefono1());
			stmt.setInt(8,entrada.getTelefono2());
			stmt.setInt(9,entrada.getTelefono3());
			stmt.setString(10,entrada.getPersonaContacto());
			stmt.setString(11,entrada.getMail());
			stmt.setString(12,entrada.getWeb());
			stmt.setString(13,entrada.getFp());
			stmt.setInt(14,entrada.getDiaPago());
			stmt.setString(15,entrada.getModaPago());
			stmt.setString(16,entrada.getObservaciones());
			stmt.setString(17,entrada.getActivo());
			stmt.setInt(18,entrada.getIdEmpresa());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
		
	}
	
	public ArrayList<ObjetoJComboBox> consultaClientes() {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;
			ArrayList<ObjetoJComboBox> salida= new ArrayList<ObjetoJComboBox>();
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT id_cliente, Nombre FROM clientes ORDER BY Nombre";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_cliente"),result.getString("Nombre"));
					salida.add(temporal);
				}
				while(result.next()); 
				}
				
			return salida;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return null;
		}	
	}

	public ClientesDTO buscaCliente(ClientesDTO cliente) {
		// TODO Auto-generated method stub
		ClientesDTO salida = new ClientesDTO();
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT * FROM clientes WHERE Nombre = '"+cliente.getNombre()+"' AND id_empresa = "+cliente.getIdEmpresa()+"";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdCliente(result.getInt("id_cliente"));
				salida.setCif(result.getString("CIF"));
				salida.setNombre(result.getString("Nombre"));
				salida.setDireccion(result.getString("Direccion"));
				salida.setPoblacion(result.getString("Poblacion"));
				salida.setProvincia(result.getString("Provincia"));
				salida.setCp(result.getInt("CP"));
				salida.setTelefono1(result.getInt("Telefono1"));
				salida.setTelefono2(result.getInt("Telefono2"));
				salida.setTelefono3(result.getInt("Telefono3"));
				salida.setPersonaContacto(result.getString("Persona_contacto"));
				salida.setMail(result.getString("mail"));
				salida.setWeb(result.getString("web"));
				salida.setFp(result.getString("fp"));
				salida.setDiaPago(result.getInt("dia_pago"));
				salida.setModaPago(result.getString("modo_pago"));
				salida.setObservaciones(result.getString("observaciones"));
				salida.setActivo(result.getString("activo"));
				salida.setIdEmpresa(result.getInt("id_empresa"));
				return salida;
			} else {
				salida.setIdCliente(0);
				return salida;
			}
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return null;
		}	
	}

	public Boolean deleteCliente(Integer idCliente) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("DELETE FROM clientes WHERE id_cliente = ?");
		    stmt.setInt(1,idCliente);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
	}

	public Boolean updateCliente(ClientesDTO cliente) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE clientes set CIF = ?, Direccion = ?, Poblacion = ?, Provincia = ?,CP=?,"
		    		+ "Telefono1=?, Telefono2=?, Telefono3=?, Persona_contacto=?,mail=?,web=?,fp=?,dia_pago=?,modo_pago=?,observaciones=?,activo=? WHERE id_cliente = ?");
			stmt.setString(1, cliente.getCif());
			stmt.setString(2,cliente.getDireccion());
			stmt.setString(3,cliente.getPoblacion());
			stmt.setString(4,cliente.getProvincia());
			stmt.setInt(5, cliente.getCp());
			stmt.setInt(6, cliente.getTelefono1());
			stmt.setInt(7, cliente.getTelefono2());
			stmt.setInt(8, cliente.getTelefono3());
			stmt.setString(9,cliente.getPersonaContacto());
			stmt.setString(10,cliente.getMail());
			stmt.setString(11,cliente.getWeb());
			stmt.setString(12,cliente.getFp());
			stmt.setInt(13, cliente.getDiaPago());
			stmt.setString(14,cliente.getModaPago());
			stmt.setString(15,cliente.getObservaciones());
			stmt.setString(16,cliente.getActivo());
			stmt.setInt(17, cliente.getIdCliente());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
	}

	public int buscaCliente(String nomBuscado) {
		int salida;
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT id_cliente FROM clientes WHERE Nombre = '"+nomBuscado+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida = result.getInt("id_cliente");
			} else {
				salida = 0;
			}
			return salida;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return 0;
		}
	}

	public String buscaNombre(Integer cliente) {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT Nombre FROM clientes WHERE id_cliente = '"+cliente+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				return result.getString("Nombre");
			} else {
				return "";
			}
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return null;
		}
	}
}