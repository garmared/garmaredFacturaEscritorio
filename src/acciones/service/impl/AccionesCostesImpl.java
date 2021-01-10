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
}