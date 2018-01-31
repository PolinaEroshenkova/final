package com.eroshenkova.conference.db.dao.user;

import com.eroshenkova.conference.db.dao.user.entity.User;

public interface IUserDAO {
    User findByEmail(String email);
}
