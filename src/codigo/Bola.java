package codigo;

import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GOval;

public class Bola extends GOval{

	int dx = 1; //velocidad eje x
	int dy = 1; //velocidad eje y

	public Bola(double width, double height) {
		super(width, height);

	}

	public Bola(double width, double height, Color c) {
		super(width, height);
		this.setFillColor(c);
		this.setFilled(true);
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

		//mueve la bola en la direccion correcta
		move(dx,dy);
	}

	private boolean chequeaColision(double posx, double posy, Arkanoid ark){
		boolean noHaChocado = true;
		GObject auxiliar;

		auxiliar = ark.getElementAt(posx, posy);

		if(auxiliar == ark.cursor){ //si entra aqui es que choca con el cursor
			dy = dy*-1;
			noHaChocado = false;
		}else if(auxiliar == null){ //si vale nll es que no había nada ahí

		}else if(auxiliar instanceof Ladrillo){ //verificamos que es un ladrillo
			if(auxiliar.getY()+getHeight()<= posy || auxiliar.getY() >= posy ){
				dy = dy*-1;
			}else if(auxiliar.getX()+getWidth()<= posx || auxiliar.getX() >= posx ){
				dx = dx*-1;
			}
			ark.miMarcador.incrementaMarcador(1);
			ark.remove(auxiliar);//borro el ladrillo


			noHaChocado = false;
		}

		return noHaChocado;
	}

}
