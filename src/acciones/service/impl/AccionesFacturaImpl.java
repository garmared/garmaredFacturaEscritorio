package acciones.service.impl;

import java.util.ArrayList;

import acciones.controller.AccionesFacturaController;
import acciones.dto.FacturasDTO;
import acciones.repository.AccionesFactura;

public class AccionesFacturaImpl implements AccionesFacturaController {
	AccionesFactura accFactura = new AccionesFactura();
	public Boolean grabarFactura(FacturasDTO entrada) {
		return accFactura.grabarFactura(entrada);
		
	}

	public FacturasDTO buscaFactura(FacturasDTO factura) {
		return accFactura.buscaFactura(factura);
	}

	public Boolean deleteFactura(int idFactura) {
		return accFactura.deleteFactura(idFactura);
	}

	public Boolean updateFactura(FacturasDTO factura) {
		return accFactura.updateFactura(factura);
	}
	
	public ArrayList buscarFacturas(FacturasDTO factura) {
		return accFactura.buscarFacturas(factura);
	}

	@Override
	public ArrayList buscaFacturas(FacturasDTO factura) {
		// TODO Auto-generated method stub
		return null;
	}

	public String creaConsulta(FacturasDTO entrada) {
		String consulta, select, where, order, whereAND;
		select = "select fecha, id_factura, vencimiento, id_proyecto, id_cliente, id_concepto, id_coste, id_proveedor, pagado from factura ";
		where = "WHERE id_empresa = "+entrada.getEmpresa()+"";
		order = " order by id_factura, fecha";
		consulta = select + where;
		if (entrada.getCliente()!=0) {
			whereAND = " AND id_cliente = '"+entrada.getCliente()+"'";
			consulta = consulta + whereAND;
		}
		if (entrada.getFecha()!=0){
			whereAND = " AND fecha = '"+entrada.getFecha()+"'";
			consulta = consulta + whereAND;
		}
		if (entrada.getProveedor()!=0){
			whereAND = " AND id_proveedor = '"+entrada.getProveedor()+"'";
			consulta = consulta + whereAND;
		}
		if (entrada.getCoste()!=0){
			whereAND = " AND id_coste = '"+entrada.getCoste()+"'";
			consulta = consulta + whereAND;
		}
		if (entrada.getPagado()!="Todos"){
			whereAND = " AND pagado = '"+entrada.getPagado()+"'";
			consulta = consulta + whereAND;
		}
		return consulta = consulta + order;
	}

}