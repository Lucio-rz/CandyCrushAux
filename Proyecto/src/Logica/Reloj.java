package Logica;

public class Reloj {
	 protected int tiempoSeg;
	 protected int tiempoSeg1;
	 protected int tiempoMinutos;
	 protected boolean activo;
	 protected int step;
	
	public Reloj(int step) {
		 activo = true;
	        tiempoSeg  = 0;
	        tiempoSeg1 = 0;
	        tiempoMinutos = 10;
	        this.step = step;
	}
	
	 public int getTiempoSeg() {
		 return tiempoSeg;
	 }
	 public int getTiempoSeg1() {
		 return tiempoSeg1;
	 }
	 public int getMinutos() {	 
		return tiempoMinutos;
			
		}
	 public void resetReloj() {
		 tiempoSeg  = 0;
	        tiempoSeg1 = 0;
	        tiempoMinutos = 10;
	 }
	 public void reloj_activo() {
		 if((tiempoMinutos != 0)&&(tiempoSeg1 != 0)&&(tiempoSeg != 0))
					tiempoSeg--;
			else {
				if((tiempoSeg == 0) && (tiempoSeg1 != 0) &&(tiempoSeg == 0) ) {
					tiempoSeg1--;
					tiempoSeg = 9;	
					
				 }else {	
					
						if((tiempoMinutos != 0)&&(tiempoSeg == 0) && (tiempoSeg1 == 0) ) {
							tiempoMinutos--;
							tiempoSeg=9;
							tiempoSeg1=5;
						}else {
							tiempoSeg--;
						}
				 	}
				}
	 }
	 

	public int getStep() {
		return step;
	}

}
