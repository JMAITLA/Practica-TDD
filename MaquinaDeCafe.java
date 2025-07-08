package com.maquinacafe;

public class MaquinaDeCafe {
    private Cafetera cafe;
    private Vaso vasosPequenos;
    private Vaso vasosMedianos;
    private Vaso vasosGrandes;
    private Azucarero azucar;

    // Constructor (opcional, puede inicializarse vía setters para flexibilidad en los tests)
    public MaquinaDeCafe() {
        // Inicialización por defecto o depender de los setters
    }

    // Setters para las dependencias (útiles para inyección de dependencias, especialmente en tests)
    public void setCafetera(Cafetera cafe) {
        this.cafe = cafe;
    }

    public void setVasosPequenos(Vaso vasosPequenos) {
        this.vasosPequenos = vasosPequenos;
    }

    public void setVasosMedianos(Vaso vasosMedianos) {
        this.vasosMedianos = vasosMedianos;
    }

    public void setVasosGrandes(Vaso vasosGrandes) {
        this.vasosGrandes = vasosGrandes;
    }

    public void setAzucarero(Azucarero azucar) {
        this.azucar = azucar;
    }

    // Método para obtener un tipo de vaso específico
    public Vaso getTipoVaso(String tipoDeVaso) {
        if ("pequeno".equalsIgnoreCase(tipoDeVaso)) {
            return vasosPequenos;
        } else if ("mediano".equalsIgnoreCase(tipoDeVaso)) {
            return vasosMedianos;
        } else if ("grande".equalsIgnoreCase(tipoDeVaso)) {
            return vasosGrandes;
        }
        return null; // Debería lanzar una excepción para tipos inválidos en un sistema robusto
    }

    // Método principal para dispensar café
    public String getVasoDeCafe(String tipoDeVaso, int cantidadDeVasos, int cantidadDeAzucar) {
        Vaso vasoSeleccionado = getTipoVaso(tipoDeVaso);

        if (vasoSeleccionado == null) {
            return "Tipo de vaso no válido.";
        }

        // 1. Verificar vasos
        if (!vasoSeleccionado.hasVasos(cantidadDeVasos)) {
            return "No hay vasos de " + tipoDeVaso + " disponibles.";
        }

        // Calcular café necesario según el contenido del vaso
        int coffeeNeeded = vasoSeleccionado.getContenido() * cantidadDeVasos;

        // 2. Verificar café
        if (!cafe.hasCafe(coffeeNeeded)) {
            return "No hay suficiente cafe.";
        }

        // 3. Verificar azúcar (solo si se solicita azúcar)
        if (cantidadDeAzucar > 0 && !azucar.hasAzucar(cantidadDeAzucar)) {
            return "No hay suficiente azucar.";
        }

        // Si todas las verificaciones pasan, dispensar recursos
        vasoSeleccionado.giveVasos(cantidadDeVasos);
        cafe.giveCafe(coffeeNeeded);
        if (cantidadDeAzucar > 0) { // Solo dispensar azúcar si se pidió
            azucar.giveAzucar(cantidadDeAzucar);
        }

        return "Dispensando vaso con cafe, con " + cantidadDeAzucar + " cucharadas de azucar";
    }
}