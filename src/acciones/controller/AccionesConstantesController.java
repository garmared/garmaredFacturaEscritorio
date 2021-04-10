package acciones.controller;

import java.util.ArrayList;

import acciones.dto.ConstantesDTO;
import acciones.dto.ObjetoJComboBox;

public interface AccionesConstantesController {
	// accion para grabar una contante
	public Boolean grabarConstante(ConstantesDTO entrada);
	// accion para grabar un lista de constates
	public Boolean grabarConstante(ArrayList<ConstantesDTO> entrada);
	//delete de un tiporeg concreto
	public Boolean deleteConstante(String constante);
	//saca todos los tipos de constantes que hay
	public ArrayList<String> buscaTipos();
	//devuelve los valores de un tipo de constante
	public ArrayList<ConstantesDTO> buscaConstantes(String tipo);
	//devuelve los valores de un tipo de constante en formato para combo
	public ArrayList<ObjetoJComboBox> consultaConstante(String tipo); 
}
