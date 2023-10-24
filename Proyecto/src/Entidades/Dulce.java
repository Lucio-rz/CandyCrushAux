package Entidades;

import Bloques.Bloque;

public abstract class Dulce implements Enfocable, Intercambiable, Matchable, Detonable{
	
	//Atributos de clase
	protected String color;
	protected boolean enfocada;
	protected boolean detonada;
	protected boolean cayendo;//si es true, eventualmente sera false para que caido sea true  
	protected boolean caido;//caido es true solamente cuando el caramelo estaba cayendo y dejo de caer
	protected int posX, posY;
	protected Bloque entidad_grafica;
	protected String rutaImage;
	protected String[] rutas;
	
	
	public Dulce(int x, int y, String color, String path_img) { 
		posX = x;
		posY = y;
		this.color = color;
		enfocada = false;
		detonada = false;
		cayendo = false;
		caido = false;
		cargar_imagenes_representativas(path_img);
	}
	
	public void set_entidad_grafica(Bloque e) {
		entidad_grafica = e;
	}
	
	public  int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public boolean getGravedad() {
		return false;
	}
	
	public boolean getAntigravedad() {
		return false;
	}
	
	public  int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String getRutaImage() {
		return rutaImage;
	}

	public abstract boolean puedeIntercambiar(Dulce d);
	
	@Override
	public void enfocar() {
		enfocada = true;
		entidad_grafica.notificarse_cambio_estado();
		
	}
	
	@Override
	public void desenfocar() {
		enfocada = false;
		entidad_grafica.notificarse_cambio_estado();
		
	}
	
	public void detonar() {
		detonada = true;
		entidad_grafica.notificarse_cambio_estado();
	}
	
	
	
	
	@Override
	public void intercambiar_posicion(int nf, int nc) {
		posX = nf;
		posY = nc;
		entidad_grafica.notificarse_intercambio_posicion();
	} 
	
	
	
	private void cargar_imagenes_representativas(String path_img) {
		rutas = new String [2];
		rutas[0] = path_img + color +".png";
		rutas[1] = path_img + color +"Resaltado.png";
	}
	
	
	public String get_imagen_representativa() {
		if(enfocada) 
			return rutas[1];
		else 
			return rutas[0];
	}
	
	public String getColor() {
	return color;
	}
	
	public void estaCayendo(boolean b) {
		cayendo = b;
		caido = !b;
	}
	public boolean getCayendo() {
		return cayendo;	
	}
	
	public boolean getCaido() {
		return caido;	
	}
	
	public boolean terminoDeCaer(boolean dejaDeCaer) {//luego de chequear caido vuelve a false
		if(cayendo && dejaDeCaer) {
			caido = true;
			cayendo = false;
		}
		return caido;
	}

}
