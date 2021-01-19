package desserts;

import java.util.List;

public interface GenericDAO<T> {

    List<T> getAll();

    T create(T entity);

    void remove(T entity);

    void remove(Long id);

    T update(T entity);

    int count();

}
