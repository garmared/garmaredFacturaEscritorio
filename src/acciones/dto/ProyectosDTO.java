package acciones.dto;

public class ProyectosDTO {

	private String descripcion,observaciones,web,iban;
	private Integer idProyecto,empresa, fechaIni, fechaFin, fechaCierre, cliente,coste;
	private double importe, margen;
	
	
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public double getMargen() {
		return margen;
	}
	public void setMargen(double margen) {
		this.margen = margen;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public Integer getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}
	public Integer getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}
	public Integer getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Integer fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Integer getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Integer fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Integer getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Integer fechaCierre) {
		this.fechaCierre = fechaCierre;
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
	
}
