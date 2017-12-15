package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.AlbanjarCustomerService;
import io.github.jhipster.application.domain.AlbanjarCustomer;
import io.github.jhipster.application.repository.AlbanjarCustomerRepository;
import io.github.jhipster.application.service.dto.AlbanjarCustomerDTO;
import io.github.jhipster.application.service.mapper.AlbanjarCustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing AlbanjarCustomer.
 */
@Service
@Transactional
public class AlbanjarCustomerServiceImpl implements AlbanjarCustomerService{

    private final Logger log = LoggerFactory.getLogger(AlbanjarCustomerServiceImpl.class);

    private final AlbanjarCustomerRepository albanjarCustomerRepository;

    private final AlbanjarCustomerMapper albanjarCustomerMapper;

    public AlbanjarCustomerServiceImpl(AlbanjarCustomerRepository albanjarCustomerRepository, AlbanjarCustomerMapper albanjarCustomerMapper) {
        this.albanjarCustomerRepository = albanjarCustomerRepository;
        this.albanjarCustomerMapper = albanjarCustomerMapper;
    }

    /**
     * Save a albanjarCustomer.
     *
     * @param albanjarCustomerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AlbanjarCustomerDTO save(AlbanjarCustomerDTO albanjarCustomerDTO) {
        log.debug("Request to save AlbanjarCustomer : {}", albanjarCustomerDTO);
        AlbanjarCustomer albanjarCustomer = albanjarCustomerMapper.toEntity(albanjarCustomerDTO);
        albanjarCustomer = albanjarCustomerRepository.save(albanjarCustomer);
        return albanjarCustomerMapper.toDto(albanjarCustomer);
    }

    /**
     * Get all the albanjarCustomers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AlbanjarCustomerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AlbanjarCustomers");
        return albanjarCustomerRepository.findAll(pageable)
            .map(albanjarCustomerMapper::toDto);
    }

    /**
     * Get one albanjarCustomer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AlbanjarCustomerDTO findOne(Long id) {
        log.debug("Request to get AlbanjarCustomer : {}", id);
        AlbanjarCustomer albanjarCustomer = albanjarCustomerRepository.findOne(id);
        return albanjarCustomerMapper.toDto(albanjarCustomer);
    }

    /**
     * Delete the albanjarCustomer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AlbanjarCustomer : {}", id);
        albanjarCustomerRepository.delete(id);
    }
}
