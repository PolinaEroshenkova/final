package command;

import resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class IndexCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.index");
    }
}
