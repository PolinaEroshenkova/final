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
    SHOWCONFERENCES {
        {
            this.command = new ConferenceCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    SIGNUP {
        {
            this.command = new SignUpCommand();
        }
    },
    ENTRYPROCESSING {
        {
            this.command = new EntryCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
