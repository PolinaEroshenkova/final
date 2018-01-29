package db;

import db.dao.DAOCommandEnum;

public interface DAO<K, T> {
    int execute(DAOCommandEnum command, T entity);

    T findByKey(K key);
}
