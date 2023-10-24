package Entidades;

public class Glaseado extends Dulce{

	public Glaseado(int x, int y) {
		super(x, y, "MerengueBloque", "/SpritesCC/");
		
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
	public void detonar() {
		// TODO Auto-generated method stub
		
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

}
