package domain.estacion;

import static org.junit.Assert.*;
import org.junit.Test;

import domain.bicicleta.Bicicleta;
import domain.bicicleta.Movil;
import domain.tarjetausuario.Autenticacion;
import domain.tarjetausuario.TarjetaUsuario;

public class EstacionTest {

    @Test
    public void testAnclajesLibres() {
        // Crear una estación con 6 anclajes como en BiciPalma
        Estacion estacion = new Estacion(1, "Manacor", 6);
        
        // Comprobar que todos los anclajes están libres
        assertEquals(6, estacion.anclajesLibres());
    }


    @Test
    public void testAnclajesLibresyBiciAnclada() {
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

    @Test
    public void testConsultarEstacion() {
        // Arrange
        Estacion estacion = new Estacion(1, "Manacor", 6);
        String representacionEsperada = "id: 1\n" +
                              "direccion: Manacor\n" +
                              "numeroAnclajes: 6";
        assertEquals(representacionEsperada, estacion.toString());
    }

    @Test
    public void testAnclarBicicleta() {
        // ARRANGE
        Estacion estacion = new Estacion(1, "Calle Test", 5);
        Movil bicicleta = new Bicicleta(42);
        int anclajesLibresInicial = estacion.anclajesLibres();
        
        // ACT
        estacion.anclarBicicleta(bicicleta);
        
        // ASSERT
        assertEquals(anclajesLibresInicial - 1, estacion.anclajesLibres());
    }

    @Test
    public void testAnclarBicicletaEstacionLlena() {
        // ARRANGE
        Estacion estacion = new Estacion(1, "Calle Test", 2);
        Movil bici1 = new Bicicleta(1);
        Movil bici2 = new Bicicleta(2);
        Movil bici3 = new Bicicleta(3);
        
        // ACT
        estacion.anclarBicicleta(bici1);
        estacion.anclarBicicleta(bici2);
        estacion.anclarBicicleta(bici3); // Intentar anclar en estación llena
        
        // ASSERT
        assertEquals(0, estacion.anclajesLibres());
    }

    @Test
    public void testAnclarBicicletaNull() {
        // ARRANGE
        Estacion estacion = new Estacion(1, "Calle Test", 1);
        int anclajesLibresInicial = estacion.anclajesLibres();
        
        // ACT
        estacion.anclarBicicleta(null);
        
        // ASSERT
        assertEquals(anclajesLibresInicial, estacion.anclajesLibres());
    }

    @Test
    public void testAnclarVariasBicicletas() {
        // ARRANGE
        Estacion estacion = new Estacion(1, "Calle Test", 3);
        Movil bici1 = new Bicicleta(1);
        Movil bici2 = new Bicicleta(2);
        
        // ACT
        estacion.anclarBicicleta(bici1);
        estacion.anclarBicicleta(bici2);
        
        // ASSERT
        assertEquals(1, estacion.anclajesLibres());
    }

    @Test
    public void testLeerTarjetaUsuarioActivada() {
        // Arrange
        Estacion estacion = new Estacion(1, "Calle Test", 1);
        Autenticacion tarjeta = new TarjetaUsuario("0099887766", true);     
        // Act
        boolean resultado = estacion.leerTarjetaUsuario(tarjeta);
        // Assert
        assertTrue(resultado);
    }

    @Test
    public void tesLeerTarjetaUsuarioDesactivada() {
        // Arrange
        Estacion estacion = new Estacion(1, "Calle Test", 1);
        Autenticacion tarjeta = new TarjetaUsuario("0099887766", false);             
        // Act
        boolean resultado = estacion.leerTarjetaUsuario(tarjeta);
        // Assert
        assertFalse(resultado);
    }

    @Test
    public void testRetirarBicicleta() {
        // Crear estación con 1 anclaje
        Estacion estacion = new Estacion(1, "Calle Test", 3);
        
        // Crear bicicleta y anclarla
        Movil bici = new Bicicleta(101);
        estacion.anclarBicicleta(bici);
        
        // Caso 1: Tarjeta inactiva
        TarjetaUsuario tarjetaInactiva = new TarjetaUsuario("000", false);
        estacion.retirarBicicleta(tarjetaInactiva);
        // Verificar que la bici sigue anclada (anclajesLibres = 0)
        assertEquals(2, estacion.anclajesLibres());
        
        // Caso 2: Tarjeta activa
        TarjetaUsuario tarjetaActiva = new TarjetaUsuario("111", true);
        estacion.retirarBicicleta(tarjetaActiva);
        // Verificar que la bici fue retirada (anclajesLibres = 1)
        assertEquals(3, estacion.anclajesLibres());
    }
} 