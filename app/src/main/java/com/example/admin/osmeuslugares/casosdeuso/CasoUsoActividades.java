package com.example.admin.osmeuslugares.casosdeuso;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.admin.osmeuslugares.modelo.LugaresVector;
import com.example.admin.osmeuslugares.presentacion.ACercaDeActivity;

public class CasoUsoActividades {
    //no necesita la lista de lugares
    //pero si la actividad
    private Activity actividad;
    private LugaresVector misLugares;
    public CasoUsoActividades(Activity actividad,LugaresVector misLugares) {

        this.actividad = actividad;
        this.misLugares = misLugares;
    }
    public void lanzarAcercaDe(View view){

        Intent i = new Intent(actividad, ACercaDeActivity.class);
        actividad.startActivity(i);
    }
    public void lanzarPreferencias(View view){
        Toast mensa = Toast.makeText(actividad,
                "Nombre Apellido Alumno: Opción en construccion", Toast.LENGTH_SHORT);
        mensa.show();

        //Intent i = new Intent(actividad, ACercaDeActivity.class);
        //actividad.startActivity(i);
    }
    public void lanzarMapas(View view){

        Toast mensa = Toast.makeText(actividad,
                "Nombre Apellido Alumno: Opción en construccion", Toast.LENGTH_SHORT);
        mensa.show();
        //Intent i = new Intent(actividad, ACercaDeActivity.class);
        //actividad.startActivity(i);
    }
}
