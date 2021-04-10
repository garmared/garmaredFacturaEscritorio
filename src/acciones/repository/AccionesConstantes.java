package acciones.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.dto.ConceptosDTO;
import acciones.dto.ConstantesDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.service.impl.AccionesServiceImpl;

public class AccionesConstantes {
	AccionesServiceImpl accService = new AccionesServiceImpl();
	
	public Boolean grabarConstante(ArrayList<ConstantesDTO> entrada) {
		ConstantesDTO constantes = new ConstantesDTO();
		try {
			Connection connection=accService.getConexion();						
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO constantes VALUES(?,?,?)");
			int elementos = entrada.size();
			for (int i=0; i<elementos;i++) {
				constantes = entrada.get(i);
				stmt.setString(1, constantes.getTipoReg());
				stmt.setInt(2, constantes.getCodigo());
				stmt.setString(3, constantes.getNombre());
				stmt.addBatch();
			}
			stmt.executeBatch();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en grabarConstante: "+ex.getMessage().toString());
			return false;
		}
	}

	public Boolean deleteConstante(String constante) {
		try{
			Connection connection=accService.getConexion();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM constantes WHERE tiporeg = ?");
		    stmt.setString(1,constante);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en deleteConstante: "+ex.getMessage().toString());
			return false;
		}	
	}

	public ArrayList<String> buscaTipos() {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;
			ArrayList<String> salida= new ArrayList<String>();
			String peticion = "SELECT distinct(tiporeg) FROM constantes order by tiporeg";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			int ind =0; 
			if (result.next());{
				do {
					salida.add(ind,result.getString("tiporeg"));
					ind++;
				}
				while(result.next()); 
			}
			stmt.close();
			connection.close();	
			return salida;
		}catch(Exception ex){
			System.out.println("Error en buscaTipos: "+ex.getMessage().toString());
			return null;
		}	
	}
	
	public ArrayList<ConstantesDTO> buscaConstantes(String tipo){
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;
			ArrayList<ConstantesDTO> salida= new ArrayList<ConstantesDTO>();
			String peticion = "SELECT * FROM constantes where tiporeg = '"+tipo+"' order by tiporeg";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			int ind =0; 
			if (result.next());{
				do {
					ConstantesDTO constantes = new ConstantesDTO();
					constantes.setCodigo(result.getInt("codigo"));
					constantes.setNombre(result.getString("nombre"));
					constantes.setTipoReg(result.getString("tiporeg"));
					salida.add(ind,constantes);
					ind++;
				}
				while(result.next()); 
			}
			stmt.close();
			connection.close();	
			return salida;
		}catch(Exception ex){
			System.out.println("Error en buscaContantes: "+ex.getMessage().toString());
			return null;
		}	
	}
}
