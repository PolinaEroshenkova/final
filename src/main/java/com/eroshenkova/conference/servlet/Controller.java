package com.eroshenkova.conference.servlet;

import com.eroshenkova.conference.command.ActionCommand;
import com.eroshenkova.conference.command.factory.ActionFactory;
import com.eroshenkova.conference.constant.Page;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        if (page == null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + Page.JSP_ERROR);
            dispatcher.forward(request, response);
        } else if (page.endsWith(".jsp")) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(page);
        }
    }
}