package codigo;



import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;


public class Bola extends GOval{

	

	GImage pelota = new GImage("imagenes/pelotaArkanoid.png");

	int dx = 1; //velocidad eje x
	int dy = 1; //velocidad eje y

	public Bola(double x,double y,double width, double height, Arkanoid ark) {
		super(x,y,width, height);

		pelota.setBounds(x,y,width,height);
		ark.add(pelota);
	}


	public void muevete(Arkanoid ark){
		//rebote con el suelo y rebote con techo
		if(getY()> ark.getHeight()||getY()<17){
			dy = dy*-1;
		}

		//rebote con pared derecha y  rebote con pared izquierda
		if(getX()+getWidth()> ark.ANCHO_PANTALLA -40||getX()<18){
			dx = dx*-1;
		}

		//chequeo el rebote en las esquinas
		if(chequeaColision(getX(), getY(), ark)){// esquina superior izquierda

			if(chequeaColision(getX()+ getWidth(), getY(), ark)){//esquina superior derecha

				if(chequeaColision(getX(), getY()+getHeight(), ark)){// esquina inferior izquierda

					if(chequeaColision(getX()+getWidth(), getY()+getHeight(), ark)){//esquina inferior derecha


					}
				}
			}
		}
		move(dx,dy);
		pelota.sendToFront();
		pelota.setLocation(getX(), getY());
		//mueve la bola en la direccion correcta

	}


	private boolean chequeaColision(double posx, double posy, Arkanoid ark){
		
		sonidoCursor _sonidoCursor=new sonidoCursor();
		sonidoLadrillo _sonidoLadrillo=new sonidoLadrillo();

		boolean noHaChocado = true;
		GObject auxiliar;
		Ladrillo ladrillo;
		auxiliar = ark.getElementAt(posx, posy);

		if(auxiliar == ark.cursor){ //si entra aqui es que choca con el cursor
			dy = dy*-1;
			//rebote con la parte izquierda del crusor
			if(posx<=ark.cursor.getX()+ ark.cursor.getWidth()/2 && posx<=ark.cursor.getX() + ark.cursor.getWidth()){
				if(dx<0){
					dx=dx*-1;
				}
			}
			
			//rebote con la parte derecha del cursor
			if(posx>=ark.cursor.getX()&& posx<=ark.cursor.getX() + ark.cursor.getWidth()/2){
				if(dx>0){
					dx=dx*-1;
				}
			}
			
			_sonidoCursor.start();
			
			noHaChocado = false;
		}else if(auxiliar == null){ //si vale nll es que no había nada ahí

		}else if(auxiliar instanceof Ladrillo){ 
			ladrillo=(Ladrillo)auxiliar;
			//verificamos que es un ladrillo
			if(ladrillo.getY()+ladrillo.getHeight()<= posy || ladrillo.getY() >= posy ){
				dy = dy*-1;
			}else if(ladrillo.getX()+ladrillo.getWidth()<= posx || ladrillo.getX() >= posx ){
				dx = dx*-1;
			}
			_sonidoLadrillo.start();
			
			noHaChocado = false;
			ladrillo.eliminaLadrillo(ark);
			ark.miMarcador.incrementaMarcador(1);
			ark.remove(auxiliar);//borro el ladrillo


		}

		return noHaChocado;
	}
	//cargo los sonidos para cuando reboten con el ladrillo y el cursor
	public class sonidoLadrillo extends Thread {		
		public void run() {                               
            Sonido ladrillo = new Sonido();
            ladrillo.sonido(ladrillo.getClass().getResource("../sonido/Sonido_golpeoLadrillo.wav").getFile());
        }
	}
	public class sonidoCursor extends Thread {		
		public void run() {                               
            Sonido cursor = new Sonido();
            cursor.sonido(cursor.getClass().getResource("../sonido/Sonido_golepoCursor.wav").getFile());
        }
	}

}
