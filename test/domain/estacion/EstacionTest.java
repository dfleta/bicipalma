package domain.estacion;

import static org.junit.Assert.*;
import org.junit.Test;

import domain.bicicleta.Bicicleta;

public class EstacionTest {

    @Test
    public void testAnclajesLibres() {
        // Crear una estación con 6 anclajes como en BiciPalma
        Estacion estacion = new Estacion(1, "Manacor", 6);
        
        // Comprobar que inicialmente todos los anclajes están libres
        assertEquals(6, estacion.anclajesLibres());

        // Anclar una bicicleta
        Bicicleta bici = new Bicicleta(1);
        estacion.anclarBicicleta(bici);

        // Comprobar que hay un anclaje menos libre
        assertEquals(5, estacion.anclajesLibres());
    }
} 