package com.eroshenkova.conference.db.dao.question.entity;

public class Question {
    private long idquestion;
    private String login;
    private String question;
    private String answer;

    public Question(long idquestion) {
        this.idquestion = idquestion;
    }

    public Question(long idquestion, String login, String question, String answer) {
        this.idquestion = idquestion;
        this.login = login;
        this.question = question;
        this.answer = answer;
    }

    public Question(String login, String question, String answer) {
        this.login = login;
        this.question = question;
        this.answer = answer;
    }

    public Question(String login, String question) {
        this.login = login;
        this.question = question;
    }

    public long getIdquestion() {
        return idquestion;
    }

    public void setIdquestion(long idquestion) {
        this.idquestion = idquestion;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

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

    @Override
    public int hashCode() {
        int result = (int) (idquestion ^ (idquestion >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

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
