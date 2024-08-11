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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskServlet() {
        super();
        this.tasks = new ApplicationInMemory();
        this.tasks = new ApplicationDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = null;
        String id = (String) request.getAttribute("id");
        
        Map<UUID, Task> taskMap = this.tasks.readTasks();
        PrintWriter writer = response.getWriter();
        
        
        taskMap.forEach((key, Task) -> {
            display += Task.getTitle() + " || " + Task.getDate() + " || " + Task.getContent() + " <br> ";
            
        });
        writer.write(display);
        
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("key1", "value1");
        dataMap.put("key2", "value2");
        dataMap.put("key3", "value3");

        request.setAttribute("dataMap", dataMap);
        
        request.setAttribute("taskList", taskMap);
        dispatcher = request.getRequestDispatcher("task.jsp");
        dispatcher.forward(request, response);
        
        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null; 
		Connection con = null;
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		Task task = new Task(name,date,content);
		String id = task.getId().toString();
		this.tasks.createOrUpdateTask(task);
		this.tasks.createTask(task);
		
		request.setAttribute("id", task.getId().toString());
		doGet(request, response);
	}

}
