package com.maquinacafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestMaquinaDeCafe {
    private MaquinaDeCafe maquinaDeCafe;
    private Vaso vasosPequenos;
    private Vaso vasosMedianos;
    private Vaso vasosGrandes;
    private Cafetera cafetera;
    private Azucarero azucarero;

    @BeforeEach
    void setUp() {
        // Inicializar los componentes para la máquina antes de cada test
        vasosPequenos = new Vaso(10, 3); // 10 vasos pequeños, 3 Oz de contenido
        vasosMedianos = new Vaso(10, 5); // 10 vasos medianos, 5 Oz de contenido
        vasosGrandes = new Vaso(10, 7); // 10 vasos grandes, 7 Oz de contenido
        cafetera = new Cafetera(100); // 100 Oz de café
        azucarero = new Azucarero(50); // 50 unidades de azúcar

        maquinaDeCafe = new MaquinaDeCafe();
        maquinaDeCafe.setVasosPequenos(vasosPequenos);
        maquinaDeCafe.setVasosMedianos(vasosMedianos);
        maquinaDeCafe.setVasosGrandes(vasosGrandes);
        maquinaDeCafe.setCafetera(cafetera);
        maquinaDeCafe.setAzucarero(azucarero);
    }

    @Test
    void shouldReturnSmallCupWhenRequested() {
        Vaso retrievedVaso = maquinaDeCafe.getTipoVaso("pequeno"); // Obtener vaso pequeño
        assertNotNull(retrievedVaso);
        assertEquals(vasosPequenos, retrievedVaso); // Asegurarse de que es la instancia correcta
        assertEquals(3, retrievedVaso.getContenido()); // El contenido del vaso pequeño es 3 Oz
    }

    @Test
    void shouldReturnMediumCupWhenRequested() {
        Vaso retrievedVaso = maquinaDeCafe.getTipoVaso("mediano"); // Obtener vaso mediano
        assertNotNull(retrievedVaso);
        assertEquals(vasosMedianos, retrievedVaso);
        assertEquals(5, retrievedVaso.getContenido()); // El contenido del vaso mediano es 5 Oz
    }

    @Test
    void shouldReturnLargeCupWhenRequested() {
        Vaso retrievedVaso = maquinaDeCafe.getTipoVaso("grande"); // Obtener vaso grande
        assertNotNull(retrievedVaso);
        assertEquals(vasosGrandes, retrievedVaso);
        assertEquals(7, retrievedVaso.getContenido()); // El contenido del vaso grande es 7 Oz
    }

    @Test
    void shouldReturnNullForInvalidCupType() {
        Vaso retrievedVaso = maquinaDeCafe.getTipoVaso("invalido");
        assertNull(retrievedVaso); // Tipo de vaso no válido debería retornar null
    }

    @Test
    void shouldDispenseCoffeeWithSugarWhenAllAvailable() {
        // El estado inicial del setUp() provee suficientes recursos
        String result = maquinaDeCafe.getVasoDeCafe("pequeno", 1, 2); // Solicitar 1 vaso pequeño, 2 de azúcar
        assertEquals("Dispensando vaso con cafe, con 2 cucharadas de azucar", result); // Mensaje de éxito esperado
        assertEquals(9, vasosPequenos.getCantidadVasos()); // Un vaso pequeño (3 Oz) dispensado
        assertEquals(97, cafetera.getCantidadDeCafe()); // 3 Oz de café dispensados para vaso pequeño
        assertEquals(48, azucarero.getCantidadDeAzucar()); // 2 unidades de azúcar dispensadas
    }

    @Test
    void shouldDispenseCoffeeWithoutSugarWhenRequested() {
        String result = maquinaDeafe.getVasoDeCafe("mediano", 1, 0); // Solicitar 1 vaso mediano, 0 de azúcar
        assertEquals("Dispensando vaso con cafe, con 0 cucharadas de azucar", result);
        assertEquals(9, vasosMedianos.getCantidadVasos());
        assertEquals(95, cafetera.getCantidadDeCafe()); // 5 Oz de café dispensados para vaso mediano
        assertEquals(50, azucarero.getCantidadDeAzucar()); // El azúcar debe permanecer sin cambios
    }

    @Test
    void shouldReturnErrorMessageIfNoCups() {
        vasosPequenos.setCantidadVasos(0); // Establecer vasos pequeños a cero

        String result = maquinaDeCafe.getVasoDeCafe("pequeno", 1, 2); // Solicitar 1 vaso pequeño
        assertEquals("No hay vasos de pequeno disponibles.", result); // Mensaje de error esperado
        assertEquals(0, vasosPequenos.getCantidadVasos()); // Los vasos no deben verse afectados
        assertEquals(100, cafetera.getCantidadDeCafe()); // El café no debe verse afectado
        assertEquals(50, azucarero.getCantidadDeAzucar()); // El azúcar no debe verse afectado
    }

    @Test
    void shouldReturnErrorMessageIfNoCoffee() {
        cafetera.setCantidaDeCafe(0); // Establecer café a cero

        String result = maquinaDeCafe.getVasoDeCafe("pequeno", 1, 2); // Solicitar 1 vaso pequeño
        assertEquals("No hay suficiente cafe.", result); // Mensaje de error esperado
        assertEquals(10, vasosPequenos.getCantidadVasos()); // Los vasos no deben verse afectados
        assertEquals(0, cafetera.getCantidadDeCafe()); // El café debe permanecer en cero
        assertEquals(50, azucarero.getCantidadDeAzucar()); // El azúcar no debe verse afectado
    }

    @Test
    void shouldReturnErrorMessageIfNoSugarWhenRequested() {
        azucarero.setCantidadDeAzucar(0); // Establecer azúcar a cero

        String result = maquinaDeCafe.getVasoDeCafe("pequeno", 1, 2); // Solicitar 1 vaso pequeño, 2 de azúcar
        assertEquals("No hay suficiente azucar.", result); // Mensaje de error esperado
        assertEquals(10, vasosPequenos.getCantidadVasos()); // Los vasos no deben verse afectados
        assertEquals(100, cafetera.getCantidadDeCafe()); // El café no debe verse afectado
        assertEquals(0, azucarero.getCantidadDeAzucar()); // El azúcar debe permanecer en cero
    }
}