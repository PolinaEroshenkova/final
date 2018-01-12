package db.dao.conference.entity;

import db.dao.section.entity.Section;

import java.util.List;

public class Conference {
    private long idconference;
    private String topic;
    private int participantsnumber;
    private String place;
    private String begin;
    private String end;
    private String deadline;
    private List<Section> sections;

    public Conference(long idconference, String topic, int participantsnumber, String place, String begin, String end, String deadline) {
        this.idconference = idconference;
        this.topic = topic;
        this.participantsnumber = participantsnumber;
        this.place = place;
        this.begin = begin;
        this.end = end;
        this.deadline = deadline;
    }

    public Conference(long idconference, String topic, int participantsnumber, String place) {
        this.idconference = idconference;
        this.topic = topic;
        this.participantsnumber = participantsnumber;
        this.place = place;
    }

    public long getIdconference() {
        return idconference;
    }

    public void setIdconference(long idconference) {
        this.idconference = idconference;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getParticipantsnumber() {
        return participantsnumber;
    }

    public void setParticipantsnumber(int participantsnumber) {
        this.participantsnumber = participantsnumber;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference that = (Conference) o;

        if (idconference != that.idconference) return false;
        if (participantsnumber != that.participantsnumber) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (begin != null ? !begin.equals(that.begin) : that.begin != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        return deadline != null ? deadline.equals(that.deadline) : that.deadline == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idconference ^ (idconference >>> 32));
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + participantsnumber;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (begin != null ? begin.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "idconference=" + idconference +
                ", topic='" + topic + '\'' +
                ", participantsnumber=" + participantsnumber +
                ", place='" + place + '\'' +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }
}
