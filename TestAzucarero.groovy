package com.maquinacafe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestAzucarero {

    @Test
    void shouldReturnInitialQuantityOfSugar() {
        Azucarero azucarero = new Azucarero(50); // Cantidad inicial de azúcar
        assertEquals(50, azucarero.getCantidadDeAzucar()); // Afirmar cantidad inicial
    }

    @Test
    void shouldDiscountSugarWhenGiven() {
        Azucarero azucarero = new Azucarero(25);
        azucarero.giveAzucar(5); // Dar 5 unidades de azúcar
        assertEquals(20, azucarero.getCantidadDeAzucar()); // Esperar 20 unidades restantes
    }

    @Test
    void shouldCheckIfHasEnoughSugar() {
        Azucarero azucarero = new Azucarero(15);
        assertTrue(azucarero.hasAzucar(10)); // Debería tener suficiente para 10 unidades
        assertFalse(azucarero.hasAzucar(20)); // No debería tener suficiente para 20 unidades
    }

    @Test
    void shouldNotGiveSugarIfQuantityIsNotAvailable() {
        Azucarero azucarero = new Azucarero(3);
        azucarero.giveAzucar(5); // Intentar dar más de lo disponible
        assertEquals(3, azucarero.getCantidadDeAzucar()); // La cantidad debe permanecer sin cambios
    }
}