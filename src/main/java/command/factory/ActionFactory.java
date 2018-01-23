package command.factory;

import command.ActionCommand;
import command.client.CommandEnum;
import command.impl.EmptyCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);

    private static final String PARAM_COMMAND = "command";
    private static final String ATTRIBUTE_WRONG_ACTION = "wrongAction";
    private static final String MESSAGE_WRONG_ACTION = "message.wrongaction";
    private static final String ACTION_INDEX = "INDEX";

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(PARAM_COMMAND);
        if (action == null || action.isEmpty()) {
            String path = request.getPathInfo();
            action = path.toUpperCase().substring(1);
            if (action.isEmpty()) {
                action = ACTION_INDEX;
            }
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute(ATTRIBUTE_WRONG_ACTION, action + MessageManager.getProperty(MESSAGE_WRONG_ACTION));
            LOGGER.log(Level.ERROR, "Command is not detected");
        }
        return current;
    }
}
