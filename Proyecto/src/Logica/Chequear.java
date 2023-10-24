package Logica;

import java.util.ArrayList;
import java.util.List;

import Bloques.Bloque;
import Entidades.Caramelo;
import Entidades.Dulce;
import Entidades.Envuelto;
import Entidades.Rayado;

public class Chequear {


	
	private Juego miJuego;
	
	public Chequear(Juego j) {
		
		miJuego = j;
		
	}
	
	
	public boolean chequear(int d) {
	
	int posActual[] = miJuego.getTableroDeJuego().getPosJugador();
	int posPrev[] = new int[2];
	
	Boolean hayForma = false;
	Boolean hayFormaDir = false;
	
	if(d == Juego.ARRIBA ) {
			posPrev[0] = posActual[0]+1;
			posPrev[1] = posActual[1];
	}
	if(d == Juego.ABAJO ) {
			posPrev[0] = posActual[0];
			posPrev[1] = posActual[1];
	}
	if(d == Juego.IZQUIERDA ) {
			posPrev[0] = posActual[0];
			posPrev[1] = posActual[1]+1;
	}
	if(d == Juego.DERECHA ) {
			posPrev[0] = posActual[0];
			posPrev[1] = posActual[1]-1;
	}
	
	
	
	
	hayForma = chequearFormasGenerales(posActual,d);
	
		if(d == Juego.ARRIBA)			
			hayFormaDir = chequearFormasGenerales(posPrev,Juego.ABAJO);//retorna booleano y guarda caramelos a detonar con arreglo
		if(d == Juego.ABAJO)
			hayFormaDir = chequearFormasGenerales(posPrev,Juego.ARRIBA);//inverti los posnueva y pos actual.
		if(d == Juego.IZQUIERDA)
			hayFormaDir  = chequearFormasGenerales(posPrev,Juego.DERECHA);
		if(d == Juego.DERECHA)
			hayFormaDir  = chequearFormasGenerales(posPrev,Juego.IZQUIERDA);
	
		return ( hayForma || hayFormaDir);
}

private void explotar(List<Dulce> candyList) {
	System.out.printf("al explotar la lista tiene %d caramelos \n",candyList.size());
	for(Dulce c: candyList){//for each
		c.detonar();
		miJuego.verificarCaida();
		Objetivos obj =miJuego.getTableroDeJuego().getNivelActual().getObjetivo();
		obj.cumplimientoObjetivo(c.getColor());
	}
	candyList.clear();
}

public Boolean chequearFormasGenerales(int[] posAct,int d) {
	Bloque b;
	Dulce aux; 
	Dulce dulceP = null;
	String s;
	Boolean hayForma = false;
	System.out.println("\n Chequeo Forma cruz\n");
	System.out.printf("\nPOSICION ACTUAL en FormasGenerales: [%d][%d] \n",posAct[0],posAct[1]);

	List<Dulce> candyList = new ArrayList<Dulce>() ;
	
	if(chequearFormaCruz(posAct,d,candyList)){
		System.out.println("LA FORMA ES CRUZ");
		hayForma = true;
		b = miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]);
		aux = b.getDulceActual();
		s = "/SpritesCC/Envueltos/";
		explotar(candyList);
		dulceP = new Envuelto(posAct[0], posAct[1], aux.getColor(), s);
		dulceP.set_entidad_grafica(miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]));
		miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]).setDulceActual(dulceP);
		dulceP.desenfocar();
	}
	else{
		System.out.println("\n la forma NO ES CRUZ\n ");
		System.out.println("\n Chequeo Forma T\n");
		candyList.clear();
		if(chequearFormaT(posAct,d,candyList)){
			System.out.println("LA FORMA ES T");
			hayForma = true;
			b = miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]);
			aux = b.getDulceActual();
			s = "/SpritesCC/Envueltos/";
			
			dulceP = new Envuelto(posAct[0], posAct[1], aux.getColor(), s);
			dulceP.set_entidad_grafica(miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]));
			miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]).setDulceActual(dulceP);
			explotar(candyList);
			dulceP.desenfocar();
			}
			else{
			System.out.println("\n la forma NO ES T\n ");
			System.out.println("\n Chequeo Forma L\n");
			candyList.clear();
				if(chequearFormaL(posAct,d,candyList)){
					System.out.println("LA FORMA ES L");
					hayForma = true;
					b = miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]);
					aux = b.getDulceActual();
					s = "/SpritesCC/Envueltos/";
					explotar(candyList);
					dulceP = new Envuelto(posAct[0], posAct[1], aux.getColor(), s);
					dulceP.set_entidad_grafica(miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]));
					miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]).setDulceActual(dulceP);
					explotar(candyList);
					dulceP.desenfocar();
					
				}	
				else{
				System.out.println("\n la forma NO ES L\n ");
				System.out.println("\n Chequeo Forma 4\n");
				candyList.clear();
					if(chequearForma4(posAct,d,candyList)){
						System.out.println("LA FORMA ES 4");
						hayForma = true;
						b = miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]);
						aux = b.getDulceActual();
						char o;
						if(d == Juego.ABAJO || d == Juego.ARRIBA){
							s = "/SpritesCC/RayadosH/";
							o = 'h';
						}else{
							s = "/SpritesCC/RayadosV/";
							o = 'v';
						}
					
						dulceP = new Rayado(posAct[0], posAct[1], aux.getColor(), s, o);
						dulceP.set_entidad_grafica(miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]));
						miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]).setDulceActual(dulceP);
						
						explotar(candyList);
						dulceP.desenfocar();
						System.out.println("Rayado es de color: "+aux.getColor());
					}
					else{
					System.out.println("\n la forma NO ES 4\n ");
					System.out.println("\n Chequeo Forma 3!!\n");
					candyList.clear();
						if(chequearForma3(posAct,d,candyList)) {
							System.out.printf("ENTRANDO A FORMA 3 HAY %d caramelos \n",candyList.size());
							System.out.println("LA FORMA ES 3");
							hayForma = true;
							b = miJuego.getTableroDeJuego().obtenerBloque(posAct[0], posAct[1]);
							aux = b.getDulceActual();
							candyList.add(aux);
							explotar(candyList);
							aux.desenfocar();
						}
					}
				}
			}
		}
		
		return hayForma;
	}

