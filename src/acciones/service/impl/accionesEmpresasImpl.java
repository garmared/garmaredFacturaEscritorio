package acciones.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.controller.accionesEmpresas;
import acciones.dto.EmpresasDTO;
import acciones.dto.ObjetoJComboBox;

//definicion de todas las acciones que tenemos en la aplicacion
public class accionesEmpresasImpl implements accionesEmpresas{
	public Boolean grabarEmpresas(EmpresasDTO entrada) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO empresas VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, entrada.getCif());
			stmt.setString(2, entrada.getTipo());
			stmt.setString(3,entrada.getNombre());
			stmt.setString(4,entrada.getDireccion());
			stmt.setString(5,entrada.getPoblacion());
			stmt.setString(6,entrada.getProvincia());
			stmt.setInt(7,entrada.getCp());
			stmt.setInt(8,entrada.getTelefono1());
			stmt.setInt(9,entrada.getTelefono2());
			stmt.setInt(10,entrada.getTelefono3());
			stmt.setString(11,entrada.getPersonaContacto());
			stmt.setString(12,entrada.getMail());
			stmt.setString(13,entrada.getWeb());
			stmt.setInt(14,entrada.getTipoCoste());
			stmt.setString(15,entrada.getIban());
			stmt.setString(16,entrada.getObservaciones());
			stmt.setString(17,entrada.getActivo());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
		
	}

	public ArrayList<ObjetoJComboBox> consultaEmpresas(String tipo) {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;
			ArrayList<ObjetoJComboBox> salida= new ArrayList<ObjetoJComboBox>();
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT id_empresa, Nombre FROM empresas WHERE tipo = '"+tipo+"' ORDER BY Nombre";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_empresa"),result.getString("Nombre"));
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

	public EmpresasDTO buscaEmpresa(EmpresasDTO empresas) {
		// TODO Auto-generated method stub
		EmpresasDTO salida = new EmpresasDTO();
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT * FROM empresas WHERE Nombre = '"+empresas.getNombre()+"' AND tipo = '"+empresas.getTipo()+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setidEmpresa(result.getInt("id_empresa"));
				salida.setCif(result.getString("CIF"));
				salida.setTipo(result.getString("tipo"));
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
				salida.setTipoCoste(result.getInt("Tipo_coste"));
				salida.setIban(result.getString("IBAN"));
				salida.setObservaciones(result.getString("observaciones"));
				salida.setActivo(result.getString("activo"));
				return salida;
			} else {
				salida.setidEmpresa(0);
				return salida;
			}
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return null;
		}	

	}

	public Boolean deleteEmpresa(int idEmpresa) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("DELETE FROM empresas WHERE id_empresa = ?");
		    stmt.setInt(1,idEmpresa);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
	}

	public Boolean updateEmpresas(EmpresasDTO empresas) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE empresas set CIF = ?, tipo = ?, Nombre = ?, Direccion = ?, Poblacion = ?, Provincia = ?, CP = ?, Telefono1=?, "
		    		+ "Telefono2=?, Telefono3=?, Persona_contacto=?,mail=?,web=?,Tipo_coste=?,IBAN=?,observaciones=?,activo=? WHERE id_empresa = ?");
		    stmt.setString(1, empresas.getCif());
		    stmt.setString(2,"E");
		    stmt.setString(3,empresas.getNombre());
		    stmt.setString(4,empresas.getDireccion());
			stmt.setString(5,empresas.getPoblacion());
			stmt.setString(6,empresas.getProvincia());
			stmt.setInt(7,empresas.getCp());
			stmt.setInt(8, empresas.getTelefono1());
			stmt.setInt(9, empresas.getTelefono2());
			stmt.setInt(10, empresas.getTelefono3());
			stmt.setString(11,empresas.getPersonaContacto());
			stmt.setString(12,empresas.getMail());
			stmt.setString(13,empresas.getWeb());
			stmt.setInt(14,empresas.getTipoCoste());
			stmt.setString(15, empresas.getIban());
			stmt.setString(16,empresas.getObservaciones());
			stmt.setString(17,empresas.getActivo());
			stmt.setInt(18, empresas.getidEmpresa());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return null;
		}
	}

	public String buscaNombre(Integer empresa, String tipo) {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT Nombre FROM empresas WHERE id_empresa = '"+empresa+"' AND tipo = '"+tipo+"'";
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

	public Integer buscaId(String variable, String string) {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT id_empresa FROM empresas WHERE Nombre = '"+variable+"' AND tipo = '"+string+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				return result.getInt("id_empresa");
			} else {
				return 0;
			}
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return 0;
		}
	}

}