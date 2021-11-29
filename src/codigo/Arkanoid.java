package codigo;
import java.awt.Color;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;
public class Arkanoid extends GraphicsProgram {
	
	CustomFont cf = new CustomFont();

	static final int ANCHO_LADRILLO =35;
	static final int ALTO_LADRILLO =15;
	static final int ANCHO_PANTALLA = 522;
	static final int ALTO_PANTALLA =586;
	static final int ANCHO_MARCADOR=300;
	Bola bola1 = new Bola(10,10,Color.BLUE);
	//declaro el fondo del juego
	GImage fondo = new GImage("imagenes/fondo500,500.png");
	GImage cursor60 = new GImage("imagenes/cursor60.png");
	GImage corazon1 = new GImage("imagenes/corazon50(1).png");
	GImage corazon2 = new GImage("imagenes/corazon50(2).png");
	GImage corazon3 = new GImage("imagenes/corazon50(3).png");
	GImage fondoMarcador = new GImage("imagenes/fondo_marcador6.png");
	Cursor cursor = new Cursor(400,60,10,Color.black);
	Marcador miMarcador= new Marcador(20,40);
	
	
	public void init(){

		//añado el fondo del marcador
		
		add(fondoMarcador,ANCHO_PANTALLA-22,0);
		
		//añado dimensiones de la pantalla
		setSize(ANCHO_PANTALLA+ANCHO_MARCADOR,ALTO_PANTALLA);
		//añado el fondo lo primero porque asi se queda al fondo
		add(fondo);

		addMouseListeners();
		add(bola1,50,100);	
		
		
		//añado las vidas en el marcador

		add(corazon1,ANCHO_PANTALLA,ALTO_PANTALLA-140);
		add(corazon2,ANCHO_PANTALLA+20,ALTO_PANTALLA-140);
		add(corazon3,ANCHO_PANTALLA+40,ALTO_PANTALLA-140);
		
		add (cursor);
		add(cursor.cursor60);
	}

	public void run(){
		//creaPiramide();
		miMarcador.addMarcador(this);


		while (true){
			bola1.muevete(this);
			pause(15);
			
			
		if(bola1.getY()>400){
			
		}
			
		}
	}
	public void mouseMoved(MouseEvent evento){
		

		cursor.setLocation(evento.getX()+14,cursor.getY());
		(cursor.cursor60).setLocation(evento.getX()+14,cursor.getY());
		
		
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
