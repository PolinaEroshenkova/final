package com.eroshenkova.conference.database;

public interface DAO<K, T> {

    long create(T entity, boolean isKeyGenerated);

    boolean update(T entity, K key);

    boolean delete(K key);

    T findByKey(K key);
}
