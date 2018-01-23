package command.impl;

import command.ActionCommand;
import resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    private static final String NEXT_PAGE = "path.page.index";

    @Override
    public String execute(HttpServletRequest request) {
        String page = JspRoutesManager.getProperty(NEXT_PAGE);
        return page;
    }
}
