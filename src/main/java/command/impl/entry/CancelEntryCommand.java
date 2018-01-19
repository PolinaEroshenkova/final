package command.impl.entry;

import command.ActionCommand;
import db.DAO;
import db.dao.DAOCommandEnum;
import db.dao.entry.entity.Entry;
import db.dao.entry.impl.EntryDAO;
import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class CancelEntryCommand implements ActionCommand {
    private static final String NEXT_PAGE = "path.page.profile";
    private static final String REQUEST_PARAMETER = "id";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String idEntryString = request.getParameter(REQUEST_PARAMETER);
        Long idEntry = Long.parseLong(idEntryString);
        Entry entry = new Entry(idEntry);
        DAO<Long, Entry> entryDAO = new EntryDAO();
        entryDAO.execute(DAOCommandEnum.DELETE, entry);
        page = ConfigurationManager.getProperty(NEXT_PAGE);
        return page;
    }
}
