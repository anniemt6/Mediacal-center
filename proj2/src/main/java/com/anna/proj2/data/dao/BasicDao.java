package com.anna.proj2.data.dao;

public interface BasicDao<T> {

    boolean create(T obj);

    boolean update(T obj);

    boolean delete(T obj);
}
