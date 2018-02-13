package com.eroshenkova.conference.database.dao.section;

import com.eroshenkova.conference.entity.impl.Section;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

/**
 * Defines specific options for section table
 */
public interface SectionDAO {
    /**
     * Selects all sections for certain conference
     *
     * @param id of conference to find all sections for certain conference
     * @return list if sections for certain conference
     * @throws DAOException which specifies database exception. Wrapper exception on SQLException
     */
    List<Section> findByConferenceId(long id) throws DAOException;

}
