package db;

import entity.User;

import java.util.ArrayList;

public class UserDAO implements DAO<User> {

    @Override
    public boolean create(User entity) {

        return false;
    }

    @Override
    public ArrayList<User> find() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }
}
