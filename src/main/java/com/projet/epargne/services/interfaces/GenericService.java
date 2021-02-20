package com.projet.epargne.services.interfaces;

// TODO: Auto-generated Javadoc
/**
 * The Interface GenericService.
 *
 * @param <T> the generic type
 */
public interface GenericService<T> {
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public Iterable<T> getAll();
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the t
	 */
	public T findById(Long id);
	
	/**
	 * Save.
	 *
	 * @param dto the dto
	 * @return the t
	 */
	public T 	save(T dto);
	
	/**
	 * Edits the.
	 *
	 * @param dto the dto
	 * @return the t
	 */
	public T edit(T dto);
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Long id);
}
