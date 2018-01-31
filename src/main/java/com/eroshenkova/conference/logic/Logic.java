package com.eroshenkova.conference.logic;

public interface Logic<K, T> {
    boolean create(T entity);

    T findByKey(K key);
}
