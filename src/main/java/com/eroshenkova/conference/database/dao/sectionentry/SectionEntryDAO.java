package com.eroshenkova.conference.database.dao.sectionentry;

import com.eroshenkova.conference.entity.impl.SectionEntry;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

/**
 * Defines specific options for intermediate table
 */
public interface SectionEntryDAO {

    /**
     * Selects from intermediate table entities by entry id
     *
     * @param id entry
     * @return list of intermediate table entities
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    List<SectionEntry> findByEntryId(long id) throws DAOException;
}
