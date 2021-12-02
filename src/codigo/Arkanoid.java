package codigo;
import java.awt.Color;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;
public class Arkanoid extends GraphicsProgram {

	
	//añado todas las variables, imagenes y objetos que voy a utilizar en mi arkanoid
	static final int ANCHO_LADRILLO =35;
	static final int ALTO_LADRILLO =15;
	static final int ANCHO_PANTALLA = 522;
	static final int ALTO_PANTALLA =586;
	static final int ANCHO_MARCADOR=300;
	int vidas= 3;
	int contador=3;
	int nivel=1;
	boolean victoria=false;
	Bola bola1 = new Bola(50,100,10,10,this);
	GImage fondo = new GImage("imagenes/fondo500,500.png");
	GImage cursor60 = new GImage("imagenes/cursor60.png");
	GImage corazon1 = new GImage("imagenes/corazon50(1).png");
	GImage corazon2 = new GImage("imagenes/corazon50(2).png");
	GImage corazon3 = new GImage("imagenes/corazon50(3).png");
	GImage ladrillo = new GImage("imagenes/ladrillo.png");
	GImage fondoMarcador = new GImage("imagenes/fondoArkanoid1.png");
	GImage fondoMarcador2 = new GImage("imagenes/fondoArkanoid2.png");
	GImage gameOver = new GImage("imagenes/gameOver4.png");
	GImage hasGanado = new GImage("imagenes/hasGanado.png");
	GImage inicio = new GImage("imagenes/inicio2.png");
	Marcador miMarcador= new Marcador(20,40,this);
	Cursor cursor = new Cursor(0,400,60,10,this);
	CustomFont cf = new CustomFont();



	public void init(){
		
		//aqui pongo todo lo que quiero que aparezca en el inicio del programa (cuando lo abres)
		inicio.setSize(ANCHO_PANTALLA+ANCHO_MARCADOR, ALTO_PANTALLA);
		//añado pantalla de inicioy cuando hagas click se quita
		add(inicio);
		inicio.setLocation(-35,0);
		waitForClick();
		remove(inicio);
		//añado el fondo del marcador
		add(fondoMarcador,ANCHO_PANTALLA-22,0);
		//añado dimensiones de la pantalla
		setSize(ANCHO_PANTALLA+ANCHO_MARCADOR,ALTO_PANTALLA);
		//añado el fondo lo primero porque asi se queda al fondo
		add(fondo);

		
		addMouseListeners();
		add(bola1,50,100);	

		//añado las vidas en forma de corazones del Minecraft
		add(corazon1,ANCHO_PANTALLA,ALTO_PANTALLA-140);
		add(corazon2,ANCHO_PANTALLA+20,ALTO_PANTALLA-140);
		add(corazon3,ANCHO_PANTALLA+40,ALTO_PANTALLA-140);
		//añado el cursor con su skin  y mando la skin de la bola al frente para que se vea
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
			ganasOpierdes();
			

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
				//añado una pausa para ver como se añade la piramide
				pause(30);
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
	//si ganas te felicita y si perdes has perido
	public void ganasOpierdes(){
		//si tu has destrozado todos los ladrillos pasas de nivel, sino GAME OVER
		if(Marcador.puntuacion==91&&contador>=1&&nivel==2){
			removeAll();
			add(hasGanado);	
			hasGanado.setLocation(0,-50);
		}else if(contador==0){
			removeAll();
			add(gameOver);
			gameOver.setLocation(0, -50);
		}
	}


}
