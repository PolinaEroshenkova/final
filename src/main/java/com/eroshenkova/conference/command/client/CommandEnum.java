package com.eroshenkova.conference.command.client;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.impl.conference.DeleteConferenceCommand;
import com.eroshenkova.conference.command.impl.conference.RegisterConferenceCommand;
import com.eroshenkova.conference.command.impl.entry.CancelEntryCommand;
import com.eroshenkova.conference.command.impl.entry.SignUpCommand;
import com.eroshenkova.conference.command.impl.page.*;
import com.eroshenkova.conference.command.impl.user.CreateProfileCommand;
import com.eroshenkova.conference.command.impl.user.LoginCommand;
import com.eroshenkova.conference.command.impl.user.LogoutCommand;
import com.eroshenkova.conference.command.impl.user.UpdateUserCommand;

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
    DELETECONFERENCE {
        {
            this.command = new DeleteConferenceCommand();
        }
    },
    REGISTERCONFERENCE {
        {
            this.command = new RegisterConferenceCommand();
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
