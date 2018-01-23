package command.impl.entry;

import command.ActionCommand;
import db.dao.entry.IEntryDAO;
import db.dao.entry.entity.Entry;
import db.dao.entry.impl.EntryDAO;
import db.dao.section.entity.Section;
import resource.UrlManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SignUpCommand implements ActionCommand {
    private static final String SESSION_ATTRIBUTE_USER = "user";
    private static final String PARAMETER_VALUE = "sections";
    private static final String NEXT_PAGE = "url.page.conference";
    private static final String REQUEST_STATE = "state";
    private static final String REQUEST_STATE_SUCCESS = "success";

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
        if (entryDAO.create(entry, sections)) {
            request.setAttribute(REQUEST_STATE, REQUEST_STATE_SUCCESS);
        }
        page = UrlManager.getProperty(NEXT_PAGE);
        return page;
    }
}