private boolean chequearFormaT(int[] posActual,int d,List<Dulce> candyList) {
	boolean forma = false;
	Bloque b = miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]);
	int conteoUP = conteoHaciaArriba(b,candyList);
	int conteoDOWN = conteoHaciaAbajo(b,candyList);
	int conteoLEFT = conteoHaciaIzq(b,candyList);
	int conteoRIGHT = conteoHaciaDer(b,candyList);
	
	if(d == Juego.ARRIBA) {
		if(conteoUP == 2 && conteoLEFT == 1 && conteoRIGHT == 1) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.ABAJO) {
		if(conteoDOWN == 2 && conteoLEFT == 1 && conteoRIGHT == 1) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.IZQUIERDA) {
		if(conteoUP == 1 && conteoDOWN == 1 && conteoLEFT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-2).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.DERECHA) { 
		if(conteoUP == 1 && conteoDOWN == 1 && conteoRIGHT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+2).getDulceActual());
			forma = true;
		}
	}
	return forma;
}

private boolean chequearFormaL(int[] posActual,int d,List<Dulce> candyList) {
	boolean forma = false;
	Bloque b = miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]);
	int conteoUP = conteoHaciaArriba(b,candyList);
	int conteoDOWN = conteoHaciaAbajo(b,candyList);
	int conteoLEFT = conteoHaciaIzq(b,candyList);
	int conteoRIGHT = conteoHaciaDer(b,candyList);
	
	if(d == Juego.ARRIBA) {
		if(conteoUP >= 2 && conteoRIGHT >= 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+2).getDulceActual());
			forma = true;
		}
		if(conteoUP >= 2 && conteoLEFT >= 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-2).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.ABAJO) {
		if(conteoDOWN == 2 && conteoRIGHT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+2).getDulceActual());
			forma = true;
		}
		if(conteoDOWN == 2 && conteoLEFT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-2).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.IZQUIERDA) {
		if(conteoUP == 2 && conteoLEFT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-2).getDulceActual());
			forma = true;
		}
		if( conteoDOWN == 2 && conteoLEFT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-2).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.DERECHA) {
		if(conteoUP == 2  && conteoRIGHT == 2 ) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+2).getDulceActual());
			forma = true;
		}
		if( conteoDOWN == 2 &&  conteoRIGHT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+2).getDulceActual());
			forma = true;
		}
	}

	return forma;
}

