package com.eroshenkova.conference.entity.impl;

import com.eroshenkova.conference.entity.Entity;

/**
 * Defines user entity of database.
 *
 * @author Palina Yerashenkava
 */
public class User implements Entity {

    /**
     * Defines login of user who asked question
     */
    private String login;

    /**
     * Defines user's password
     */
    private String password;

    /**
     * Defines user's email address
     */
    private String email;

    /**
     * Defines user's account type. Might be "user" or "admin"
     */
    private String type;

    /**
     * Defines participant data
     */
    private Participant participant;

    /**
     * Constructor for filling object with data from database without type
     * @param login is user's login
     * @param password is user's password
     * @param email is user's email
     */
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.type = "user";
    }

    /**
     * Constructor for filling object with data from database
     * @param login is user's login
     * @param password is user's password
     * @param email is user's email
     */
    public User(String login, String password, String email, String type) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    /**
     * Constructor for filling user only with login and password
     * @param login is user's login
     * @param password is user's password
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.type = "user";
    }

    /**
     * @return user's login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login is user's login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password is user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email is user's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return type of account
     */
    public String getType() {
        return type;
    }

    /**
     * @param type is user's account type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return user's participant data
     */
    public Participant getParticipant() {
        return participant;
    }

    /**
     * @param participant is user's participant data
     */
    public void setParticipant(Participant participant) {
        this.participant = participant;
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

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (type != null ? !type.equals(user.type) : user.type != null) return false;
        return participant != null ? participant.equals(user.participant) : user.participant == null;
    }

    /**
     * Works in pair with equals.
     * @return number of hashcode for particular object
     * @see Object
     */
    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (participant != null ? participant.hashCode() : 0);
        return result;
    }

    /**
     * Transform conference object to string
     * @return string representation of conference object
     * @see Object
     */
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", participant=" + participant +
                '}';
    }
}
