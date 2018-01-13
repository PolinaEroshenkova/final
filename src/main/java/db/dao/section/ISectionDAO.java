package db.dao.section;

import db.dao.section.entity.Section;

import java.util.List;

public interface ISectionDAO {
    List<Section> findByConferenceId(long id);
}
