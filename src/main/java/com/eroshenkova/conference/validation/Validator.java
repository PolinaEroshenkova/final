package com.eroshenkova.conference.validation;

import com.eroshenkova.conference.entity.impl.*;

/**
 * Used for server validation of input data
 *
 * @author Palina Yerashenkava
 */
public class Validator {
    /**
     * Min string length is 0
     */
    private static final int MIN_LENGTH_0 = 0;

    /**
     * Max string length is 5
     */
    private static final int MAX_LENGTH_5 = 5;

    /**
     * Max string length is 11
     */
    private static final int MAX_LENGTH_11 = 11;

    /**
     * Max string length is 30
     */
    private static final int MAX_LENGTH_30 = 30;

    /**
     * Max string length is 35
     */
    private static final int MAX_LENGTH_35 = 35;

    /**
     * Max string length is 60
     */
    private static final int MAX_LENGTH_60 = 60;

    /**
     * Max string length is 100
     */
    private static final int MAX_LENGTH_100 = 100;

    /**
     * Max string length is 255
     */
    private static final int MAX_LENGTH_255 = 255;

    /**
     * Max string length is 9999
     */
    private static final int MAX_LENGTH_9999 = 9999;


    /**
     * @param conference is validated object
     * @return true if validation completed successfully and
     *         false if object does not satisfy the conditions
     */
    public boolean validate(Conference conference) {
        return !(conference.getTopic() == null || conference.getTopic().isEmpty() ||
                conference.getTopic().length() > MAX_LENGTH_255 ||
                conference.getParticipantsnumber() < MIN_LENGTH_0 ||
                conference.getParticipantsnumber() > MAX_LENGTH_9999 ||
                conference.getPlace() == null || conference.getPlace().isEmpty() ||
                conference.getPlace().length() > MAX_LENGTH_60 || conference.getBegin() == null ||
                conference.getEnd() == null ||
                conference.getDeadline() == null);
    }

    /**
     * @param entry is validated object
     * @return true if validation completed successfully and
     *         false if object does not satisfy the conditions
     */
    public boolean validate(Entry entry) {
        return entry.getLogin() == null || entry.getLogin().isEmpty() ||
                entry.getLogin().length() > MAX_LENGTH_30 || entry.getStatus() == null ||
                entry.getStatus().isEmpty() || entry.getStatus().length() > MAX_LENGTH_11;
    }

    /**
     * @param question is validated object
     * @return true if validation completed successfully and
     *         false if object does not satisfy the conditions
     */
    public boolean validate(Question question) {
        return question.getLogin() == null || question.getLogin().isEmpty() ||
                question.getLogin().length() > MAX_LENGTH_30 || question.getQuestion() == null ||
                question.getQuestion().isEmpty() || question.getQuestion().length() > MAX_LENGTH_255 ||
                question.getAnswer() == null || question.getAnswer().isEmpty() ||
                question.getAnswer().length() > MAX_LENGTH_255;
    }

    /**
     * @param user is validated object
     * @return true if validation completed successfully and
     *         false if object does not satisfy the conditions
     */
    public boolean validate(User user) {
        return user.getLogin() == null || user.getLogin().isEmpty() ||
                user.getLogin().length() > MAX_LENGTH_30 || user.getPassword() == null ||
                user.getPassword().isEmpty() || user.getPassword().length() > MAX_LENGTH_255 ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getEmail().length() > MAX_LENGTH_255 || user.getType() == null ||
                user.getType().isEmpty() || user.getType().length() > MAX_LENGTH_5;
    }

    /**
     * @param participant is validated object
     * @return true if validation completed successfully and
     *         false if object does not satisfy the conditions
     */
    public boolean validate(Participant participant) {
        return participant.getLogin() == null || participant.getLogin().isEmpty() ||
                participant.getLogin().length() > MAX_LENGTH_30 || participant.getSurname() == null ||
                participant.getSurname().isEmpty() || participant.getSurname().length() > MAX_LENGTH_35 ||
                participant.getName() == null || participant.getName().isEmpty() ||
                participant.getName().length() > MAX_LENGTH_35 || participant.getScope() == null ||
                participant.getScope().isEmpty() || participant.getScope().length() > MAX_LENGTH_100;
    }
}
