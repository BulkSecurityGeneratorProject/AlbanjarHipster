package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.AlbanjarProductService;
import io.github.jhipster.application.domain.AlbanjarProduct;
import io.github.jhipster.application.repository.AlbanjarProductRepository;
import io.github.jhipster.application.service.dto.AlbanjarProductDTO;
import io.github.jhipster.application.service.mapper.AlbanjarProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing AlbanjarProduct.
 */
@Service
@Transactional
public class AlbanjarProductServiceImpl implements AlbanjarProductService{

    private final Logger log = LoggerFactory.getLogger(AlbanjarProductServiceImpl.class);

    private final AlbanjarProductRepository albanjarProductRepository;

    private final AlbanjarProductMapper albanjarProductMapper;

    public AlbanjarProductServiceImpl(AlbanjarProductRepository albanjarProductRepository, AlbanjarProductMapper albanjarProductMapper) {
        this.albanjarProductRepository = albanjarProductRepository;
        this.albanjarProductMapper = albanjarProductMapper;
    }

    /**
     * Save a albanjarProduct.
     *
     * @param albanjarProductDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AlbanjarProductDTO save(AlbanjarProductDTO albanjarProductDTO) {
        log.debug("Request to save AlbanjarProduct : {}", albanjarProductDTO);
        AlbanjarProduct albanjarProduct = albanjarProductMapper.toEntity(albanjarProductDTO);
        albanjarProduct = albanjarProductRepository.save(albanjarProduct);
        return albanjarProductMapper.toDto(albanjarProduct);
    }

    /**
     * Get all the albanjarProducts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AlbanjarProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AlbanjarProducts");
        return albanjarProductRepository.findAll(pageable)
            .map(albanjarProductMapper::toDto);
    }

    /**
     * Get one albanjarProduct by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AlbanjarProductDTO findOne(Long id) {
        log.debug("Request to get AlbanjarProduct : {}", id);
        AlbanjarProduct albanjarProduct = albanjarProductRepository.findOne(id);
        return albanjarProductMapper.toDto(albanjarProduct);
    }

    /**
     * Delete the albanjarProduct by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AlbanjarProduct : {}", id);
        albanjarProductRepository.delete(id);
    }
}
