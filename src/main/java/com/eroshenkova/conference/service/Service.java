package com.eroshenkova.conference.service;


import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;

/**
 * Used to define general service methods
 *
 * @param <K> is a entity key
 * @param <T> is entity
 * @author Palina Yerashenkava
 */
public interface Service<K, T> {

    /**
     * Used to register new T entity
     * @param entity is T entity
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    void register(T entity) throws ServiceException, DAOException;

    /**
     * Used to delete T entity by K key
     * @throws ServiceException thrown when general service layer exception occurred
     * @throws DAOException thrown when database throw exception
     */
    void delete(K key) throws ServiceException, DAOException;
}
