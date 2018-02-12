package com.eroshenkova.conference.database.dao;

import com.eroshenkova.conference.database.DAO;
import com.eroshenkova.conference.database.dao.participant.ParticipantDAO;
import com.eroshenkova.conference.entity.impl.Participant;
import com.eroshenkova.conference.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;

public class ParticipantDAOTest {

    @Test(expectedExceptions = DAOException.class)
    public void insertWithError() throws DAOException {
        DAO<String, Participant> dao = new ParticipantDAO();
        String login = null;
        String surname = "Ivanov";
        String name = "Ihar";
        String scope = "Computer science";
        String position = "Engineer";
        String company = "Intetics";
        Participant participant = new Participant(login, surname, name, scope, position, company);
        dao.create(participant, false);
    }

    @Test
    public void insert() throws DAOException {
        DAO<String, Participant> dao = new ParticipantDAO();
        String login = "test";
        String surname = "Ivanov";
        String name = "Ihar";
        String scope = "Computer science";
        String position = "Engineer";
        String company = "Intetics";
        Participant participant = new Participant(login, surname, name, scope, position, company);
        long result = dao.create(participant, false);
        long expected = 0;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void delete() throws DAOException {
        DAO<String, Participant> dao = new ParticipantDAO();
        String key = "popo83";
        dao.delete(key);
        Assert.assertNull(dao.findByKey(key));
    }

    @Test
    public void update() throws DAOException, ParseException {
        DAO<String, Participant> dao = new ParticipantDAO();
        String login = "test";
        String surname = "TESTSURNAME";
        String name = "Ihar";
        String scope = "Computer science";
        String position = "Engineer";
        String company = "Intetics";
        Participant participant = new Participant(login, surname, name, scope, position, company);
        dao.update(participant, null);
        Participant find = dao.findByKey(participant.getLogin());
        Assert.assertEquals(find, participant);
    }

    @Test
    public void findByKey() throws DAOException {
        DAO<String, Participant> dao = new ParticipantDAO();
        String key = "zver'";
        Participant find = dao.findByKey(key);
        Participant participant = new Participant("zver'", "Zverkiv", "Ihor", "Management", "Sales Manager", "SoftScience");
        Assert.assertEquals(find, participant);
    }
}
