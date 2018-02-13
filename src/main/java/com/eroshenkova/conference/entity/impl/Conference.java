package com.eroshenkova.conference.entity.impl;

import com.eroshenkova.conference.entity.Entity;

import java.util.Date;
import java.util.List;

/**
 * Defines conference entity of database.
 * Implements interface Comparable to compare conferences by deadline date
 *
 * @author Palina Yerashenkava
 */
public class Conference implements Entity, Comparable<Conference> {
    /**
     * Id of conference
     */
    private long idconference;

    /**
     * Topic discussed on particular conference
     */
    private String topic;

    /**
     * Number of conference participants
     */
    private int participantsnumber;


    /**
     * Space where conference locates
     */
    private String place;


    /**
     * Date and time when conference starts
     */
    private Date begin;


    /**
     * Date and time when conference ends
     */
    private Date end;


    /**
     * Deadline date for filling applications
     */
    private Date deadline;


    /**
     * List of sections than includes in particular conference
     */
    private List<Section> sections;

    /**
     * Basic constructor is used to set up database date
     * @param idconference id of conference
     * @param topic conference topic
     * @param participantsnumber number of participants
     * @param place location of conference
     * @param begin date and time of conference start
     * @param end date and time of conference end
     * @param deadline deadline date for filling applications
     */
    public Conference(long idconference, String topic, int participantsnumber, String place, Date begin, Date end, Date deadline) {
        this.idconference = idconference;
        this.topic = topic;
        this.participantsnumber = participantsnumber;
        this.place = place;
        this.begin = begin;
        this.end = end;
        this.deadline = deadline;
    }

    /**
     * Constructor that initialize basic conference data plus sections
     * @param topic conference topic
     * @param participantsnumber number of participants
     * @param place location of conference
     * @param begin date and time of conference start
     * @param end date and time of conference end
     * @param deadline deadline date for filling applications
     * @param sections defines conference sections
     */
    public Conference(String topic, int participantsnumber, String place, Date begin, Date end, Date deadline, List<Section> sections) {
        this.topic = topic;
        this.participantsnumber = participantsnumber;
        this.place = place;
        this.begin = begin;
        this.end = end;
        this.deadline = deadline;
        this.sections = sections;
    }


    /**
     * Constructor defines basic parameters for conference except id
     * @param topic conference topic
     * @param participantsnumber number of participants
     * @param place location of conference
     * @param begin date and time of conference start
     * @param end date and time of conference end
     * @param deadline deadline date for filling applications
     */
    public Conference(String topic, int participantsnumber, String place, Date begin, Date end, Date deadline) {
        this.topic = topic;
        this.participantsnumber = participantsnumber;
        this.place = place;
        this.begin = begin;
        this.end = end;
        this.deadline = deadline;
    }

    /**
     * @return id of conference
     */
    public long getIdconference() {
        return idconference;
    }


    /**
     * @param idconference id of conference entity
     */
    public void setIdconference(long idconference) {
        this.idconference = idconference;
    }

    /**
     * @return conference topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic is conference's topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return number of participants in conference
     */
    public int getParticipantsnumber() {
        return participantsnumber;
    }

    /**
     * @param participantsnumber number of participants in conference
     */
    public void setParticipantsnumber(int participantsnumber) {
        this.participantsnumber = participantsnumber;
    }

    /**
     * @return place where conference located
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place where conference located
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return begin date of conference
     */
    public Date getBegin() {
        return begin;
    }

    /**
     * @param begin is begin date of conference
     */
    public void setBegin(Date begin) {
        this.begin = begin;
    }

    /**
     * @return end date of conference
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end date of conference end
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * @return deadline date for filling applications
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline is deadline date for filling applications
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * @return list of sections included into conference
     */
    public List<Section> getSections() {
        return sections;
    }

    /**
     * @param sections is sections of particular conference
     */
    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    /**
     * Used for comparing two entities of conference
     * @param o object entity which is used for comparing with this
     * @return true if objects are the same and false when objects are different
     * @see Object
     */
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
        if (deadline != null ? !deadline.equals(that.deadline) : that.deadline != null) return false;
        return sections != null ? sections.equals(that.sections) : that.sections == null;
    }

    /**
     * Works in pair with equals.
     * @return number of hashcode for particular object
     * @see Object
     */
    @Override
    public int hashCode() {
        int result = (int) (idconference ^ (idconference >>> 32));
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + participantsnumber;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (begin != null ? begin.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (sections != null ? sections.hashCode() : 0);
        return result;
    }

    /**
     * Transform conference object to string
     * @return string representation of conference object
     * @see Object
     */
    @Override
    public String toString() {
        return "Conference{" +
                "idconference=" + idconference +
                ", topic='" + topic + '\'' +
                ", participantsnumber=" + participantsnumber +
                ", place='" + place + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", deadline=" + deadline +
                ", sections=" + sections +
                '}';
    }

    /**
     * Method belongs to Comparable interface. Used to compare objects by some option
     * @param o object of conference entity
     * @return -1 if date of this object is less than date of o object
     *          0 this object and o object has same date field
     *          1 if date of this object is more than date of o object
     * @see Comparable
     */
    @Override
    public int compareTo(Conference o) {
        return deadline.compareTo(o.getDeadline());
    }
}
