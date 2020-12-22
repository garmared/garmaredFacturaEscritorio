package acciones.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.controller.accionesConceptos;
import acciones.dto.ConceptosDTO;
import acciones.dto.ObjetoJComboBox;

//definicion de todas las acciones que tenemos en la aplicacion
public class accionesConceptosImpl implements accionesConceptos{
	public  Boolean grabarConcepto(ConceptosDTO conceptos) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("INSERT INTO conceptos VALUES(NULL,?,?)");
			stmt.setString(1, conceptos.getDescripcion());
			stmt.setInt(2, conceptos.getIdEmpresa());
					
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en grabarConcepto: "+ex.getMessage().toString());
			return false;
		}
		
	}
	
	public  ArrayList<ObjetoJComboBox> consultaConceptos(int empresa) {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;
			ArrayList<ObjetoJComboBox> salida= new ArrayList<ObjetoJComboBox>();
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT id_concepto, descripcion FROM conceptos WHERE id_empresa = "+empresa+" ORDER BY descripcion";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_concepto"),result.getString("descripcion"));
					salida.add(temporal);
				}
				while(result.next()); 
				}
				
			return salida;
		}catch(Exception ex){
			System.out.println("Error en consultaConceptos: "+ex.getMessage().toString());
			return null;
		}	
	}

	public  ConceptosDTO buscaConcepto(ConceptosDTO conceptos) {
		ConceptosDTO salida = new ConceptosDTO();
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT * FROM conceptos WHERE descripcion = '"+conceptos.getDescripcion()+"' AND id_empresa = "+conceptos.getIdEmpresa()+"";
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
			System.out.println("Error en buscaConcepto: "+ex.getMessage().toString());
			return null;
		}		
	}

	public  Boolean deleteConcepto(int idConcepto) {
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
			System.out.println("Error en deleteConcepto: "+ex.getMessage().toString());
			return false;
		}	
	}

	public  Boolean updateConcepto(ConceptosDTO conceptos) {
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
			System.out.println("Error en updateConcepto: "+ex.getMessage().toString());
			return false;
		}
	}

	public  String buscaDescripcion(Integer concepto) {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT descripcion FROM conceptos WHERE id_concepto = '"+concepto+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				return result.getString("descripcion");
			} else {
				return "";
			}
		}catch(Exception ex){
			System.out.println("Error en buscaDescripcion concepto: "+ex.getMessage().toString());
			return null;
		}	
	}

	public  Integer buscaConcepto(String variable) {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT id_comcepto FROM conceptos WHERE descripcion = '"+variable+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				return result.getInt("id_concepto");
			} else {
				return 0;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaConcepto: "+ex.getMessage().toString());
			return null;
		}	
	}
}