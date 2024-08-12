package com.becca.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

/**
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet("/delete") 
public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicationService tasks;

    public DeleteTaskServlet() {
        super();
        this.tasks = new ApplicationInMemory();
        this.tasks = new ApplicationDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String id = (String) request.getParameter("id");
		tasks.deleteTask(id);
		Map<UUID, Task> taskMap = this.tasks.readTasks();
		request.setAttribute("id", id);
		request.setAttribute("taskList", taskMap);
		dispatcher = request.getRequestDispatcher("control");
		dispatcher.forward(request, response);
	}


}
