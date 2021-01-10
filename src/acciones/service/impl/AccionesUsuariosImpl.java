package acciones.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import acciones.controller.AccionesUsuariosController;
import acciones.dto.ObjetoJComboBox;
import acciones.dto.UsuariosDTO;
import acciones.repository.AccionesUsuarios;


//definicion de todas las acciones que tenemos en la aplicacion
public class AccionesUsuariosImpl implements AccionesUsuariosController{
	AccionesUsuarios accUsuarios = new AccionesUsuarios();
	@Override
	public Boolean grabarUsuario(UsuariosDTO entrada) {
		return accUsuarios.grabarUsuario(entrada);
	}

	public UsuariosDTO buscaUsuario(String nomBuscado) {
		return accUsuarios.buscaUsuario(nomBuscado);
	}

	public Boolean deleteUsuario(int idUsuario) {
		return accUsuarios.deleteUsuario(idUsuario);
	}

	public Boolean updateUsuarios(UsuariosDTO usuarios) {
		return accUsuarios.updateUsuarios(usuarios);
	}

	public ArrayList<ObjetoJComboBox> listaUsuarios() {
		return accUsuarios.listaUsuarios();
	}

	public String buscaSeguridad(Integer nivelSeguridad) {
		String salida ="";
		switch (nivelSeguridad) {
			case 1: 
				salida = "desarrollo";
				break;
			case 2: 
				salida = "usuario";
				break;
		}
		return salida;
	}

	public Integer buscaIdSeguridad(String variable) {
		Integer salida = 0;
		switch (variable) {
			case "desarrollo":
				salida=1;
				break;
			case "usuario":
				salida=2;
				break;
		}
		return salida;
	}

}