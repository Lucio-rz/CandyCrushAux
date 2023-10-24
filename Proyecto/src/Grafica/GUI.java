package Grafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import AnimacionesIntercambiables.CentralAnimaciones;
import Bloques.Bloque;
import Logica.*;

import java.awt.event.*;
import java.net.URL;


@SuppressWarnings("serial")
public class GUI extends JFrame implements VentanaAnimable, VentanaNotificable {	
	//Atributos
	protected Juego miJuego;
	protected static final int filas =7;
	protected static final int columnas =6;
	protected Tablero mi_tablero;
	protected JPanel panel_matriz ;
	protected JLabel Matriz[][];
	protected JPanel panel_reloj;
	protected JLabel temp;
	protected JLabel vida;
	protected JPanel panel_vida;
	protected String temporizador;
	protected String vida_Jugador;
	protected JPanel panel_objetivo;
	protected JLabel objetivo;
	protected String objetivo_nivel;
	
	protected Bloque bloque_1_pendiente_animacion;
	protected Bloque bloque_2_pendiente_animacion;
	private JPanel panel_objetivo_consumidos;
	private JLabel objetivoConsumido;
	protected JPanel panel_spawnZone;
	protected String objetivo_nivel1;
	protected int animaciones_pendientes;
	protected boolean bloquear_intercambios;
	protected CentralAnimaciones mi_animador;
	
	
	public GUI(Juego juego) {
		
	getContentPane().setEnabled(true);
		
	setTitle("Candy Crush");
	miJuego=juego;
	mi_animador = new CentralAnimaciones(this);
	animaciones_pendientes = 0;
	bloquear_intercambios = false;
	
	//Panel fondo de pantalla
	JPanel panel_fondo = new JPanel();
	ImageIcon fondo = new ImageIcon(getClass().getResource("/SCharacters/BackgroundBlue.png"));
	JLabel fondoLabel = new JLabel(fondo);
	
	panel_fondo.setBounds(0,0,600,800);
	panel_fondo.add(fondoLabel);
	
	
    

	//Panel para reloj
/*	panel_reloj = new JPanel();	
    temp = new JLabel(temporizador);
    Font font = new Font("Century Gothic", Font.BOLD, 18); 
    temp.setFont(font);
    Color timeColor = new Color(255,80,170);
    temp.setForeground(timeColor);    
    panel_reloj.setBounds(45,28,200,33);
    panel_reloj.add(temp);
    getContentPane().add(panel_reloj);	
    
    //Panel para la vida
	panel_vida = new JPanel();
	vida = new JLabel(vida_Jugador);
	Font font1 = new Font("Century Gothic", Font.BOLD, 18); 
	vida.setFont(font1);
	Color life_Color = new Color(255,80,170);
	vida.setForeground(life_Color);    
	panel_vida.setBounds(365, 28, 180, 33);
	panel_vida.add(vida);
	getContentPane().add(panel_vida);
	
	
	//Panel par objetivo
	panel_objetivo = new JPanel();
	panel_objetivo.setBounds(45, 631, 500, 38);
	objetivo = new JLabel(objetivo_nivel);
	Font font2 = new Font("Century Gothic", Font.BOLD, 18); 
	objetivo.setFont(font2);
	Color obj_Color = new Color(255,80,170);
	objetivo.setForeground(obj_Color);    
	panel_objetivo.add(objetivo);
	getContentPane().add(panel_objetivo);
	
	//Panel par objetivo consumidos
	panel_objetivo_consumidos = new JPanel();
	panel_objetivo_consumidos.setBounds(45, 660, 500, 38);
	objetivoConsumido = new JLabel(objetivo_nivel1);
	Font font3 = new Font("Century Gothic", Font.BOLD, 18); 
	objetivoConsumido.setFont(font3);
	Color obj1_Color = new Color(255,80,170);
	objetivoConsumido.setForeground(obj1_Color);    
	panel_objetivo_consumidos.add(objetivoConsumido);
	getContentPane().add(panel_objetivo_consumidos);

	*/
     panel_spawnZone = new JPanel();
     panel_spawnZone.setBounds(45, 80, 456, 76);
     JLabel labelSpawn = new JLabel(fondo);
     panel_spawnZone.add(labelSpawn);
     getContentPane().add(panel_spawnZone);
     
	
	this.setSize(new Dimension(600,800));
	getContentPane().setLayout(null);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel_matriz = new JPanel(new GridLayout(7,6));
	panel_matriz.setBounds(45, 80, 456, 532);
   // panel_matriz.setBounds(45, 80, 456, 456);
	
	Matriz = new JLabel[filas][columnas];
	
	for(int i=0; i<filas;i++) {
		for (int j=0; j<columnas;j++) {
			
			Matriz[i][j] = miJuego.getTableroDeJuego().obtenerBloque(i, j);
			panel_matriz.add(Matriz[i][j]);
		}
		
	}

	
	panel_matriz.setOpaque(false);//allowing the underlying pixels from the spawnZone to show through
	getContentPane().add(panel_matriz);

	panel_fondo.setOpaque(false);
    getContentPane().add(panel_fondo);
	
	
	this.getContentPane().setFocusable(true);
	
	
    this.getContentPane().addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT: { miJuego.moverJugador(Juego.IZQUIERDA); 
                break;
                }
                case KeyEvent.VK_RIGHT: { miJuego.moverJugador(Juego.DERECHA); 
                break;
                }
                case KeyEvent.VK_UP: { miJuego.moverJugador(Juego.ARRIBA); 
                break; 
                }
                case KeyEvent.VK_DOWN: { miJuego.moverJugador(Juego.ABAJO); 
                break; 
                }
                case KeyEvent.VK_W: {if (!bloquear_intercambios) miJuego.intercambiar(Juego.ARRIBA); 
                break;
                }
                case KeyEvent.VK_S: {if (!bloquear_intercambios) miJuego.intercambiar(Juego.ABAJO);; 
                break;
                }
                case KeyEvent.VK_A: {if (!bloquear_intercambios) miJuego.intercambiar(Juego.IZQUIERDA); 
                break; 
                }
                case KeyEvent.VK_D: { if (!bloquear_intercambios)miJuego.intercambiar(Juego.DERECHA);
                break; 
                }
            }
        }
    });
	
	}
	
	
 public void reiniciarMatriz()	{	 
	 
	 for(int i=0; i<filas;i++) {
			for (int j=0; j<columnas;j++) {
				Bloque aux = miJuego.getTableroDeJuego().obtenerBloque(i, j);
				panel_matriz.remove(Matriz[i][j]);
				Matriz[i][j] = aux;
				miJuego.getTableroDeJuego().obtenerBloque(i, j).getDulceActual().desenfocar();
				panel_matriz.add(Matriz[i][j]);
				aux.setBounds(j*76, i*76, 76, 76);
				System.out.println("Bloque: "+i +" "+j+" a�adido");
				actualizarBloque(i,j, aux.getDulceActual().get_imagen_representativa());
			}	
		}
	 Matriz = miJuego.getTableroDeJuego().getNivelActual().getNivel();
	 miJuego.getTableroDeJuego().obtenerBloque(1,0).getDulceActual().enfocar();
	 panel_matriz.repaint();
	 this.repaint();
	
 }
	
 public void actualizarBloque(int posX, int posY, String imagen) {
	 ImageIcon imgIcon = new ImageIcon(this.getClass().getResource(imagen));
		Image imgEscalada = imgIcon.getImage().getScaledInstance(76, 76, Image.SCALE_SMOOTH);
		Icon iconoEscalado = new ImageIcon(imgEscalada);
		Matriz[posX][posY].setIcon(iconoEscalado);

		//Matriz[posX][posY].setIcon(new ImageIcon(GUI.class.getResource(imagen)));
	

	}
 

 public void setLabelTiempo(int min,int seg1, int seg2) {
	  temporizador =  "Tiempo restante: " + min + ":" + seg1 + seg2;
	temp.setText(temporizador);
 }
 
 public void setLabelVida(int v) {
	 vida_Jugador =  "Vida restante: " + v;
	 vida.setText(vida_Jugador);
 }
 
 public void setLabelObjetivos(int o) {
	 objetivo_nivel =  "Objetivos de este nivel: " + o +" movimientos";
	 objetivo.setText(objetivo_nivel);
	 
 }
 public void setLabelObjetivosConsumidos(int c, String string) {
	 objetivo_nivel1 =  "Objetivos de este nivel: " + c + string;
	 objetivoConsumido.setText(objetivo_nivel1);
 }
 
 public void mostrarMensajePerdiste(String mens, String titulo) {
		JOptionPane.showMessageDialog(null, mens, titulo, JOptionPane.PLAIN_MESSAGE);
	}
 
 /*public void considerar_para_intercambio_posicion(Bloque c) {
		if (bloque_1_pendiente_animacion == null) {
			bloque_1_pendiente_animacion = c;
		}else {
			bloque_2_pendiente_animacion = c;

			AnimacionIntercambio mi_animador_intercambio = new AnimacionIntercambio( 10, 1000, bloque_1_pendiente_animacion, bloque_2_pendiente_animacion);

			bloque_1_pendiente_animacion = null;
			bloque_2_pendiente_animacion = null;
			mi_animador_intercambio.start();

		}
	}*/
 public void notificarse_animacion_en_progreso() {
		synchronized(this){
			animaciones_pendientes ++;
			bloquear_intercambios = true;
		}
	}	
	@Override
	public void notificarse_animacion_finalizada() {
		synchronized(this){
			animaciones_pendientes --;
			bloquear_intercambios = animaciones_pendientes > 0;
			System.out.println("Animacion finalizada");
			this.repaint();
		}
	}
	@Override
	public void animar_movimiento(Bloque b) {
		mi_animador.animar_cambio_posicion(b);
	}
	@Override
	public void animar_cambio_estado(Bloque b) {
		mi_animador.animar_cambio_estado(b);
	}
	
}