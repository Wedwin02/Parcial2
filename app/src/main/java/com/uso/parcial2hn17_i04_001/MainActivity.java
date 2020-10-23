package com.uso.parcial2hn17_i04_001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uso.parcial2hn17_i04_001.Adaptadores.Jugador;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 Button btnJuegos;
 Button btnConfiguraciones;
 Button btnPuntajesAltos;
    List<Jugador> ListaGlobal = new ArrayList<Jugador>();
    public static final int id=4;
    String an = "";



    public void onActivityResult(int requestcode, int resultcode, Intent data ){
        super.onActivityResult(requestcode, resultcode, data);

        switch (requestcode){
            case id:{
                if(RESULT_OK == resultcode){
                    Jugador n = new Jugador();
                    n.setJugadorname(data.getStringExtra(Juegos.textoNombre));
                    n.setJugadorpuntos(data.getStringExtra(Juegos.textoPunto));
                    n.setJugadorIntentos(data.getStringExtra(Juegos.textoIntentos));
                    ListaGlobal.add(n);
                    Toast.makeText(this, "Datos Guardados.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Saliendo....", Toast.LENGTH_SHORT).show();

                }
            }
            break;
            default:
                Toast.makeText(this, "Error en el switch..", Toast.LENGTH_SHORT).show();
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.btnJuegos = findViewById(R.id.btnJuegos);
        this.btnConfiguraciones = findViewById(R.id.btnConfiguraciones);
        this.btnPuntajesAltos =  findViewById(R.id.btnuntajesAltos);

        btnJuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Juegos.class);
                startActivityForResult(i,id);
            }
        });
        btnConfiguraciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Configuraciones.class);
                startActivity(i);
            }
        });
        btnPuntajesAltos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(ListaGlobal.size() == 0)){
                    Intent i = new Intent(MainActivity.this, PuntajesAltos.class);
                    Gson g = new Gson();
                    String Datos = g.toJson(ListaGlobal);
                    i.putExtra("DatosLista",Datos);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this,"Lista Vacia!..",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void onSaveInstanceState(Bundle estado){
        super.onSaveInstanceState(estado);
        //Guardamos el estado de las variables nativas
        Gson g = new Gson();
        an = g.toJson(ListaGlobal);

        estado.putString("ListaG", an );

    }
    public void onRestoreInstanceState(Bundle estadoAnterior){
        super.onRestoreInstanceState(estadoAnterior);
        //Recuperamos el estado antes del cambio

        an = estadoAnterior.getString("ListaG");


    }
}