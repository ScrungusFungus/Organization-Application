package com.becca.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/control") 
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ApplicationService tasks;
	String display = "hey";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        this.tasks = new ApplicationInMemory();
        this.tasks = new ApplicationDao();
    }
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
        if(request.getAttribute("taskList")!=null) {
        	display = "";
        }
        if(request.getAttribute("id")!=null) {
        	display = "";
        }
      
        Map<UUID, Task> taskMap = this.tasks.readTasks();
        String id = (String) request.getAttribute("id");
      
        taskMap.forEach((key, Task) -> {
            display += Task.getTitle() + " || " + Task.getDate() + " || " + Task.getContent() + "<a href=delete?id=" + Task.getId().toString() + ">" + "Delete</a>" + " <br> ";
            
        });
  
        request.setAttribute("taskList", taskMap);
        request.setAttribute("display", display);
        dispatcher = request.getRequestDispatcher("task.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
