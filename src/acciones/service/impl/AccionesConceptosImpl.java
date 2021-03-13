package acciones.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.controller.AccionesConceptosController;
import acciones.dto.ConceptosDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.ProyectosDTO;
import acciones.repository.AccionesConceptos;

//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesConceptosImpl implements AccionesConceptosController{
	AccionesConceptos accConceptos = new AccionesConceptos();
	public  Boolean grabarConcepto(ConceptosDTO conceptos) {
		return accConceptos.grabarConcepto(conceptos);
	}
	
	public  ArrayList<ObjetoJComboBox> consultaConceptos(int empresa) {
		return accConceptos.consultaConceptos(empresa);	
	}

	public  ConceptosDTO buscaConcepto(ConceptosDTO conceptos) {
		return accConceptos.buscaConcepto(conceptos);		
	}

	public  Boolean deleteConcepto(int idConcepto) {
		return accConceptos.deleteConcepto(idConcepto);	
	}

	public  Boolean updateConcepto(ConceptosDTO conceptos) {
		return accConceptos.updateConcepto(conceptos);
	}

	public  String buscaDescripcion(Integer concepto) {
		return accConceptos.buscaDescripcion(concepto);	
	}

	public  Integer buscaConcepto(String variable) {
		return accConceptos.buscaConcepto(variable);	
	}

	@Override
	public Boolean grabarConceptoProyecto(ConceptosDTO conceptos) {
		// TODO Auto-generated method stub
		return accConceptos.grabarConcepProyecto(conceptos);
	}
	
	public String creaConsulta(ConceptosDTO paramConsulta) {
		String consulta, select, where, order, whereAND;
		select = "select idProyecto,idConcepto, importe, iva from conceptoproyecto ";
		where = "WHERE empresa = "+paramConsulta.getIdEmpresa()+" AND idProyecto = "+paramConsulta.getIdProyecto()+"";
		order = " order by idConcepto";
		consulta = select + where;
		
		return consulta = consulta + order;
	}

}