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
		public  String buscaNombre(int proyecto, int empresa) {
			return accProyectos.buscaNombre(proyecto,empresa);
		}
		public Integer buscaProyecto(String variable, int empresa) {
			return accProyectos.buscaProyecto(variable,empresa);
		}

		public String creaConsulta(ProyectosDTO paramConsulta) {
			String consulta, select, where, order, whereAND;
			select = "select id_proyecto,fecha_ini,fecha_fin,fecha_cierre,id_cliente,descripcion,tipo_coste,importe,margen from proyectos ";
			where = "WHERE id_empresa = "+paramConsulta.getEmpresa()+"";
			order = " order by id_proyecto, fecha_ini,fecha_fin,fecha_cierre";
			consulta = select + where;
			if (paramConsulta.getFechaCierre() > 0) {
				whereAND = " AND fecha_cierre = '"+paramConsulta.getFechaCierre()+"'";
				consulta = consulta + whereAND;
			}
			if (paramConsulta.getFechaFin() > 0) {
				whereAND = " AND fecha_fin = '"+paramConsulta.getFechaFin()+"'";
				consulta = consulta + whereAND;
			}
			if (paramConsulta.getFechaIni() > 0) {
				whereAND = " AND fecha_ini = '"+paramConsulta.getFechaIni()+"'";
				consulta = consulta + whereAND;
			}
			if (paramConsulta.getCliente() != 0) {
				whereAND = " AND id_cliente = '"+paramConsulta.getCliente()+"'";
				consulta = consulta + whereAND;
			}
			if (paramConsulta.getDescripcion() != "") {
				whereAND = " AND descripcion LIKE '%"+paramConsulta.getDescripcion()+"%'";
				consulta = consulta + whereAND;
			}

			return consulta = consulta + order;
		}

		@Override
		public ProyectosDTO buscaProyecto(int variable, int empresa) {
			// TODO Auto-generated method stub
			return accProyectos.buscaProyecto(variable,empresa);
		}

		public ProyectosDTO infoProyecto(String variable, Integer idEmpresa) {
			//obtenemos informacion del proyecto a partir del nombre
			return accProyectos.infoProyecto(variable,idEmpresa);
		}

		public ArrayList<ObjetoJComboBox> consultaProyectos(Integer idCliente, Integer idEmpresa) {
			// TODO Auto-generated method stub
			return accProyectos.consultaProyectos(idCliente,idEmpresa);	
		}
}
