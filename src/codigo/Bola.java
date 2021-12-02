package codigo;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GImage;

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
			noHaChocado = false;
		}else if(auxiliar == null){ //si vale nll es que no había nada ahí

		}else if(auxiliar instanceof Ladrillo){ 
			ladrillo=(Ladrillo)auxiliar;
			//verificamos que es un ladrillo
			if(auxiliar.getY()+getHeight()<= posy || auxiliar.getY() >= posy ){
				dy = dy*-1;
			}else if(auxiliar.getX()+getWidth()<= posx || auxiliar.getX() >= posx ){
				dx = dx*-1;
			}
			ladrillo.eliminaLadrillo(ark);
			ark.miMarcador.incrementaMarcador(1);
			ark.remove(auxiliar);//borro el ladrillo


			noHaChocado = false;
		}

		return noHaChocado;
	}
}
