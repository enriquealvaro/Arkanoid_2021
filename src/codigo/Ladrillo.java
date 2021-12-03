package codigo;

import java.awt.Color;
import acm.graphics.GImage;
import acm.graphics.GRect;

public class Ladrillo extends GRect{

	int numero_golpes =0;
	GImage ladrillo = new GImage("imagenes/ladrillo.png");

	public Ladrillo(double x, double y, double width, double height, Arkanoid ark) {
		//aqui añado la skin del ladrillo encima del ladrillo inicial
		super(x, y, width, height);
		ladrillo.setBounds(x,y,width,height);
		ark.add(ladrillo);
	}
	public void eliminaLadrillo(Arkanoid ark){
		//este método es para que se eliminen los ladrillos
		ark.remove(ladrillo);
		ark.remove(this);
	}
}
