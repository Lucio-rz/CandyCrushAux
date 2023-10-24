package Entidades;

public interface Enfocable {
	
	/**
	 * Fija el foco sobre el elemento que recibe el mensaje, en caso de ser posible.
	 * @return true si es posible aplicar el foco, false en caso contrario.
	 */
	public void enfocar();
	/**
	 * Quita el foco sobre el elemento que recibe el mensaje.
	 */
	public void desenfocar();

}
