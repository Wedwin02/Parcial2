package com.uso.parcial2hn17_i04_001.Clases;

public class JugadorData {
    private String Puntos;
    private String Intento;



    public JugadorData(){
        this.setIntento("");
        this.setPuntos("");
    }


    public String getPuntos() {
        return Puntos;
    }

    public void setPuntos(String puntos) {
        Puntos = puntos;
    }

    public String getIntento() {
        return Intento;
    }

    public void setIntento(String intento) {
        Intento = intento;
    }
}
