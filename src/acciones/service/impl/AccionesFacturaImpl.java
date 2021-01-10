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

	public String creaConsulta(int idCliente, int fentrada, int empresa) {
		String consulta;
		if (idCliente==0) {
			if (fentrada==0){
				consulta = "select fecha, id_factura, vencimiento, id_proyecto, id_cliente, id_concepto, id_coste, id_proveedor from factura WHERE id_empresa = "+empresa+" order by id_factura, fecha";
			} else {
				consulta = "select fecha, id_factura, vencimiento, id_proyecto, id_cliente, id_concepto, id_coste, id_proveedor from factura WHERE fecha = '"+fentrada+"' AND id_empresa = "+empresa+" order by id_factura, fecha";
			}
		} else if (fentrada==0){
			consulta = "select fecha, id_factura, vencimiento, id_proyecto, id_cliente, id_concepto, id_coste, id_proveedor from factura WHERE id_cliente = '"+idCliente+"' AND id_empresa = "+empresa+" order by id_factura, fecha";
		} else {
			consulta = "select fecha, id_factura, vencimiento, id_proyecto, id_cliente, id_concepto, id_coste, id_proveedor from factura WHERE id_cliente = '"+idCliente+"' AND fecha = '"+fentrada+"' AND id_empresa = "+empresa+" order by id_factura, fecha";
		}
		
		return consulta;
	}

}
