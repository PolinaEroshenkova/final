package com.eroshenkova.conference.entity.impl;

import com.eroshenkova.conference.entity.Entity;

/**
 * Defines participant entity of database.
 *
 * @author Palina Yerashenkava
 */
public class Participant implements Entity {

    /**
     * Defines user's login
     */
    private String login;


    /**
     * Defines user's surname
     */
    private String surname;

    /**
     * Defines user's name
     */
    private String name;

    /**
     * Defines participant's scope
     */
    private String scope;

    /**
     * Defines participant's position in company
     */
    private String position;

    /**
     * Defines participants's company
     */
    private String company;

    /**
     * Basic constructor for specifying database table data
     * @param login is user's login
     * @param surname is user's surname
     * @param name is user's name
     * @param scope is participant's scope
     */
    public Participant(String login, String surname, String name, String scope) {
        this.login = login;
        this.surname = surname;
        this.name = name;
        this.scope = scope;
    }

    /**
     * @param login is user's login
     * @param surname is user's surname
     * @param name is user's name
     * @param scope is participant's scope
     * @param position is participant's position in company
     * @param company is participant's company where he works
     */
    public Participant(String login, String surname, String name, String scope, String position, String company) {
        this.login = login;
        this.surname = surname;
        this.name = name;
        this.scope = scope;
        this.position = position;
        this.company = company;
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
     * @return user's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname is user's surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name is user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return scope in which user fill confident
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope is user's scope in which user fill confident
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return position of user in company
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position is user's position in company
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return user's company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company is company where user works
     */
    public void setCompany(String company) {
        this.company = company;
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

        Participant that = (Participant) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (scope != null ? !scope.equals(that.scope) : that.scope != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        return company != null ? company.equals(that.company) : that.company == null;
    }

    /**
     * Works in pair with equals.
     * @return number of hashcode for particular object
     * @see Object
     */
    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    /**
     * Transform conference object to string
     * @return string representation of conference object
     * @see Object
     */
    @Override
    public String toString() {
        return "Participant{" +
                "login='" + login + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", scope='" + scope + '\'' +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
