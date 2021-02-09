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

	public FacturasDTO buscaFactura(int identificador, int empresa) {
		return accFactura.buscaFactura(identificador,empresa);
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


	public String creaConsultaEmitida(FacturasDTO entrada) {
		String consulta, select, where, order, whereAND;
		select = "select fecha, id_factura, vencimiento, id_proyecto, id_cliente, id_concepto, id_coste, pagado from factura ";
		where = "WHERE id_empresa = "+entrada.getEmpresa()+" AND id_proveedor = '0'";
		order = " order by id_factura, fecha";
		consulta = select + where;
		if (entrada.getCliente()!=0) {
			whereAND = " AND id_cliente = '"+entrada.getCliente()+"'";
			consulta = consulta + whereAND;
		}
		if (entrada.getFecha()>0){
			whereAND = " AND fecha = '"+entrada.getFecha()+"'";
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
	public String creaConsultaRecibida(FacturasDTO entrada) {
		String consulta, select, where, order, whereAND;
		select = "select fecha, id_factura, vencimiento, id_proyecto, id_concepto, id_coste, id_proveedor, pagado from factura ";
		where = "WHERE id_empresa = "+entrada.getEmpresa()+" AND id_cliente = '0'";
		order = " order by id_factura, fecha";
		consulta = select + where;
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

	public String creaConsultaAnyos(Integer idEmpresa) {
		String consulta, select, where, order;
		select = "SELECT DISTINCT(YEAR(fecha)) AS anyoFecha FROM factura ";
		where = "WHERE id_empresa = "+idEmpresa+" AND id_proveedor = '0'";
		order = " order by anyoFecha";
		consulta = select + where;
		return consulta = consulta + order;
	}

	public String creaConsultaIvaE(FacturasDTO entrada) {
		String consulta, select, where, order, whereAND;
		select = "select fecha, id_factura, vencimiento, id_proyecto, id_concepto, id_coste, id_cliente, base_impo,IVA,pagado from factura ";
		where = "WHERE id_empresa = "+entrada.getIdEmpresa()+" AND id_proveedor = '0'";
		order = " order by id_factura, fecha";
		consulta = select + where;
		int anyo,mes,dia1,dia31,fecha1,fecha2;
		anyo=entrada.getAnyo()*10000;
		mes=entrada.getMes()*100;
		dia1=1;
		dia31=31;
		fecha1=anyo+mes+dia1;
		fecha2=anyo+mes+dia31;
		whereAND = " AND fecha between '"+fecha1+"' AND '"+fecha2+"'";
		consulta = consulta + whereAND;
		
		return consulta = consulta + order;
	}

	public String creaConsultaIvaR(FacturasDTO entrada) {
		String consulta, select, where, order, whereAND;
		select = "select fecha, id_factura, vencimiento, id_proyecto, id_concepto, id_coste, id_proveedor, base_impo,IVA,pagado from factura ";
		where = "WHERE id_empresa = "+entrada.getIdEmpresa()+" AND id_cliente = '0'";
		order = " order by id_factura, fecha";
		consulta = select + where;
		int anyo,mes,dia1,dia31,fecha1,fecha2;
		anyo=entrada.getAnyo()*10000;
		mes=entrada.getMes()*100;
		dia1=1;
		dia31=31;
		fecha1=anyo+mes+dia1;
		fecha2=anyo+mes+dia31;
		whereAND = " AND fecha between '"+fecha1+"' AND '"+fecha2+"'";
		consulta = consulta + whereAND;
		
		return consulta = consulta + order;
	}
}
