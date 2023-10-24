package AnimacionesIntercambiables;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Bloques.Bloque;
import Grafica.GUI;

/**
 * Modela el manager de animaciones requerido para efectivizar las mismas en función al orden en el que fueron solicitadas desde la ventana.
 * Ante cada nueva animación solicitada por ventana y que se debe realizar por sobre una celda, se encarga de efectivizar estas animaciones 
 * en el orden que corresponda a su arriba (FIFO).
 * Permite que dos o más animaciones por sobre una misma celda se realicen efectivamente en orden secuencial, sin solapamientos.
 * Las animaciones entre entidades diferentes se resolverán, recurrentemente, considerando que algunos animadores se efectivizarán mediante Threads.
 
 *
 */
public class CentralAnimaciones implements ManejadorAnimaciones{
	
	protected GUI ventana;
	protected HashMap<Bloque, List<Animador>> mapeo_bloque_animaciones;
	//protected int size_label;
	
	public CentralAnimaciones(GUI v) {
		ventana = v;
		mapeo_bloque_animaciones = new HashMap<Bloque, List<Animador>>();
	}
	
	/**
	 * Indica que la celda parametrizada debe ser animada a partir de un cambio de posición.
	 * La animación será lanzada de inmediato, siempre que no existan animaciones en progreso sobre c.
	 * La animación será encolada para efectivizarse en el futuro, a la espera de que las animaciones solicitadas previamente sobre c
	 * se realicen primero.
	 * @param c Celda que debe animarse, en relación a la posición que ubica la JLabel y la ubicación indicada por la entidad lógica
	 * referenciada por c.
	 */
	public void animar_cambio_posicion(Bloque b) {
		Animador animador = new AnimadorMovimiento(this, 1, 1, b);
		ventana.notificarse_animacion_en_progreso();
		
		if (tiene_animaciones_en_progreso (b) ) {
			mapeo_bloque_animaciones.get(b).add(animador);
		}else {
			mapeo_bloque_animaciones.put(b, new LinkedList<Animador>());
			mapeo_bloque_animaciones.get(b).add(animador);
			animador.comenzar_animacion();
			System.out.println("llegue al else");
		}
	}
	
	/**
	 * Indica que la celda parametrizada debe ser animada a partir de un cambio de estado.
	 * La animación será lanzada de inmediato, siempre que no existan animaciones en progreso sobre c.
	 * La animación será encolada para efectivizarse en el futuro, a la espera de que las animaciones solicitadas previamente sobre c
	 * se realicen primero.
	 * @param c Celda que debe animarse, en relación a la imagen actual que la representa.
	 */
	public void animar_cambio_estado(Bloque b) {
		Animador animador = new AnimadorCambioEstado(this, b);
		ventana.notificarse_animacion_en_progreso();
		
		if (tiene_animaciones_en_progreso (b) ) {
			mapeo_bloque_animaciones.get(b).add(animador);
		}else {
			mapeo_bloque_animaciones.put(b, new LinkedList<Animador>());
			mapeo_bloque_animaciones.get(b).add(animador);
			animador.comenzar_animacion();
			
		}
		
	}

	@Override
	public void notificarse_finalizacion_animacion(Animador a) {
		Animador animador;
		List<Animador> animaciones_para_bloque;
		
		ventana.notificarse_animacion_finalizada();
		
		animaciones_para_bloque = mapeo_bloque_animaciones.get(a.get_bloque_asociada());
		animaciones_para_bloque.remove(a);
		
		if (!animaciones_para_bloque.isEmpty()) {
			animador = animaciones_para_bloque.get(0);
			animador.comenzar_animacion();
		}
	}
	
	/**
	 * Estima si la celda parametrizada actualmente cuenta con animaciones en progreso. 
	 * @param c Celda que se desea considerar para el chequeo de animaciones en progreso.
	 * @return True si la celda tiene animaciones actualmente en progreso; false en caso contrario.
	 */
	private boolean tiene_animaciones_en_progreso(Bloque b) {
		boolean retorno = false;
		if (mapeo_bloque_animaciones.get(b) != null) {
			retorno = !mapeo_bloque_animaciones.get(b).isEmpty();
		}
		
		return retorno;
	}
}
