package com.eroshenkova.conference.database;

import com.eroshenkova.conference.exception.DAOException;

/**
 * Defines simple CRUD operations for DAO pattern
 *
 * @param <K> is used as key
 * @param <T> is used as database entity
 */
public interface DAO<K, T> {

    /**
     * @param entity is database entity for inserting to database
     * @param isKeyGenerated is used for specification the action of the insert statement.
     *                       If table after inserting entity generate key such as id, this value
     *                       would be returned
     * @return zero if inserting completed successfully and keys were not generated.
     *                       If keys generated return value of generated key.
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    long create(T entity, boolean isKeyGenerated) throws DAOException;

    /**
     * @param entity is database entity for updating in database
     * @param key is key value of the update statement
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    void update(T entity, K key) throws DAOException;

    /**
     * @param key is key value of the deleted statement
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    void delete(K key) throws DAOException;

    /**
     * @param key is key value of the selected statement
     * @return found entity or null if entity wasn't found by key value
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    T findByKey(K key) throws DAOException;
}
