package acciones.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import acciones.dto.ObjetoJComboBox;

//definicion de todas las acciones que tenemos en la aplicacion
public interface accionesFactura{
	public static Boolean grabarFactura(int empresa,int fecha,int vencimiento,int proyecto,int cliente,int concepto,int coste,int proveedor,Double irpf,
			Double descuento,String iban, Double baseImponible, Double iva,Double tasa) {
		try{
			String conexion = "jdbc:mysql://localhost:3306/garmared_factura";
			Connection connection=null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(conexion,"Edu","garmared");						
		    PreparedStatement stmt = connection.prepareStatement("INSERT INTO factura VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, empresa);
			stmt.setInt(2,fecha);
			stmt.setInt(3,vencimiento);
			stmt.setInt(4,proyecto);
			stmt.setInt(5,cliente);
			stmt.setInt(6,concepto);
			stmt.setInt(7,coste);
			stmt.setInt(8,proveedor);
			stmt.setDouble(9,irpf);
			stmt.setDouble(10,descuento);
			stmt.setString(11,iban);
			stmt.setDouble(12,baseImponible);
			stmt.setDouble(13,iva);
			stmt.setDouble(14,tasa);
						
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