package acciones.dto;

public class PagosDTO extends GeneralDTO {

	private String estado,tipo;
	private Integer idPago,idProyecto,fecha,vencimiento;
	private double importe;
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getIdPago() {
		return idPago;
	}
	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}
	public Integer getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
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
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	
}
