package Grafica;

public interface EntidadGrafica {
	//public void notificarse_cambio_estado(); //se mueve cursor sobre la imagen o no cambiando lo grafico
	public void notificarse_intercambio_posicion();  //mueve el caramelo seleccionado hacia la direccion especificada si existe intercambio valido
	public void notificarse_cambio_estado();
}
