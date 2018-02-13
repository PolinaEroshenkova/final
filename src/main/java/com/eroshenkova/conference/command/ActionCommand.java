package com.eroshenkova.conference.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Defines commands for command pattern
 *
 * @author Palina Yerashenkava
 */
public interface ActionCommand {

    /**
     * Used as guide force of the system
     * @param request is request from page
     * @return next jsp page
     */
    String execute(HttpServletRequest request);
}
