package domain.bicicleta;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BicicletaTest {

    private static Bicicleta bici = null;

    @Before
    public void setup_bicicleta() {
        // ARRANGE
        bici = new Bicicleta(101);
    }
    
    @Test
    public void test_constructor_bicicleta() {
        // ARRANGE
        int idExpected = 101;
        // ACT
        int id = bici.getId();
        // ASSERT
        assertEquals(idExpected, id);
    }

    @Test
    public void test_toString_bicicleta() {
        // ARRANGE
        String id = "101";
        // ACT
        String representacionIdBici = bici.toString();
        // ASSERT
        assertEquals(id, representacionIdBici);
    }
}
