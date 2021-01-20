package acciones.dto;

public class ServiceDTO{

	private Integer altoVentana, anchoVentana, acceso, idEmpresa, identificador;
	private String nombreEmpresa, noPrincipal;

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

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getNoPrincipal() {
		return noPrincipal;
	}

	public void setNoPrincipal(String noPrincipal) {
		this.noPrincipal = noPrincipal;
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
	
	
}
