package com.example.admin.osmeuslugares.presentacion;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.admin.osmeuslugares.R;
import com.example.admin.osmeuslugares.casosdeuso.CasoUsoActividades;
import com.example.admin.osmeuslugares.casosdeuso.CasoUsoLugares;
import com.example.admin.osmeuslugares.modelo.LugaresVector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //private Button bSalir = findViewById(R.id.bSalir);
    private CasoUsoActividades usoAcercaDE;
    private CasoUsoLugares usoLugares;
    private LugaresVector lugares ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1 creo lugares
        lugares =((Aplicacion) getApplication()).getLugares();
        //2 luegopaso lugares
        usoAcercaDE = new CasoUsoActividades(this, lugares);
        usoLugares = new CasoUsoLugares(this, lugares);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_edicion_lugar);--> tiene algun error
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /*************************************************************
         * escuchadores de eventos para boton salir
         * ***********************************************************/
        /*bSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });Da error de ejecucion en la declaracion de la variable boton??????*/
        /*Button bAcercade = findViewById(R.id.bAcercaDe);
        bAcercade.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CasoUsoActividades usoAcercaDE = new CasoUsoActividades(this);
                usoAcercaDE.lanzarAcercaDe(null);
            }
        });*/

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
        //int id = item.getItemId();
/*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

 */
        boolean ok=true;
        int id = item.getItemId();
        switch(id){

            case R.id.acercaDe:
                lanzarAcercaDe(null);

                break;
            case R.id.menu_buscar:
                lanzarVistaLugar(null);
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
    /******************************************************
     * accions dos botons
     * ****************************************************/
    //PASA A CASO USO
      public void lanzarAcercaDe(View view){

         //CasoUsoActividades usoAcercaDE = new CasoUsoActividades(this);
         usoAcercaDE.lanzarAcercaDe(null);
    }
    public void salir(View view){
        finish();
    }
    public void lanzarVistaLugar(View view){
        final EditText entrada = new EditText(this);
        entrada.setText("0");
        new AlertDialog.Builder(this)
                .setTitle("Selección de lugar")
                .setMessage("indica su id:")
                .setView(entrada)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        int id = Integer.parseInt (entrada.getText().toString());
                        usoLugares.mostrar(id);
                    }})
                .setNegativeButton("Cancelar", null)
                .show();
    }

}
