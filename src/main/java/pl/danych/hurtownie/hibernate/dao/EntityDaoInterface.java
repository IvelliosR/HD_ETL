package pl.danych.hurtownie.hibernate.dao;

import java.util.List;

/**
 * Created by Arek on 23.12.2016.
 */
public interface EntityDaoInterface<T, R> {
    void persist(T entity);
    void update(T entity);
    T findById(int id);
    void delete(T entity);
    List<T> findAll();
    void deleteAll();
    void deleteAll(List<R> entities);

}