package acciones.dto;

public class FacturasDTO extends GeneralDTO {

	private String iban;
	private Integer idFactura,empresa, proveedor, fecha, vencimiento, proyecto, cliente,coste, concepto;
	private double irpf, descuento,baseImpo,iva,tasa;
	
	
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public Integer getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}
	
	
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public Integer getCoste() {
		return coste;
	}
	public void setCoste(Integer coste) {
		this.coste = coste;
	}
	public Integer getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	public Integer getFecha() {
		return fecha;
	}
	public void setFecha(Integer fecha) {
		this.fecha = fecha;
	}
	public Integer getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Integer vencimiento) {
		this.vencimiento = vencimiento;
	}
	public Integer getProyecto() {
		return proyecto;
	}
	public void setProyecto(Integer proyecto) {
		this.proyecto = proyecto;
	}
	public Integer getConcepto() {
		return concepto;
	}
	public void setConcepto(Integer concepto) {
		this.concepto = concepto;
	}
	public double getIrpf() {
		return irpf;
	}
	public void setIrpf(double irpf) {
		this.irpf = irpf;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getBaseImpo() {
		return baseImpo;
	}
	public void setBaseImpo(double baseImpo) {
		this.baseImpo = baseImpo;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getTasa() {
		return tasa;
	}
	public void setTasa(double tasa) {
		this.tasa = tasa;
	}
	public Integer getProveedor() {
		return proveedor;
	}
	public void setProveedor(Integer proveedor) {
		this.proveedor = proveedor;
	}
	
}
