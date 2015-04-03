package scrollingbackground.scrollingbackground;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

/**
 * Created by francis on 23/02/15.
 */
public class MotorJuego extends Thread
{
    // Atributos de la clase

    static final long FPS = 25;                                         // Frames Por Segundo para pintar en el juego
    private PantallaJuego pantalla;                                     // Pantalla donde se va a jugar
    private boolean running = Boolean.FALSE;                            // Indica si el motor está en marcha o no
    private long ticksPS, startTime, sleepTime;                         //
    private Canvas c;                                                   // Lienzo para dibujar

    // Constructores de la clase

    /**
     * Constructor de la clase
     * @param pantalla Lienzo del juego
     */
    public MotorJuego(PantallaJuego pantalla)
    {
        this.pantalla = pantalla;
        ticksPS = 1000 / FPS;  // Frecuencia de actualización, depende de los FPS
        c = null;
    }

    /**
     * Método para decir que el juego anda o no, servirá para pausar
     * @param run Verdadero si el juego se está ejecutando, falso en caso contrario
     */
    public void setRunning(boolean run)
    {
        running = run;
    }

    /**
     * Devuelve si el juego está pausado o no
     * @return Juego pausado
     */
    public Boolean getRunning()
    {
        return running;
    }

    /**
     * Pausa o reanuda el juego
     */
    public void pause()
    {
        setRunning(!getRunning());
    }

    /**
     * Mata la hebra del motor del juego
     */
    public void matar()
    {
        this.interrupt();
    }

    /**
     * Método a ejecutar en la hebra
     */
    @SuppressLint("WrongCall")
    @Override
    public void run()
    {
        while (Boolean.TRUE)
        {
            if(running)
            {
                startTime = System.currentTimeMillis();
                try
                {
                    /*
                    *  Para dibujar se bloquea el canvas, con esto se consigue
                    *  que únicamente pinte un objeto a la vez y no se líe parda
                    */
                    c = pantalla.getHolder().lockCanvas();
                    // Sincrozinamos para evitar problemas con otras hebras
                    synchronized (pantalla.getHolder())
                    {
                        pantalla.onDraw(c);
                    }
                }
                finally
                {
                    if (c != null)
                    {
                        pantalla.getHolder().unlockCanvasAndPost(c);
                    }
                }

                /*
                 * Calculo si tengo que dormir la hebra o no
                 */

                sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
                try
                {
                    if (sleepTime > 0)
                        sleep(sleepTime);
                    else
                        sleep(10);
                }
                catch (Exception e) {}
            }
        }
    }
}

