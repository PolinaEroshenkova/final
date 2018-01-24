package command.impl.page;

import command.ActionCommand;
import resource.JspRoutesManager;

import javax.servlet.http.HttpServletRequest;

public class RegistrationFormCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return JspRoutesManager.getProperty("path.page.registration");
    }
}