private boolean chequearFormaCruz(int[] posActual,int d, List<Dulce> candyList) { //chequear que no sea out of bounds
	boolean forma = false;
	Bloque b = miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]);
	int conteoUP, conteoDOWN, conteoLEFT, conteoRIGHT;
		conteoUP = conteoHaciaArriba(b,candyList);
		conteoLEFT = conteoHaciaIzq(b,candyList);
		conteoRIGHT = conteoHaciaDer(b,candyList);
		conteoDOWN = conteoHaciaAbajo(b,candyList);
		
		
		if(conteoUP == 1 && conteoDOWN == 1 && conteoLEFT ==1 && conteoRIGHT == 1 ) {
		
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
				
			forma = true;
			
		}
		
	return forma;
}

private boolean chequearForma4(int[] posActual,int d,List<Dulce> candyList) { 
	boolean forma = false;
	Bloque b = miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]);
	int conteoUP = 0;
	int conteoDOWN = 0;
	int conteoLEFT = 0;
	int conteoRIGHT = 0;
	
	if(d == Juego.ARRIBA) {
		 conteoLEFT = conteoHaciaIzq(b, candyList);
		 conteoRIGHT = conteoHaciaDer(b, candyList);
		if( conteoLEFT == 2 && conteoRIGHT == 1) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-2).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
		
			forma = true;
		}
		if( conteoLEFT == 1 && conteoRIGHT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+2).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.ABAJO) {
		 conteoLEFT = conteoHaciaIzq(b, candyList);
		 conteoRIGHT = conteoHaciaDer(b, candyList);
		if( conteoLEFT == 2 && conteoRIGHT == 1) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-2).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			forma = true;
		}
		if( conteoLEFT == 1 && conteoRIGHT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+2).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.IZQUIERDA) {
		conteoUP = conteoHaciaArriba(b, candyList);
		conteoDOWN = conteoHaciaAbajo(b, candyList);
		if(conteoUP == 2 && conteoDOWN == 1 ) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			forma = true;
		}
		if(conteoUP == 1 && conteoDOWN == 2 ) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.DERECHA) {
		conteoUP = conteoHaciaArriba(b, candyList);
		conteoDOWN = conteoHaciaAbajo(b, candyList);
		if(conteoUP == 2 && conteoDOWN == 1 ) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			forma = true;
		}
		if(conteoUP == 1 && conteoDOWN == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+2, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			forma = true;
		}
	}

	return forma;
}

private boolean chequearForma3(int[] posActual,int d, List<Dulce> candyList) {
	boolean forma = false;
	Bloque b = miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]);
	int conteoUP = 0;
	int conteoDOWN = 0;
	int conteoLEFT = 0;
	int conteoRIGHT = 0;

	if(d == Juego.ARRIBA) {
		conteoUP = conteoHaciaArriba(b, candyList);
		conteoLEFT = conteoHaciaIzq(b, candyList);
		conteoRIGHT = conteoHaciaDer(b, candyList);
		
		if(conteoUP == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-2, posActual[1]).getDulceActual());
			forma = true;
		}
		if(conteoLEFT == 1 && conteoRIGHT == 1) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			forma = true;
		}
		if( conteoLEFT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-2).getDulceActual());
			forma = true;
		}
		if( conteoRIGHT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+2).getDulceActual());
			forma = true;
		}
	}

	if(d == Juego.ABAJO) {
		conteoLEFT = conteoHaciaIzq(b, candyList);
		conteoRIGHT = conteoHaciaDer(b, candyList);
		conteoDOWN = conteoHaciaAbajo(b, candyList);
		if(conteoDOWN == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+2, posActual[1]).getDulceActual());
			forma = true;
		}
		if( conteoLEFT == 1 && conteoRIGHT == 1) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			forma = true;
		}
		if( conteoLEFT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-2).getDulceActual());
			forma = true;
		}
		if(conteoRIGHT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+2).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.IZQUIERDA) {
		conteoLEFT = conteoHaciaIzq(b, candyList);
		conteoDOWN = conteoHaciaAbajo(b, candyList);
		conteoUP = conteoHaciaArriba(b, candyList);
		if(conteoUP == 2 ) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-2, posActual[1]).getDulceActual());
			forma = true;
		}
		if( conteoDOWN == 2 ) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+2, posActual[1]).getDulceActual());
			forma = true;
		}
		if(conteoUP == 1 && conteoDOWN == 1) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			forma = true;
		}
		if(conteoLEFT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]-2).getDulceActual());
			forma = true;
		}
	}
	
	if(d == Juego.DERECHA) {
		conteoRIGHT = conteoHaciaDer(b, candyList);
		conteoDOWN = conteoHaciaAbajo(b, candyList);
		conteoUP = conteoHaciaArriba(b, candyList);
		
		if(conteoUP == 2 ) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-2, posActual[1]).getDulceActual());
			forma = true;
		}
		if( conteoDOWN == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+2, posActual[1]).getDulceActual());
			forma = true;
		}
		if(conteoUP == 1 && conteoDOWN == 1) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]+1, posActual[1]).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0]-1, posActual[1]).getDulceActual());
			forma = true;
		}
		if( conteoRIGHT == 2) {
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+1).getDulceActual());
			candyList.add(miJuego.getTableroDeJuego().obtenerBloque(posActual[0], posActual[1]+2).getDulceActual());
			forma = true;
		}
	}

	return forma;
}

