package acciones.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import acciones.dto.PagosDTO;
import acciones.service.impl.AccionesServiceImpl;

//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesPagos{
	AccionesServiceImpl accService = new AccionesServiceImpl();
	public Boolean grabarPagos(ArrayList<PagosDTO> entrada) {
		try{
			PagosDTO pagos = new PagosDTO();
			Connection connection=accService.getConexion();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO pagos VALUES(?,NULL,?,?,?,?,?,?,?)");
			int elementos = entrada.size();
		    for (int i=0; i<elementos;i++) {
		    	pagos = entrada.get(i);
		    	stmt.setInt(1, pagos.getIdEmpresa());
		    	stmt.setInt(2, pagos.getIdProyecto());
		    	stmt.setDouble(3, pagos.getImporte());
		    	stmt.setInt(4, pagos.getFecha());
		    	stmt.setInt(5, pagos.getVencimiento());
		    	stmt.setString(6, pagos.getEstado());
		    	stmt.setString(7, pagos.getTipo());
		    	stmt.setString(8, pagos.getObservaciones());
		    	stmt.addBatch();
		    }
					
			stmt.executeBatch();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en grabarPagos: "+ex.getMessage().toString());
			return false;
		}
	}
	public Boolean deletePagos(Integer idProyecto, Integer idEmpresa) {
		try{
			Connection connection=accService.getConexion();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM pagos WHERE idProyecto = ? AND id_Empresa = ?");
		    stmt.setInt(1,idProyecto);
		    stmt.setInt(2,idEmpresa);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en deletePagos: "+ex.getMessage().toString());
			return false;
		}		
	}
	
}