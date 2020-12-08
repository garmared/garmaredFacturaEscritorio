package acciones.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.dto.EmpresasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ProyectosDTO;

//definicion de todas las acciones que tenemos en la aplicacion
public interface accionesProyectos {
	//accion para grabar un proyecto
	public Boolean grabarProyectos(ProyectosDTO entrada);
	//listado de proyectos
	public ArrayList<ObjetoJComboBox> consultaProyectos();
	//consulta de un proyecto
	public ProyectosDTO buscaProyecto(ProyectosDTO proyecto);
	//borrado de un proyecto
	public Boolean deleteProyecto(int idProyecto);
	//modificacion de un proyecto
	public Boolean updateProyectos(ProyectosDTO proyecto);
	//descripcion de un proyecto por identificador
	public  String buscaDescripcion(Integer proyecto);
	//identificador de proyecto a partir de nombre
	public Integer buscaProyecto(String variable);
	
}