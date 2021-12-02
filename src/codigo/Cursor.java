package codigo;


import acm.graphics.GImage;
import acm.graphics.GRect;

public class Cursor extends GRect {
	 GImage cursor60;
	public Cursor(double x, double y, double width, double height, Arkanoid ark) {
		super(x, y, width, height);
		cursor60 = new GImage("imagenes/cursor60.png");
		cursor60.setBounds(x, y, width, height);

	}
	public void muevete(int anchoPantalla,double posX){
		if(posX + getWidth()<anchoPantalla-307){
			setLocation(posX, getY());
			cursor60.setLocation(posX, getY());
		}
	}
}
