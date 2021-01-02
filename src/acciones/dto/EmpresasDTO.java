package acciones.dto;

public class EmpresasDTO extends GeneralDTO {

	private String tipo, iban;
	private Integer idEmpresa,diaPago,tipoCoste,Empresa;
	

	

	public Integer getDiaPago() {
		return diaPago;
	}

	public void setDiaPago(Integer diaPago) {
		this.diaPago = diaPago;
	}




	public Integer getidEmpresa() {
		return idEmpresa;
	}

	public void setidEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Integer getTipoCoste() {
		return tipoCoste;
	}

	public void setTipoCoste(Integer tipoCoste) {
		this.tipoCoste = tipoCoste;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getEmpresa() {
		return Empresa;
	}

	public void setEmpresa(Integer empresa) {
		Empresa = empresa;
	}

}
