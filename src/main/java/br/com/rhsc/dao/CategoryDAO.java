package br.com.rhsc.dao;

import org.hibernate.Session;

import br.com.rhsc.entity.Category;

public class CategoryDAO extends BaseDAO<Category, Integer> {
	
	public CategoryDAO(Session session) {
		super(Category.class, session);
	}
	
	public Boolean baseEmpty() {
		return this.findAll().size() > 0;
	}
}
