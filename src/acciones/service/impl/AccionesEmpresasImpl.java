package acciones.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.controller.AccionesEmpresasController;
import acciones.dto.EmpresasDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.repository.AccionesEmpresas;

//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesEmpresasImpl implements AccionesEmpresasController{
	AccionesEmpresas accEmpresa = new AccionesEmpresas();
	public Boolean grabarEmpresas(EmpresasDTO entrada) {
		return accEmpresa.grabarEmpresas(entrada);	
	}

	public ArrayList<ObjetoJComboBox> listadoEmpresas() {
		return accEmpresa.listadoEmpresas();	
	}
	
	public ArrayList<ObjetoJComboBox> consultaEmpresas(String tipo, int empresa) {
		return accEmpresa.consultaEmpresas(tipo, empresa);	
	}


	public EmpresasDTO buscaEmpresa(EmpresasDTO empresas) {
		return accEmpresa.buscaEmpresa(empresas);	
	}

	public Boolean deleteEmpresa(int idEmpresa) {
		return accEmpresa.deleteEmpresa(idEmpresa);
	}

	public Boolean updateEmpresas(EmpresasDTO empresas) {
		return accEmpresa.updateEmpresas(empresas);
	}

	public String buscaNombre(Integer empresa, String tipo) {
		return accEmpresa.buscaNombre(empresa, tipo);
	}

	public Integer buscaId(String variable, String string) {
		return accEmpresa.buscaId(variable, string);
	}

	public String creaConsulta(EmpresasDTO paramConsulta) {
		String consulta, select, where, order, whereAND;
		select = "select id_empresa, Nombre, CIF, Direccion, Poblacion, CP, Telefono1, Persona_contacto, mail, web, activo  from empresas ";
		where = "WHERE empresa = "+paramConsulta.getIdEmpresa()+" AND tipo = 'P' ";
		order = " order by nombre, id_empresa";
		consulta = select + where;
		if (paramConsulta.getActivo()!="Todos") {
			whereAND = " AND activo = '"+paramConsulta.getActivo()+"'";
			consulta = consulta + whereAND;
		}
		if (paramConsulta.getPoblacion()!="Todos"){
			whereAND = " AND Poblacion = '"+paramConsulta.getPoblacion()+"'";
			consulta = consulta + whereAND;
		}
		return consulta = consulta + order;
	}

	public ArrayList<String> consultaPoblacion(Integer idEmpresa) {
		return accEmpresa.buscaDireccion(idEmpresa);
	}

}