package br.com.rhsc.controller;

import java.io.IOException;

import br.com.rhsc.facade.TaskFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/MainServlet", "/editTask", "/deleteTask", "/pageCreateTask", "/pageEditTask", "/createTask", "/alterTask"})
public class MainServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public MainServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		TaskFacade facade = TaskFacade.newInstance();
		
		if(path.equals("/pageCreateTask")) {
			facade.loadPageCreateTask(request, response);
			
		} else if (path.equals("/createTask")) {
			facade.createTask(request, response);
			
		} else if (path.equals("/pageEditTask")) {
			facade.loadPageEditTask(request, response);
			
		} else if (path.equals("/editTask")) {
			facade.editTask(request, response);
			
		} else if (path.equals("/alterTask")) {
			facade.alterTask(request, response);
			
		} else if (path.equals("/deleteTask")) {
			facade.deleteTask(request, response);
			
		} 
	}

	//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
	//}

	//protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//}

}
