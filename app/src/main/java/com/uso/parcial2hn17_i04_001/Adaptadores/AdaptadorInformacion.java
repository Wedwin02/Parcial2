package com.uso.parcial2hn17_i04_001.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uso.parcial2hn17_i04_001.R;

import java.util.List;

public class AdaptadorInformacion extends RecyclerView.Adapter<viewHolderInformacion>  {
   private List<Jugador> listaJugador;

    public AdaptadorInformacion(List<Jugador> data) {
        this.listaJugador = data;
    }

    @NonNull
    @Override
    public viewHolderInformacion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_lista,parent,false);
        viewHolderInformacion vhi = new viewHolderInformacion(vista);
        return vhi;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderInformacion holder, int position) {
        holder.getLblJugadorNombre().setText(this.listaJugador.get(position).getJugadorname());
        holder.getLblJugadorPuntos().setText(this.listaJugador.get(position).getJugadorpuntos());
        holder.getLblJugadorIntentos().setText(this.listaJugador.get(position).getJugadorIntentos());
    }

    @Override
    public int getItemCount() {
        return  this.listaJugador.size();
    }
}
