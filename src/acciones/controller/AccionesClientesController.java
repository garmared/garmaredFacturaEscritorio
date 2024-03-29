package acciones.controller;

import java.util.ArrayList;

import acciones.dto.ClientesDTO;
import acciones.dto.ObjetoJComboBox;

public interface AccionesClientesController {
	// accion para grabar un cliente
	public Boolean grabarCliente(ClientesDTO entrada);
	// accion para recuperar la lista de los clientes de la base de datos (identificador y nombre)
	public ArrayList<ObjetoJComboBox> consultaClientes(int empresa);
	// accion para obtener los datos de un cliente a partir del nombre
	public ClientesDTO buscaCliente(ClientesDTO cliente);
	// accion para borrar un cliente a partir del identificador
	public Boolean deleteCliente(Integer idCliente);
	// accion para actualizar un cliente a partir del identificador
	public Boolean updateCliente(ClientesDTO cliente);
	// accion para buscar el identificador de un cliente a partir del nombre
	public int buscaCliente(String nomBuscado, int empresa);
	// accion para buscar el nombre de un cliente a partir del identificador
	public String buscaNombre(Integer cliente);
	//
	public ArrayList<String> consultaPoblacion(Integer idEmpresa);
	
	public ClientesDTO buscaCliente(Integer identificador, Integer empresa);
	
	public ClientesDTO infoCliente(String variable, Integer idEmpresa);
}
