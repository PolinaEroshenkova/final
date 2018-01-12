package db;

public interface DAO<K, T> {
    boolean create(T entity);

    T findByKey(K key);
}
