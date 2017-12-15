package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.AlbanjarCustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing AlbanjarCustomer.
 */
public interface AlbanjarCustomerService {

    /**
     * Save a albanjarCustomer.
     *
     * @param albanjarCustomerDTO the entity to save
     * @return the persisted entity
     */
    AlbanjarCustomerDTO save(AlbanjarCustomerDTO albanjarCustomerDTO);

    /**
     * Get all the albanjarCustomers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AlbanjarCustomerDTO> findAll(Pageable pageable);

    /**
     * Get the "id" albanjarCustomer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    AlbanjarCustomerDTO findOne(Long id);

    /**
     * Delete the "id" albanjarCustomer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
