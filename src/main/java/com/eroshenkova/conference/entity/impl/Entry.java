package com.eroshenkova.conference.entity.impl;

import com.eroshenkova.conference.entity.Entity;

/**
 * Defines entry entity of database.
 *
 * @author Palina Yerashenkava
 */
public class Entry implements Entity {

    /**
     * Id of entry
     */
    private long identry;

    /**
     * Login of user
     */
    private String login;

    /**
     * Status of user's entry
     */
    private String status;

    /**
     * Conference in entry
     */
    private Conference conference;

    /**
     * User object specified for particular entry
     */
    private User user;


    /**
     * Basic constructor for specifying database table data
     * @param identry id of entry
     * @param login of user whom belongs specified entry
     * @param status is entry's status
     */
    public Entry(long identry, String login, String status) {
        this.identry = identry;
        this.login = login;
        this.status = status;
    }

    /**
     * Defines only login
     * @param login of user whom belongs specified entry
     */
    public Entry(String login) {
        status = "Waiting";
        this.login = login;
    }

    /**
     * @return id of entry
     */
    public long getIdentry() {
        return identry;
    }

    /**
     * @param identry is id oj entry
     */
    public void setIdentry(long identry) {
        this.identry = identry;
    }

    /**
     * @return user's login whom belongs entry
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login is user's login whom belongs entry
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return status of entry
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status is status of entry
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return conference specified in entry
     */
    public Conference getConference() {
        return conference;
    }

    /**
     * @param conference is conference specified in entry
     */
    public void setConference(Conference conference) {
        this.conference = conference;
    }

    /**
     * @return user whom belongs entry
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user is user whom belongs entry
     */
    public void setUser(User user) {
        this.user = user;
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

        Entry entry = (Entry) o;

        if (identry != entry.identry) return false;
        if (login != null ? !login.equals(entry.login) : entry.login != null) return false;
        if (status != null ? !status.equals(entry.status) : entry.status != null) return false;
        if (conference != null ? !conference.equals(entry.conference) : entry.conference != null) return false;
        return user != null ? user.equals(entry.user) : entry.user == null;
    }

    /**
     * Works in pair with equals.
     * @return number of hashcode for particular object
     * @see Object
     */
    @Override
    public int hashCode() {
        int result = (int) (identry ^ (identry >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (conference != null ? conference.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    /**
     * Transform conference object to string
     * @return string representation of conference object
     * @see Object
     */
    @Override
    public String toString() {
        return "Entry{" +
                "identry=" + identry +
                ", login='" + login + '\'' +
                ", status='" + status + '\'' +
                ", conference=" + conference +
                ", user=" + user +
                '}';
    }
}
