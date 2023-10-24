package Entidades;

public class Gelatina extends Dulce{

	//Atributos de clase
	protected Caramelo carameloOculto; 

	public Gelatina(int x, int y, Caramelo c) {
		super(x, y, "gelatina", "/SpritesCC/");
		carameloOculto = c;
		
	}
	
	/*public boolean enfocar() {
		entidad_grafica.notificarse_cambio_estado(posX,posY);
		return true;
	}*/

	public boolean puedeIntercambiar(Dulce d) {
		return false;
		
	}

	@Override
	public boolean puede_recibir(Caramelo c) {
		
		return false;
	}

	@Override
	public boolean puede_recibir(Glaseado g) {
	
		return false;
	}

	public boolean puede_recibir(Vacio v) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean puede_recibir(Potenciadores p) {
		
		return false;
	}
	
	public boolean puede_recibir(Gelatina g) {
		return false;
	}

	@Override
	public void detonar() {
	 
	 entidad_grafica.setDulceActual(carameloOculto);
	 entidad_grafica.getDulceActual().desenfocar();
		
	}

	@Override
	public boolean machea(Dulce d) {
		
		return false;
	}

	@Override
	public boolean match_con(Caramelo c) {
		
		return false;
	}

	@Override
	public boolean match_con(Potenciadores p) {
		
		return false;
	}

	@Override
	public boolean match_con(Glaseado g) {
		
		return false;
	}
	
	public boolean match_con(Gelatina g) {
		
		return false;
	}

}
