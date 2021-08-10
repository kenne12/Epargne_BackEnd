package com.projet.epargne.services.interfaces;

// TODO: Auto-generated Javadoc

/**
 * The Interface GenericService.
 *
 * @param <T> the generic type
 */
public interface GenericService<T, A> {

    /**
     * Gets the all.
     *
     * @return the all
     */
    public Iterable<A> getAll();

    /**
     * Find by id.
     *
     * @param id the id
     * @return the t
     */
    public A findById(Long id);

    /**
     * Save.
     *
     * @param dto the dto
     * @return the t
     */
    public A save(T dto);

    /**
     * Edits the.
     *
     * @param dto the dto
     * @return the t
     */
    public A edit(T dto);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    public void deleteById(Long id);
}
