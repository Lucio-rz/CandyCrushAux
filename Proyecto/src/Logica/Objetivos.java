package Logica;

public class Objetivos {
	private int movsDisp;
	private int movsInicial;
	protected int tiempoInicial;
	private int caramelosObjetivo;
	private String colorObjetivo;
	
	
	public Objetivos(char movs, char time, char obj ) {
		switch(movs) {
		case 'x':
		movsDisp=30;
		movsInicial=30;
		break;
		
		case 'l':
		movsDisp=20;
		movsInicial=20;
		break;
		
		case 'h':
		movsDisp=15;
		movsInicial=15;
		break;
			
		}
		
		switch(time) {
		case 't':
		tiempoInicial=10;
		break;
		
		case 'T':
			tiempoInicial=8;
		break;	
		
		}
		
		switch(obj) {
		case 'i':
		caramelosObjetivo = 20;
		colorObjetivo = "";
		break;
		
		case 'j':
		caramelosObjetivo = 40;
		colorObjetivo = "";
			break;
			
		case 'k':
		caramelosObjetivo = 15;
		colorObjetivo = "carameloVioleta";
			break;
			
		case 'p':
		caramelosObjetivo = 5;
		colorObjetivo = "MerengueBloque";
			break;
			
		case 'a':
		caramelosObjetivo = 7;
		colorObjetivo = "";
			break;	
			
		}
		
		
	}


	public boolean cumple_objetivo() {
		return caramelosObjetivo <=0;
	}


	public int getCantMov() {
		
		return movsDisp;
	}


	public void reducirMovs()
	{	
		movsDisp--;
	}	
	
	
	public void cumplimientoObjetivo(String colorRoto) {
		
		if(colorObjetivo.isBlank()) {
			caramelosObjetivo--;}
			
		else {
			if(colorObjetivo.equals(colorRoto)) {
			caramelosObjetivo--;}
		}	
		
	}
	
	
	public int caramelosRestantes() {
		return caramelosObjetivo;
		
	}
	
	public String colorARomper() {
		
		 return colorObjetivo;
	}
	
	public void resetearMovs() {
		movsDisp = movsInicial;
	}
	
}
