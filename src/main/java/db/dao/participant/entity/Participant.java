package db.dao.participant.entity;

public class Participant {
    private String login;
    private String surname;
    private String name;
    private String scope;
    private String position;
    private String company;

    public Participant(String login, String surname, String name, String scope) {
        this.login = login;
        this.surname = surname;
        this.name = name;
        this.scope = scope;
    }

    public Participant(String login, String surname, String name, String scope, String position, String company) {
        this.login = login;
        this.surname = surname;
        this.name = name;
        this.scope = scope;
        this.position = position;
        this.company = company;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

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
