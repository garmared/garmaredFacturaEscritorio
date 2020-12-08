package acciones.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import acciones.dto.ClientesDTO;
import acciones.dto.ConceptosDTO;
import acciones.dto.ObjetoJComboBox;

//definicion de todas las acciones que tenemos en la aplicacion
public interface accionesConceptos{
	public static Boolean grabarConcepto(String descripcion) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("INSERT INTO conceptos VALUES(NULL,?)");
			stmt.setString(1, descripcion);
					
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
		
	}
	
	public static Vector<ObjetoJComboBox> consultaConceptos() {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;
			Vector<ObjetoJComboBox> salida= new Vector<ObjetoJComboBox>();
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT id_concepto, descripcion FROM conceptos ORDER BY descripcion";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_concepto"),result.getString("descripcion"));
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

	public static ConceptosDTO buscaConcepto(ConceptosDTO conceptos) {
		ConceptosDTO salida = new ConceptosDTO();
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT * FROM conceptos WHERE descripcion = '"+conceptos.getDescripcion()+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdConcepto(result.getInt("id_concepto"));
				salida.setDescripcion(result.getString("descripcion"));
				return salida;
			} else {
				salida.setIdConcepto(0);
				return salida;
			}
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return null;
		}		
	}

	public static Boolean deleteConcepto(int idConcepto) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("DELETE FROM conceptos WHERE id_concepto = ?");
		    stmt.setInt(1,idConcepto);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}	
	}

	public static Boolean updateConcepto(ConceptosDTO conceptos) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE conceptos set descripcion = ? WHERE id_concepto = ?");
			stmt.setString(1, conceptos.getDescripcion());
			stmt.setInt(2, conceptos.getIdConcepto());
			
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