package command;

import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    private static final String NEXT_PAGE = "path.page.index";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(NEXT_PAGE);
        request.getSession().invalidate();
        return page;
    }
}
