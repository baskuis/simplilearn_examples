package desserts;

import java.util.List;

public interface GenericDAO<T> {

    List<T> getDesserts();

    T create(T dessert);

    void eat(T dessert);

    T update(T dessert);

    int count();

}
