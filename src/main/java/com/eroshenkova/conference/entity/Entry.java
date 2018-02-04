package com.eroshenkova.conference.entity;

public class Entry {
    private long identry;
    private String login;
    private String status;
    private Conference conference;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (identry != entry.identry) return false;
        if (login != null ? !login.equals(entry.login) : entry.login != null) return false;
        return status != null ? status.equals(entry.status) : entry.status == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (identry ^ (identry >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "identry=" + identry +
                ", login='" + login + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
