package acciones.controller;

import java.util.ArrayList;

import acciones.dto.ClientesDTO;
import acciones.dto.ConceptosDTO;
import acciones.dto.ObjetoJComboBox;

public interface accionesConceptos {
	//insert de conceptos
	public Boolean grabarConcepto(String descripcion);
	//listado de identificadores de conceptos
	public ArrayList<ObjetoJComboBox> consultaConceptos();
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
