package db;

import db.dao.DAOCommandEnum;

public interface DAO<K, T> {
    boolean execute(DAOCommandEnum command, T entity);

    T findByKey(K key);
}
