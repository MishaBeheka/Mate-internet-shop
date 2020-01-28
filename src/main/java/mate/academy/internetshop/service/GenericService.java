package mate.academy.internetshop.service;

import mate.academy.internetshop.exceptions.DataProcessingException;

public interface GenericService<T, N> {
    T create(T entity) throws DataProcessingException;

    T get(N id) throws DataProcessingException;

    T update(T entity) throws DataProcessingException;

    boolean deleteById(N id) throws DataProcessingException;

    boolean deleteByEntity(T entity) throws DataProcessingException;
}
