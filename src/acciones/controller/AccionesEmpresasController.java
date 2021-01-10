package acciones.controller;

import java.util.ArrayList;

import acciones.dto.EmpresasDTO;
import acciones.dto.ObjetoJComboBox;

public interface AccionesEmpresasController {
	//accion para grabar una empresa o proveedor segun el tipo
	public Boolean grabarEmpresas(EmpresasDTO entrada);
	//lista de empresas o proveedores segun el tipo
	public ArrayList<ObjetoJComboBox> listadoEmpresas();
	//lista de empresas o proveedores segun el tipo
	public ArrayList<ObjetoJComboBox> consultaEmpresas(String tipo, int empresa);
	//consulta de una empresa o proveedor por nombre
	public EmpresasDTO buscaEmpresa(EmpresasDTO empresas);
	//borrado de una emprea o proveedor por identificador
	public Boolean deleteEmpresa(int idEmpresa);
	//modificacion de una emprea o proveedor por identificador
	public Boolean updateEmpresas(EmpresasDTO empresas);
	//consulta del nombre de una empresa o proveedor por identificador y tipo
	public String buscaNombre(Integer empresa, String tipo);
	//consulta identificador de empresa o proveedor segun nombre y tipo
	public Integer buscaId(String variable, String string);
	
}
