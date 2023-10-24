package Entidades;

public class Rayado extends Potenciadores{

	private char orientacion;
	
	public Rayado(int x, int y, String c, String rt, char o) {
		super(x, y, c, rt);
		orientacion = o;
	}

	public char tipoRayado() {
		return orientacion;//cambiar metodo
	}
	
public void detonar() {
	if(orientacion == 'h') {
		for(int i=0; i<6; i++) {
			//entidad_grafica.getMiTablero().obtenerBloque(posX, i).getDulceActual().detonar();		
			entidad_grafica.getMiTablero().ponerVacios(posX,i,entidad_grafica.getMiTablero());
			
		}
			
	}else {
		for(int i=1; i<7; i++) {
			//entidad_grafica.getMiTablero().obtenerBloque(posX, i).getDulceActual().detonar();
			entidad_grafica.getMiTablero().ponerVacios(i,posY,entidad_grafica.getMiTablero());
			
		}
	}
	this.desenfocar();
		entidad_grafica.notificarse_cambio_estado();
	}
	
	
}
