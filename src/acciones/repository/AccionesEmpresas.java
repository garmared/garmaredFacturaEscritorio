package acciones.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import acciones.dto.EmpresasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.service.impl.AccionesServiceImpl;

//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesEmpresas{
	AccionesServiceImpl accService = new AccionesServiceImpl();
	public Boolean grabarEmpresas(EmpresasDTO entrada) {
		try{
			Connection connection=accService.getConexion();						
			PreparedStatement stmt;
			//las empresas no necesitan relacionarse con sigo mismas los proveedores se han de relacionar con una empresa.
			int ind=0;
			if (entrada.getTipo()=="E") {
				stmt = connection.prepareStatement("INSERT INTO empresas VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, 0);
				stmt.setString(18, entrada.getRegMercantil());
				stmt.setInt(19, entrada.getTomo());
				stmt.setInt(20, entrada.getFolio());
				stmt.setInt(21, entrada.getHoja());
				stmt.setInt(22, entrada.getInscripcion());
			}else {
				stmt = connection.prepareStatement("INSERT INTO empresas VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, entrada.getIdEmpresa());
				stmt.setInt(2, entrada.getEmpresa());
				ind = 1;
			}
			stmt.setString(ind+2, entrada.getNif());
			stmt.setString(ind+3, entrada.getTipo());
			stmt.setString(ind+4,entrada.getNombre());
			stmt.setString(ind+5,entrada.getDireccion());
			stmt.setString(ind+6,entrada.getPoblacion());
			stmt.setString(ind+7,entrada.getProvincia());
			stmt.setInt(ind+8,entrada.getCp());
			stmt.setInt(ind+9,entrada.getTelefono1());
			stmt.setInt(ind+10,entrada.getTelefono2());
			stmt.setInt(ind+11,entrada.getTelefono3());
			stmt.setString(ind+12,entrada.getPersonaContacto());
			stmt.setString(ind+13,entrada.getMail());
			stmt.setString(ind+14,entrada.getWeb());
			stmt.setString(ind+15,entrada.getIban());
			stmt.setString(ind+16,entrada.getObservaciones());
			stmt.setString(ind+17,entrada.getActivo());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en grabarEmpresas: "+ex.getMessage().toString());
			return false;
		}
		
	}

	public ArrayList<ObjetoJComboBox> listadoEmpresas() {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;
			ArrayList<ObjetoJComboBox> salida= new ArrayList<ObjetoJComboBox>();
			String peticion = "SELECT id_empresa, Nombre FROM empresas WHERE tipo = 'E' ORDER BY Nombre";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_empresa"),result.getString("Nombre"));
					salida.add(temporal);
				}
				while(result.next()); 
			}
			stmt.close();
			connection.close();	
			return salida;
		}catch(Exception ex){
			System.out.println("Error en listadoEmpresas: "+ex.getMessage().toString());
			return null;
		}	
	}
	
	public ArrayList<ObjetoJComboBox> consultaEmpresas(String tipo, int empresa) {
		//dada una empresa y un tipo (tiene sentido solo para proveedor) devolvemos los nombres e identificador. 
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;
			ArrayList<ObjetoJComboBox> salida= new ArrayList<ObjetoJComboBox>();
			String peticion = "SELECT id_empresa, Nombre FROM empresas WHERE tipo = '"+tipo+"' AND empresa = "+empresa+" ORDER BY Nombre";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					ObjetoJComboBox temporal = new ObjetoJComboBox(result.getInt("id_empresa"),result.getString("Nombre"));
					salida.add(temporal);
				}
				while(result.next()); 
			}
			connection.close();	
			return salida;
		}catch(Exception ex){
			System.out.println("Error en consultaEmpresas: "+ex.getMessage().toString());
			return null;
		}	
	}


	public EmpresasDTO buscaEmpresa(EmpresasDTO empresas) {
		// TODO Auto-generated method stub
		EmpresasDTO salida = new EmpresasDTO();
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion ="";
			if(empresas.getTipo()=="E") {
				peticion = "SELECT * FROM empresas WHERE Nombre = '"+empresas.getNombre()+"' AND tipo = '"+empresas.getTipo()+"' AND empresa = '"+empresas.getIdEmpresa()+"'";
			} else {
				peticion = "SELECT * FROM empresas WHERE Nombre = '"+empresas.getNombre()+"' AND tipo = '"+empresas.getTipo()+"' AND id_empresa = '"+empresas.getIdEmpresa()+"'";
			}
			
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdEmpresa(result.getInt("id_empresa"));
				salida.setEmpresa(result.getInt("empresa"));
				salida.setNif(result.getString("CIF"));
				salida.setTipo(result.getString("tipo"));
				salida.setNombre(result.getString("Nombre"));
				salida.setDireccion(result.getString("Direccion"));
				salida.setPoblacion(result.getString("Poblacion"));
				salida.setProvincia(result.getString("Provincia"));
				salida.setCp(result.getInt("CP"));
				salida.setTelefono1(result.getInt("Telefono1"));
				salida.setTelefono2(result.getInt("Telefono2"));
				salida.setTelefono3(result.getInt("Telefono3"));
				salida.setPersonaContacto(result.getString("Persona_contacto"));
				salida.setMail(result.getString("mail"));
				salida.setWeb(result.getString("web"));
				salida.setIban(result.getString("IBAN"));
				salida.setObservaciones(result.getString("observaciones"));
				salida.setActivo(result.getString("activo"));
				salida.setRegMercantil(result.getString("reg_mercantil"));
				salida.setTomo(result.getInt("tomo"));
				salida.setFolio(result.getInt("folio"));
				salida.setHoja(result.getInt("hoja"));
				salida.setInscripcion(result.getInt("inscripcion"));
				connection.close();
				return salida;
			} else {
				salida.setIdEmpresa(0);
				connection.close();
				return salida;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaEmpresa: "+ex.getMessage().toString());
			return null;
		}	

	}

	public Boolean deleteEmpresa(int idEmpresa) {
		try{
			Connection connection=accService.getConexion();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM empresas WHERE id_empresa = ?");
		    stmt.setInt(1,idEmpresa);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en deleteEmpresa: "+ex.getMessage().toString());
			return false;
		}
	}

	public Boolean updateEmpresas(EmpresasDTO empresas) {
		try{
			Connection connection=accService.getConexion();						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE empresas set CIF = ?, tipo = ?, Nombre = ?, Direccion = ?, Poblacion = ?, Provincia = ?, CP = ?, Telefono1=?, "
		    		+ "Telefono2=?, Telefono3=?, Persona_contacto=?,mail=?,web=?,IBAN=?,observaciones=?,activo=?,reg_mercantil=?,tomo=?,folio=?,hoja=?,inscripcion=?  WHERE id_empresa = ?");
		    stmt.setString(1, empresas.getNif());
		    stmt.setString(2,empresas.getTipo());
		    stmt.setString(3,empresas.getNombre());
		    stmt.setString(4,empresas.getDireccion());
			stmt.setString(5,empresas.getPoblacion());
			stmt.setString(6,empresas.getProvincia());
			stmt.setInt(7,empresas.getCp());
			stmt.setInt(8, empresas.getTelefono1());
			stmt.setInt(9, empresas.getTelefono2());
			stmt.setInt(10, empresas.getTelefono3());
			stmt.setString(11,empresas.getPersonaContacto());
			stmt.setString(12,empresas.getMail());
			stmt.setString(13,empresas.getWeb());
			stmt.setString(14, empresas.getIban());
			stmt.setString(15,empresas.getObservaciones());
			stmt.setString(16,empresas.getActivo());
			stmt.setInt(22, empresas.getIdEmpresa());
			stmt.setString(17, empresas.getRegMercantil());
			stmt.setInt(18, empresas.getTomo());
			stmt.setInt(19, empresas.getFolio());
			stmt.setInt(20, empresas.getHoja());
			stmt.setInt(21, empresas.getInscripcion());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en updateEmpresas: "+ex.getMessage().toString());
			return false;
		}
	}
	
	public Boolean updateProveedor(EmpresasDTO empresas) {
		try{
			Connection connection=accService.getConexion();						
		    PreparedStatement stmt = connection.prepareStatement("UPDATE empresas set CIF = ?, tipo = ?, Nombre = ?, Direccion = ?, Poblacion = ?, Provincia = ?, CP = ?, Telefono1=?, "
		    		+ "Telefono2=?, Telefono3=?, Persona_contacto=?,mail=?,web=?,IBAN=?,observaciones=?,activo=? WHERE id_empresa = ? AND empresa = ?");
		    stmt.setString(1, empresas.getNif());
		    stmt.setString(2,empresas.getTipo());
		    stmt.setString(3,empresas.getNombre());
		    stmt.setString(4,empresas.getDireccion());
			stmt.setString(5,empresas.getPoblacion());
			stmt.setString(6,empresas.getProvincia());
			stmt.setInt(7,empresas.getCp());
			stmt.setInt(8, empresas.getTelefono1());
			stmt.setInt(9, empresas.getTelefono2());
			stmt.setInt(10, empresas.getTelefono3());
			stmt.setString(11,empresas.getPersonaContacto());
			stmt.setString(12,empresas.getMail());
			stmt.setString(13,empresas.getWeb());
			stmt.setString(14, empresas.getIban());
			stmt.setString(15,empresas.getObservaciones());
			stmt.setString(16,empresas.getActivo());
			stmt.setInt(17, empresas.getIdEmpresa());
			stmt.setInt(18, empresas.getEmpresa());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en updateProveedor: "+ex.getMessage().toString());
			return false;
		}
	}

	public String buscaNombre(Integer empresa, String tipo) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT Nombre FROM empresas WHERE id_empresa = '"+empresa+"' AND tipo = '"+tipo+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				String salida = result.getString("Nombre");
				connection.close();
				return salida;
			} else {
				connection.close();
				return "";
			}
		}catch(Exception ex){
			System.out.println("Error en buscaNombre empresa/proyecto: "+ex.getMessage().toString());
			return null;
		}
	}

	public Integer buscaId(String variable, String string) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT id_empresa FROM empresas WHERE Nombre = '"+variable+"' AND tipo = '"+string+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next()){
				int salida = result.getInt("id_empresa");
				stmt.close();
				connection.close();
				return salida;
			} else {
				stmt.close();
				connection.close();
				return 0;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaId empresa/proyecto: "+ex.getMessage().toString());
			return 0;
		}
	}

	public ArrayList<String> buscaDireccion(Integer idEmpresa) {
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;
			ArrayList<String> salida= new ArrayList<String>();
			String peticion = "SELECT distinct(Poblacion) FROM empresas WHERE id_empresa = "+idEmpresa+" and TIPO = 'P' ORDER BY Poblacion";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					salida.add(result.getString("Poblacion"));
				}
				while(result.next()); 
			}
			stmt.close();
			connection.close();	
			return salida;
		}catch(Exception ex){
			System.out.println("Error en buscaDireccion: "+ex.getMessage().toString());
			return null;
		}	
	}

	public EmpresasDTO buscaProveedor(int identificador, int empresa) {
		// TODO Auto-generated method stub
		EmpresasDTO salida = new EmpresasDTO();
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion = "SELECT * FROM empresas WHERE empresa = '"+identificador+"' AND id_empresa = '"+empresa+"' AND tipo = 'P'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdEmpresa(result.getInt("id_empresa"));
				salida.setEmpresa(result.getInt("empresa"));
				salida.setNif(result.getString("CIF"));
				salida.setTipo(result.getString("tipo"));
				salida.setNombre(result.getString("Nombre"));
				salida.setDireccion(result.getString("Direccion"));
				salida.setPoblacion(result.getString("Poblacion"));
				salida.setProvincia(result.getString("Provincia"));
				salida.setCp(result.getInt("CP"));
				salida.setTelefono1(result.getInt("Telefono1"));
				salida.setTelefono2(result.getInt("Telefono2"));
				salida.setTelefono3(result.getInt("Telefono3"));
				salida.setPersonaContacto(result.getString("Persona_contacto"));
				salida.setMail(result.getString("mail"));
				salida.setWeb(result.getString("web"));
				salida.setIban(result.getString("IBAN"));
				salida.setObservaciones(result.getString("observaciones"));
				salida.setActivo(result.getString("activo"));
				stmt.close();
				connection.close();
				return salida;
			} else {
				salida.setIdEmpresa(0);
				stmt.close();
				connection.close();
				return salida;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaEmpresa: "+ex.getMessage().toString());
			return null;
		}	
	}

	public Integer numeraProveedor(Integer identificador) {
		int salida=0;
		Connection connection=accService.getConexion();
		ResultSet result =null;	
		String peticion = "SELECT IFNULL(MAX(empresa), 0) as valor FROM empresas WHERE id_empresa = '"+identificador+"' AND tipo = 'P'";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			result.next();
			salida = result.getInt("valor");
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error en numeraProveedor: "+e.getMessage().toString());
			e.printStackTrace();
		}
		return salida;
		
	}

	public EmpresasDTO buscaEmpresaId(EmpresasDTO empresas) {
		EmpresasDTO salida = new EmpresasDTO();
		try {
			Connection connection=accService.getConexion();
			ResultSet result =null;	
			String peticion ="";
			peticion = "SELECT * FROM empresas WHERE empresa = 0 AND tipo = '"+empresas.getTipo()+"' AND id_empresa = '"+empresas.getIdEmpresa()+"'";
			
			
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setIdEmpresa(result.getInt("id_empresa"));
				salida.setEmpresa(result.getInt("empresa"));
				salida.setNif(result.getString("CIF"));
				salida.setTipo(result.getString("tipo"));
				salida.setNombre(result.getString("Nombre"));
				salida.setDireccion(result.getString("Direccion"));
				salida.setPoblacion(result.getString("Poblacion"));
				salida.setProvincia(result.getString("Provincia"));
				salida.setCp(result.getInt("CP"));
				salida.setTelefono1(result.getInt("Telefono1"));
				salida.setTelefono2(result.getInt("Telefono2"));
				salida.setTelefono3(result.getInt("Telefono3"));
				salida.setPersonaContacto(result.getString("Persona_contacto"));
				salida.setMail(result.getString("mail"));
				salida.setWeb(result.getString("web"));
				salida.setIban(result.getString("IBAN"));
				salida.setObservaciones(result.getString("observaciones"));
				salida.setActivo(result.getString("activo"));
				salida.setRegMercantil(result.getString("reg_mercantil"));
				salida.setTomo(result.getInt("tomo"));
				salida.setFolio(result.getInt("folio"));
				salida.setHoja(result.getInt("hoja"));
				salida.setInscripcion(result.getInt("inscripcion"));
				stmt.close();
				connection.close();
				return salida;
			} else {
				salida.setIdEmpresa(0);
				stmt.close();
				connection.close();
				return salida;
			}
		}catch(Exception ex){
			System.out.println("Error en buscaEmpresa: "+ex.getMessage().toString());
			return null;
		}	
	}
}