package com.eroshenkova.conference.validation;

import com.eroshenkova.conference.entity.impl.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class ValidatorTest {
    private Validator validator = new Validator();

    @Test
    public void validateConference() {
        String topic = "Test topic";
        int number = 40;
        String place = "Test place";
        Date dateStart = new Date();
        Date dateEnd = new Date();
        Date deadline = new Date();
        Conference conference = new Conference(topic, number, place, dateStart, dateEnd, deadline);
        Assert.assertFalse(validator.validate(conference));
    }

    @Test
    public void validateConferenceWrongDate() {
        String topic = "Test topic";
        int number = 40;
        String place = "Test place";
        Date dateStart = null;
        Date dateEnd = new Date();
        Date deadline = new Date();
        Conference conference = new Conference(topic, number, place, dateStart, dateEnd, deadline);
        Assert.assertTrue(validator.validate(conference));
    }

    @Test
    public void validateConferenceWrongNumber() {
        String topic = "Test topic";
        int number = 1000000;
        String place = "Test place";
        Date dateStart = new Date();
        Date dateEnd = new Date();
        Date deadline = new Date();
        Conference conference = new Conference(topic, number, place, dateStart, dateEnd, deadline);
        Assert.assertTrue(validator.validate(conference));
    }

    @Test
    public void validateEntry() {
        String login = "test";
        Entry entry = new Entry(login);
        Assert.assertFalse(validator.validate(entry));
    }

    @Test
    public void validateEntryWrongLogin() {
        String login = null;
        Entry entry = new Entry(login);
        Assert.assertTrue(validator.validate(entry));
    }

    @Test
    public void validateParticipant() {
        String login = "login";
        String surname = "test";
        String name = "test";
        String scope = "test";
        Participant participant = new Participant(login, surname, name, scope);
        Assert.assertFalse(validator.validate(participant));
    }

    @Test
    public void validateParticipantWrongSurname() {
        String login = "login";
        String surname = null;
        String name = "test";
        String scope = "test";
        Participant participant = new Participant(login, surname, name, scope);
        Assert.assertTrue(validator.validate(participant));
    }

    @Test
    public void validateQuestion() {
        String login = "popo83";
        String questionText = "Test question";
        String answer = "Test answer";
        Question question = new Question(login, questionText, answer);
        Assert.assertFalse(validator.validate(question));
    }

    @Test
    public void validateQuestionWrongLogin() {
        String login = null;
        String questionText = "Test question";
        String answer = "Test answer";
        Question question = new Question(login, questionText, answer);
        Assert.assertTrue(validator.validate(question));
    }


    @Test
    public void validateUser() {
        String login = "new";
        String password = "123";
        String email = "email@mail.ru";
        User user = new User(login, password, email);
        Assert.assertFalse(validator.validate(user));
    }

    @Test
    public void validateUserWrongPassword() {
        String login = "new";
        String password = "";
        String email = "email@mail.ru";
        User user = new User(login, password, email);
        Assert.assertTrue(validator.validate(user));
    }

}
