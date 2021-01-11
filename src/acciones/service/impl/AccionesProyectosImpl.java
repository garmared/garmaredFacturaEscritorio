package acciones.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.controller.AccionesProyectosController;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ProyectosDTO;
import acciones.repository.AccionesProyectos;

public class AccionesProyectosImpl implements AccionesProyectosController{
	AccionesProyectos accProyectos = new AccionesProyectos();	
	public Boolean grabarProyectos(ProyectosDTO entrada) {
			return accProyectos.grabarProyectos(entrada);			
		}

		public ArrayList<ObjetoJComboBox> consultaProyectos(int empresa) {
			return accProyectos.consultaProyectos(empresa);	
		}

		public ProyectosDTO buscaProyecto(ProyectosDTO proyecto) {
			return accProyectos.buscaProyecto(proyecto);	
		}

		public Boolean deleteProyecto(int idProyecto) {
			return accProyectos.deleteProyecto(idProyecto);
		}

		public Boolean updateProyectos(ProyectosDTO proyecto) {
			return accProyectos.updateProyectos(proyecto);
		}

		public  String buscaDescripcion(int proyecto, int empresa) {
			return accProyectos.buscaDescripcion(proyecto,empresa);
		}

		public Integer buscaProyecto(String variable, int empresa) {
			return accProyectos.buscaProyecto(variable,empresa);
		}
}
