package command;

import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class IndexCommand implements ActionCommand {
    private static final String NEXT_PAGE = "path.page.index";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(NEXT_PAGE);
    }
}
