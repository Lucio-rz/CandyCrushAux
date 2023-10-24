package Logica;
import java.awt.EventQueue;

import Entidades.Caramelo;
import Entidades.Dulce;
import Grafica.GUI;

import Hilos.Controlador;
import Hilos.TimerCaida;

import java.util.List;
import java.util.Random;
//import Hilos.Vida;


public class Juego {
	
	//Atributos de clase
	public static final int ARRIBA = 15000;
	public static final int ABAJO = 15001;
	public static final int IZQUIERDA = 15002;
	public static final int DERECHA = 15003;

	protected Tablero tableroDeJuego;
	protected GUI gui;
	protected Controlador controlador; 
	protected int vida = 3;
	protected Reloj timer;
	protected boolean juego_finalizado;
	protected Chequear check;
	protected TimerCaida caida;
	protected int nivel;

	//Constructor
	public Juego(int niv) {
		nivel = niv;
		reiniciarJuego(nivel);					
	}
	
	 public void reiniciarJuego(int n) {
			
		 	tableroDeJuego= new Tablero(this,n);
			gui= new GUI(this);
			asociar_entidades_logicas_graficas();
			gui.reiniciarMatriz();
			timer = new Reloj(1000);
			controlador = new Controlador(timer, this);
			Thread hilo_controlador = new Thread(controlador);
			hilo_controlador.start();
			check = new Chequear(this);
			
			caida = new TimerCaida(this);
			Thread hilo_caida = new Thread(caida);
			hilo_caida.start();
			
		 
	 }
	
	//Comandos
	
	public Dulce generarCaramelo(int i,int j) {
		Random rand = new Random();
		int cotaSuperior = 5;
		int aleatorio = rand.nextInt(cotaSuperior);
		Dulce d = null;
		switch(aleatorio) {
			case 0: {
				d = new Caramelo(i,j,"carameloRojo","/SpritesCC/comunes/");
				break;
						}
			
			case 1: {
				d = new Caramelo(i,j,"carameloAmarillo","/SpritesCC/comunes/");
				break;
						}
			
			case 2: {
				d = new Caramelo(i,j,"carameloAzul","/SpritesCC/comunes/");
				break;
						}
			case 3: {
				d = new Caramelo(i,j,"carameloVerde","/SpritesCC/comunes/");
				break;
						}
			case 4: {
				d = new Caramelo(i,j,"carameloVioleta","/SpritesCC/comunes/");
				break;
						}
		}
		
		return d;
	}
		
	private void asociar_entidades_logicas_graficas() {
		for(int i=0; i<7;i++) {
			for(int j=0; j<6; j++) {
				miGUI().actualizarBloque(i, j, miTablero().tableroDeJuego[i][j].getDulceActual().get_imagen_representativa());
			}
		}
		miTablero().tableroDeJuego[0][0].getDulceActual().enfocar();
		miGUI().actualizarBloque(0, 0, miTablero().tableroDeJuego[0][0].getDulceActual().get_imagen_representativa());
		miGUI().setVisible(true);
		System.out.println("llegue");
	}
	
	public void moverJugador(int d) {
		System.out.println("Me movi");
		tableroDeJuego.mover_Jugador(d);
		

	}
	
	public void intercambiar(int d) {
		tableroDeJuego.intercambiarPos(d);
	}
	
	public boolean chequear(int d) {
		return check.chequear(d);
	}
	
	/*Recorre todos los dulces y si se encuentra que un dulce con gravedad
	 * tiene un dulce con antigravedad debajo los intercambia y genera  
	 */
/*	public void verificarCaida() {
		int cont = 0;
		do{
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					
					if (i == 0 && j == 0)
						cont = 0;
					
					if (tableroDeJuego.realizarCaida(i, j)) {
						cont++;
					}
						tableroDeJuego.spawnCaramelo(j);
					}
				}		
		}while(cont != 0);
	}
	*/

	
	 public void verificarCaida() {
		int cont = 0;
		do{
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					
					if (i == 0 && j == 0)
						cont = 0;
					
					if (tableroDeJuego.realizarCaida(i, j)) {
						 tableroDeJuego.obtenerBloque(i, j).getDulceActual().estaCayendo(true);
						cont++;
					}
					else {
						if (tableroDeJuego.obtenerBloque(i, j).getDulceActual().getCayendo())//si ya no cae y estaba cayendo
							tableroDeJuego.obtenerBloque(i, j).getDulceActual().terminoDeCaer(true);
					}
						
					tableroDeJuego.spawnCaramelo(j);
					}
				}		
		}while(cont != 0);//si cont = 0, ya no hay mas caidas
		chequearCaidos();

	}
	
	public synchronized void chequearCaidos(){
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				int pos[] = {i,j};
				check.chequearFormasGenerales(pos,ABAJO);
			}
		}
	}
	
