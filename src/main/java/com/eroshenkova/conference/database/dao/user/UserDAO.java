package com.eroshenkova.conference.database.dao.user;

import com.eroshenkova.conference.entity.User;
import com.eroshenkova.conference.exception.DAOException;

public interface UserDAO {
    User findByEmail(String email) throws DAOException;
}
