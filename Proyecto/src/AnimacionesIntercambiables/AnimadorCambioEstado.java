package AnimacionesIntercambiables;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import Bloques.Bloque;


/**
 * Modela el comportamiento de un animador que permite visualizar el cambio de estado de una entidad.
 * Cuando el animador comienza su animación, modifica la imagen asociada a la celda animada.
 * La imagen que se considerará para efectivizar el cambio de estado, será la que se encontraba asociada a la celda lógica al momento
 * de crear el animador.
 * Una vez finalizada la animación, el animador notificará a su manager de esta situación.
 * 
 *
 */
public class AnimadorCambioEstado implements Animador {

	protected ManejadorAnimaciones mi_manager;
	protected Bloque mi_bloque;
	
	protected String path_img;
	
	/**
	 * Inicializa el estado interno del animador, considerando:
	 * @param m El manejador de animaciones al que le notificará el fin de la animación, cuando corresponda.
	 * @param c La celda animada.
	 */
	public AnimadorCambioEstado(ManejadorAnimaciones m, Bloque b) {
		mi_manager = m;
		mi_bloque = b;
		
		path_img = b.getDulceActual().get_imagen_representativa();
	}
	
	
	@Override
	public Bloque get_bloque_asociada() {
		return mi_bloque;
	}

	@Override
	public void comenzar_animacion() {
		int size_label = 76;
		ImageIcon imgIcon = new ImageIcon(this.getClass().getResource(path_img));
		Image imgEscalada = imgIcon.getImage().getScaledInstance(size_label, size_label, Image.SCALE_SMOOTH);
		Icon iconoEscalado = new ImageIcon(imgEscalada);
		mi_bloque.setIcon(iconoEscalado);
		mi_manager.notificarse_finalizacion_animacion(this);
	}

}
