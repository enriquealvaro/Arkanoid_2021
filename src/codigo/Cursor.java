package codigo;

import java.awt.Color;
import acm.graphics.GImage;
import acm.graphics.GRect;

public class Cursor extends GRect {

	GImage cursor60;

	public Cursor(int posicionY,double ancho,double alto,Color color ){
		super (ancho, alto);
		//color del recuadro del cursor
		setVisible(false);


		setLocation(0, posicionY);
		cursor60 = new GImage("imagenes/cursor60.png");
		cursor60.setSize(ancho, alto);

	}
	public void muevete(int anchoPantalla,int posX){
		if(posX + getWidth()<anchoPantalla){
			setLocation(posX, getY());
		}
	}
}

