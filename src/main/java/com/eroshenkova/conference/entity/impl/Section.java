package com.eroshenkova.conference.entity.impl;

import com.eroshenkova.conference.entity.Entity;

/**
 * Defines section entity of database.
 *
 * @author Palina Yerashenkava
 */
public class Section implements Entity {

    /**
     * Defines Id of section
     */
    private long idsection;

    /**
     * Defines of conference
     */
    private long idconference;

    /**
     * Defines section title
     */
    private String title;

    /**
     * Basic constructor for defining data from database table
     * @param idsection is id of section
     * @param idconference is id of conference for particular section
     * @param title is section's title
     */
    public Section(long idsection, long idconference, String title) {
        this.idsection = idsection;
        this.idconference = idconference;
        this.title = title;
    }

    /**
     * Constructor for specifying section by id conference and title
     * @param idconference is id of conference for particular section
     * @param title is section's title
     */
    public Section(long idconference, String title) {
        this.idconference = idconference;
        this.title = title;
    }

    /**
     * Defines only section's title
     * @param title is section's title
     */
    public Section(String title) {
        this.title = title;
    }

    /**
     * Defines only section's id
     * @param idsection is id of section
     */
    public Section(long idsection) {
        this.idsection = idsection;
    }

    /**
     * @return id of section
     */
    public long getIdsection() {
        return idsection;
    }

    /**
     * @param idsection is section's id
     */
    public void setIdSection(long idsection) {
        this.idsection = idsection;
    }

    /**
     * @return id of conference in which includes specified section
     */
    public long getIdConference() {
        return idconference;
    }

    /**
     * @param idconference is id of conference in which includes specified section
     */
    public void setIdConference(long idconference) {
        this.idconference = idconference;
    }

    /**
     * @return title of section
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title is section's title
     */
    public void setTitle(String title) {
        this.title = title;
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

        Section section = (Section) o;

        if (idsection != section.idsection) return false;
        if (idconference != section.idconference) return false;
        return title != null ? title.equals(section.title) : section.title == null;
    }

    /**
     * Works in pair with equals.
     * @return number of hashcode for particular object
     * @see Object
     */
    @Override
    public int hashCode() {
        int result = (int) (idsection ^ (idsection >>> 32));
        result = 31 * result + (int) (idconference ^ (idconference >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    /**
     * Transform conference object to string
     * @return string representation of conference object
     * @see Object
     */
    @Override
    public String toString() {
        return "Section{" +
                "idsection=" + idsection +
                ", idconference=" + idconference +
                ", title='" + title + '\'' +
                '}';
    }
}
