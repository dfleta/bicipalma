package domain.estacion;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import domain.bicicleta.*;

public class AnclajesTest {

    private Anclajes anclajes;
    private static final int NUM_ANCLAJES = 3;

    @Before
    public void setUp() {
        anclajes = new Anclajes(NUM_ANCLAJES);
    }
    
    @Test
    public void test_anclaje_ocupado() {
        Movil bici = new Bicicleta(101);
        anclajes.ocuparAnclaje(2, bici);
        // Act
        boolean ocupado = anclajes.isAnclajeOcupado(2);
        // Assert
        assertTrue(ocupado);
    }

    @Test
    public void test_anclaje_no_ocupado() {
        // Act
        boolean ocupado = anclajes.isAnclajeOcupado(2);
        // Assert
        assertFalse(ocupado);
    }

    @Test
    public void testOcuparAnclaje() {
        // Given
        Bicicleta bici = new Bicicleta(101);
        int posicion = 0;
        // When
        anclajes.ocuparAnclaje(posicion, bici);
        // Then
        assertTrue(anclajes.isAnclajeOcupado(posicion));
        assertEquals(bici, anclajes.getBiciAt(posicion));
    }

    @Test
    public void testOcuparMultiplesAnclajes() {
        // Given
        Bicicleta bici1 = new Bicicleta(1);
        Bicicleta bici2 = new Bicicleta(2);  
        // When
        anclajes.ocuparAnclaje(0, bici1);
        anclajes.ocuparAnclaje(1, bici2);
        // Then
        assertTrue(anclajes.isAnclajeOcupado(0));
        assertTrue(anclajes.isAnclajeOcupado(1));
        assertFalse(anclajes.isAnclajeOcupado(2));
        assertEquals(bici1, anclajes.getBiciAt(0));
        assertEquals(bici2, anclajes.getBiciAt(1));
    }    

    @Test
    public void testLiberarAnclaje() {
        // Arrange
        Bicicleta bici = new Bicicleta(1);
        int posicion = 0;
        // Act - anclar la bici en el unico anclaje
        anclajes.ocuparAnclaje(posicion, bici);
        assertTrue(anclajes.isAnclajeOcupado(posicion));
        assertEquals(bici, anclajes.getBiciAt(posicion));
        // Act - liberar el anclaje
        anclajes.liberarAnclaje(posicion);
        // Assert
        assertFalse(anclajes.isAnclajeOcupado(posicion));
        assertNull(anclajes.getBiciAt(posicion));
    }
} 