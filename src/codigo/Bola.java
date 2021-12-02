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
			sonidoCursor _sonidoCursor=new sonidoCursor();
			_sonidoCursor.start();
			noHaChocado = false;
		}else if(auxiliar == null){ //si vale nll es que no hab�a nada ah�

		}else if(auxiliar instanceof Ladrillo){ 
			ladrillo=(Ladrillo)auxiliar;
			//verificamos que es un ladrillo
			if(ladrillo.getY()+ladrillo.getHeight()<= posy || ladrillo.getY() >= posy ){
				dy = dy*-1;
			}else if(ladrillo.getX()+ladrillo.getWidth()<= posx || ladrillo.getX() >= posx ){
				dx = dx*-1;
			}
			ladrillo.eliminaLadrillo(ark);
			ark.miMarcador.incrementaMarcador(1);
			ark.remove(auxiliar);//borro el ladrillo

			sonidoLadrillo _sonidoLadrillo=new sonidoLadrillo();
			_sonidoLadrillo.start();
			noHaChocado = false;
		}

		return noHaChocado;
	}
	
	public class sonidoLadrillo extends Thread {		
		public void run() {                               
            Sonido s = new Sonido();
            s.sonido(s.getClass().getResource("/sonido/Sonido_golpeoLadrillo.mp3").getFile());
        }
	}
	public class sonidoCursor extends Thread {		
		public void run() {                               
            Sonido s = new Sonido();
            s.sonido(s.getClass().getResource("/sonido/Sonido_golpeoCursor.mp3").getFile());
        }
	}

}
