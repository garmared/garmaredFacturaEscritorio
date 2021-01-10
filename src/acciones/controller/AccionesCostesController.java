package acciones.controller;

import java.util.ArrayList;

import acciones.dto.CostesDTO;
import acciones.dto.ObjetoJComboBox;

public interface AccionesCostesController {
	// accion para grabar costes
	public Boolean grabarCoste(CostesDTO coste);
	// accion para recuperar la lista de los costes de la base de datos (identificador y descripcion)
	public ArrayList<ObjetoJComboBox> consultaCostes(int empresa);
	// accion para obtener los datos de un coste a partir de la descripcion
	public CostesDTO buscaCoste(CostesDTO costes);
	// accion para borrar un coste a partir del identificador
	public Boolean deleteCoste(int idCoste);
	// accion para actualizar un coste a partir del identificador
	public Boolean updateCoste(CostesDTO costes);
	// accion para buscar la descripcion de un coste a partir del identificador
	public String buscaNombre(Integer tipoCoste);
	// accion para buscar el identificanombre de un cliente a partir del identificador
	public int buscaIdCoste(String variable);
	//busca la descripcion a partir del identificador
	public String buscaDescripcion(Integer coste);
}
