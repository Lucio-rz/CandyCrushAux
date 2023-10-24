package Hilos;

import Logica.Juego;

public class TimerCaida extends Thread{
	
	protected Juego miJuego;
	
	public TimerCaida(Juego j) {
		miJuego = j;
		
	}
	
	public void run() {
		while(true) {
			try {
				miJuego.chequearCaidos();
				Thread.sleep(1200);
				//System.out.println("TimerGuisante");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

}
