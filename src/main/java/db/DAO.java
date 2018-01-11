package db;

import entity.Entity;

public interface DAO<K, T extends Entity> {
    boolean create(T entity);

    T findByKey(K key);
}
