package com.eroshenkova.conference.service;


import com.eroshenkova.conference.exception.DAOException;
import com.eroshenkova.conference.exception.ServiceException;

public interface Service<K, T> {

    void register(T entity) throws ServiceException, DAOException;

    void delete(K key) throws ServiceException, DAOException;
}
