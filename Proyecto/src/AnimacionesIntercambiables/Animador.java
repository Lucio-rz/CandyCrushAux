package AnimacionesIntercambiables;

import Bloques.Bloque;


/**
 * Define las operaciones esperables por sobre un elemento Animador.
 * Un animador podrá llevar adelante la animación de movimiento o de cambio de estado, de una entidad.
 * 
 *
 */
public interface Animador {
	/**
	 * Obtiene la celda que se animará.
	 * @return Retorna la celda obtenida.
	 */
	public Bloque get_bloque_asociada();
	
	/**
	 * Inicia el comportamiento asociado con la animación.
	 */
	public void comenzar_animacion();
}
