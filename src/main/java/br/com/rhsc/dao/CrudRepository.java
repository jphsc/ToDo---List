package br.com.rhsc.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID extends Serializable> {
	
    T save(T registro);
    
    Optional<T> findById(ID id);
    
    List<T> findAll();
    
    T update(T registro);
    
    void delete(T registro);
    
    void deleteById(ID id);
}