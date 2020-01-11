package mate.academy.internetshop.service;

public interface GenericService<T, N> {
    T create(T entity);

    T get(N id);

    T update(T entity);

    boolean deleteById(N id);

    boolean deleteByEntity(T entity);
}
