package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.AlbanjarDeliveryService;
import io.github.jhipster.application.domain.AlbanjarDelivery;
import io.github.jhipster.application.repository.AlbanjarDeliveryRepository;
import io.github.jhipster.application.service.dto.AlbanjarDeliveryDTO;
import io.github.jhipster.application.service.mapper.AlbanjarDeliveryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing AlbanjarDelivery.
 */
@Service
@Transactional
public class AlbanjarDeliveryServiceImpl implements AlbanjarDeliveryService{

    private final Logger log = LoggerFactory.getLogger(AlbanjarDeliveryServiceImpl.class);

    private final AlbanjarDeliveryRepository albanjarDeliveryRepository;

    private final AlbanjarDeliveryMapper albanjarDeliveryMapper;

    public AlbanjarDeliveryServiceImpl(AlbanjarDeliveryRepository albanjarDeliveryRepository, AlbanjarDeliveryMapper albanjarDeliveryMapper) {
        this.albanjarDeliveryRepository = albanjarDeliveryRepository;
        this.albanjarDeliveryMapper = albanjarDeliveryMapper;
    }

    /**
     * Save a albanjarDelivery.
     *
     * @param albanjarDeliveryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AlbanjarDeliveryDTO save(AlbanjarDeliveryDTO albanjarDeliveryDTO) {
        log.debug("Request to save AlbanjarDelivery : {}", albanjarDeliveryDTO);
        AlbanjarDelivery albanjarDelivery = albanjarDeliveryMapper.toEntity(albanjarDeliveryDTO);
        albanjarDelivery = albanjarDeliveryRepository.save(albanjarDelivery);
        return albanjarDeliveryMapper.toDto(albanjarDelivery);
    }

    /**
     * Get all the albanjarDeliveries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AlbanjarDeliveryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AlbanjarDeliveries");
        return albanjarDeliveryRepository.findAll(pageable)
            .map(albanjarDeliveryMapper::toDto);
    }

    /**
     * Get one albanjarDelivery by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AlbanjarDeliveryDTO findOne(Long id) {
        log.debug("Request to get AlbanjarDelivery : {}", id);
        AlbanjarDelivery albanjarDelivery = albanjarDeliveryRepository.findOne(id);
        return albanjarDeliveryMapper.toDto(albanjarDelivery);
    }

    /**
     * Delete the albanjarDelivery by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AlbanjarDelivery : {}", id);
        albanjarDeliveryRepository.delete(id);
    }
}
