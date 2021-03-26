package acciones.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.dto.FacturasDTO;
import acciones.service.impl.AccionesServiceImpl;

//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesFactura{
	AccionesServiceImpl accService = new AccionesServiceImpl();
	public Boolean grabarFactura(FacturasDTO entrada) {
		try{
			Connection connection=accService.getConexion();
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO factura VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
			stmt.setString(15,entrada.getPagado());
						
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en grabarFactura: "+ex.getMessage().toString());
			return false;
		}
		
	}

	public FacturasDTO buscaFactura(FacturasDTO factura) {
		try {
			FacturasDTO salida = new FacturasDTO();
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT * FROM factura WHERE id_cliente = '"+factura.getCliente()+"' AND fecha = '"+factura.getFecha()+"' AND id_empresa = "+factura.getIdEmpresa()+"";
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
				salida.setPagado(result.getString("pagado"));
				connection.close();
				return salida;
			} else {
				salida.setIdFactura(0);
				connection.close();
				return salida;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaFactura: "+ex.getMessage().toString());
			return null;
		}
	}

	public FacturasDTO buscaFactura(int identificador, int empresa) {
		try {
			FacturasDTO salida = new FacturasDTO();
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT * FROM factura WHERE id_factura = '"+identificador+"' AND id_empresa = "+empresa+"";
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
				salida.setPagado(result.getString("pagado"));
				connection.close();
				return salida;
			} else {
				salida.setIdFactura(0);
				connection.close();
				return salida;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaFactura: "+ex.getMessage().toString());
			return null;
		}
	}
	public Boolean deleteFactura(int idFactura) {
		try{
			Connection connection=accService.getConexion();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM factura WHERE id_factura = ?");
		    stmt.setInt(1,idFactura);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en deleteFactura: "+ex.getMessage().toString());
			return false;
		}
	}

	public Boolean updateFactura(FacturasDTO factura) {
		try{
			Connection connection=accService.getConexion();						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE factura set id_empresa = ?, fecha=?, vencimiento=?, id_proyecto=?, id_cliente=?,"
		    		+ "id_concepto=?, id_coste=?, id_proveedor=?, IRPF=?, descuento=?, IBAN=?, base_impo=?, IVA=?, tasa=?, pagado = ? WHERE id_factura = ?");
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
			stmt.setString(15, factura.getPagado());
			stmt.setInt(16, factura.getIdFactura());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en updateFactura: "+ex.getMessage().toString());
			return null;
		}
	}
	
	public ArrayList buscarFacturas(FacturasDTO factura) {
		Connection connection=accService.getConexion();
		ArrayList listaFacturas = new ArrayList();
		try {
			ResultSet result =null;	
			String peticion = "SELECT * FROM factura WHERE id_cliente = '"+factura.getCliente()+"' AND fecha = '"+factura.getFecha()+"' AND id_empresa = "+factura.getIdEmpresa()+"";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			while (result.next()){
				factura.setIdFactura(result.getInt("id_factura"));
				factura.setBaseImpo(result.getDouble("base_impo"));
				factura.setCliente(result.getInt("id_cliente"));
				factura.setConcepto(result.getInt("id_concepto"));
				factura.setCoste(result.getInt("id_coste"));
				factura.setDescuento(result.getDouble("descuento"));
				factura.setEmpresa(result.getInt("id_empresa"));
				factura.setFecha(result.getInt("fecha"));
				factura.setIban(result.getString("IBAN"));
				factura.setIrpf(result.getDouble("IRPF"));
				factura.setIva(result.getDouble("IVA"));
				factura.setProveedor(result.getInt("id_proveedor"));
				factura.setProyecto(result.getInt("id_proyecto"));
				factura.setTasa(result.getDouble("tasa"));
				factura.setVencimiento(result.getInt("vencimiento"));
				factura.setPagado(result.getString("pagado"));
				connection.close();
				listaFacturas.add(factura);
			}
			stmt.close();
			connection.close();
		} catch(Exception ex){
			System.out.println("Error en buscarFacturas: "+ex.getMessage().toString());
		} 
		return listaFacturas;
	}
}