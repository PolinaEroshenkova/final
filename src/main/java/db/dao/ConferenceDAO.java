package db.dao;

import db.AbstractDAO;
import entity.Conference;
import locale.DateWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConferenceDAO extends AbstractDAO<Integer, Conference> {
    private final static String SQL_FIND_BY_DATE = "SELECT * FROM conference WHERE deadline>=?";


    public ConferenceDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Conference findEntityByKey(Integer key) {
        return null;
    }

    @Override
    public boolean create(Conference entity) {
        return false;
    }

    public List<Conference> findByDate() {
        List<Conference> conferences = null;
        Connection connection = super.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_DATE);
            DateWorker dateWorker = new DateWorker();
            String dbdate = dateWorker.receiveDBformatDate();
            statement.setString(1, dbdate);
            ResultSet resultSet = statement.executeQuery();
            conferences = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_conference");
                String topic = resultSet.getString("topic");
                int number = resultSet.getInt("number_of_participants");
                String place = resultSet.getString("place");
                Date start = resultSet.getDate("date_start");
                Date end = resultSet.getDate("date_end");
                Date deadline = resultSet.getDate("deadline");
                Conference conference = new Conference(id, topic, number, place);
                conference.setBegin(dateWorker.receiveFormatDateByLocale(start));
                conference.setEnd(dateWorker.receiveFormatDateByLocale(end));
                conference.setDeadline(dateWorker.receiveFormatDateByLocale(deadline));
                conferences.add(conference);
            }
        } catch (SQLException e) {
            //LOGGER
        } finally {
            super.closeStatement(statement);
        }
        return conferences;
    }
}
