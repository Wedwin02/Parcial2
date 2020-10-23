package com.uso.parcial2hn17_i04_001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Object;
import com.uso.parcial2hn17_i04_001.Clases.JugadorData;

public class Juegos extends AppCompatActivity {
    private final String TAG = "parcial2";
    private SharedPreferences  configuraciones;
    public static final String KEY_INTENTOS= "Intentos" ;
    public static final String KEY_PUNTOS = "puntos" ;
    public static final String Numeros = "NumeroData";
    public static final String textoNombre = "textoNombre";
    public static final String textoPunto = "textoPunto";
    public static final String textoIntentos = "textoIntentos";
    private SharedPreferences datosN;
    private TextView idPuntos;
    private TextView intentos;
    private TextView aleatorio;
    private TextView idJugador;
    private TextView idDificultad;
    private  TextView idRango;
    private Button btnIntentos;
    private EditText txtNumero;
    int contador = 0;
    int puntos = 0;
    int inten = 0;
    private JugadorData jugadorData;
    private TextView labelPosicion;
    private WindowManager administradorPantalla;
    private int contadorInicioSesion;
    Button Terminar;
    String datIn = "";
    String datPun = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_juegos);
        btnIntentos = findViewById(R.id.btnIntentar);
        intentos = findViewById(R.id.idIntentos);
        idDificultad = findViewById(R.id.idDificultad);
        idRango = findViewById(R.id.idRango);
        idJugador = findViewById(R.id.idJugador);
        aleatorio = findViewById(R.id.aleatorio);
        txtNumero = findViewById(R.id.txtNumero);
        idPuntos = findViewById(R.id.idPuntos);
        Terminar = findViewById(R.id.idTerminarIntentos);

        this.administradorPantalla = getWindowManager();
        datosN = getSharedPreferences(Numeros, MODE_PRIVATE);
        inten = Integer.valueOf(intentos.getText().toString());
    this.configuraciones = getSharedPreferences(Configuraciones.ARCHIVO, MODE_PRIVATE);



    conectarServicios();
        btnIntentos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idJugador.getText().toString().equals("")) {
                    if (!validarEntrada()) {
                        contador++;
                        intentos.setText(String.valueOf(contador));
                        dataDeJuego();
                        if (txtNumero.getText().toString().equals(aleatorio.getText().toString())) {
                            int s = 10;
                            puntos += s;
                            Toast.makeText(Juegos.this, "Respuesta Correcta!.", Toast.LENGTH_SHORT).show();
                            txtNumero.setText("");
                            idPuntos.setText(String.valueOf(puntos));
                            numeroAlazar();
                        } else {
                            Toast.makeText(Juegos.this, "Sigue intentado!.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        contador = contador;
                        intentos.setText(String.valueOf(contador));
                    }
                }else{
                    Toast.makeText(Juegos.this, "Debe crear un jugador en las configuracioens!.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        numeroAlazar();
        dataDeJuego();
        CargarConfiguraciones();
        Terminar.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!idJugador.getText().toString().equals("")){
                    String n = idJugador.getText().toString();
                    String p = idPuntos.getText().toString();
                    String in = intentos.getText().toString();
                    Intent msg = new Intent();
                    msg.putExtra(textoNombre, n);
                    msg.putExtra(textoPunto, p);
                    msg.putExtra(textoIntentos, in);
                    setResult(RESULT_OK, msg);
                    Toast.makeText(Juegos.this, "Informacion guardada Correcta!.", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Juegos.this, "Debe crear un jugador en las configuracioens!.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
    private Boolean validarEntrada(){
        boolean r = false;
        if(txtNumero.getText().toString().trim().equals("")){
            txtNumero.setError("¡Campo vacio!");
            r = true;
        }else{
            r = false;
        }
        return r;
}


    private void conectarServicios(){
        if(this.configuraciones != null){
            String jugador = this.configuraciones.getString(Configuraciones.KEY_JUGADOR,"");
            String tipo = this.configuraciones.getString(Configuraciones.KEY_Dificultad,"");
            idDificultad.setText(tipo);
            idJugador.setText(jugador);
        }
    }

    private void numeroAlazar(){
        if (idDificultad.getText().toString().equals("Facil")) {
            idRango.setText("1 y 50");
            int n = (int) (Math.random() * (1 - 50)) + 50;
            aleatorio.setText(String.valueOf(n));
        }else if(idDificultad.getText().toString().equals("Medio")){
            idRango.setText("1 y 100");
            int n = (int) (Math.random() * (1 - 100)) + 100;
            aleatorio.setText(String.valueOf(n));
        }else if(idDificultad.getText().toString().equals("Dificil")){
            idRango.setText("1 y 150");
            int n = (int) (Math.random() * (1 - 150)) + 150;
            aleatorio.setText(String.valueOf(n));

        }


    }

    public void onSaveInstanceState(Bundle estado){
        super.onSaveInstanceState(estado);
        //Guardamos el estado de las variables nativas
       datIn = intentos.getText().toString();
       datPun = idPuntos.getText().toString() ;
        estado.putString("DIntentos", datIn);
       estado.putString("DPuntajes",datPun);
    }
    public void onRestoreInstanceState(Bundle estadoAnterior){
        super.onRestoreInstanceState(estadoAnterior);
        //Recuperamos el estado antes del cambio
       datIn  = estadoAnterior.getString("DIntentos");
       datPun= estadoAnterior.getString("DPuntajes");
       idPuntos.setText(datPun);
       intentos.setText(datIn);
       puntos = Integer.valueOf(datPun);
       contador = Integer.valueOf(datIn);

    }
    private void CargarConfiguraciones(){
        if(this.configuraciones !=null){
            this.intentos.setText(this.configuraciones.getString(KEY_INTENTOS,""));
            this.idPuntos.setText(this.configuraciones.getString(KEY_PUNTOS,""));
        }
    }
    private void dataDeJuego(){
    SharedPreferences.Editor editor = this.configuraciones.edit();
    editor.putString(KEY_INTENTOS, this.intentos.getText().toString());
    editor.putString(KEY_PUNTOS,this.idPuntos.getText().toString());
    editor.commit();
   }


}