package acciones.controller;

import java.util.ArrayList;
import acciones.dto.FacturasDTO;

public interface AccionesFacturaController {
	//accion para grabar una factura
	public Boolean grabarFactura(FacturasDTO entrada);
	//consulta de una factura por cliente y fecha
	public FacturasDTO buscaFactura(FacturasDTO factura);
	//borrado de una factura por identificador
	public Boolean deleteFactura(int idFactura);
	//modificacion de un afactura por identificador
	public Boolean updateFactura(FacturasDTO factura);
	//prepara la consutla db2 seg�n par�metro
	public String creaConsultaEmitida(FacturasDTO factura);
	public String creaConsultaRecibida(FacturasDTO factura);
}
