package codigo;
import java.awt.Font;
import java.io.InputStream;

//nose que como funciona esto pero lo he visto en internet, esta chulo y funciona

public class CustomFont {

	private Font font = null;

	public CustomFont() {
		String fontName = "arkanoid.ttf" ;
		try {
			//Se carga la fuente
			InputStream is =  getClass().getResourceAsStream("/imagenes/Arkanoid2.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (Exception ex) {
			//Si existe un error se carga fuente por defecto ARIAL
			System.err.println(fontName + " No se cargo la fuente");
			font = new Font("Arial", Font.BOLD, 100);            
		}
	}
	/* Font.PLAIN = 0 , Font.BOLD = 1 , Font.ITALIC = 2
	 * tamanio = float
	 */
	public Font MyFont( int estilo, float tamanio)
	{
		Font tfont = font.deriveFont(estilo, tamanio);
		return tfont;
	}

}