package acciones.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.dto.ClientesDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.service.impl.AccionesServiceImpl;

public class AccionesClientes {
	AccionesServiceImpl accService = new AccionesServiceImpl();
	public Boolean grabarCliente(ClientesDTO entrada) {
		try{
			Connection connection=accService.getConexion();						
		    PreparedStatement stmt = connection.prepareStatement("INSERT INTO clientes VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, entrada.getNif());
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
			stmt.setString(19,entrada.getIban());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en grabarCliente: "+ex.getMessage().toString());
			return false;
		}
		
	}
	
	public ArrayList<ObjetoJComboBox> consultaClientes(int empresa) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;
			ArrayList<ObjetoJComboBox> salida= new ArrayList<ObjetoJComboBox>();
			String peticion = "SELECT id_cliente, Nombre FROM clientes WHERE id_empresa = "+empresa+" ORDER BY Nombre";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_cliente"),result.getString("Nombre"));
					salida.add(temporal);
				}
				while(result.next()); 
			}
			stmt.close();
			connection.close();	
			return salida;
		}catch(Exception ex){
			System.out.println("Error en consultaClientes: "+ex.getMessage().toString());
			return null;
		}	
	}

	public ClientesDTO buscaCliente(ClientesDTO cliente) {
		// TODO Auto-generated method stub
		ClientesDTO salida = new ClientesDTO();
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT * FROM clientes WHERE Nombre = '"+cliente.getNombre()+"' AND id_empresa = "+cliente.getIdEmpresa()+"";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdCliente(result.getInt("id_cliente"));
				salida.setNif(result.getString("CIF"));
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
				salida.setIban(result.getString("iban"));
				stmt.close();
				connection.close();
				return salida;
			} else {
				salida.setIdCliente(0);
				stmt.close();
				connection.close();
				return salida;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaCliente: "+ex.getMessage().toString());
			return null;
		}	
	}

	public Boolean deleteCliente(Integer idCliente) {
		try{
			Connection connection=accService.getConexion();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM clientes WHERE id_cliente = ?");
		    stmt.setInt(1,idCliente);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en deleteCliente: "+ex.getMessage().toString());
			return false;
		}
	}

	public Boolean updateCliente(ClientesDTO cliente) {
		try{
			Connection connection=accService.getConexion();						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE clientes set CIF = ?, Direccion = ?, Poblacion = ?, Provincia = ?,CP=?,"
		    		+ "Telefono1=?, Telefono2=?, Telefono3=?, Persona_contacto=?,mail=?,web=?,fp=?,dia_pago=?,modo_pago=?,observaciones=?,activo=?,iban=? WHERE id_cliente = ?");
			stmt.setString(1, cliente.getNif());
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
			stmt.setInt(18, cliente.getIdCliente());
			stmt.setString(17,cliente.getIban());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en updateCliente: "+ex.getMessage().toString());
			return false;
		}
	}

	public int buscaCliente(String nomBuscado, int empresa) {
		int salida;
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT id_cliente FROM clientes WHERE Nombre = '"+nomBuscado+"' AND id_empresa = '"+empresa+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida = result.getInt("id_cliente");
			} else {
				salida = 0;
			}
			stmt.close();
			connection.close();
			return salida;
		}catch(Exception ex){
			System.out.println("Error en buscaCliente: "+ex.getMessage().toString());
			return 0;
		}
	}

	public String buscaNombre(Integer cliente) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT Nombre FROM clientes WHERE id_cliente = '"+cliente+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				String salida = result.getString("Nombre"); 
				stmt.close();
				connection.close();
				return salida;
			} else {
				stmt.close();
				connection.close();
				return "";
			}
		}catch(Exception ex){
			System.out.println("Error buscaNombre cliente: "+ex.getMessage().toString());
			return null;
		}
	}

	public ArrayList<String> buscaDireccion(Integer idEmpresa) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;
			ArrayList<String> salida= new ArrayList<String>();
			String peticion = "SELECT distinct(Poblacion) FROM clientes WHERE id_empresa = "+idEmpresa+" ORDER BY Poblacion";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					salida.add(result.getString("Poblacion"));
				}
				while(result.next()); 
			}
			stmt.close();
			connection.close();	
			return salida;
		}catch(Exception ex){
			System.out.println("Error en buscaDireccion: "+ex.getMessage().toString());
			return null;
		}	
	}

	public ClientesDTO buscaCliente(Integer identificador, Integer empresa) {
		ClientesDTO salida = new ClientesDTO();
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT * FROM clientes WHERE id_cliente = '"+identificador+"' AND id_empresa = "+empresa+"";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdCliente(result.getInt("id_cliente"));
				salida.setNif(result.getString("CIF"));
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
				salida.setIban(result.getString("iban"));
				stmt.close();
				connection.close();
				return salida;
			} else {
				salida.setIdCliente(0);
				stmt.close();
				connection.close();
				return salida;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaCliente: "+ex.getMessage().toString());
			return null;
		}	
	}

	public ClientesDTO infoCliente(String nombre, Integer idEmpresa) {
		ClientesDTO salida = new ClientesDTO();
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT * FROM clientes WHERE Nombre = '"+nombre+"' AND id_empresa = "+idEmpresa+"";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				salida.setIdCliente(result.getInt("id_cliente"));
				salida.setNif(result.getString("CIF"));
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
				salida.setIban(result.getString("iban"));
				stmt.close();
				connection.close();
				return salida;
			} else {
				salida.setIdCliente(0);
				stmt.close();
				connection.close();
				return salida;
			}
		}catch(Exception ex){
			System.out.println("Error en infoCliente: "+ex.getMessage().toString());
			return null;
		}	
	}
}
