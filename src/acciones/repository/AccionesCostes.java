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
		}	
	}

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

	public Boolean grabarCosteIndirecto(CostesDTO entrada) {
		// TODO Auto-generated method stub
		try {
			Connection connection=accService.getConexion();						
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO costesindirectos VALUES(NULL,?,?,?,?,?,?)");
			stmt.setInt(1, entrada.getIdEmpresa());
			stmt.setInt(2, entrada.getTipoCoste());
			stmt.setInt(3, entrada.getProyecto());
			stmt.setInt(4, entrada.getConcepto());
			stmt.setDouble(5, entrada.getImporte());
			stmt.setString(6, entrada.getDescripcion());
			stmt.executeUpdate();
		
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en insert de costes: "+ex.getMessage().toString());
			return false;
		}
	}

	public CostesDTO buscaCosteIndirectos(CostesDTO varCostes) {
		CostesDTO salida = new CostesDTO();
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT * FROM costesindirectos WHERE id_proyecto = '"+varCostes.getProyecto()+"' AND id_concepto = '"+varCostes.getConcepto()+"' AND empresa = "+varCostes.getIdEmpresa()+"";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdCoste(result.getInt("id_costeInd"));
				salida.setDescripcion(result.getString("descripcion"));
				salida.setIdEmpresa(result.getInt("empresa"));
				salida.setConcepto(result.getInt("id_concepto"));
				salida.setTipoCoste(result.getInt("id_coste"));
				salida.setImporte(result.getDouble("importe"));
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

	public CostesDTO buscaCosteIndirectos(int identificador, int empresa) {
		// TODO Auto-generated method stub
		CostesDTO salida = new CostesDTO();
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT * FROM costesindirectos WHERE id_costeInd = '"+identificador+"' AND empresa = "+empresa+"";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdCoste(result.getInt("id_costeInd"));
				salida.setDescripcion(result.getString("descripcion"));
				salida.setIdEmpresa(result.getInt("empresa"));
				salida.setConcepto(result.getInt("id_concepto"));
				salida.setTipoCoste(result.getInt("id_coste"));
				salida.setImporte(result.getDouble("importe"));
				salida.setProyecto(result.getInt("id_proyecto"));
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

	public Boolean deleteCosteIndirecto(int idCoste) {
		try{
			Connection connection=accService.getConexion();						
		    PreparedStatement stmt = connection.prepareStatement("DELETE FROM costesindirectos WHERE id_costeInd = ?");
		    stmt.setInt(1,idCoste);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en delteCosteIndirecto: "+ex.getMessage().toString());
			return false;
		}
	}

	public Boolean updateCosteIndirectos(CostesDTO costes) {
		try{
			Connection connection=accService.getConexion();						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE costesindirectos set descripcion = ?, id_coste=?, id_proyecto=?,id_concepto=?,importe=? WHERE id_coste = ?");
			stmt.setString(1, costes.getDescripcion());
			stmt.setInt(2, costes.getTipoCoste());
			stmt.setInt(3, costes.getProyecto());
			stmt.setInt(4, costes.getConcepto());
			stmt.setDouble(5, costes.getImporte());
			stmt.setInt(6, costes.getIdCoste());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en updateCosteIndirecto: "+ex.getMessage().toString());
			return null;
		}	}
}