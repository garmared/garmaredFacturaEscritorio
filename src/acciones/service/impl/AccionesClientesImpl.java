package acciones.service.impl;

import java.util.ArrayList;

import acciones.controller.AccionesClientesController;
import acciones.dto.ClientesDTO;
import acciones.dto.ObjetoJComboBox;
import acciones.repository.AccionesClientes;

//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesClientesImpl implements AccionesClientesController{
	AccionesClientes accClientes = new AccionesClientes();
	
	public Boolean grabarCliente(ClientesDTO entrada) {
		return accClientes.grabarCliente(entrada);
	}
	
	public ArrayList<ObjetoJComboBox> consultaClientes(int empresa) {
		ArrayList<ObjetoJComboBox> salida= new ArrayList<ObjetoJComboBox>();
		salida = accClientes.consultaClientes(empresa);
		return salida;	
	}

	public ClientesDTO buscaCliente(ClientesDTO cliente) {
		ClientesDTO salida = new ClientesDTO();
		salida = accClientes.buscaCliente(cliente);
		return salida;	
	}

	public Boolean deleteCliente(Integer idCliente) {
		return accClientes.deleteCliente(idCliente);
	}

	public Boolean updateCliente(ClientesDTO cliente) {
		return accClientes.updateCliente(cliente);
	}

	public int buscaCliente(String nomBuscado) {
		return accClientes.buscaCliente(nomBuscado);
	}

	public String buscaNombre(Integer cliente) {
		return accClientes.buscaNombre(cliente);
	}
	
}