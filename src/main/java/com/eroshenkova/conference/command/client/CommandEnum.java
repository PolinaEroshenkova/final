package com.eroshenkova.conference.command.client;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.impl.conference.DeleteConferenceCommand;
import com.eroshenkova.conference.command.impl.conference.RegisterConferenceCommand;
import com.eroshenkova.conference.command.impl.entry.ChangeStatusCommand;
import com.eroshenkova.conference.command.impl.entry.DeleteEntryCommand;
import com.eroshenkova.conference.command.impl.entry.SignUpCommand;
import com.eroshenkova.conference.command.impl.page.*;
import com.eroshenkova.conference.command.impl.question.AnswerQuestionCommand;
import com.eroshenkova.conference.command.impl.question.AskQuestionCommand;
import com.eroshenkova.conference.command.impl.question.DeleteQuestionCommand;
import com.eroshenkova.conference.command.impl.question.PublishQuestionCommand;
import com.eroshenkova.conference.command.impl.user.*;
import com.eroshenkova.conference.command.impl.util.ChangeLanguageCommand;

/**
 * Defines command for Command pattern
 */
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
    FAQ {
        {
            this.command = new FaqCommand();
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
    UPDATEPASSWORD {
        {
            this.command = new UpdatePasswordCommand();
        }
    },
    CHANGESTATUS {
        {
            this.command = new ChangeStatusCommand();
        }
    },
    DELETEENTRY {
        {
            this.command = new DeleteEntryCommand();
        }
    },
    ASKQUESTION {
        {
            this.command = new AskQuestionCommand();
        }
    },
    ANSWERQUESTION {
        {
            this.command = new AnswerQuestionCommand();
        }
    },
    PUBLISHQUESTION {
        {
            this.command = new PublishQuestionCommand();
        }
    },
    DELETEQUESTION {
        {
            this.command = new DeleteQuestionCommand();
        }
    },
    CHANGELANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
