package command.impl.page;

import command.ActionCommand;
import resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

public class NewConferenceCommand implements ActionCommand {
    private static final String NEXT_PAGE = "path.page.newconference";

    @Override
    public String execute(HttpServletRequest request) {
        return JspRoutesManager.getProperty(NEXT_PAGE);
    }
}
