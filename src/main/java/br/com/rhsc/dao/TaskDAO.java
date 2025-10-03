package br.com.rhsc.dao;

import org.hibernate.Session;

import br.com.rhsc.entity.Task;

public class TaskDAO extends BaseDAO<Task, Integer> {

	public TaskDAO(Session session) {
		super(Task.class, session);
	}
	
	public Boolean baseEmpty() {
		return this.findAll().size() > 0 ;
	}
	
}
