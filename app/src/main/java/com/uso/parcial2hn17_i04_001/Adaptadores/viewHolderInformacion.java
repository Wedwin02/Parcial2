package com.uso.parcial2hn17_i04_001.Adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uso.parcial2hn17_i04_001.R;

public class viewHolderInformacion extends RecyclerView.ViewHolder {

    private TextView lblJugadorNombre;
    private TextView lblJugadorPuntos;
    private  TextView lblJugadorIntentos;
    public viewHolderInformacion(@NonNull View itemView) {
        super(itemView);

        this.lblJugadorNombre = itemView.findViewById(R.id.idJuegoNombre);
        this.lblJugadorPuntos = itemView.findViewById(R.id.idJuegoPuntos);
        this.lblJugadorIntentos = itemView.findViewById(R.id.idJuegoIntentos);
    }

    public TextView getLblJugadorNombre() {
        return lblJugadorNombre;
    }

    public TextView getLblJugadorPuntos() {
        return lblJugadorPuntos;
    }

    public TextView getLblJugadorIntentos() {
        return lblJugadorIntentos;
    }

    public void setLblJugadorIntentos(TextView lblJugadorIntentos) {
        this.lblJugadorIntentos = lblJugadorIntentos;
    }
}
