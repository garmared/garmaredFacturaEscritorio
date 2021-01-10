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

}
