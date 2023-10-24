package Entidades;

public interface Intercambiable {
	
	public boolean puedeIntercambiar(Dulce d);
	
	public boolean puede_recibir(Caramelo c);
	
	public boolean puede_recibir(Glaseado g);
	
	public boolean puede_recibir(Potenciadores p);
	
	public boolean puede_recibir(Vacio v);
	
	public void intercambiar_posicion(int nf, int nc);

}
