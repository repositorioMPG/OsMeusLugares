package com.example.admin.osmeuslugares.presentacion;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.osmeuslugares.R;
import com.example.admin.osmeuslugares.casosdeuso.CasoUsoLugares;
import com.example.admin.osmeuslugares.modelo.Lugar;
import com.example.admin.osmeuslugares.modelo.LugaresVector;
import com.example.admin.osmeuslugares.modelo.TipoLugar;

public class EdicionLugarActivity extends AppCompatActivity {


    /***************************
    * interfaz grafica
    * **************************/
    private EditText nombre;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;
    //1.- declaro spinner
    private Spinner tipo;
    /***************************
     * datos
     * **************************/
    private CasoUsoLugares usoLugar;//----------------------------------
    private LugaresVector lugares;
    private int pos;
    private Lugar lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_lugar);
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);
        lugares = ((Aplicacion) getApplication()).getLugares();
        usoLugar = new CasoUsoLugares(this, lugares);//-------------
        /*Recupero el lugar segun su posicion*/
        lugar = lugares.elemento(pos);
        actualizaVistas();
        /*********************
         * codigo del menu
         *******************/

    }//fin onCreate
    public void modificaVistas() {
        lugar.setNombre(nombre.getText().toString());
        lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);
        lugar.setDireccion(direccion.getText().toString());
        lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
        lugar.setUrl(url.getText().toString());
        lugar.setComentario(comentario.getText().toString());
    }

    public void actualizaVistas() {
        nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        direccion = findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        url = findViewById(R.id.url);
        url.setText(lugar.getUrl());
        comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        //2. lo asocio a la vista
        tipo=findViewById(R.id.spTipo);
        //desplegables:
        /*
        * Para inicializar los valores que puede tomar un Spinner
        * necesitamos una clase especial conocida como Adapter
        * La clase ArrayAdapter<String> es un tipo de Adapter
        * que permite inicializar sus valores
        * a partir de un ARRAY de String*/
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                this,/*contexto la actividad actual*/
                android.R.layout.simple_spinner_item,/*una vista para mostrar elemento */
                TipoLugar.getNombres()/*ARRAY con todos los valores
                que debemos programar en TipoLugar para
                mantener el aislamiento entre los códigos*/);
        adaptador.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        /*permite indicar una vista alternativa que se usará cuando se despliegue el Spinner*/
        tipo.setAdapter(adaptador);/*vinculamos el adaptador al spinner*/
        tipo.setSelection(lugar.getTipo().ordinal());/*realizamos la selecion*/

    }//fin actualiza vista
    /**********************
     * codigo del menu
     **********************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edicion_lugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean ok=true;
        int id = item.getItemId();
        switch(id){

            case R.id.accion_guardar:
                modificaVistas();
                usoLugar.guardar(pos,lugar);
                finish();
                break;
            case R.id.accion_cancelar:
                finish();
                break;

            default:
                Toast mensa = Toast.makeText(this, "Nombre Apellido Alumno: Opción en construccion",
                        Toast.LENGTH_SHORT);
                mensa.show();
                ok=super.onOptionsItemSelected(item);
                break;
        }
        return ok;
    }
}
