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

	public ArrayList<String> consultaPoblacion(Integer idEmpresa) {
		// TODO Auto-generated method stub
		return accClientes.buscaDireccion(idEmpresa);
	}

	public String creaConsulta(ClientesDTO paramConsulta) {
		String consulta, select, where, order, whereAND;
		select = "select id_cliente, Nombre, CIF, Direccion, Poblacion, CP, Telefono1, Persona_contacto, mail, web, activo  from clientes ";
		where = "WHERE id_empresa = "+paramConsulta.getIdEmpresa()+"";
		order = " order by nombre, id_cliente";
		consulta = select + where;
		if (paramConsulta.getActivo()!="Todos") {
			whereAND = " AND activo = '"+paramConsulta.getActivo()+"'";
			consulta = consulta + whereAND;
		}
		if (paramConsulta.getPoblacion()!="Todos"){
			whereAND = " AND Poblacion = '"+paramConsulta.getPoblacion()+"'";
			consulta = consulta + whereAND;
		}
		return consulta = consulta + order;
	}

	public ClientesDTO buscaCliente(Integer identificador, Integer empresa) {
		// TODO Auto-generated method stub
		return accClientes.buscaCliente(identificador,empresa);
	}
	
}