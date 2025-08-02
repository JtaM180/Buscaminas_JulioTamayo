package consolaBuscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class buscaminastestTest {

    @Test
    public void testCasillaMarcaYDesmarca() {
        Casilla c = new Casilla();
        assertFalse(c.estaMarcada());
        c.toggleMarca();
        assertTrue(c.estaMarcada());
    }

    @Test
    public void testCasillaDescubrir() {
        Casilla c = new Casilla();
        assertFalse(c.estaDescubierta());
        c.descubrir();
        assertTrue(c.estaDescubierta());
    }

    @Test
    public void testTableroGetCasilla() {
        Tablero tablero = new Tablero();
        Casilla c = tablero.getCasilla(0, 0);
        assertNotNull(c);
    }

    @Test
    public void testTableroGanar() {
        Tablero tablero = new Tablero();
        // Simula el tablero como ya descubierto todo menos minas
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (!tablero.getCasilla(i, j).tieneMina())
                    tablero.getCasilla(i, j).descubrir();
        assertTrue(tablero.haGanado());
    }
}
