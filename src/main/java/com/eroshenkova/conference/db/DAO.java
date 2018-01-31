package com.eroshenkova.conference.db;

import com.eroshenkova.conference.db.dao.DAOCommandEnum;

public interface DAO<K, T> {
    boolean execute(DAOCommandEnum command, T entity);

    int insertWithGeneratedKeys(T entity);

    T findByKey(K key);
}
