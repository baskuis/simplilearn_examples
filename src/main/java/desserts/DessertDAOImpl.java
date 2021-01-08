package desserts;

import java.util.ArrayList;
import java.util.List;

public class DessertDAOImpl implements GenericDAO<DessertDTO> {

    List<DessertDTO> desserts = new ArrayList<>();

    public DessertDAOImpl() {
        desserts.add(new DessertDTO("Tira Misu", true));
        desserts.add(new DessertDTO("Petit Four", true));
        desserts.add(new DessertDTO("Cheesecake", true));
        desserts.add(new DessertDTO("Creme Brule", true));
        desserts.add(new DessertDTO("Bonbons", true));
        desserts.add(new DessertDTO("Brownies", true));
    }

    @Override
    public List<DessertDTO> getDesserts() {
        return desserts;
    }

    @Override
    public DessertDTO create(DessertDTO dessert) {
        desserts.add(dessert);
        return dessert;
    }

    @Override
    public void eat(DessertDTO dessert) {
        desserts.remove(dessert);
    }

    @Override
    public DessertDTO update(DessertDTO dessert) {
        return null;
    }

    @Override
    public int count() {
        return desserts.size();
    }

}
