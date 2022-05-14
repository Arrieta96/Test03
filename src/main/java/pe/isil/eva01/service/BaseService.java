package pe.isil.eva01.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {

    T save(T t);
    T update(T t);
    void delete(T t);

    List<T> getAll();
    Optional<T> findById(ID id);

}
