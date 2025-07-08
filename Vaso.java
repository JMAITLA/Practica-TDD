package com.maquinacafe;

public class Vaso {
    private int cantidadVasos;
    private int contenido; // en Oz

    public Vaso(int cantidadVasos, int contenido) {
        this.cantidadVasos = cantidadVasos;
        this.contenido = contenido;
    }

    public int getCantidadVasos() {
        return cantidadVasos;
    }

    public void setCantidadVasos(int cantidadVasos) {
        this.cantidadVasos = cantidadVasos;
    }

    public int getContenido() {
        return contenido;
    }

    public void setContenido(int contenido) {
        this.contenido = contenido;
    }

    public boolean hasVasos(int cantidad) {
        return this.cantidadVasos >= cantidad;
    }

    public void giveVasos(int cantidad) {
        if (hasVasos(cantidad)) {
            this.cantidadVasos -= cantidad;
        } else {
            // En una aplicación real, podrías lanzar una excepción o registrar un error.
            System.err.println("Error: No hay suficientes vasos para dispensar.");
        }
    }
}