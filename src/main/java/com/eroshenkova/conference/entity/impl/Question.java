package com.eroshenkova.conference.entity.impl;

import com.eroshenkova.conference.entity.Entity;

/**
 * Defines question entity of database.
 *
 * @author Palina Yerashenkava
 */
public class Question implements Entity {

    /**
     * Defines id of question
     */
    private long idquestion;

    /**
     * Defines login of user who asked question
     */
    private String login;

    /**
     * Defines question text
     */
    private String question;

    /**
     * Defines answer the question
     */
    private String answer;

    /**
     * Basic constructor for specifying database table data
     * @param idquestion is id of question
     * @param login is user's login
     * @param question is text of question
     * @param answer is answer for question
     */
    public Question(long idquestion, String login, String question, String answer) {
        this.idquestion = idquestion;
        this.login = login;
        this.question = question;
        this.answer = answer;
    }

    /**
     * Basic constructor specified table data except id
     * @param login is user's login
     * @param question is text of question
     * @param answer is answer for question
     */
    public Question(String login, String question, String answer) {
        this.login = login;
        this.question = question;
        this.answer = answer;
    }

    /**
     * Constructor for defining only login of user and text question
     * @param login is user's login
     * @param question is text of question
     */
    public Question(String login, String question) {
        this.login = login;
        this.question = question;
    }


    /**
     * Constructor defines question without answer
     * @param idquestion is id of question
     * @param login is user's login
     * @param question is text of question
     */
    public Question(long idquestion, String login, String question) {
        this.idquestion = idquestion;
        this.login = login;
        this.question = question;
    }

    /**
     * @return id of question
     */
    public long getIdquestion() {
        return idquestion;
    }

    /**
     * @param idquestion is id of question
     */
    public void setIdquestion(long idquestion) {
        this.idquestion = idquestion;
    }

    /**
     * @return user's login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login of user who asked question
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return question text
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question is question text
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return answer of question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer is question's answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Used for comparing two entities of entry
     * @param o object is used for comparing with this
     * @return true if objects are the same and false when objects differs
     * @see Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (idquestion != question1.idquestion) return false;
        if (login != null ? !login.equals(question1.login) : question1.login != null) return false;
        if (question != null ? !question.equals(question1.question) : question1.question != null) return false;
        return answer != null ? answer.equals(question1.answer) : question1.answer == null;
    }

    /**
     * Works in pair with equals.
     * @return number of hashcode for particular object
     * @see Object
     */
    @Override
    public int hashCode() {
        int result = (int) (idquestion ^ (idquestion >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

    /**
     * Transform conference object to string
     * @return string representation of conference object
     * @see Object
     */
    @Override
    public String toString() {
        return "Question{" +
                "idquestion=" + idquestion +
                ", login='" + login + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
