
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
            //el sleep este sirve para poner la duracion del audio, es decir, si lo pones en 10
            //el sonido va a ser muy corto, pero si lo pones en 1200, se reproduce el sonido completo
            Thread.sleep(1200);
            sonido.loop();
            sonido.stop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}