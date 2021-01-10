package acciones.controller;

import java.util.ArrayList;

import acciones.dto.ClientesDTO;
import acciones.dto.ConceptosDTO;
import acciones.dto.ObjetoJComboBox;

public interface AccionesConceptosController {
	//insert de conceptos
	public Boolean grabarConcepto(ConceptosDTO conceptos);
	//listado de identificadores de conceptos
	public ArrayList<ObjetoJComboBox> consultaConceptos(int empresa);
	//consulta concepto por descripción
	public ConceptosDTO buscaConcepto(ConceptosDTO conceptos);
	//borrado de un concepto
	public Boolean deleteConcepto(int idConcepto);
	//modificacion de un concepto por identificador
	public Boolean updateConcepto(ConceptosDTO conceptos);
	//obtencion de descripcion a partir de identificador
	public String buscaDescripcion(Integer concepto);
	//obtencion de identificador a partir de descripcion	
	public Integer buscaConcepto(String variable);
}
