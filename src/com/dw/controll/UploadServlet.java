package com.dw.controll;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {

	this.doPost(req, resp);
    }
    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String mainPage = "upload.jsp";
	request.setAttribute("mainPage", mainPage);
	RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
	dispatcher.forward(request, response);
    }
}
