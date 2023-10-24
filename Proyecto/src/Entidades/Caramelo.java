package Entidades;

public class Caramelo extends Dulce {
	
	protected String color;
	protected boolean detonado;
	
	public Caramelo(int x, int y, String c,String rt) {
		super(x,y, c, rt);
		color = c;
		detonado = false;
	}
	
	public boolean sameColor(Caramelo c) {
		System.out.printf("%s comparado con a %s\n",color,c.getColor());
		return color == c.getColor();
		
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean getGravedad() {
		return true;
	}

	public boolean estaDetonado() {
		return detonado;
	}

	public boolean puede_recibir(Caramelo c) {
		// To Do: programar la lógica para chequear match 3
		return true;
	}
	
	public boolean puede_recibir(Glaseado g) {
		return false;
	}
	
	public boolean puede_recibir(Vacio v) {
		return false;
	}
	
	public boolean puede_recibir(Gelatina g) {
		return false;
	}
	
	public boolean puede_recibir(Potenciadores p) {
		// To Do: programar la lógica para chequear match 3
		return true;
	}

	@Override
	public boolean puedeIntercambiar(Dulce d) {

		return d.puede_recibir(this);
	}

	@Override
	public void detonar() {
		if(!detonado)
		detonado = true;
		
		entidad_grafica.getMiTablero().ponerVacios(posX,posY,entidad_grafica.getMiTablero());
		
		this.desenfocar();
	}

	@Override
	public boolean machea(Dulce d) {
		// TODO Auto-generated method stub
		return d.match_con(this);
	}
	//Falta hacer cuando matchea.
	@Override
	
	public boolean match_con(Caramelo c) {
		// TODO Auto-generated method stub
		return sameColor(c);
	}
	
	@Override
	public boolean match_con(Potenciadores p) {
		// TODO Auto-generated method stub
	    return sameColor(p);
	}
	
	@Override
	public boolean match_con(Glaseado g) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean match_con(Gelatina g) {
		return false;
	}
	
	public boolean match_con(Vacio v) {
		return false;
	}

	
	
}
