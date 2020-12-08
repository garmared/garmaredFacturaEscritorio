package acciones.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import acciones.dto.EmpresasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ProyectosDTO;

//definicion de todas las acciones que tenemos en la aplicacion
public interface accionesProyectos{
	public static Boolean grabarProyectos(int empresa, int fechaIni, int fechaFin, int fechaCierre, int cliente, String descripcion, String web,
			int tipoCoste, String iban, String observaciones, double importe, double margen) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("INSERT INTO proyectos VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, empresa);
			stmt.setInt(2,fechaIni);
			stmt.setInt(3,fechaFin);
			stmt.setInt(4,fechaCierre);
			stmt.setInt(5,cliente);
			stmt.setString(6,descripcion);
			stmt.setString(7,web);
			stmt.setInt(8,tipoCoste);
			stmt.setString(9,iban);
			stmt.setString(10,observaciones);
			stmt.setDouble(11,importe);
			stmt.setDouble(12,margen);
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
		
	}

	public static Vector<ObjetoJComboBox> consultaProyectos() {
		try {
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;
			Vector<ObjetoJComboBox> salida= new Vector<ObjetoJComboBox>();
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT id_proyecto, descripcion FROM proyectos ORDER BY descripcion";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_proyecto"),result.getString("descripcion"));
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

	public static ProyectosDTO buscaProyecto(ProyectosDTO proyecto) {
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

}