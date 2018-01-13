package command;

import db.dao.entry.IEntryDAO;
import db.dao.entry.entity.Entry;
import db.dao.entry.impl.EntryDAO;
import db.dao.section.entity.Section;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class EntryCommand implements ActionCommand {
    private static final String SESSION_ATTRIBUTE_USER = "user";
    private static final String PARAMETER_VALUE = "sections";
    private static final String NEXT_PAGE = "path.page.index";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(SESSION_ATTRIBUTE_USER);
        Entry entry = new Entry(login);
        String[] ids = request.getParameterValues(PARAMETER_VALUE);
        List<Long> longid = new ArrayList<>();
        for (String id : ids) {
            long longId = Long.parseLong(id);
            longid.add(longId);
        }
        List<Section> sections = new ArrayList<>();
        for (Long id : longid) {
            Section section = new Section(id);
            sections.add(section);
        }
        IEntryDAO entryDAO = new EntryDAO();
        entryDAO.create(entry, sections);
        page = ConfigurationManager.getProperty(NEXT_PAGE);
        return page;
    }
}
