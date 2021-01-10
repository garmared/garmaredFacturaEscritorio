package acciones.controller;

import java.sql.Connection;
import java.sql.ResultSet;

import acciones.dto.ServiceDTO;

//definicion de todas las acciones que tenemos en la aplicacion
public interface AccionesServiceController{
	//devuelve el alto y ancho de la pantalla del sistema en que se está ejecutando, así podemos montar las ventanas en función de esta definición
	public ServiceDTO obtenerVentanaActual();	
	public Integer controlLogin(String usuario, String password);
	public Connection getConexion();
	public ResultSet getTabla(String Consulta, Connection connection);
}