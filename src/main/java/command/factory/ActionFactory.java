package command.factory;

import command.ActionCommand;
import command.EmptyCommand;
import command.IndexCommand;
import command.client.CommandEnum;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            //String path = request.getServletPath();
            String path = request.getRequestURI();
            action = path.toUpperCase().substring(1);
        }
        try {
            if (action.isEmpty()) {
                return new IndexCommand();
            }
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
