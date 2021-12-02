
package codigo;

import java.applet.AudioClip;
import java.net.URL;

public class Sonido{
	
	//me queria pegar un tiro porque no sabia que tenia que instalar otra libreria
	//esto es para cargar los sonidos
	
	public void sonido(String fichero) {
        AudioClip sonido;
        URL url;
        try {
            url = new URL("file:" + fichero);
            sonido = java.applet.Applet.newAudioClip(url);
            sonido.play();
            Thread.sleep(1200);
            sonido.loop();
            sonido.stop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}