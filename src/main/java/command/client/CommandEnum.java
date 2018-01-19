package command.client;

import command.ActionCommand;
import command.impl.entry.CancelEntryCommand;
import command.impl.entry.SignUpCommand;
import command.impl.page.ConferenceCommand;
import command.impl.page.EntryCommand;
import command.impl.page.ProfileCommand;
import command.impl.user.LoginCommand;
import command.impl.user.LogoutCommand;
import command.impl.user.RegisterCommand;
import command.impl.user.UpdateUserCommand;

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
    },
    UPDATEUSERINFO {
        {
            this.command = new UpdateUserCommand();
        }
    },
    CANCELENTRY {
        {
            this.command = new CancelEntryCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
