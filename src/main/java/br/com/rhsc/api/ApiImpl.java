package br.com.rhsc.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rhsc.dao.TaskDAO;
import br.com.rhsc.entity.Task;
import br.com.rhsc.util.HibernateUtil;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class ApiImpl {

	private TaskDAO taskDAO = null;
    private static final Logger logger = LoggerFactory.getLogger(ApiImpl.class);
	
	@GET
	@Path("/getTasks")
	public Response getAllTasks() {
		taskDAO = new TaskDAO(HibernateUtil.openSession());
        logger.info("Método getAllTasks() chamado");
        List<Task> tasks = taskDAO.findAll();
		return Response.ok().entity(tasks).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getTask(@PathParam("id") Integer id) {
		taskDAO = new TaskDAO(HibernateUtil.openSession());
		return Response.ok().entity(taskDAO.findById(id)).build();
	}
	
	@POST
	public Response createTask(Task task) {
		taskDAO = new TaskDAO(HibernateUtil.openSession());
		return Response.ok().entity(taskDAO.save(task)).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteTask(@PathParam("id")Integer id) {
		taskDAO = new TaskDAO(HibernateUtil.openSession());
		taskDAO.deleteById(id);
		return Response.ok().entity("Task deleted").build();
	}
	
	@PUT
	public Response updateTask(Task task) {
		taskDAO = new TaskDAO(HibernateUtil.openSession());
		return Response.ok().entity(taskDAO.update(task)).build();
	}
}
