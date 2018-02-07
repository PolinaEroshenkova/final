package com.eroshenkova.conference.database;

import com.eroshenkova.conference.exception.DAOException;

public interface DAO<K, T> {

    long create(T entity, boolean isKeyGenerated) throws DAOException;

    void update(T entity, K key) throws DAOException;

    void delete(K key) throws DAOException;

    T findByKey(K key) throws DAOException;
}
