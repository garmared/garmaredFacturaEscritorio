package acciones.dto;

public class CostesDTO extends GeneralDTO {

	public Integer getConcepto() {
		return concepto;
	}
	public void setConcepto(Integer concepto) {
		this.concepto = concepto;
	}
	private String descripcion;
	private Integer idCoste, tipoCoste,proyecto,concepto;
	private Double importe;
		
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getIdCoste() {
		return idCoste;
	}
	public void setIdCoste(Integer idCoste) {
		this.idCoste = idCoste;
	}
	public Integer getTipoCoste() {
		return tipoCoste;
	}
	public void setTipoCoste(Integer tipoCoste) {
		this.tipoCoste = tipoCoste;
	}
	public Integer getProyecto() {
		return proyecto;
	}
	public void setProyecto(Integer proyecto) {
		this.proyecto = proyecto;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	

}
