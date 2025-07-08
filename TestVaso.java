package com.maquinacafe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestVaso {

    @Test
    void shouldReturnInitialQuantityOfSmallCups() {
        Vaso vasosPequenos = new Vaso(10, 3); // 10 vasos, 3 Oz de contenido
        assertEquals(10, vasosPequenos.getCantidadVasos()); // Afirmar cantidad inicial
    }

    @Test
    void shouldDiscountCupsWhenGiven() {
        Vaso vasos = new Vaso(5, 3);
        vasos.giveVasos(1); // Dar 1 vaso
        assertEquals(4, vasos.getCantidadVasos()); // Esperar 4 vasos restantes
    }

    @Test
    void shouldCheckIfHasEnoughCups() {
        Vaso vasos = new Vaso(5, 3);
        assertTrue(vasos.hasVasos(3)); // Debería tener suficientes para 3 vasos
        assertFalse(vasos.hasVasos(7)); // No debería tener suficientes para 7 vasos
    }

    @Test
    void shouldReturnContentOfCup() {
        Vaso vasoPequeno = new Vaso(1, 3); // Contenido de vaso pequeño es 3 Oz
        assertEquals(3, vasoPequeno.getContenido());

        Vaso vasoMediano = new Vaso(1, 5); // Contenido de vaso mediano es 5 Oz
        assertEquals(5, vasoMediano.getContenido());

        Vaso vasoGrande = new Vaso(1, 7); // Contenido de vaso grande es 7 Oz
        assertEquals(7, vasoGrande.getContenido());
    }

    @Test
    void shouldNotGiveCupsIfQuantityIsNotAvailable() {
        Vaso vasos = new Vaso(2, 3);
        vasos.giveVasos(5); // Intentar dar más de lo disponible
        assertEquals(2, vasos.getCantidadVasos()); // La cantidad debe permanecer sin cambios
    }
}