package com.eroshenkova.conference.database.dao.user;

import com.eroshenkova.conference.entity.User;

public interface UserDAO {
    User findByEmail(String email);
}
