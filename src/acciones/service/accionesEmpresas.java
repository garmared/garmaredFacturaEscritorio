package acciones.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import acciones.dto.*;

//definicion de todas las acciones que tenemos en la aplicacion
public interface accionesEmpresas{
	public static Boolean grabarEmpresas(String activo, String cif, int cp, String direccion,String mail, String nombre, String observaciones,String personaContacto,
			String poblacion,String provincia, int telefono1, int telefono2, int telefono3,String web, int tipoCoste, String iban, String tipo) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO empresas VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, cif);
			stmt.setString(2, tipo);
			stmt.setString(3,nombre);
			stmt.setString(4,direccion);
			stmt.setString(5,poblacion);
			stmt.setString(6,provincia);
			stmt.setInt(7,cp);
			stmt.setInt(8,telefono1);
			stmt.setInt(9,telefono2);
			stmt.setInt(10,telefono3);
			stmt.setString(11,personaContacto);
			stmt.setString(12,mail);
			stmt.setString(13,web);
			stmt.setInt(14,tipoCoste);
			stmt.setString(15,iban);
			stmt.setString(16,observaciones);
			stmt.setString(17,activo);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
		
	}

	public static Vector<ObjetoJComboBox> consultaEmpresas(String tipo) {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;
			Vector<ObjetoJComboBox> salida= new Vector<ObjetoJComboBox>();
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT id_empresa, Nombre FROM empresas WHERE tipo = '"+tipo+"' ORDER BY Nombre";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_empresa"),result.getString("Nombre"));
					salida.addElement(temporal);
				}
				while(result.next()); 
				}
				
			return salida;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return null;
		}	
	}

	public static EmpresasDTO buscaEmpresa(EmpresasDTO empresas) {
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

	public static Boolean deleteEmpresa(int idEmpresa) {
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

	public static Boolean updateEmpresas(EmpresasDTO empresas) {
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

}