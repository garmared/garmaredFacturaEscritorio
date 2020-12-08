package acciones.dto;

public class ObjetoJComboBox {
	private String nombre;
	private int numero;
	
	public ObjetoJComboBox(int numero, String nombre) {
		this.setNumero(numero);
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String toString() {
		return this.getNombre();
	}
}
