package command.client;

import command.ActionCommand;
import command.LoginCommand;
import command.LogoutCommand;
import command.RegisterCommand;

public enum CommandEnum {
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
