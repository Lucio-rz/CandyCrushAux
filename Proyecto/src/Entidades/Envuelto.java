package Entidades;

public class Envuelto extends Potenciadores{ //Aca en ves de extends potenciadores no seria extends caramelo???????
														//Igual no deja de ser caramelo. asique es lo mismo
	public Envuelto(int x, int y, String c, String rt) {
		super(x, y, c, rt);
		
	}
	
	public void detonar() {
		for (int i = -1; i < 2; i++) {
			
			for(int j=-1; j<2; j++) {
				if(posX+i>=0 && posX+i<=5 && posY+j>=0 && posY+j<=5) {
				entidad_grafica.getMiTablero().ponerVacios(posX+i,posY+j,entidad_grafica.getMiTablero());}
				
				
			}
			
		}
		this.desenfocar();
		entidad_grafica.notificarse_cambio_estado();
	}

}
