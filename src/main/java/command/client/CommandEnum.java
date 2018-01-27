package command.client;

import command.ActionCommand;
import command.impl.entry.CancelEntryCommand;
import command.impl.entry.SignUpCommand;
import command.impl.page.*;
import command.impl.user.CreateProfileCommand;
import command.impl.user.LoginCommand;
import command.impl.user.LogoutCommand;
import command.impl.user.UpdateUserCommand;

public enum CommandEnum {
    INDEX {
        {
            this.command = new IndexCommand();
        }
    },
    PROFILE {
        {
            this.command = new ProfileCommand();
        }
    },
    CONFERENCES {
        {
            this.command = new ConferenceCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    MANAGEMENT {
        {
            this.command = new ManagementCommand();
        }
    },
    NEWCONFERENCE {
        {
            this.command = new NewConferenceCommand();
        }
    },
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
    CREATEPROFILE {
        {
            this.command = new CreateProfileCommand();
        }
    },
    SIGNUP {
        {
            this.command = new EntryCommand();
        }
    },
    ENTRYPROCESSING {
        {
            this.command = new SignUpCommand();
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
