package acciones.service.impl;

import java.util.ArrayList;

import acciones.controller.AccionesConstantesController;
import acciones.dto.ConstantesDTO;
import acciones.repository.AccionesConstantes;

public class AccionesConstantesImpl implements AccionesConstantesController {
	AccionesConstantes accConstantes = new AccionesConstantes();
	@Override
	public Boolean grabarConstante(ConstantesDTO entrada) {
		return null;
	}

	public Boolean grabarConstante(ArrayList<ConstantesDTO> entrada) {
		return accConstantes.grabarConstante(entrada);
	}

	public Boolean deleteConstante(String constante) {
		return accConstantes.deleteConstante(constante);
	}

	public Boolean updateConstante(ArrayList<ConstantesDTO> entrada) {
		// borramos todas las constantes por tipoReg y insertamos la entrada
		ConstantesDTO constantes = entrada.get(0);
		Boolean accion = accConstantes.deleteConstante(constantes.getTipoReg());
		if (accion) {
			accion = accConstantes.grabarConstante(entrada);
		}
		return accion;
	}

	public ArrayList<String> buscaTipos() {
		return accConstantes.buscaTipos();
	}

	public ArrayList<ConstantesDTO> buscaConstantes(String tipo) {
		return accConstantes.buscaConstantes(tipo);
	}

}
