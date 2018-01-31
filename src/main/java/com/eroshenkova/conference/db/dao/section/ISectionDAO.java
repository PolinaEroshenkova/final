package com.eroshenkova.conference.db.dao.section;

import com.eroshenkova.conference.db.dao.section.entity.Section;

import java.util.List;

public interface ISectionDAO {
    List<Section> findByConferenceId(long id);

    List<Section> findByEntryId(long id);
}
