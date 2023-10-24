package Entidades;

public class Vacio extends Dulce {
	protected String color;
	public Vacio(int x, int y) {
		super(x, y, "bloqueVacio", "/SpritesCC/");
	}
	public boolean puedeIntercambiar(Glaseado g) {
		return false;
	}
	
	public boolean getAntigravedad() {
		return true;
	}

	@Override
	public boolean puede_recibir(Caramelo c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean puede_recibir(Glaseado g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean puede_recibir(Potenciadores p) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean puedeIntercambiar(Dulce d) {

		return false;
	}
	@Override
	public boolean machea(Dulce d) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean match_con(Caramelo c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean match_con(Potenciadores p) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean match_con(Glaseado g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean puede_recibir(Vacio v) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean match_con(Gelatina g) {
		return false;
	}

}
