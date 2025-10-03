package br.com.rhsc.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class BaseDAO<T, ID extends Serializable> implements CrudRepository<T, ID> {
    
    private final Class<T> clazz;
    protected final Session session;
    private Transaction transaction = null;
    
    public BaseDAO(Class<T> clazz, Session session) {
        this.clazz = clazz;
        this.session = session;
    }

    @Override
    public T save(T registro) {
        try {
            transaction = session.beginTransaction();
            session.persist(registro);
            transaction.commit();
            return registro;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Optional<T> findById(ID id) {
        try {
            T registro = session.find(clazz, id);
            return Optional.ofNullable(registro);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    @Override
    public List<T> findAll() {
        try {
            String className = clazz.getSimpleName();
            Query<T> query = session.createQuery(String.format("from %s", className), clazz);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    @Override
    public T update(T registro) {
        try {
            transaction = session.beginTransaction();
            T merged = session.merge(registro); // Hibernate 7 usa merge em vez de refresh
            transaction.commit();
            return merged;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void delete(T registro) {
        try {
            transaction = session.beginTransaction();
            session.remove(registro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteById(ID id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            T registro = session.find(clazz, id);
            if (registro != null) {
                session.remove(registro);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}