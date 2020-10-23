package com.uso.parcial2hn17_i04_001;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uso.parcial2hn17_i04_001.Adaptadores.AdaptadorInformacion;
import com.uso.parcial2hn17_i04_001.Adaptadores.Jugador;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PuntajesAltos extends AppCompatActivity {
 ArrayList<String> jugadores;
 ListView listaJugadores;
 TextView mNombre;
 TextView mPuntos;
    private List<Jugador> ListaJugadores = new ArrayList<Jugador>();
    private RecyclerView Lista;
    private AdaptadorInformacion Adaptador;
    private LinearLayoutManager manager;
    private SharedPreferences configuraciones1;
    private SharedPreferences configuraciones2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes_altos);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Recibimos los datos
        Intent a = getIntent();
        String DatosLista = a.getStringExtra("DatosLista");
        Gson gn = new Gson();
        Type type =  new TypeToken<ArrayList<Jugador>>(){}.getType();
        ArrayList<Jugador>items = gn.fromJson(DatosLista,type);
        this.ListaJugadores = items;
        //
        this.Lista = findViewById(R.id.RCV);
        this.manager = new  LinearLayoutManager(this);
        this.Adaptador= new AdaptadorInformacion(ListaJugadores);



        //Configurar

        this.Lista.setHasFixedSize(true);
        this.Lista.setLayoutManager(manager);
        this.Lista.setAdapter(Adaptador);

        /////

        this.configuraciones1 = getSharedPreferences(Configuraciones.ARCHIVO, MODE_PRIVATE);
        this.configuraciones2 = getSharedPreferences(Juegos.KEY_PUNTOS, MODE_PRIVATE);


    }




}