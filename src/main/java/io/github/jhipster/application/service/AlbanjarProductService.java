package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.AlbanjarProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing AlbanjarProduct.
 */
public interface AlbanjarProductService {

    /**
     * Save a albanjarProduct.
     *
     * @param albanjarProductDTO the entity to save
     * @return the persisted entity
     */
    AlbanjarProductDTO save(AlbanjarProductDTO albanjarProductDTO);

    /**
     * Get all the albanjarProducts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AlbanjarProductDTO> findAll(Pageable pageable);

    /**
     * Get the "id" albanjarProduct.
     *
     * @param id the id of the entity
     * @return the entity
     */
    AlbanjarProductDTO findOne(Long id);

    /**
     * Delete the "id" albanjarProduct.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
