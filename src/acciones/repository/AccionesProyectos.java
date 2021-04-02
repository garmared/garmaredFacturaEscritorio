package acciones.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.dto.ObjetoJComboBox;
import acciones.dto.ProyectosDTO;
import acciones.service.impl.AccionesServiceImpl;

//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesProyectos{
	AccionesServiceImpl accService = new AccionesServiceImpl();	
	public Boolean grabarProyectos(ProyectosDTO entrada) {
			try{
				Connection connection=accService.getConexion();
				PreparedStatement stmt = connection.prepareStatement("INSERT INTO proyectos VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setString(1, entrada.getNombre());
				stmt.setInt(2, entrada.getEmpresa());
				stmt.setInt(3,entrada.getFechaIni());
				stmt.setInt(4,entrada.getFechaFin());
				stmt.setInt(5,entrada.getFechaCierre());
				stmt.setInt(6,entrada.getCliente());
				stmt.setString(7,entrada.getDescripcion());
				stmt.setString(8,entrada.getWeb());
				stmt.setInt(9,entrada.getCoste());
				stmt.setString(10,entrada.getIban());
				stmt.setString(11,entrada.getObservaciones());
				stmt.setDouble(12,entrada.getImporte());
				stmt.setDouble(13,entrada.getMargen());
				
				stmt.executeUpdate();
				stmt.close();
				connection.close();
				return true;
			}catch(Exception ex){
				System.out.println("Error en grabarProyectos: "+ex.getMessage().toString());
				return false;
			}
			
		}

		public ArrayList<ObjetoJComboBox> consultaProyectos(int empresa) {
			try {
				Connection connection=accService.getConexion();ResultSet result =null;
				ArrayList<ObjetoJComboBox> salida= new ArrayList<ObjetoJComboBox>();
				String peticion = "SELECT id_proyecto, descripcion FROM proyectos WHERE id_empresa = "+empresa+" ORDER BY descripcion";
				Statement stmt = connection.createStatement();
				result = stmt.executeQuery(peticion);
				if (result.next());{
					do {
						ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_proyecto"),result.getString("descripcion"));
						salida.add(temporal);
					}
					while(result.next()); 
				}
				stmt.close();
				connection.close();	
				return salida;
			}catch(Exception ex){
				System.out.println("Error en consultaProyectos: "+ex.getMessage().toString());
				return null;
			}	
		}

		public ProyectosDTO buscaProyecto(ProyectosDTO proyecto) {
			ProyectosDTO salida = new ProyectosDTO();
			try {
				Connection connection=accService.getConexion();
				ResultSet result =null;	
				String peticion = "SELECT * FROM proyectos WHERE id_cliente = '"+proyecto.getCliente()+"' AND fecha_ini = '"+proyecto.getFechaIni()+"'";
				Statement stmt = connection.createStatement();
				result = stmt.executeQuery(peticion);
				//result.next();
				if (result.next()){
					salida.setNombre(result.getString("nombre"));
					salida.setIdProyecto(result.getInt("id_proyecto"));
					salida.setEmpresa(result.getInt("id_empresa"));
					salida.setFechaIni(result.getInt("fecha_ini"));
					salida.setFechaFin(result.getInt("fecha_fin"));
					salida.setFechaCierre(result.getInt("fecha_cierre"));
					salida.setCliente(result.getInt("id_cliente"));
					salida.setDescripcion(result.getString("descripcion"));
					salida.setWeb(result.getString("web"));
					salida.setCoste(result.getInt("Tipo_coste"));
					salida.setObservaciones(result.getString("observaciones"));
					salida.setImporte(result.getDouble("importe"));
					salida.setMargen(result.getDouble("margen"));
					salida.setIban(result.getString("IBAN"));
					stmt.close();
					connection.close();
					return salida;
				} else {
					salida.setIdProyecto(0);
					stmt.close();
					connection.close();
					return salida;
				}
			}catch(Exception ex){
				System.out.println("Error en buscaProyecto: "+ex.getMessage().toString());
				return null;
			}	
		}

		public Boolean deleteProyecto(int idProyecto) {
			try{
				Connection connection=accService.getConexion();						
			    PreparedStatement stmt = connection.prepareStatement("DELETE FROM proyectos WHERE id_proyecto = ?");
			    stmt.setInt(1,idProyecto);
				stmt.executeUpdate();
				stmt.close();
				connection.close();
				return true;
			}catch(Exception ex){
				System.out.println("Error en deleteProyecto: "+ex.getMessage().toString());
				return false;
			}
		}

		public Boolean updateProyectos(ProyectosDTO proyecto) {
			try{
				Connection connection=accService.getConexion();						
			    PreparedStatement stmt = connection.prepareStatement("UPDATE proyectos set id_empresa = ?, fecha_ini = ?, fecha_fin = ?, fecha_cierre = ?, id_cliente=?, descripcion=?,"
			    		+ "web=?,Tipo_coste=?,IBAN=?,observaciones=?,importe=?,margen=?, nombre=? WHERE id_proyecto = ?");
			    stmt.setInt(1, proyecto.getEmpresa());
			    stmt.setInt(2,proyecto.getFechaIni());
			    stmt.setInt(3,proyecto.getFechaFin());
			    stmt.setInt(4,proyecto.getFechaCierre());
				stmt.setInt(5,proyecto.getCliente());
				stmt.setString(6,proyecto.getDescripcion());
				stmt.setString(7,proyecto.getWeb());
				stmt.setInt(8,proyecto.getCoste());
				stmt.setString(9, proyecto.getIban());
				stmt.setString(10, proyecto.getObservaciones());
				stmt.setDouble(11, proyecto.getImporte());
				stmt.setDouble(12,proyecto.getMargen());
				stmt.setString(13,proyecto.getNombre());
				stmt.setInt(14,proyecto.getIdProyecto());
				
				stmt.executeUpdate();
				stmt.close();
				connection.close();
				return true;
			}catch(Exception ex){
				System.out.println("Error en updateProyectos: "+ex.getMessage().toString());
				return null;
			}
		}

		public  String buscaDescripcion(Integer proyecto, int empresa) {
			try {
				Connection connection=accService.getConexion();
				ResultSet result =null;	
				String peticion = "SELECT descripcion FROM proyectos WHERE id_empresa = '"+empresa+"' AND id_proyecto = '"+proyecto+"'";
				Statement stmt = connection.createStatement();
				result = stmt.executeQuery(peticion);
				if (result.next()){
					String salida =result.getString("descripcion"); 
					stmt.close();
					connection.close();
					return salida;
				} else {
					stmt.close();
					connection.close();
					return "";
				}
			}catch(Exception ex){
				System.out.println("Error buscaDescripcion proyecto: "+ex.getMessage().toString());
				return null;
			}
		}

		public Integer buscaProyecto(String variable, int empresa) {
			try {
				Connection connection=accService.getConexion();
				ResultSet result =null;	
				String peticion = "SELECT id_proyecto FROM proyectos WHERE id_empresa = '"+empresa+"' AND descripcion = '"+variable+"'";
				Statement stmt = connection.createStatement();
				result = stmt.executeQuery(peticion);
				if (result.next()){
					int salida = result.getInt("id_proyecto");
					stmt.close();
					connection.close();
					return salida;
				} else {
					stmt.close();
					connection.close();
					return 0;
				}
			}catch(Exception ex){
				System.out.println("Error en buscaProyecto: "+ex.getMessage().toString());
				return null;
			}
		}

		public ProyectosDTO buscaProyecto(int variable, int empresa) {
			// TODO Auto-generated method stub
			ProyectosDTO salida = new ProyectosDTO();
			try {
				Connection connection=accService.getConexion();
				ResultSet result =null;	
				String peticion = "SELECT * FROM proyectos WHERE id_proyecto = '"+variable+"' AND id_empresa = '"+empresa+"'";
				Statement stmt = connection.createStatement();
				result = stmt.executeQuery(peticion);
				//result.next();
				if (result.next()){
					salida.setIdProyecto(result.getInt("id_proyecto"));
					salida.setEmpresa(result.getInt("id_empresa"));
					salida.setFechaIni(result.getInt("fecha_ini"));
					salida.setFechaFin(result.getInt("fecha_fin"));
					salida.setFechaCierre(result.getInt("fecha_cierre"));
					salida.setCliente(result.getInt("id_cliente"));
					salida.setDescripcion(result.getString("descripcion"));
					salida.setWeb(result.getString("web"));
					salida.setCoste(result.getInt("Tipo_coste"));
					salida.setObservaciones(result.getString("observaciones"));
					salida.setImporte(result.getDouble("importe"));
					salida.setMargen(result.getDouble("margen"));
					salida.setIban(result.getString("IBAN"));
					salida.setNombre(result.getString("nombre"));
					stmt.close();
					connection.close();
					return salida;
				} else {
					salida.setIdProyecto(0);
					stmt.close();
					connection.close();
					return salida;
				}
			}catch(Exception ex){
				System.out.println("Error en buscaProyecto: "+ex.getMessage().toString());
				return null;
			}	
		}


}