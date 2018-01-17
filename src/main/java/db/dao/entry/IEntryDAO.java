package db.dao.entry;

import db.dao.entry.entity.Entry;
import db.dao.section.entity.Section;

import java.util.List;

public interface IEntryDAO {
    boolean create(Entry entry, List<Section> sections);

    List<Entry> findByLogin(String login);
}
