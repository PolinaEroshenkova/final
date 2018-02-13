package com.eroshenkova.conference.entity.impl;

import com.eroshenkova.conference.entity.Entity;

/**
 * Defines intermediate sectionentry entity of database.
 *
 * @author Palina Yerashenkava
 */
public class SectionEntry implements Entity {

    /**
     * Defines section's id
     */
    private Long idsection;


    /**
     * Defines id of entry
     */
    private Long identry;

    /**
     * Basic constructor for filling class with data from table in database
     * @param identry is entry's id
     * @param idsection is section's id
     */
    public SectionEntry(Long identry, Long idsection) {
        this.identry = identry;
        this.idsection = idsection;
    }

    /**
     * @return id of section
     */
    public Long getIdsection() {
        return idsection;
    }

    /**
     * @param idsection is section's id
     */
    public void setIdsection(Long idsection) {
        this.idsection = idsection;
    }

    /**
     * @return id of entry
     */
    public long getIdentry() {
        return identry;
    }

    /**
     * @param identry is entry's id
     */
    public void setIdentry(Long identry) {
        this.identry = identry;
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

        SectionEntry that = (SectionEntry) o;

        if (idsection != null ? !idsection.equals(that.idsection) : that.idsection != null) return false;
        return identry != null ? identry.equals(that.identry) : that.identry == null;
    }

    /**
     * Works in pair with equals.
     * @return number of hashcode for particular object
     * @see Object
     */
    @Override
    public int hashCode() {
        int result = idsection != null ? idsection.hashCode() : 0;
        result = 31 * result + (identry != null ? identry.hashCode() : 0);
        return result;
    }

    /**
     * Transform conference object to string
     * @return string representation of conference object
     * @see Object
     */
    @Override
    public String toString() {
        return "SectionEntry{" +
                "idsection=" + idsection +
                ", identry=" + identry +
                '}';
    }
}
