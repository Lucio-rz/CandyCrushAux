package Hilos;

import Grafica.GUI;
import Logica.Juego;
import Logica.Reloj;

public class Controlador extends Thread{
		 protected GUI miGUI;
		 protected boolean activo;
		 protected Reloj timer;
		 protected Juego juego;
		 
		 public Controlador(Reloj r, Juego j) {
			 	activo=false;//poner true
		       
		        timer = r;
		        juego = j;
		    }
		public void run() {
			
			 while (activo) {
	             try {
					Thread.sleep(timer.getStep());
					timer.reloj_activo();
					juego.actualizarTiempo(timer.getTiempoSeg(),timer.getTiempoSeg1(),timer.getMinutos());

					juego.actualizarVida();
					juego.actualizarObjetivo();
					
					
					if (timer.getMinutos() == 0 && (timer.getTiempoSeg1() == 0) && (timer.getTiempoSeg() == 0)) {
						timer.resetReloj();
						juego.finDelJuego();
					}   
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			       
	         }		
		}
		
		
		 public void detener() {
		        activo = false;
		    }
		 
		
	}

