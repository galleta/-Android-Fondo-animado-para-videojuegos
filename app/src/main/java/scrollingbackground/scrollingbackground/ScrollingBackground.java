package scrollingbackground.scrollingbackground;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by francis on 23/02/15.
 */
public class ScrollingBackground
{
    private Bitmap fondo;
    private int x, y, topeancho, topealto;
    private final int VELOCIDAD = 5;

    /**
     * Constructor
     * @param fondo Fondo del juego
     * @param anchopantalla Ancho de la pantalla donde se va a jugar
     * @param altopantalla Alto de la pantalla donde se va a jugar
     */
    public ScrollingBackground(Bitmap fondo, int anchopantalla, int altopantalla)
    {
        this.fondo = fondo;
        x = y = 0;
        topeancho = anchopantalla;
        topealto = altopantalla;
    }

    /**
     * Obtiene la posición X por donde va el fondo
     * @return Posición X por donde va el fondo
     */
    public int getX()
    {
        return x;
    }

    /**
     * Obtiene la posición Y por donde va el fondo
     * @return Posición Y por donde va el fondo
     */
    public int getY()
    {
        return y;
    }

    /**
     * Avanza el fondo
     */
    public void avanzarX()
    {
        x += VELOCIDAD;
        if(x > fondo.getWidth())
            x = fondo.getWidth() - topeancho;
    }

    /**
     * Retrocede el fondo
     */
    public void retrocederX()
    {
        x -= VELOCIDAD;
        if(x < 0)
            x = 0;
    }

    /**
     * Dibuja el fondo por donde vaya
     * @param lienzo Lienzo para dibujar
     */
    public void onDraw(Canvas lienzo)
    {
        int x_tope = x + topeancho;
        Rect imagenfondoactual = new Rect(x, 0, x_tope, topealto);
        Rect imagenenpantalla = new Rect(0, 0, topeancho, topealto);
        lienzo.drawBitmap(fondo, imagenfondoactual, imagenenpantalla, null);
    }
}
