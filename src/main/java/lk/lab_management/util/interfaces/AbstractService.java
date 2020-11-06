package lk.lab_management.util.interfaces;


import java.util.List;

public interface AbstractService<E, I> {

   Object findAll();

    E findById(I id);

    E persist(E e);

    boolean delete(I id);

    List<E> search(E e);

}
