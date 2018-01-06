package command.client;

import command.*;

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
    PROFILEGENERATION {
        {
            this.command = new ProfileCommand();
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
