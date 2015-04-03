package scrollingbackground.scrollingbackground;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener
{
    private static ImageButton ibDerecha, ibIzquierda;
    private static scrollingbackground.scrollingbackground.PantallaJuego pantallajuego;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pantallajuego = (scrollingbackground.scrollingbackground.PantallaJuego)findViewById(R.id.pantallajuego);
        ibDerecha = (ImageButton)findViewById(R.id.ibDerecha);
        ibIzquierda = (ImageButton)findViewById(R.id.ibIzquierda);

        ibDerecha.setOnClickListener(this);
        ibIzquierda.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.ibDerecha:
                pantallajuego.avanzarFondo();
                break;
            case R.id.ibIzquierda:
                pantallajuego.retrocederFondo();
                break;
        }
    }
}
