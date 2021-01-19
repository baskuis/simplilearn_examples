package desserts;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DessertDAOImplTest {

    DessertDAOImpl dessertDAO = new DessertDAOImpl();

    @Test
    void iHaveANonNullDAO() {
        assertNotNull(dessertDAO);
    }

    @Test
    void canIGetDesserts() {

        // when
        List<DessertDTO> desserts = dessertDAO.getAll();

        // then
        assertNotNull(desserts);
        assertTrue(desserts.size() > 0);

        Runnable hi = () -> {
            System.out.println("I can eat deserts");
        };
    }

    @Test
    void canIUpdateADessert() {

        // setup
        DessertDTO toUpdate = new DessertDTO(1L, "Was good", true);
        dessertDAO.create(toUpdate);

        // when
        toUpdate.setGood(false);
        dessertDAO.update(toUpdate);
        List<DessertDTO> desserts = dessertDAO.getAll();
        DessertDTO updatedDesert = null;
        for (DessertDTO newDesert : desserts) {
            if (newDesert.getName().equals("Was good")) {
                updatedDesert = newDesert;
            }
        }

        // then
        assertNotNull(updatedDesert);
        assertFalse(updatedDesert.isGood());

    }

}