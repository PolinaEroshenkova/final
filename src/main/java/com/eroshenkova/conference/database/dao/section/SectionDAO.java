package com.eroshenkova.conference.database.dao.section;

import com.eroshenkova.conference.entity.Section;

import java.util.List;

public interface SectionDAO {
    List<Section> findByConferenceId(long id);

}
