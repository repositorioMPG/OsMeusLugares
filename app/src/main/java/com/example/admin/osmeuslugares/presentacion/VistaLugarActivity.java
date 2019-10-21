package com.example.admin.osmeuslugares.presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.osmeuslugares.R;
import com.example.admin.osmeuslugares.casosdeuso.CasoUsoLugares;
import com.example.admin.osmeuslugares.modelo.Lugar;
import com.example.admin.osmeuslugares.modelo.LugaresVector;

import java.text.DateFormat;
import java.util.Date;


public class VistaLugarActivity extends AppCompatActivity {
    final static int RESULTADO_EDITAR = 1;
    private LugaresVector lugares;
    private CasoUsoLugares usoLugar;
    private int pos;
    private Lugar lugar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_lugar);
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);
        lugares = ((Aplicacion) getApplication()).getLugares();
        usoLugar = new CasoUsoLugares(this, lugares);
        lugar = lugares.elemento(pos);
        actualizaVistas();
    }

    public void actualizaVistas() {
        TextView nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        ImageView logo_tipo = findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        TextView tipo = findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());
        TextView direccion = findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        TextView telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        TextView url = findViewById(R.id.url);
        url.setText(lugar.getUrl());
        TextView comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        TextView fecha = findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        TextView hora = findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        RatingBar valoracion = findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override public void onRatingChanged(RatingBar ratingBar,
                                                          float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                });
    }
    /*****************
    * MENU
    * ***************/
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vista_lugar, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        boolean opExito=true;
        switch (item.getItemId()) {
            case R.id.accion_compartir:
                break;
            case R.id.accion_llegar:
                break;
            case R.id.accion_editar:
                usoLugar.editar(pos,RESULTADO_EDITAR);
                break;
            case R.id.accion_borrar:
                usoLugar.borrar(pos);
                break;
            default:
                opExito=super.onOptionsItemSelected(item);
        }
        return opExito;
    }
    @Override protected void onActivityResult(int requestCode, int resultCode,
                                              Intent data) {
        /***************
         * Una vez regresamos de la actividad EdicionLugarActivity
         * lo que hacemos es actualizar los valores de las vistas y
         * forzar al sistema a que repinte la vista con id scrollView1.
         * Esta vista corresponde al ScrollView que contiene todo el LAYOUT
         */
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULTADO_EDITAR) {
            actualizaVistas();
        }
    }



}