package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Bloques.Bloque;
import Entidades.Caramelo;
import Entidades.Dulce;
import Entidades.Gelatina;
import Entidades.Glaseado;

public class Nivel {
	private Bloque[][] nivel;
	private int numeroNivel;
	private Objetivos obj;
	private Tablero miTablero;
	
	//private GUI miVentana;//Coinciderando lo mismo que tablero, hay que hacerlo comunicar con el juego para que le avise a la gui
	
	public Nivel(int i,Tablero t) {
		miTablero = t;
		numeroNivel = i;
		nivel = new Bloque[7][6];
	    try {
			setNivel(i);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	
	}
	
	public Bloque[][] getNivel() {
		return nivel;
	}
	
	public void setNivel(int i) throws IOException {
		numeroNivel = i;
		cargarTxt();
	}
	
	
	@SuppressWarnings("resource")
	public void cargarTxt() throws IOException {
		String ruta = "src/Niveles/Nivel "+numeroNivel+".txt";
		BufferedReader lector;
		lector = new BufferedReader(new FileReader(ruta));
		String linea = null;
		for(int i = 0;i <7;i++) {
			 linea = lector.readLine();
			for(int j = 0; j < 6; j++) {
				char caracterAInsertar = linea.charAt(0);
				if( linea.length() != 1) 
					linea = linea.substring(1);
				Dulce d;
				Caramelo aux;
				Bloque b = null;
				switch(caracterAInsertar) {
					case 'R':
						d = new Caramelo(i,j,"carameloRojo","/SpritesCC/comunes/");
						
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					case 'Y':
						d = new Caramelo(i,j,"carameloAmarillo","/SpritesCC/comunes/");
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					case 'B':
						d = new Caramelo(i,j,"carameloAzul","/SpritesCC/comunes/");
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					case 'G':
						d = new Caramelo(i,j,"carameloVerde","/SpritesCC/comunes/");
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					case 'V':
						d = new Caramelo(i,j,"carameloVioleta","/SpritesCC/comunes/");
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					case 'r':
						aux = new Caramelo(i,j,"RojoG","/SpritesCC");
						d = new Gelatina(i,j,aux);
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					case 'y':
						aux = new Caramelo(i,j,"AmarilloG","/SpritesCC");
						d = new Gelatina(i,j,aux);
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					case 'b':
						aux = new Caramelo(i,j,"AzulG","/SpritesCC");
						d = new Gelatina(i,j,aux);
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					case 'g':
						aux = new Caramelo(i,j,"VerdeG","/SpritesCC");
						d = new Gelatina(i,j,aux);
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					case 'v':
						aux = new Caramelo(i,j,"VioletaG","/SpritesCC");
						d = new Gelatina(i,j,aux);
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					case 'M':
						d = new Glaseado(i,j);
						b = new Bloque(miTablero,d);
						d.set_entidad_grafica(b);
					break;
					
				}
				nivel[i][j]=b;
				
				
			}
		}
		 char movs =linea.charAt(0);
		if( linea.length() != 1) 
			linea = linea.substring(1);
		char time = linea.charAt(0);
		if( linea.length() != 1) 
			linea = linea.substring(1);
		
		char obj = linea.charAt(0);
		if( linea.length() != 1) 
			linea = linea.substring(1);
		
		this.obj = new Objetivos(movs,time,obj);
		
	}
	
	public int nroNivel() {
		return numeroNivel;
		
	}
	
	public Objetivos getObjetivo() {
		return obj;
	}
	
}
