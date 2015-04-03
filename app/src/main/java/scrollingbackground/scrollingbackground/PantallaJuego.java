package scrollingbackground.scrollingbackground;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by francis on 23/02/15.
 */
public class PantallaJuego extends SurfaceView
{
    private ScrollingBackground fondo;
    private SurfaceHolder holder;
    private MotorJuego motor;

    public PantallaJuego(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        funcionalidad();
    }

    public PantallaJuego(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        funcionalidad();
    }

    public PantallaJuego(Context context)
    {
        super(context);
        funcionalidad();
    }

    @Override
    /**
     * Método donde se dibujan las cosas en la pantalla
     * @param canvas Lienzo de la pantalla donde se dibuja
     */
    protected void onDraw(Canvas canvas)
    {
        if(canvas != null)
        {
            if(fondo != null)
                fondo.onDraw(canvas);
        }
    }

    /**
     * Funcionalidad de la pantalla de juego
     */
    private void funcionalidad()
    {
        motor = new MotorJuego(this);

        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback()
        {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                motor.setRunning(false);
                motor.matar();
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
                fondo = new ScrollingBackground(BitmapFactory.decodeResource(getResources(), R.drawable.world_1_1), getWidth(), getHeight());
                motor.setRunning(Boolean.TRUE);
                motor.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
            {
            }
        });

    }

    /**
     * Pausa o reanuda el juego
     */
    public void pausar()
    {
        motor.pause();
    }

    /**
     * Indica si el juego está en marcha o no
     * @return Verdadero si el juego está en marcha, falso en caso contrario
     */
    public Boolean isRunning()
    {
        return motor.getRunning();
    }

    public void avanzarFondo()
    {
        fondo.avanzarX();
    }

    public void retrocederFondo()
    {
        fondo.retrocederX();
    }

}

