package com.becca.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/tasks") 
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ApplicationService tasks;
	String display;

    public AddTaskServlet() {
        super();
        this.tasks = new ApplicationInMemory();
        this.tasks = new ApplicationDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null; 
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		Task task = new Task(name,date,content);
		this.tasks.createOrUpdateTask(task);
		this.tasks.createTask(task);
		
		dispatcher = request.getRequestDispatcher("control");
		request.setAttribute("id", task.getId().toString());
		//doGet(request, response);
		dispatcher.forward(request, response);
	}

}
