package pl.danych.hurtownie.hibernate.dao;

import java.util.List;

public interface EntityDaoInterface<T, R> {
    void persist(T entity);
    void update(T entity);
    List<T> findAll();
    List<R> findAllComments();
    void deleteAll(List<R> entities);

}