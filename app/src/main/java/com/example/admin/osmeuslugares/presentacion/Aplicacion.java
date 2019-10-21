package com.example.admin.osmeuslugares.presentacion;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.admin.osmeuslugares.modelo.LugaresVector;

public class Aplicacion extends Application {
    private int saldo;
    //creamos e inicializamos la lista
    private LugaresVector misLugares;
    @Override public void onCreate() {
        super.onCreate();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        saldo = pref.getInt("saldo_inicial", -1);
        misLugares= new LugaresVector();
    }
    /*codigo que maneja el saldo*/
    public int getSaldo(){
        return saldo;
    }

    public void setSaldo(int saldo){
        this.saldo=saldo;

    }
    /*codigo que maneja los datos*/
    public LugaresVector getLugares() {
        return misLugares;
    }
}

