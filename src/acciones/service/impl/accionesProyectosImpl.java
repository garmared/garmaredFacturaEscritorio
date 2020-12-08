package acciones.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.controller.accionesProyectos;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ProyectosDTO;

public class accionesProyectosImpl implements accionesProyectos{
		public Boolean grabarProyectos(ProyectosDTO entrada) {
			try{
				String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
				Connection connection=null;
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection=DriverManager.getConnection(conexion,"Edu","garmared");						
			    PreparedStatement stmt = connection.prepareStatement("INSERT INTO proyectos VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, entrada.getEmpresa());
				stmt.setInt(2,entrada.getFechaIni());
				stmt.setInt(3,entrada.getFechaFin());
				stmt.setInt(4,entrada.getFechaCierre());
				stmt.setInt(5,entrada.getCliente());
				stmt.setString(6,entrada.getDescripcion());
				stmt.setString(7,entrada.getWeb());
				stmt.setInt(8,entrada.getCoste());
				stmt.setString(9,entrada.getIban());
				stmt.setString(10,entrada.getObservaciones());
				stmt.setDouble(11,entrada.getImporte());
				stmt.setDouble(12,entrada.getMargen());
				
				stmt.executeUpdate();
				stmt.close();
				connection.close();
				return true;
			}catch(Exception ex){
				System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
				return false;
			}
			
		}

		public ArrayList<ObjetoJComboBox> consultaProyectos() {
			try {
				String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
				Connection connection=null;
				ResultSet result =null;
				ArrayList<ObjetoJComboBox> salida= new ArrayList<ObjetoJComboBox>();
				
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection=DriverManager.getConnection(conexion,"Edu","garmared");
				String peticion = "SELECT id_proyecto, descripcion FROM proyectos ORDER BY descripcion";
				Statement stmt = connection.createStatement();
				result = stmt.executeQuery(peticion);
				if (result.next());{
					do {
						ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_proyecto"),result.getString("descripcion"));
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

		public ProyectosDTO buscaProyecto(ProyectosDTO proyecto) {
			ProyectosDTO salida = new ProyectosDTO();
			try {
				String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
				Connection connection=null;
				ResultSet result =null;	
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection=DriverManager.getConnection(conexion,"Edu","garmared");
				String peticion = "SELECT * FROM proyectos WHERE id_cliente = '"+proyecto.getCliente()+"' AND fecha_ini = '"+proyecto.getFechaIni()+"'";
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
					return salida;
				} else {
					salida.setIdProyecto(0);
					return salida;
				}
			}catch(Exception ex){
				System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
				return null;
			}	
		}

		public Boolean deleteProyecto(int idProyecto) {
			try{
				String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
				Connection connection=null;
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection=DriverManager.getConnection(conexion,"Edu","garmared");						
			    PreparedStatement stmt = connection.prepareStatement("DELETE FROM proyectos WHERE id_proyecto = ?");
			    stmt.setInt(1,idProyecto);
				stmt.executeUpdate();
				stmt.close();
				connection.close();
				return true;
			}catch(Exception ex){
				System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
				return false;
			}
		}

		public Boolean updateProyectos(ProyectosDTO proyecto) {
			try{
				String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
				Connection connection=null;
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection=DriverManager.getConnection(conexion,"Edu","garmared");						
			    PreparedStatement stmt = connection.prepareStatement("UPDATE proyectos set id_empresa = ?, fecha_ini = ?, fecha_fin = ?, fecha_cierre = ?, id_cliente=?, descripcion=?,"
			    		+ "web=?,Tipo_coste=?,IBAN=?,observaciones=?,importe=?,margen=? WHERE id_proyecto = ?");
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
				stmt.setInt(13,proyecto.getIdProyecto());
				
				stmt.executeUpdate();
				stmt.close();
				connection.close();
				return true;
			}catch(Exception ex){
				System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
				return null;
			}
		}

		public  String buscaDescripcion(Integer proyecto) {
			try {
				String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
				Connection connection=null;
				ResultSet result =null;	
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection=DriverManager.getConnection(conexion,"Edu","garmared");
				String peticion = "SELECT descripcion FROM proyectos WHERE id_proyecto = '"+proyecto+"'";
				Statement stmt = connection.createStatement();
				result = stmt.executeQuery(peticion);
				if (result.next()){
					return result.getString("descripcion");
				} else {
					return "";
				}
			}catch(Exception ex){
				System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
				return null;
			}
		}

		public Integer buscaProyecto(String variable) {
			try {
				String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
				Connection connection=null;
				ResultSet result =null;	
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection=DriverManager.getConnection(conexion,"Edu","garmared");
				String peticion = "SELECT id_proyecto FROM proyectos WHERE id_empresa = '"+variable+"'";
				Statement stmt = connection.createStatement();
				result = stmt.executeQuery(peticion);
				if (result.next()){
					return result.getInt("id_proyecto");
				} else {
					return 0;
				}
			}catch(Exception ex){
				System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
				return null;
			}
		}


}
