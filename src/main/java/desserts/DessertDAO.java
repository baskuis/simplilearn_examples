package desserts;

public interface DessertDAO extends GenericDAO<DessertDTO> {

    boolean isGood(Long id);

}
