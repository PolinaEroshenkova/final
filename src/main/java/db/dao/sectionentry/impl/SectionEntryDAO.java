package db.dao.sectionentry.impl;

import db.dao.AbstractDAO;
import db.dao.sectionentry.entity.SectionEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SectionEntryDAO extends AbstractDAO<List<Long>, SectionEntry> {
    private static final String SQL_SET_VARIABLE = "SET @identry=LAST_INSERT_ID()";
    private static final String SQL_CREATE = "INSERT INTO sectionentry VALUES(?,@identry)";
    private static final String SQL_DELETE = "DELETE FROM sectionentry WHERE id_entry=?";


    @Override
    public SectionEntry parseResultset(ResultSet resultSet) throws SQLException {
        long idsection = resultSet.getLong("id_section");
        long identry = resultSet.getLong("id_entry");
        return new SectionEntry(identry, idsection);
    }

    @Override
    public PreparedStatement receiveFindByKeyStatement(Connection connection, List<Long> key) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PreparedStatement receiveCreateStatement(Connection connection, SectionEntry entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SET_VARIABLE);
        statement.execute();
        statement = connection.prepareStatement(SQL_CREATE);
        statement.setLong(1, entity.getIdsection());
        return statement;
    }

    @Override
    public PreparedStatement receiveUpdateStatement(Connection connection, SectionEntry entity, List<Long> key) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PreparedStatement receiveDeleteStatement(Connection connection, SectionEntry entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
        statement.setLong(1, entity.getIdentry());
        return statement;
    }


}
