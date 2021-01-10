package acciones.controller;

import acciones.dto.UsuariosDTO;

public interface AccionesUsuariosController {
	// accion para grabar un cliente
	public Boolean grabarUsuario(UsuariosDTO entrada);
	//búsqueda de usuario a partir de nombre
	public UsuariosDTO buscaUsuario(String nomBuscado);
	//borrado de usuario por identificador
	public Boolean deleteUsuario(int idUsuario);
	//accion para actualizar usuario
	public Boolean updateUsuarios(UsuariosDTO usuarios);
}
