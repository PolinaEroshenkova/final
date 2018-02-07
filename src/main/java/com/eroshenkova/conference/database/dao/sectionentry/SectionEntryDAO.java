package com.eroshenkova.conference.database.dao.sectionentry;

import com.eroshenkova.conference.entity.SectionEntry;
import com.eroshenkova.conference.exception.DAOException;

import java.util.List;

public interface SectionEntryDAO {

    List<SectionEntry> findByEntryId(long id) throws DAOException;
}
