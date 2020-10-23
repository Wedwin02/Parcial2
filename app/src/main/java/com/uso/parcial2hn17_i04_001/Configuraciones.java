package com.uso.parcial2hn17_i04_001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Configuraciones extends AppCompatActivity {
    public static final String ARCHIVO = "Configuracion";
    public static final String KEY_JUGADOR= "Jugador" ;
    public static final String KEY_Dificultad = "Dificultad" ;
    private EditText txtJugador;
    private RadioButton rbFacil;
    private RadioButton rbMedio;
    private RadioButton rbDificil;
    private  String tipo = "";
    private SharedPreferences configuraciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);
        //orientacion verical
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        configuraciones = getSharedPreferences(ARCHIVO, MODE_PRIVATE);
        txtJugador = findViewById(R.id.txtJugador);
        rbFacil = findViewById(R.id.rbFacil);
        rbMedio = findViewById(R.id.rbMedio);
        rbDificil = findViewById(R.id.rbDificil);

        this.rbFacil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    tipo = "Facil";
                }
            }
        });
        this.rbMedio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    tipo = "Medio";
                }
            }
        });
        this.rbDificil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    tipo = "Dificil";
                }
            }
        });
        CargarConfiguraciones();
    }

    private void CargarConfiguraciones(){
        if(this.configuraciones !=null){
            this.txtJugador.setText(this.configuraciones.getString(KEY_JUGADOR,""));
            this.tipo.equals(this.configuraciones.getString(KEY_Dificultad,""));


        }
    }

    public void btnGuardar_onClick(View v){
        if(configuraciones != null){
            if(validarConfiguraciones()){
                SharedPreferences.Editor editor = this.configuraciones.edit();
                editor.putString(KEY_JUGADOR, this.txtJugador.getText().toString());
                editor.putString(KEY_Dificultad,tipo);
                if(editor.commit()){
                    Toast.makeText(this, "Configuración Guardada con Éxito", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "Error al guardar la Configuración", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Es necesario establecer los valores de configuración", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean validarConfiguraciones(){
        if(txtJugador.getText().toString().trim() != "" && !tipo.equals("")){
            return true;
        }else{
            return false;
        }
    }


}