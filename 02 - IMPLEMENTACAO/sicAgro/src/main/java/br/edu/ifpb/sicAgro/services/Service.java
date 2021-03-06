package br.edu.ifpb.sicAgro.services;

import java.util.List;

import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;

/**
 * 
 * @author  <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 * @param <T>
 * @param <K>
 */
public interface Service<T,K> {
	
	/**
	 * 
	 * @param entity
	 * @throws SicAgroException 
	 * @throws SicAgroExceptionHandler 
	 */
	public void add(T entity) throws SicAgroException;
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public T update(T entity);
	
	/**
	 * 
	 * @param entity
	 * @throws SicAgroException 
	 */
	public void remove(T entity) throws SicAgroException;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public T findById(K id);
	
	/**
	 * 
	 * @return
	 */
	public List<T> findAll();

}
