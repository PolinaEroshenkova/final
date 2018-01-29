package command.impl.entry;

import command.ActionCommand;
import db.dao.AbstractDAO;
import db.dao.DAOCommandEnum;
import db.dao.entry.entity.Entry;
import db.dao.entry.impl.EntryDAO;
import db.dao.sectionentry.entity.SectionEntry;
import db.dao.sectionentry.impl.SectionEntryDAO;
import resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SignUpCommand implements ActionCommand {
    private static final String SESSION_ATTRIBUTE_USER = "user";
    private static final String PARAMETER_VALUE = "sections";
    private static final String NEXT_PAGE = "path.page.entry";
    private static final String REQUEST_STATE = "state";
    private static final String REQUEST_STATE_SUCCESS = "success";
    private static final String MESSAGE = "message";
    private static final String MESSAGE_SUCCESS = "Вы зарегистрировались на конференцию. Ждите проверки заявки администратором";
    private static final String HREF = "href";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(SESSION_ATTRIBUTE_USER);
        Entry entry = new Entry(login);
        String[] sectionIds = request.getParameterValues(PARAMETER_VALUE);
        List<Long> longSectionId = new ArrayList<>();
        for (String id : sectionIds) {
            long longId = Long.parseLong(id);
            longSectionId.add(longId);
        }
        AbstractDAO<Long, Entry> entryDAO = new EntryDAO();
        AbstractDAO<List<Long>, SectionEntry> sectionEntryDao = new SectionEntryDAO();
        long insertedId = entryDAO.execute(DAOCommandEnum.CREATE, entry);
        int result = -1;
        if (insertedId > 0) {
            result = 0;
            for (Long id : longSectionId) {
                SectionEntry sectionEntry = new SectionEntry(insertedId, id);
                if (sectionEntryDao.execute(DAOCommandEnum.CREATE, sectionEntry) < 0) {
                    result++;
                }
            }
        }
        if (result == 0) {
            request.setAttribute(REQUEST_STATE, REQUEST_STATE_SUCCESS);
            request.setAttribute(MESSAGE, MESSAGE_SUCCESS);
            request.setAttribute(HREF, "/conference");
        }
        page = JspRoutesManager.getProperty(NEXT_PAGE);
        return page;
    }
}
