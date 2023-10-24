package Logica;
import Bloques.*;
import Entidades.Dulce;
import Entidades.Vacio;

public class Tablero {
	//Atributos de Clase
	protected Bloque[][] tableroDeJuego;
	protected Juego miJuego;
	protected int pos_f_jugador;
	protected int pos_c_jugador;
	protected int filas;
	protected int columnas;
	protected Nivel nivelActual;
	
	//Constructor
	public Tablero(Juego j, int niv) {
		miJuego = j;
		generarNivel(niv);
		//generarNivel(2);
		filas=7;
		columnas=6;
		pos_f_jugador=1;
		pos_c_jugador=0;
	}
	
	//Comandos
	public void combinacionTablero() {}
	
	public void generarNivel(int nivel) {
		nivelActual = new Nivel(nivel,this);
		tableroDeJuego = nivelActual.getNivel();
		if(nivel>1)miJuego.miGUI().reiniciarMatriz();
		
	} 
	
	public int obtenerFila() {
		return filas;
	}
	
	public int obtenerColumna() {
		return columnas;
	}
	public Bloque obtenerBloque(int i, int j) {
		return tableroDeJuego[i][j];
	}
	public int[] getPosJugador() {
		int pos[] = new int [2];
		pos[0] = pos_f_jugador;
		pos[1] = pos_c_jugador;
		return pos;
	}
	
	
	//Consultas
	
	public String rutaImagen(int x, int y) {
		return tableroDeJuego[x][y].getDulceActual().get_imagen_representativa();
		
	}
	
	public void fijar_jugador(int f, int c) {
		
			tableroDeJuego[f][c].getDulceActual().enfocar();
			tableroDeJuego[pos_f_jugador][pos_c_jugador].getDulceActual().desenfocar();
			pos_f_jugador = f;
			pos_c_jugador = c;
			System.out.println(pos_f_jugador);
			System.out.println(pos_c_jugador);
		
	}
	
	public void resetar_tablero(int f, int c, int n) {
		filas = f;
		columnas = c;
		pos_f_jugador = 1;
		pos_c_jugador = 0;
		tableroDeJuego = new Bloque[f][c];
		generarNivel(n+1);	//aparece el siguiente nivel pero se abren 3 millones de ventanas
		//Ver como se cierran las ventanas y que se quede la que deberia abierta nomas
		
	}
	

	public Bloque ponerVacios(int x, int y, Tablero t) {
		Dulce v = new Vacio(x,y);
		
		tableroDeJuego[x][y].setDulceActual(v);
		
		v.set_entidad_grafica(tableroDeJuego[x][y]);
		v.desenfocar();
		System.out.println("puse vacio");
		
		return tableroDeJuego[x][y];
	}
	
	public void intercambiarPos(int d) {
		switch(d) {
			case Juego.ABAJO:{
				intercambiar_auxiliar(pos_f_jugador + 1, pos_c_jugador, d);
				break;
			}
			case Juego.ARRIBA:{
				intercambiar_auxiliar(pos_f_jugador - 1, pos_c_jugador, d);
				break;
			}
			case Juego.IZQUIERDA:{
				intercambiar_auxiliar(pos_f_jugador, pos_c_jugador - 1, d);
				break;
			}
			case Juego.DERECHA:{
				intercambiar_auxiliar(pos_f_jugador, pos_c_jugador + 1, d);
				break;
			}
		}
	}
	
