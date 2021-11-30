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
	int vidas= 3;
	int contador=3;
	boolean victoria=false;
	Bola bola1 = new Bola(50,100,10,10,this);
	GImage fondo = new GImage("imagenes/fondo500,500.png");
	GImage cursor60 = new GImage("imagenes/cursor60.png");
	GImage corazon1 = new GImage("imagenes/corazon50(1).png");
	GImage corazon2 = new GImage("imagenes/corazon50(2).png");
	GImage corazon3 = new GImage("imagenes/corazon50(3).png");
	GImage ladrillo = new GImage("imagenes/ladrillo.png");

	Marcador miMarcador= new Marcador(20,40);
	GImage fondoMarcador = new GImage("imagenes/fondo_marcador8.png");
	Cursor cursor = new Cursor(0,400,60,10,this);



	public void init(){
		//add(foto)
		//waitForClick();
		//remove(foto)
		//añado el fondo del marcador

		add(fondoMarcador,ANCHO_PANTALLA-22,0);

		//añado dimensiones de la pantalla
		setSize(ANCHO_PANTALLA+ANCHO_MARCADOR,ALTO_PANTALLA);
		//añado el fondo lo primero porque asi se queda al fondo
		add(fondo);


		addMouseListeners();
		add(bola1,50,100);	


		add(corazon1,ANCHO_PANTALLA,ALTO_PANTALLA-140);
		add(corazon2,ANCHO_PANTALLA+20,ALTO_PANTALLA-140);
		add(corazon3,ANCHO_PANTALLA+40,ALTO_PANTALLA-140);

		add (cursor);
		add(cursor.cursor60);
		bola1.pelota.sendToFront();
	}

	public void run(){		
		creaPiramide();
		miMarcador.addMarcador(this);
		while (true){
			bola1.muevete(this);
			bola1.pelota.sendToFront();
			bola1.pelota.setLocation(bola1.getX(),bola1.getY());
			pause(5);
			quitaVidas();
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
						this);//color
				add(miLadrillo);
			}
		}
	}
	public void quitaVidas(){
		if(bola1.getY()>=420&&contador==3){
			remove(corazon3);
			vidas=vidas-1;
			contador=contador-1;
			remove(bola1);
			add(bola1,50,100);	
			miMarcador.addMarcador(this);
			remove(corazon3);
			waitForClick();
		}
		if(bola1.getY()>=420&&contador==2){
			remove(corazon2);
			vidas=vidas-1;
			contador=contador-1;
			remove(bola1);
			add(bola1,50,100);
			miMarcador.addMarcador(this);
			remove(corazon2);
			waitForClick();
		}
		if(bola1.getY()>420&& contador==1){
			remove(corazon1);
			vidas=vidas-1;
			contador=contador-1;
			removeAll();
		}
	}

}
