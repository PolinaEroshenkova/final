package db;

import java.util.ArrayList;

public interface DAO<T> {
    boolean create(T entity);

    ArrayList<T> find();

    boolean delete();

    boolean update();
}
