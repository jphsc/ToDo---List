package br.com.rhsc.configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.com.rhsc.dao.CategoryDAO;
import br.com.rhsc.dao.TaskDAO;
import br.com.rhsc.entity.Category;
import br.com.rhsc.entity.Task;
import br.com.rhsc.util.HibernateUtil;
import br.com.rhsc.util.Enumeradores.Status;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

	@Override
    public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Iniciando aplicação...");
		
        try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
       
    }

//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        System.out.println("Finalizando aplicação...");
//        // Aqui você pode adicionar código de limpeza se necessário
//    }

	public static void initialize() throws Exception {

  	  System.out.println("Creating test data...");
  	  
  	  Session session = null;
  	  
  	  try{
  		  session = HibernateUtil.openSession();
			
  		  CategoryDAO categoryDAO = new CategoryDAO(session);
  		  Boolean categoryEmpty = categoryDAO.baseEmpty();
			
  		  if(!categoryEmpty) {
  			  List<Category> categories = Arrays.asList(
  					new Category("Mercado", new Date()),
					new Category("Educação", new Date()),
					new Category("Tributos e legislação", new Date()),
					new Category("Casa", new Date())
				);
  			  categories.forEach(categoryDAO::save);
  			} else {
  				System.out.println("There is at least one category in the database, categories will not be created now.");
			}
  		  TaskDAO taskDAO = new TaskDAO(session);
  		  Boolean taskEmpty = taskDAO.baseEmpty();
  		  
  		  if(!taskEmpty) {
  			  List<Category> categories = categoryDAO.findAll();
  			  if(!categories.isEmpty()) {
  			  	  List<Task> tasks = Arrays.asList(
  	  			  		new Task("Tarefa PENDENTE", "Descrição da tarefa", new Date(), LocalDate.now().plusDays(5), null, Status.PENDENTE.getCodigo(), new Date(), categories.get(0))
  				  		, new Task("Tarefa FAZENDO", "Descrição da tarefa", new Date(), LocalDate.now().plusDays(5), null, Status.FAZENDO.getCodigo(), new Date(), categories.get(2))
  				  		, new Task("Tarefa FINALIZADA", "Descrição da tarefa", new Date(), LocalDate.now().plusDays(5), new Date(), Status.FEITO.getCodigo(), new Date(), categories.get(3))
  			  );
  			  tasks.forEach(taskDAO::save);
  			  
  			  } else {
  				  throw new Exception("\n\nThere isn't a category on database\n\n");
  			  }
			}
			
  	  } catch(Exception e) {
  		  e.printStackTrace();
  		  throw new Exception(e.getMessage());
  	  } finally {
  		  if (session != null && session.isOpen()) {
  			  session.close(); // Apenas fecha a sessão, não a SessionFactory
  		  }
	  }
	}
}
