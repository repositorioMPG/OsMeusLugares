package com.example.admin.osmeuslugares.casosdeuso;

import android.app.Activity;
import android.content.Intent;

import com.example.admin.osmeuslugares.modelo.Lugar;
import com.example.admin.osmeuslugares.modelo.LugaresVector;
import com.example.admin.osmeuslugares.presentacion.EdicionLugarActivity;
import com.example.admin.osmeuslugares.presentacion.VistaLugarActivity;

public class CasoUsoLugares {
    private Activity actividad;
    private LugaresVector misLugares;
    public CasoUsoLugares(Activity actividad,LugaresVector misLugares) {

        this.actividad = actividad;
        this.misLugares = misLugares;
    }
    public void mostrar(int pos) {
        Intent i = new Intent(actividad, VistaLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivity(i);
    }
    public void borrar(int pos) {
        misLugares.borrar(pos);
        actividad.finish();
    }
    public void guardar(int pos, Lugar lugar){
        misLugares.actualiza(pos,lugar);
    }
    public void editar(int pos,int codidoSolicitud) {
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivityForResult(i, codidoSolicitud);
        //actividad.startActivity(i);
    }

}