///////////////////////COMIENZO_CONTADORES///////////////////////////////////////////////////////

private int conteoHaciaArriba(Bloque b,List<Dulce> candyList) {
	System.out.println("\nEntro a contear a arriba");
	int cont = 0;
	boolean cortar = false;
	Dulce db = b.getDulceActual();
	int filaActual = b.getDulceActual().getPosX();
	int columnaActual = b.getDulceActual().getPosY();
	

	for(int i = filaActual-1; i >= 1 && cortar == false ; i--) {
		Bloque a = miJuego.getTableroDeJuego().obtenerBloque(i, columnaActual);
		Dulce dulce = a.getDulceActual();
		if(db.machea(dulce) == true) {
			
			cont++;
			
		}else cortar = true;
	}
	return cont;
}


private int conteoHaciaAbajo(Bloque b, List<Dulce> candyList) {
	int cont = 0;
	boolean cortar = false;
	Dulce db = b.getDulceActual();
	int filaActual = b.getDulceActual().getPosX();
	int columnaActual = b.getDulceActual().getPosY();

     
	for(int i = filaActual+1 ; i <= 6 && cortar == false ; i++) {
		
		Bloque a = miJuego.getTableroDeJuego().obtenerBloque(i, columnaActual);
		Dulce dulce = a.getDulceActual();
		if(db.machea(dulce) == true) {
			
			cont++;
			
		}else cortar = true;
	}
	return cont;
	
}



private int conteoHaciaIzq(Bloque b, List<Dulce> candyList) {
	int cont = 0;
	boolean cortar = false;
	Dulce db = b.getDulceActual();
	int filaActual = b.getDulceActual().getPosX();
	int columnaActual = b.getDulceActual().getPosY();
	
	for(int i = columnaActual-1; i >= 0 && cortar == false ; i--) {
		Bloque a = miJuego.getTableroDeJuego().obtenerBloque(filaActual, i);
		Dulce dulce = a.getDulceActual();
		if(db.machea(dulce) == true) {
		
			cont++;
			
		}else{
			cortar = true;
			
			}
	}
	return cont;
}

private int conteoHaciaDer(Bloque b, List<Dulce> candyList) {
	System.out.println("Entro a contear a derecha");
	int cont = 0;
	boolean cortar = false;
	Dulce db = b.getDulceActual();
	int filaActual = b.getDulceActual().getPosX();
	int columnaActual = b.getDulceActual().getPosY();
     
	for(int i = columnaActual+1 ; i <= 5 && cortar == false ; i++) {
		Bloque a = miJuego.getTableroDeJuego().obtenerBloque(filaActual, i);
		Dulce dulce = a.getDulceActual();
		
		if(db.machea(dulce) == true) {
			
			cont++;
			
		}else cortar = true;
	}	
	return cont;

}
}