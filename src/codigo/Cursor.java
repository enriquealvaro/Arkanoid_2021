package codigo;


import acm.graphics.GImage;
import acm.graphics.GRect;

public class Cursor extends GRect {
	 GImage cursor60;
	public Cursor(double x, double y, double width, double height, Arkanoid ark) {
		//aqui cargo la imagen del cursor que ira encima del crectangulo que teniamos inicialmente
		super(x, y, width, height);
		cursor60 = new GImage("imagenes/cursor60.png");
		cursor60.setBounds(x, y, width, height);

	}
	public void muevete(int anchoPantalla,double posX){
		//aqui pongo los limites del cursor para que no se salga de la pantalla de juego
		if(posX + getWidth()<anchoPantalla-307){
			setLocation(posX, getY());
			cursor60.setLocation(posX, getY());
		}
	}
}
