package db.dao.user;

import db.dao.user.entity.User;

public interface IUserDAO {
    User findByEmail(String email);
}
