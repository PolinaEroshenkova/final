package com.eroshenkova.conference.database.dao.section;

import com.eroshenkova.conference.entity.impl.Section;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

public interface SectionDAO {
    List<Section> findByConferenceId(long id) throws DAOException;

}
