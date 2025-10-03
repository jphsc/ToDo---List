package br.com.rhsc.facade;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.Session;

import br.com.rhsc.dao.CategoryDAO;
import br.com.rhsc.dao.TaskDAO;
import br.com.rhsc.entity.Category;
import br.com.rhsc.entity.Task;
import br.com.rhsc.util.Enumeradores.Status;
import br.com.rhsc.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TaskFacade {

	private Session session = null;
	private TaskDAO taskDAO = null;
	private CategoryDAO categoryDAO = null;
	  
	public TaskFacade() {
		this.session = HibernateUtil.openSession();
		this.taskDAO = new TaskDAO(session);
		this.categoryDAO = new CategoryDAO(session);
	}
	
	public static TaskFacade newInstance() {
		return new TaskFacade();
	}
	
	public void loadPageCreateTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("categories", categoryDAO.findAll());
		req.setAttribute("acao", "Create");
		req.getRequestDispatcher("pageCreateEditTask.jsp").forward(req, resp);
	}
	
	public void createTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			
			Category category = categoryDAO.findById(Integer.parseInt(req.getParameter("category"))).get();
			
			Task task = new Task.Builder().setCreatedAt(new Date()).setDescription(req.getParameter("description"))
					.setFinishLimit(LocalDate.parse(req.getParameter("date-finish")))
					.setStatus(Status.PENDENTE.getCodigo()).setTitle(req.getParameter("title"))
					.setCategory(category).setVersion(new Date()).build();
			
			taskDAO.save(task);
			req.setAttribute("mensagem", "Task salva");
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem", "Não foi possível salvar a task");
		} finally {
			resp.sendRedirect("index.jsp");
		}
	}
	
	public void loadPageEditTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Task task = taskDAO.findById(Integer.parseInt(req.getParameter("task_id"))).get();

		req.setAttribute("categories", categoryDAO.findAll());
		req.setAttribute("acao", "Edit");
		req.setAttribute("task", task);
		req.getRequestDispatcher("pageCreateEditTask.jsp").forward(req, resp);
	}
	
	public void editTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Category category = categoryDAO.findById(Integer.parseInt(req.getParameter("category"))).get();
		
		Task taskBase = taskDAO.findById(Integer.parseInt(req.getParameter("task_id"))).get();
		taskBase.setDescription(req.getParameter("description"));
		taskBase.setFinishLimit(LocalDate.parse(req.getParameter("date-finish")));
		taskBase.setTitle(req.getParameter("title"));
		taskBase.setCategory(category);
		taskBase.setVersion(new Date());
		
		taskDAO.update(taskBase);
		resp.sendRedirect("index.jsp");
	}
	
	public void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Task task = taskDAO.findById(Integer.parseInt(req.getParameter("task_id"))).get();
		taskDAO.delete(task);
		resp.sendRedirect("index.jsp");
	}
	
	public void alterTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer status = 0;
		Task task = taskDAO.findById(Integer.parseInt(req.getParameter("task_id"))).get();
		
		if(req.getParameter("action").equals("doing")) {
			status = Status.FAZENDO.getCodigo();
		} else if (req.getParameter("action").equals("doned")) {
			status = Status.FEITO.getCodigo();
			task.setFinishedAt(new Date());
		} else {
			status = Status.CANCELADO.getCodigo();
			task.setFinishedAt(new Date());
		}
		
		task.setStatus(status);
		task.setVersion(new Date());
		
		taskDAO.save(task);
		resp.sendRedirect("index.jsp");
	}
		
}
