package acciones.dto;

public class ServiceDTO{

	private Integer altoVentana, anchoVentana, acceso;

	public Integer getAltoVentana() {
		return altoVentana;
	}

	public void setAltoVentana(Integer altoVentana) {
		this.altoVentana = altoVentana;
	}

	public Integer getAnchoVentana() {
		return anchoVentana;
	}

	public void setAnchoVentana(Integer anchoVentana) {
		this.anchoVentana = anchoVentana;
	}

	public ServiceDTO(Integer altoVentana, Integer anchoVentana) {
		super();
		this.altoVentana = altoVentana;
		this.anchoVentana = anchoVentana;
	}

	public ServiceDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getAcceso() {
		return acceso;
	}

	public void setAcceso(Integer acceso) {
		this.acceso = acceso;
	}
	
	
}
