package Bloques;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entidades.Dulce;
import Grafica.EntidadGrafica;
import Logica.*;
@SuppressWarnings("serial")
public class Bloque extends JLabel implements EntidadGrafica {

	//Atributos de clase
 //Cada clase creara el obj grafico con el string que le corresponda. (Esto no deberia ir en caramelo o en dulce?).
	protected Dulce dulceActual; //desde aca se podria obtener la imagen de la otra forma. 
	protected boolean hayDulce;
	protected static final int size_label = 76;
	protected Tablero miTablero;
	
	//Constructor
	
	
	public Bloque(Tablero t,Dulce d) {
		dulceActual=d;
		miTablero= t;	
	}
	
	//Consultas
	
	public boolean hayDulce() {
		return hayDulce;
	}
	

	 
	public Dulce getDulceActual() {
		return dulceActual;
	}
	
	public void setDulceActual(Dulce actual) {
		this.dulceActual = actual;
	}
	
	public Tablero getMiTablero() {
		return miTablero;
	}

	@Override
	/*public void notificarse_cambio_estado(int x, int y) {
		cambiar_imagen(dulceActual.get_imagen_representativa());
		miTablero.getMiJuego().miGUI().actualizarBloque(x, y, dulceActual.get_imagen_representativa());
	}
*/
	
	public void notificarse_intercambio_posicion() {
		miTablero.getMiJuego().miGUI().animar_movimiento(this);
		
	}

	protected void cambiar_imagen(String i) {
		ImageIcon imgIcon = new ImageIcon(this.getClass().getResource(i));
		Image imgEscalada = imgIcon.getImage().getScaledInstance(size_label, size_label, Image.SCALE_SMOOTH);
		Icon iconoEscalado = new ImageIcon(imgEscalada);
		setIcon(iconoEscalado);
	}
	
	public void notificarse_cambio_estado() {
		miTablero.getMiJuego().miGUI().animar_cambio_estado(this);
		
	}
	
	
	
}
