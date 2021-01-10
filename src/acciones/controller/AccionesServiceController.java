package acciones.controller;

import java.sql.Connection;
import java.sql.ResultSet;

import acciones.dto.ServiceDTO;

//definicion de todas las acciones que tenemos en la aplicacion
public interface AccionesServiceController{
	//devuelve el alto y ancho de la pantalla del sistema en que se est� ejecutando, as� podemos montar las ventanas en funci�n de esta definici�n
	public ServiceDTO obtenerVentanaActual();	
	public Integer controlLogin(String usuario, String password);
	public Connection getConexion();
	public ResultSet getTabla(String Consulta, Connection connection);
}