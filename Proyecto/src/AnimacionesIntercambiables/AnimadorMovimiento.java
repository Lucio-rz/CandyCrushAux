package AnimacionesIntercambiables;

import java.awt.Point;

import Bloques.Bloque;
import Grafica.GUI;


/**
 * Modela el comportamiento de un animador que permite visualizar el cambio de posición de una entidad.
 * Cuando el animador comienza su animación, modifica la ubicación de la celda, en función de parámetros step, delay, que se asumen válidos.
 * El animador hace uso de un Thread específico para efectivizar el comportamiento.
 * Una vez finalizada la animación, el animador notificará a su manager de esta situación.
 *
 *
 */

public class AnimadorMovimiento extends Thread implements Animador {

	protected ManejadorAnimaciones mi_manager;
	protected Bloque mi_celda_animada;
	protected  int pos_x_destino;
	protected  int pos_y_destino;
	
	protected int step;
	protected int delay;
	
	/**
	 * Inicializa el estado del animador, considerando
	 * @param m El manejador de animaciones al que le notificará el fin de la animación, cuando corresponda.
	 * @param step La cantidad de pixels que se desplaza la Celda en cada movimiento.
	 * @param d El delay establecido entre desplazamiento y desplazamiento.
	 * @param c La celda animada.
	 */
	public AnimadorMovimiento(ManejadorAnimaciones m, int step, int d, Bloque b) {
		mi_manager = m;
		mi_celda_animada = b;
		this.step = step;
		delay = d;
		
		int size_label = 76;

		pos_x_destino = b.getDulceActual().getPosX()  * size_label;
		pos_y_destino = b.getDulceActual().getPosY() * size_label;

	}	
	public Bloque get_bloque_asociada() {
		return mi_celda_animada;
	}
	
	@Override
	public void comenzar_animacion() {
		this.start();
	}
	
	@Override
	public void run() {
		
		int size_label = 76;

		int pos_x_actual =  mi_celda_animada.getBounds().y;
		int pos_y_actual =  mi_celda_animada.getBounds().x;
		
		int paso_en_x = 0;
		int paso_en_y = 0;

		if (pos_x_actual != pos_x_destino) {
			if (pos_x_actual < pos_x_destino) {
				paso_en_x = 1;
			
			}
			else paso_en_x = -1;
		
		}
		
		if (pos_y_actual != pos_y_destino) {
			if (pos_y_actual < pos_y_destino) {
				paso_en_y = 1;
			}
			else paso_en_y = -1;
		}

		
			while( (pos_x_actual != pos_x_destino) || (pos_y_actual != pos_y_destino) ){
			pos_x_actual += paso_en_x * step;
			pos_y_actual += paso_en_y * step; 
			mi_celda_animada.setBounds(pos_y_actual, pos_x_actual, size_label, size_label); 
			try {
		
				sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		System.out.println("termine de animar");
		
		mi_manager.notificarse_finalizacion_animacion(this);
}
}