	private void intercambiar_auxiliar(int nf, int nc , int d) {
		int af = pos_f_jugador;
		int ac = pos_c_jugador;
		

		if ( en_rango(nf, nc) ) {	
			if (tableroDeJuego[af][ac].getDulceActual().puedeIntercambiar( tableroDeJuego[nf][nc].getDulceActual() )) { //puede recibir caramelo = true \\ glaseado = false
				// Anima el posible intercambio de entidades
		
				aplicar_intercambio(af, ac, nf, nc); 		
				if (!miJuego.chequear(d)) {
						System.out.println("no hay forma");
						aplicar_intercambio(nf, nc, af, ac);

						
					}else {
						System.out.println("se encontro forma");
						nivelActual.getObjetivo().reducirMovs();
						
					}
			

					}
					
					}
				
				

				
		}
		
	
		

	
	private void aplicar_intercambio(int af, int ac, int nf, int nc) {
		Bloque bloque_aux = tableroDeJuego[af][ac];
		
		tableroDeJuego[af][ac].getDulceActual().intercambiar_posicion(nf, nc);
		tableroDeJuego[nf][nc].getDulceActual().intercambiar_posicion(af, ac);
		
		tableroDeJuego[af][ac] = tableroDeJuego[nf][nc];
		tableroDeJuego[nf][nc] = bloque_aux;
		
		pos_f_jugador = nf;
		pos_c_jugador = nc;
		/*
		System.out.print(af);
		System.out.print(ac);
		System.out.print(nf);
		System.out.print(nf);*/
	}
	
	private boolean en_rango(int nf, int nc){
		return ((0 < nf) && (nf < filas) && (0 <= nc) && (nc < columnas));//nf mayor que cero para no moverme a la spawnzone
	}
	
	public void mover_Jugador(int d) {
		switch(d) {
			case Juego.ABAJO:{
				mover_jugador_auxiliar(pos_f_jugador + 1, pos_c_jugador);
				break;
			}
			case Juego.ARRIBA:{
				mover_jugador_auxiliar(pos_f_jugador - 1, pos_c_jugador);
				break;
			}
			case Juego.IZQUIERDA:{
				mover_jugador_auxiliar(pos_f_jugador, pos_c_jugador - 1);
				break;
			}
			case Juego.DERECHA:{
				mover_jugador_auxiliar(pos_f_jugador, pos_c_jugador + 1);
				break;
			}
		}
	}
	
	private void mover_jugador_auxiliar(int nf, int nc) {
		if ( en_rango(nf,nc) ) {
			tableroDeJuego[nf][nc].getDulceActual().enfocar();//tableroDeJuego[nf][nc].getDulceActual() null al explotar
			tableroDeJuego[pos_f_jugador][pos_c_jugador].getDulceActual().desenfocar();
			pos_f_jugador = nf;
			pos_c_jugador = nc;
		}
		System.out.println(pos_f_jugador);
		System.out.println(pos_c_jugador);
	}
	

	public boolean realizarCaida(int f, int c) {
		boolean caida = false;
		boolean caedizo = obtenerBloque(f,c).getDulceActual().getGravedad();
		boolean levitable = false;
		if ( (f+1) < 7 ){
		
		Bloque below = obtenerBloque(f+1,c); 
		levitable = below.getDulceActual().getAntigravedad();
		
			if(caedizo && levitable) {
				aplicar_intercambio(f,c,f+1,c);
				caida = true;
			}	
			else if ( (c-1) >= 0 ){
					Bloque belowLeft = obtenerBloque(f+1,c-1); 
					levitable = belowLeft.getDulceActual().getAntigravedad();
						if(caedizo && levitable) {
							aplicar_intercambio(f,c,f+1,c-1);
							caida = true;
						}
					}
					else if ( (c+1) <  6 ){
					Bloque belowRight = obtenerBloque(f+1,c+1); 
					levitable = belowRight.getDulceActual().getAntigravedad();
					if(caedizo && levitable) {
						aplicar_intercambio(f,c,f+1,c+1);
						caida = true;
						
				}
		}
		}
		return caida;
	}
	
	
	
	public void spawnCaramelo(int c) {	
	
		int spawnZone = 0;
		Bloque spawner = obtenerBloque(spawnZone,c);
		Dulce newCandy;		
		if(spawner.getDulceActual().getAntigravedad()) {
			 newCandy = miJuego.generarCaramelo(spawnZone, c);
			 newCandy.set_entidad_grafica(spawner);
			 spawner.setDulceActual(newCandy);
			 newCandy.enfocar();
			 newCandy.desenfocar();
		}
		
	}


	
	
	public Juego getMiJuego() {
		return miJuego;
	}
	
	public Nivel getNivelActual() {
		return nivelActual;
	}
	
	
}
