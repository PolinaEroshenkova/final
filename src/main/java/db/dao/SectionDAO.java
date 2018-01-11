package db.dao;

import db.AbstractDAO;
import entity.Section;

import java.sql.Connection;

public class SectionDAO extends AbstractDAO<Integer, Section> {

    public SectionDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Section findEntityByKey(Integer key) {
        return null;
    }

    @Override
    public boolean create(Section entity) {
        return false;
    }
}
