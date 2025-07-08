package com.maquinacafe;

public class Cafetera {
    private int cantidadCafe; // en Oz

    public Cafetera(int cantidadCafe) {
        this.cantidadCafe = cantidadCafe;
    }

    public int getCantidadDeCafe() {
        return cantidadCafe;
    }

    public void setCantidaDeCafe(int cantidadCafe) {
        this.cantidadCafe = cantidadCafe;
    }

    public boolean hasCafe(int cantidad) {
        return this.cantidadCafe >= cantidad;
    }

    public void giveCafe(int cantidad) {
        if (hasCafe(cantidad)) {
            this.cantidadCafe -= cantidad;
        } else {
            // En una aplicación real, podrías lanzar una excepción o registrar un error.
            System.err.println("Error: No hay suficiente café para dispensar.");
        }
    }
}