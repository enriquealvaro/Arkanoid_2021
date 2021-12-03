package codigo;
import java.awt.Color;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;
public class Arkanoid extends GraphicsProgram {


	//añado todas las variables, imagenes ,booleans y objetos que voy a utilizar en mi arkanoid
	static final int ANCHO_LADRILLO =35;
	static final int ALTO_LADRILLO =15;
	static final int ANCHO_PANTALLA = 522;
	static final int ALTO_PANTALLA =586;
	static final int ANCHO_MARCADOR=300;
	int vidas= 3;
	int contador=3;
	Bola bola1 = new Bola(50,100,10,10,this);
	Bonus bonus = new Bonus(50,100,10,10,this);
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
	boolean activo=false;



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
		//lo pongo en falso para que solo se vea la skin
		cursor.setVisible(false);
		add(cursor.cursor60);
		bola1.pelota.sendToFront();
	}

	public void run(){	
		//depsues de la pantalla de inicio que cargue la piramide y añada el marcador
		creaPiramide();
		miMarcador.addMarcador(this);

		while (true){
			//todo lo qu ehay aqui dentro se va a cumplir siempre ya que siempre va a ser true
			bonus();
			bola1.pelota.sendToFront();
			bola1.pelota.setLocation(bola1.getX(),bola1.getY());
			pause(8);
			quitaVidas();
			ganasOpierdes();



		}
	}
	public void mouseMoved(MouseEvent evento){
		
		//esto es para que el cursor se mueva por el eje x

		cursor.muevete(getWidth(), evento.getX());

	}
	private void creaPiramide(){
		int desplazamiento_inicial_X=23;
		int desplazamiento_inicial_Y=20;
		int numeroLadrillos = 13;
		//aqui he hecho que la priamide tenga que tener dos toques para romeprla con el bucle for de debajo
		for(int a=0;a<=2;a++){
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
					pause(10);
				}
			}
		}
	}

	public void quitaVidas(){
		//cada vez que la bola este por debajo del cursor se quitara un corazon y la bola 
		//volverá al inicio esperando un click para volver a jugar
		if(bola1.getY()>=420&&contador==3){
			remove(corazon3);
			vidas=vidas-1;
			contador=contador-1;
			remove(bola1);
			remove(bonus);
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
			remove(bonus);
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
	//si ganas te felicita y si perdes te dice que has perdido
	public void ganasOpierdes(){
		//si tu has destrozado todos los ladrillos has ganado, sino GAME OVER
		if(Marcador.puntuacion==91*2&&contador>=1){
			removeAll();
			add(hasGanado);	
			hasGanado.setLocation(0,-50);
		}else if(contador==0){
			removeAll();
			add(gameOver);
			gameOver.setLocation(0, -50);
		}
	}
	public void bonus(){
		//este bonus hace que si has roto 35 ladrillos aparece una segunda bola que solo tiene un vida
		//y si te cae, se supone que deberia desaparecer pero no me detecta el remove(bonus)
		if(Marcador.puntuacion==35){
			add(bonus,50,200);
			activo=true;

		}
		if(bonus.getY()>420&&contador>=3){
			remove(bonus);
			activo=false;
		}
		bola1.muevete(this);
		if(activo){
			bonus.muevete(this);

		}
	}
}


