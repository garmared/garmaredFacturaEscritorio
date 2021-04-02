package acciones.dto;

public class EmpresasDTO extends GeneralDTO {

	private String tipo, iban,regMercantil;
	private Integer diaPago,tipoCoste,Empresa,tomo,folio,hoja,inscripcion;
	

	public Integer getDiaPago() {
		return diaPago;
	}

	public void setDiaPago(Integer diaPago) {
		this.diaPago = diaPago;
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

	public String getRegMercantil() {
		return regMercantil;
	}

	public void setRegMercantil(String regMercantil) {
		this.regMercantil = regMercantil;
	}

	public Integer getTomo() {
		return tomo;
	}

	public void setTomo(Integer tomo) {
		this.tomo = tomo;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Integer getHoja() {
		return hoja;
	}

	public void setHoja(Integer hoja) {
		this.hoja = hoja;
	}

	public Integer getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Integer inscripcion) {
		this.inscripcion = inscripcion;
	}

}
