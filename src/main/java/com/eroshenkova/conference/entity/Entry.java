package com.eroshenkova.conference.entity;

public class Entry {
    private long identry;
    private String login;
    private String status;
    private Conference conference;
    private User user;

    public Entry(long identry, String login, String status) {
        this.identry = identry;
        this.login = login;
        this.status = status;
    }

    public Entry(String login) {
        this.login = login;
    }

    public long getIdentry() {
        return identry;
    }

    public void setIdentry(long identry) {
        this.identry = identry;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    @Override
    public int hashCode() {
        int result = (int) (identry ^ (identry >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (conference != null ? conference.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

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
