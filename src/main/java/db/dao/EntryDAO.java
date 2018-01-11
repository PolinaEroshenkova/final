package db.dao;

import db.AbstractDAO;
import entity.Entry;

import java.sql.Connection;

public class EntryDAO extends AbstractDAO<Integer, Entry> {
    //private final static String SQL_FIND_BY_LOGIN="SELECT * FROM entry where login=?";
    private final static String SQL_FIND_ENTRY_CONFERENCE_BY_LOGIN = "SELECT conference.topic, entry.status\n" +
            "FROM entry\n" +
            "INNER JOIN sectionentry ON sectionentry.id_entry=entry.id_entry\n" +
            "INNER JOIN section ON section.id_section=sectionentry.id_section\n" +
            "INNER JOIN conference ON conference.id_conference=section.id_conference\n" +
            "WHERE entry.login=?\n" +
            "GROUP BY entry.id_entry";

    public EntryDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Entry findEntityByKey(Integer key) {
        return null;
    }

    @Override
    public boolean create(Entry entity) {
        return false;
    }

//    public List<Entry> findUsersConferences(String login){
//        List<Entry> entries=null;
//        Connection connection=super.getConnection();
//        PreparedStatement statement=null;
//        try{
//            statement=connection.prepareStatement(SQL_FIND_ENTRY_CONFERENCE_BY_LOGIN);
//            statement.setString(1,login);
//            ResultSet resultSet=statement.executeQuery();
//            entries=new ArrayList<>();
//            while (resultSet.next()){
//
//            }
//        }
//        catch (SQLException e){
//            //LOGGER
//        }
//    }


//    public List<Entry> findByLogin(String login){
//        List<Entry> entries=null;
//        Connection connection = super.getConnection();
//        PreparedStatement statement=null;
//        try {
//            statement=connection.prepareStatement(SQL_FIND_BY_LOGIN);
//            statement.setString(1,login);
//            ResultSet resultSet=statement.executeQuery();
//            entries=new ArrayList<>();
//            while (resultSet.next()){
//                long id=resultSet.getInt("id_entry");
//                String status=resultSet.getString("status");
//                Entry entry=new Entry(id,login,status);
//                entries.add(entry);
//            }
//        } catch (SQLException e) {
//            //LOGGER
//        }
//        return entries;
//    }

}
