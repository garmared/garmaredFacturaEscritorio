package acciones.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import acciones.dto.CostesDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.service.impl.AccionesServiceImpl;

//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesCostes{
	AccionesServiceImpl accService = new AccionesServiceImpl();
	public Boolean grabarCoste(CostesDTO coste) {
		// TODO Auto-generated method stub
		try {
			Connection connection=accService.getConexion();						
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO costes VALUES(NULL,?,?)");
			stmt.setString(1, coste.getDescripcion());
			stmt.setInt(2, coste.getIdEmpresa());
			stmt.executeUpdate();
		
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en insert de costes: "+ex.getMessage().toString());
			return false;
		}
	}
	
	public ArrayList<ObjetoJComboBox> consultaCostes(int empresa) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;
			ArrayList<ObjetoJComboBox> salida= new ArrayList<ObjetoJComboBox>();
			String peticion = "SELECT id_coste, descripcion FROM costes WHERE id_empresa = "+empresa+"  ORDER BY descripcion";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_coste"),result.getString("descripcion"));
					salida.add(temporal);
				}
				while(result.next()); 
				}
				
			return salida;
		}catch(Exception ex){
			System.out.println("Error en consultaCostes: "+ex.getMessage().toString());
			return null;
		}	
	}

	public CostesDTO buscaCoste(CostesDTO costes) {
		CostesDTO salida = new CostesDTO();
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT * FROM costes WHERE descripcion = '"+costes.getDescripcion()+"' AND id_empresa = "+costes.getIdEmpresa()+"";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdCoste(result.getInt("id_coste"));
				salida.setDescripcion(result.getString("descripcion"));
				salida.setIdEmpresa(result.getInt("id_empresa"));
				return salida;
			} else {
				salida.setIdCoste(0);
				return salida;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaCoste: "+ex.getMessage().toString());
			return null;
		}
	}

	public Boolean deleteCoste(int idCoste) {
		try{
			Connection connection=accService.getConexion();						
		    PreparedStatement stmt = connection.prepareStatement("DELETE FROM costes WHERE id_coste = ?");
		    stmt.setInt(1,idCoste);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en delteCoste: "+ex.getMessage().toString());
			return false;
		}	}

	public Boolean updateCoste(CostesDTO costes) {
		try{
			Connection connection=accService.getConexion();						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE costes set descripcion = ? WHERE id_coste = ?");
			stmt.setString(1, costes.getDescripcion());
			stmt.setInt(2, costes.getIdCoste());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en updateCoste: "+ex.getMessage().toString());
			return null;
		}
	}

	public String buscaNombre(Integer tipoCoste) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT descripcion FROM costes WHERE id_coste = '"+tipoCoste+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				return result.getString("descripcion");
			} else {
				return "";
			}
		}catch(Exception ex){
			System.out.println("Error en buscaNombre: "+ex.getMessage().toString());
			return null;
		}
	}

	public int buscaIdCoste(String variable) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT id_coste FROM costes WHERE descripcion = '"+variable+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				return result.getInt("id_coste");
			} else {
				return 0;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaIdCoste: "+ex.getMessage().toString());
			return 0;
		}
	}

	public String buscaDescripcion(Integer coste) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT descripcion FROM costes WHERE id_coste = '"+coste+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				return result.getString("descripcion");
			} else {
				return "";
			}
		}catch(Exception ex){
			System.out.println("Error en buscaDescripcion Coste: "+ex.getMessage().toString());
			return null;
		}
	}

	public Integer buscaCoste(String variable) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT id_coste FROM costes WHERE descripcion = '"+variable+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				return result.getInt("id_coste");
			} else {
				return 0;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaCoste: "+ex.getMessage().toString());
			return null;
		}
	}		
		
}