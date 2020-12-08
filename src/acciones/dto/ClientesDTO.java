package acciones.dto;

public class ClientesDTO extends GeneralDTO{

	private Integer idCliente,diaPago;
	String modaPago, fp;
	
	public String getFp() {
		return fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
	}

	public Integer getDiaPago() {
		return diaPago;
	}

	public void setDiaPago(Integer diaPago) {
		this.diaPago = diaPago;
	}

	public String getModaPago() {
		return modaPago;
	}

	public void setModaPago(String string) {
		this.modaPago = string;
	}


	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	
}
