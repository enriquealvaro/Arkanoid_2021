package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;
public class Marcador  extends GRect{

	GLabel texto =new GLabel("");
	static int puntuacion=0;
	CustomFont cf = new CustomFont();

	public Marcador(double width,double height, Arkanoid ark){
		//aqui pongo el marcador que subirá cuando se detecte que ha tocado un ladrillo
		super(width,height);
		setFilled(true);
		setFillColor(Color.BLACK);

		texto.setColor(Color.WHITE);
		//aqui cargo la fuente que tendra el marcador, la original del Arkanoid
		this.texto.setFont(cf.MyFont(1, 27f));
		this.texto.setLabel("0");


	}
	//suma el nº de puntos que se indica en puntos a la puntuacion actual
	public void incrementaMarcador(int puntos){
		puntuacion=puntuacion+puntos;//puntuacion += puntos;
		texto.setLabel(""+puntuacion); 

	}
	public void addMarcador(Arkanoid arkanoid){
		//este metodo es para añadir el marcador cuando lo inicias
		arkanoid.add(this,arkanoid.getWidth()-215,330);
		arkanoid.add(texto,arkanoid.getWidth()-215,330);
	
	}

}
