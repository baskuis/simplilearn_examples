package desserts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static interface GetGood {
        boolean good(DessertDTO dessertDTO);
    }

    public static interface Convert<I,O> {
        O execute(I in);
    }

    public List<String> sortedByName() {

        Convert<DessertDTO, String> out = (DessertDTO dessertDTO) -> {
            return dessertDTO.name;
        };

        return desserts.stream()
                .sorted(DessertDTO::compareByName)
                .map(dessertDTO -> dessertDTO.name)
                .collect(Collectors.toList());
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