/////////////////////////FIN_CONTADORES/////////////////////////////////////////////////////////////////////

	//Consultas
	
	public Tablero miTablero() {
		return tableroDeJuego;
	}
	
	
	public GUI miGUI(){
		return gui;
	}
	
	public void actualizar_tablero(int n) {
		tableroDeJuego.resetar_tablero(tableroDeJuego.obtenerFila(),tableroDeJuego.obtenerColumna(), n);
		timer.resetReloj();
		vida = 3;
	}
	
	public void actualizarTiempo(int seg, int seg1, int min) { //Cambio aca por que el reloj no deberia conocer a la gui.
		System.out.println(min+":"+seg1+seg);	}
	
	public void actualizarVida() {

		if ((timer.getMinutos()==0) &&(timer.getTiempoSeg1() ==0 )&&(timer.getTiempoSeg() == 0 )) {			
			if(!tableroDeJuego.getNivelActual().getObjetivo().cumple_objetivo()) {
				vida--;
				finDelJuego();
			}
		}			
	/*if ((timer.getMinutos()==0) &&(timer.getTiempoSeg1() ==0 )&&(timer.getTiempoSeg() ==0 ))
			//vida.setVida(vida.getVida()-1);
			vida --;*/
		System.out.println("vidas restantes: "+vida);
		
		//gui.setLabelVida(vida.getVida());
		//gui.setLabelVida(vida);
	}

	
	public void actualizarObjetivo() {
		Objetivos obj = tableroDeJuego.getNivelActual().getObjetivo();
		System.out.println("Movimientos restantes: "+ obj.getCantMov());
		String colorDestruir = tableroDeJuego.nivelActual.getObjetivo().colorARomper();
		if(obj.cumple_objetivo()) {
			//Avanzar nivel
			
			//gui.setDefaultCloseOperation(gui.EXIT_ON_CLOSE);//Aca cierro la ventana
			//System.exit(0);//Se cierra la ventana
			System.out.println("Pase al siguiente");
			if(nivel<5) {
				siguienteNivel(nivel +1);
				nivel++;
			}
				
			else {
				gui.mostrarMensajePerdiste("FINALIZASTE CON EXITO EL JUEGO", "���GANASTE!!!");}
			
		}
		if(!colorDestruir.isBlank()) {
			System.out.println("Caramelos a romper: "+obj.caramelosRestantes()+" de color: "+obj.colorARomper());
		}
		else {
			System.out.println("Caramelos a romper en general: "+obj.caramelosRestantes());
		}
		if (obj.getCantMov()<=0) {
			if(vida>0) {
				obj.resetearMovs();
				vida--;
				timer.resetReloj();
				tableroDeJuego.generarNivel(tableroDeJuego.getNivelActual().nroNivel());
				gui.reiniciarMatriz();
			}
			else {
				finDelJuego();
			}
		}
//	gui.setLabelObjetivos(tableroDeJuego.getNivelActual().getObjetivo().getCantMov());

		//gui.setLabelObjetivos(10);

	
		//gui.setLabelObjetivosConsumidos(tableroDeJuego.getNivelActual().getObjetivo().getObj(),tableroDeJuego.getNivelActual().getObjetivo().gatCaramelo());

		//gui.setLabelObjetivosConsumidos(10);

	}
	
	private void siguienteNivel(int n) {
		//actualizar_tablero(nivel);
		tableroDeJuego.generarNivel(n);
		
	}

	public void finDelJuego() {
		//Cuando te quedas sin vida
		if(vida == 0) {
			gui.mostrarMensajePerdiste("PERDISTE", "Fin del juego");
			//actualizar_tablero(1);
			controlador.detener();
			gui.setDefaultCloseOperation(gui.EXIT_ON_CLOSE);
			
		}
		
	}
	
	public Tablero getTableroDeJuego() {
		return tableroDeJuego;
	}


	
public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	new Juego(1);
                	
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
	}
}