package acciones.service.impl;

import java.util.ArrayList;

import acciones.controller.AccionesPagosController;
import acciones.dto.ConceptosDTO;
import acciones.dto.PagosDTO;
import acciones.repository.AccionesPagos;

public class AccionesPagosImpl implements AccionesPagosController {
	AccionesPagos accPagos = new AccionesPagos();

	public String creaConsultaPagos(PagosDTO entrada) {
		String consulta, select, where, order;
		select = "select id_Empresa, idPago, idProyecto, importe, fecha, vencimiento, estado, tipo, observaciones from pagos ";
		where = "WHERE id_Empresa = "+entrada.getIdEmpresa()+"";
		order = " order by idPago";
		consulta = select + where;
		return consulta = consulta + order;
	}

	public Boolean grabarPagos(ArrayList<PagosDTO> pagos) {		
		PagosDTO entrada= pagos.get(0);
		Boolean seguir = accPagos.deletePagos(entrada.getIdProyecto(),entrada.getIdEmpresa());
		if (seguir) {
			seguir = accPagos.grabarPagos(pagos);
		}
		return seguir;
	}
}
