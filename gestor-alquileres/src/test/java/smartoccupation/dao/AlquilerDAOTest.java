package smartoccupation.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import smartoccupation.model.Alquiler;

class AlquilerDAOTest {

    private static AlquilerDAO alquilerDAO;

    @BeforeAll
    static void setup() {
        alquilerDAO = new AlquilerDAO();
    }

    @Test
    void testBuscarRangoConResultados() {
        List<Alquiler> lista = alquilerDAO.findByFechaEntradaBetween(
                "2025-12-01", "2025-12-31");

        assertNotNull(lista, "La lista no debe ser null");
        assertTrue(lista.size() > 0, "Debe devolver resultados");
    }

    @Test
    void testBuscarRangoSinResultados() {
        List<Alquiler> lista = alquilerDAO.findByFechaEntradaBetween(
                "2020-01-01", "2020-01-31");

        assertNotNull(lista);
        assertEquals(0, lista.size(), "Debe devolver lista vacía");
    }

    @Test
    void testFechaInicioMayorQueFechaFin() {
        List<Alquiler> lista = alquilerDAO.findByFechaEntradaBetween(
                "2025-12-31", "2025-12-01");

        assertNotNull(lista);
        assertEquals(0, lista.size(), "Debe devolver lista vacía si el rango es inválido");
    }

    @Test
    void testFechasExactasCoinciden() {
        List<Alquiler> lista = alquilerDAO.findByFechaEntradaBetween(
                "2025-12-10", "2025-12-10");

        assertNotNull(lista);
        assertEquals(1, lista.size(), "Debe devolver exactamente 1 alquiler");
        assertEquals("EXP-003", lista.get(0).getNumeroExpediente());
    }
}
