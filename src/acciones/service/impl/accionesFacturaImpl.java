package acciones.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import acciones.controller.accionesFactura;
import acciones.dto.FacturasDTO;

public class accionesFacturaImpl implements accionesFactura {
	public Boolean grabarFactura(FacturasDTO entrada) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("INSERT INTO factura VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, entrada.getEmpresa());
			stmt.setInt(2,entrada.getFecha());
			stmt.setInt(3,entrada.getVencimiento());
			stmt.setInt(4,entrada.getProyecto());
			stmt.setInt(5,entrada.getCliente());
			stmt.setInt(6,entrada.getConcepto());
			stmt.setInt(7,entrada.getCoste());
			stmt.setInt(8,entrada.getProveedor());
			stmt.setDouble(9,entrada.getIrpf());
			stmt.setDouble(10,entrada.getDescuento());
			stmt.setString(11,entrada.getIban());
			stmt.setDouble(12,entrada.getBaseImpo());
			stmt.setDouble(13,entrada.getIva());
			stmt.setDouble(14,entrada.getTasa());
						
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
		
	}

	public FacturasDTO buscaFactura(FacturasDTO factura) {
		try {
			FacturasDTO salida = new FacturasDTO();
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			ResultSet result =null;	
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");
			String peticion = "SELECT * FROM factura WHERE id_cliente = '"+factura.getCliente()+"' AND fecha = '"+factura.getFecha()+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				salida.setIdFactura(result.getInt("id_factura"));
				salida.setBaseImpo(result.getDouble("base_impo"));
				salida.setCliente(result.getInt("id_cliente"));
				salida.setConcepto(result.getInt("id_concepto"));
				salida.setCoste(result.getInt("id_coste"));
				salida.setDescuento(result.getDouble("descuento"));
				salida.setEmpresa(result.getInt("id_empresa"));
				salida.setFecha(result.getInt("fecha"));
				salida.setIban(result.getString("IBAN"));
				salida.setIrpf(result.getDouble("IRPF"));
				salida.setIva(result.getDouble("IVA"));
				salida.setProveedor(result.getInt("id_proveedor"));
				salida.setProyecto(result.getInt("id_proyecto"));
				salida.setTasa(result.getDouble("tasa"));
				salida.setVencimiento(result.getInt("vencimiento"));
				return salida;
			} else {
				salida.setIdFactura(0);
				return salida;
			}
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return null;
		}
	}

	public Boolean deleteFactura(int idFactura) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("DELETE FROM factura WHERE id_factura = ?");
		    stmt.setInt(1,idFactura);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.print("Ha ocurrido el siguiente error: "+ex.getMessage().toString());
			return false;
		}
	}

	public Boolean updateFactura(FacturasDTO factura) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE factura set id_empresa = ?, fecha=?, vencimiento=?, id_proyecto=?, id_cliente=?,"
		    		+ "id_concepto=?, id_coste=?, id_proveedor=?, IRPF=?, descuento=?, IBAN=?, base_impo=?, IVA=?, tasa=? WHERE id_factura = ?");
		    stmt.setInt(1, factura.getEmpresa());
		    stmt.setInt(2,factura.getFecha());
		    stmt.setInt(3,factura.getVencimiento());
		    stmt.setInt(4,factura.getProyecto());
			stmt.setInt(5,factura.getCliente());
			stmt.setInt(6,factura.getConcepto());
			stmt.setInt(7,factura.getCoste());
			stmt.setInt(8, factura.getProveedor());
			stmt.setDouble(9, factura.getIrpf());
			stmt.setDouble(10, factura.getDescuento());
			stmt.setString(11,factura.getIban());
			stmt.setDouble(12,factura.getBaseImpo());
			stmt.setDouble(13,factura.getIva());
			stmt.setDouble(14,factura.getTasa());
			stmt.setInt(15, factura.getIdFactura());
			
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
