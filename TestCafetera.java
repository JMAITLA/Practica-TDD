package com.maquinacafe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCafetera {

    @Test
    void shouldReturnInitialQuantityOfCoffee() {
        Cafetera cafetera = new Cafetera(100); // Cantidad inicial de café
        assertEquals(100, cafetera.getCantidadDeCafe()); // Afirmar cantidad inicial
    }

    @Test
    void shouldDiscountCoffeeWhenGiven() {
        Cafetera cafetera = new Cafetera(50);
        cafetera.giveCafe(10); // Dar 10 Oz de café
        assertEquals(40, cafetera.getCantidadDeCafe()); // Esperar 40 Oz restantes
    }

    @Test
    void shouldCheckIfHasEnoughCoffee() {
        Cafetera cafetera = new Cafetera(30);
        assertTrue(cafetera.hasCafe(20)); // Debería tener suficiente para 20 Oz
        assertFalse(cafetera.hasCafe(40)); // No debería tener suficiente para 40 Oz
    }

    @Test
    void shouldNotGiveCoffeeIfQuantityIsNotAvailable() {
        Cafetera cafetera = new Cafetera(5);
        cafetera.giveCafe(10); // Intentar dar más de lo disponible
        assertEquals(5, cafetera.getCantidadDeCafe()); // La cantidad debe permanecer sin cambios
    }
}