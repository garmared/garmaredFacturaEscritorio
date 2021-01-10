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

}