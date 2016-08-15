package com.github.signal2564.magorarest.services;

interface CrudService<T, ID> {
    Iterable<T> list();
    T get(ID id);
    T create(T object);
    T update(ID id, T object);
    void delete(ID id);
}
