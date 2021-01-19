package desserts;

import java.util.List;

public interface GenericDAO<T> {

    List<T> getAll(); //GET (Http Verbs/Methods)

    T create(T entity); //POST (Http Verbs/Methods)

    void remove(T entity); //DELETE (Http Verbs/Methods)

    void remove(Long id); //DELETE (Http Verbs/Methods)

    T update(T entity); //PUT (Http Verbs/Methods)

    int count();

}
