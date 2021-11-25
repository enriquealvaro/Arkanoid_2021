package codigo;
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
public class Arkanoid extends GraphicsProgram {

	static final int ANCHO_LADRILLO =35;
	static final int ALTO_LADRILLO =15;
	static final int ANCHO_PANTALLA = 522;
	static final int ALTO_PANTALLA =586;
	static final int ANCHO_MARCADOR=300;
	Bola bola1 = new Bola(10,10,Color.BLUE);
	//declaro el cursor del juego
	Cursor miCursor = new Cursor(0,400,60,10,Color.RED);
	//declaro el fondo del juego
	GImage fondo = new GImage("imagenes/fondo500,500.png");
	GRect fondoMarcador = new GRect(ANCHO_MARCADOR,ALTO_PANTALLA);

	public void init(){
		//añado el fondo del marcador
		fondoMarcador.setFilled(true);
		add(fondoMarcador,ANCHO_PANTALLA-22,0);
		//añado dimensiones de la pantalla
		setSize(ANCHO_PANTALLA+ANCHO_MARCADOR,ALTO_PANTALLA);
		//añado el fondo lo primero porque asi se queda al fondo
		add(fondo);
		addMouseListeners();
		add(bola1,50,100);	
		add(miCursor);
	
		
	}

	public void run(){
		creaPiramide();
		while (true){
			bola1.muevete(this);
			pause(20);
			miCursor.muevete(ANCHO_PANTALLA -40, (int) bola1.getX());
		}
	}
	public void mouseMoved(MouseEvent evento){
		miCursor.muevete(getWidth(), evento.getX());
	}

	private void creaPiramide(){
		int desplazamiento_inicial_X=23;
		int desplazamiento_inicial_Y=20;
		int numeroLadrillos = 13;
		for(int j=0; j<numeroLadrillos; j++){
			for(int i=j; i<numeroLadrillos; i++){
				Ladrillo miLadrillo = new Ladrillo(
						ANCHO_LADRILLO*i - ANCHO_LADRILLO/2*j+desplazamiento_inicial_X, //posX
						ALTO_LADRILLO*j+desplazamiento_inicial_Y,//posY
						ANCHO_LADRILLO,//ancho
						ALTO_LADRILLO,//alto
						Color.YELLOW);//color
				add(miLadrillo);
			}
		}
	}

}
