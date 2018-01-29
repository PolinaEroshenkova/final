package db.dao.entry;

import db.dao.entry.entity.Entry;

import java.util.List;

public interface IEntryDAO {
    List<Entry> findByLogin(String login);
}
