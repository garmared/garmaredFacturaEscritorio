package acciones.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.controller.AccionesCostesController;
import acciones.dto.CostesDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.repository.AccionesCostes;

//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesCostesImpl implements AccionesCostesController{
	AccionesCostes accCostes = new AccionesCostes();
	public Boolean grabarCoste(CostesDTO coste) {
		// TODO Auto-generated method stub
		return accCostes.grabarCoste(coste);
	}
	
	public ArrayList<ObjetoJComboBox> consultaCostes(int empresa) {
		return accCostes.consultaCostes(empresa); 
	}

	public CostesDTO buscaCoste(CostesDTO costes) {
		return accCostes.buscaCoste(costes);
	}

	public Boolean deleteCoste(int idCoste) {
		return accCostes.deleteCoste(idCoste);
	}
		

	public Boolean updateCoste(CostesDTO costes) {
		return accCostes.updateCoste(costes);
	}

	public String buscaNombre(Integer tipoCoste) {
		return accCostes.buscaNombre(tipoCoste);
	}

	public int buscaIdCoste(String variable) {
		return accCostes.buscaIdCoste(variable);
	}

	public String buscaDescripcion(Integer coste) {
		return accCostes.buscaDescripcion(coste);
	}

	public Integer buscaCoste(String variable) {
		return accCostes.buscaCoste(variable);
	}

	public Boolean grabarCosteIndirecto(CostesDTO entrada) {
		// TODO Auto-generated method stub
		return accCostes.grabarCosteIndirecto(entrada);
	}

	public CostesDTO buscaCostesIndirectos(CostesDTO varCostes) {
		// TODO Auto-generated method stub
		return accCostes.buscaCosteIndirectos(varCostes);
	}

	public CostesDTO buscaCostesIndirectos(int identificador, int empresa) {
		// TODO Auto-generated method stub
		return accCostes.buscaCosteIndirectos(identificador,empresa);
	}
	public String creaConsulta(CostesDTO paramConsulta) {
		String consulta, select, where, order, whereAND;
		select = "select id_costeInd, empresa, id_coste, id_proyecto, id_concepto, importe, descripcion from costesindirectos ";
		where = "WHERE empresa = "+paramConsulta.getIdEmpresa()+"";
		order = " order by id_costeInd";
		consulta = select + where;
		if (paramConsulta.getTipoCoste() > 0) {
			whereAND = " AND id_coste = '"+paramConsulta.getTipoCoste()+"'";
			consulta = consulta + whereAND;
		}
		if (paramConsulta.getProyecto() > 0){
			whereAND = " AND id_proyecto = '"+paramConsulta.getProyecto()+"'";
			consulta = consulta + whereAND;
		}
		if (paramConsulta.getConcepto() > 0){
			whereAND = " AND id_concepto = '"+paramConsulta.getConcepto()+"'";
			consulta = consulta + whereAND;
		}
		return consulta = consulta + order;
	}

	public Boolean deleteCosteIndirecto(int idCoste) {
		// TODO Auto-generated method stub
		return accCostes.deleteCosteIndirecto(idCoste);
	}

	public Boolean updateCosteIndirecto(CostesDTO costes) {
		// TODO Auto-generated method stub
		return accCostes.updateCosteIndirectos(costes);
	}		
}