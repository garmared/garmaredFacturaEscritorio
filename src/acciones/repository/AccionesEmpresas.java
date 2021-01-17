package acciones.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO empresas VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			//las empresas no necesitan relacionarse con sigo mismas los proveedores se han de relacionar con una empresa.
			if (entrada.getTipo()=="E") {
				stmt.setInt(1, 0);
			}else {
				stmt.setInt(1, entrada.getEmpresa());
			}
			stmt.setString(2, entrada.getCif());
			stmt.setString(3, entrada.getTipo());
			stmt.setString(4,entrada.getNombre());
			stmt.setString(5,entrada.getDireccion());
			stmt.setString(6,entrada.getPoblacion());
			stmt.setString(7,entrada.getProvincia());
			stmt.setInt(8,entrada.getCp());
			stmt.setInt(9,entrada.getTelefono1());
			stmt.setInt(10,entrada.getTelefono2());
			stmt.setInt(11,entrada.getTelefono3());
			stmt.setString(12,entrada.getPersonaContacto());
			stmt.setString(13,entrada.getMail());
			stmt.setString(14,entrada.getWeb());
			stmt.setString(15,entrada.getIban());
			stmt.setString(16,entrada.getObservaciones());
			stmt.setString(17,entrada.getActivo());
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
			String peticion = "SELECT * FROM empresas WHERE Nombre = '"+empresas.getNombre()+"' AND tipo = '"+empresas.getTipo()+"'";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			//result.next();
			if (result.next()){
				salida.setidEmpresa(result.getInt("id_empresa"));
				salida.setEmpresa(result.getInt("empresa"));
				salida.setCif(result.getString("CIF"));
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
				return salida;
			} else {
				salida.setidEmpresa(0);
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
		    PreparedStatement stmt = connection.prepareStatement("UPDATE empresas set empresa=?, CIF = ?, tipo = ?, Nombre = ?, Direccion = ?, Poblacion = ?, Provincia = ?, CP = ?, Telefono1=?, "
		    		+ "Telefono2=?, Telefono3=?, Persona_contacto=?,mail=?,web=?,IBAN=?,observaciones=?,activo=? WHERE id_empresa = ?");
		    stmt.setInt(1, empresas.getEmpresa());
		    stmt.setString(2, empresas.getCif());
		    stmt.setString(3,empresas.getTipo());
		    stmt.setString(4,empresas.getNombre());
		    stmt.setString(5,empresas.getDireccion());
			stmt.setString(6,empresas.getPoblacion());
			stmt.setString(7,empresas.getProvincia());
			stmt.setInt(8,empresas.getCp());
			stmt.setInt(9, empresas.getTelefono1());
			stmt.setInt(10, empresas.getTelefono2());
			stmt.setInt(11, empresas.getTelefono3());
			stmt.setString(12,empresas.getPersonaContacto());
			stmt.setString(13,empresas.getMail());
			stmt.setString(14,empresas.getWeb());
			stmt.setString(15, empresas.getIban());
			stmt.setString(16,empresas.getObservaciones());
			stmt.setString(17,empresas.getActivo());
			stmt.setInt(18, empresas.getidEmpresa());
			
			stmt.executeUpdate();
			stmt.close();
			connection.close();
			return true;
		}catch(Exception ex){
			System.out.println("Error en updateEmpresas: "+ex.getMessage().toString());
			return null;
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
				return result.getString("Nombre");
			} else {
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
				return result.getInt("id_empresa");
			} else {
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
			String peticion = "SELECT distinct(Poblacion) FROM empresas WHERE empresa = "+idEmpresa+" and TIPO = 'P' ORDER BY Poblacion";
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery(peticion);
			if (result.next());{
				do {
					salida.add(result.getString("Poblacion"));
				}
				while(result.next()); 
				}
				
			return salida;
		}catch(Exception ex){
			System.out.println("Error en buscaDireccion: "+ex.getMessage().toString());
			return null;
		}	
	}
}