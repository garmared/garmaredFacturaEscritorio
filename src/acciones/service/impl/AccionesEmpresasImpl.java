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
		if (entrada.getTipo()=="P") {
			int numero = accEmpresa.numeraProveedor(entrada.getIdEmpresa());
			numero ++;
			entrada.setEmpresa(numero);
		}
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

	public Boolean updateProveedor(EmpresasDTO empresas) {
		return accEmpresa.updateProveedor(empresas);
	}
	public String buscaNombre(Integer empresa, String tipo) {
		return accEmpresa.buscaNombre(empresa, tipo);
	}

	public Integer buscaId(String variable, String string) {
		return accEmpresa.buscaId(variable, string);
	}

	public String creaConsulta(EmpresasDTO paramConsulta) {
		String consulta, select, where, order, whereAND;
		select = "select empresa, Nombre, CIF, Direccion, Poblacion, CP, Telefono1, Persona_contacto, mail, web, activo  from empresas ";
		where = "WHERE id_empresa = "+paramConsulta.getIdEmpresa()+" AND tipo = 'P' ";
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

	@Override
	public EmpresasDTO buscaProveedor(int identificador, int empresa) {
		// TODO Auto-generated method stub
		return accEmpresa.buscaProveedor(identificador,empresa);
	}

	public EmpresasDTO buscaEmpresaId(EmpresasDTO empresa) {
		// TODO Auto-generated method stub
		return accEmpresa.buscaEmpresaId(empresa);
	}

}