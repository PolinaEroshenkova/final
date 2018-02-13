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
 * Defines commands for Command pattern
 * @author Palina Yerashenkava
 */
public enum CommandEnum {
    /**
     * Command for index page
     */
    INDEX {
        {
            this.command = new IndexCommand();
        }
    },
    /**
     * Command for profile page
     */
    PROFILE {
        {
            this.command = new ProfileCommand();
        }
    },
    /**
     * Command for conferences page
     */
    CONFERENCES {
        {
            this.command = new ConferenceCommand();
        }
    },
    /**
     * Command for faq page
     */
    FAQ {
        {
            this.command = new FaqCommand();
        }
    },
    /**
     * Command for registration page
     */
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    /**
     * Command for management page
     */
    MANAGEMENT {
        {
            this.command = new ManagementCommand();
        }
    },
    /**
     * Command for new conference page
     */
    NEWCONFERENCE {
        {
            this.command = new NewConferenceCommand();
        }
    },
    /**
     * Command for deleting conference
     */
    DELETECONFERENCE {
        {
            this.command = new DeleteConferenceCommand();
        }
    },
    /**
     * Command for creating new conference
     */
    REGISTERCONFERENCE {
        {
            this.command = new RegisterConferenceCommand();
        }
    },
    /**
     * Command for log out user
     */
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    /**
     * Command for log in user
     */
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    /**
     * Command for creating new user
     */
    CREATEPROFILE {
        {
            this.command = new CreateProfileCommand();
        }
    },
    /**
     * Command for entry page
     */
    SIGNUP {
        {
            this.command = new EntryCommand();
        }
    },
    /**
     * Command for sign user up on conference
     */
    ENTRYPROCESSING {
        {
            this.command = new SignUpCommand();
        }
    },
    /**
     * Command for updating user information
     */
    UPDATEUSERINFO {
        {
            this.command = new UpdateUserCommand();
        }
    },
    /**
     * Command for updating user password
     */
    UPDATEPASSWORD {
        {
            this.command = new UpdatePasswordCommand();
        }
    },
    /**
     * Command for changing entry status
     */
    CHANGESTATUS {
        {
            this.command = new ChangeStatusCommand();
        }
    },
    /**
     * Command for deleting entry
     */
    DELETEENTRY {
        {
            this.command = new DeleteEntryCommand();
        }
    },
    /**
     * Command for asking question
     */
    ASKQUESTION {
        {
            this.command = new AskQuestionCommand();
        }
    },
    /**
     * Command for answering question
     */
    ANSWERQUESTION {
        {
            this.command = new AnswerQuestionCommand();
        }
    },
    /**
     * Command for publish user's question
     */
    PUBLISHQUESTION {
        {
            this.command = new PublishQuestionCommand();
        }
    },
    /**
     * Command for deleting question
     */
    DELETEQUESTION {
        {
            this.command = new DeleteQuestionCommand();
        }
    },
    /**
     * Command for changing language
     */
    CHANGELANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    };

    /**
     * Defines command
     */
    ActionCommand command;

    /**
     * @return current command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
