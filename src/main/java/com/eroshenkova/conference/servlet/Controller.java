package com.eroshenkova.conference.servlet;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.factory.ActionFactory;
import com.eroshenkova.conference.constant.Page;
import com.eroshenkova.conference.resource.JspRoutesManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FrontController servlet is used to processing all requests from jsp pages
 *
 * @author Palina Yerashenkava
 * @see HttpServlet
 */
public class Controller extends HttpServlet {

    /**
     * Used to process GET requests
     * @param req is request from jsp
     * @param resp is response from jsp
     * @throws ServletException thrown when general servlet exception occurred
     * @throws IOException thrown when I/O stream exception occurred
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Used to process POST requests
     * @param req is request from jsp
     * @param resp is response from jsp
     * @throws ServletException thrown when general servlet exception occurred
     * @throws IOException thrown when I/O stream exception occurred
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Used to process all requests
     * @param request is request from jsp
     * @param response is response from jsp
     * @throws ServletException thrown when general servlet exception occurred
     * @throws IOException thrown when I/O stream exception occurred
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        if (page == null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(JspRoutesManager.getProperty(Page.JSP_ERROR));
            dispatcher.forward(request, response);
        } else if (page.endsWith(".jsp")) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(page);
        }
    }
}