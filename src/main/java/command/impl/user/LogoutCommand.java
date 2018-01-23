package command.impl.user;

import command.ActionCommand;
import resource.UrlManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    private static final String NEXT_PAGE = "path.page.index";

    @Override
    public String execute(HttpServletRequest request) {
        String page = UrlManager.getProperty(NEXT_PAGE);
        request.getSession().invalidate();
        return page;
    }
}
