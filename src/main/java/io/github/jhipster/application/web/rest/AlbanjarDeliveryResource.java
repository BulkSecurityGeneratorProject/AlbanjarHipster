package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.AlbanjarDeliveryService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.AlbanjarDeliveryDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AlbanjarDelivery.
 */
@RestController
@RequestMapping("/api")
public class AlbanjarDeliveryResource {

    private final Logger log = LoggerFactory.getLogger(AlbanjarDeliveryResource.class);

    private static final String ENTITY_NAME = "albanjarDelivery";

    private final AlbanjarDeliveryService albanjarDeliveryService;

    public AlbanjarDeliveryResource(AlbanjarDeliveryService albanjarDeliveryService) {
        this.albanjarDeliveryService = albanjarDeliveryService;
    }

    /**
     * POST  /albanjar-deliveries : Create a new albanjarDelivery.
     *
     * @param albanjarDeliveryDTO the albanjarDeliveryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new albanjarDeliveryDTO, or with status 400 (Bad Request) if the albanjarDelivery has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/albanjar-deliveries")
    @Timed
    public ResponseEntity<AlbanjarDeliveryDTO> createAlbanjarDelivery(@Valid @RequestBody AlbanjarDeliveryDTO albanjarDeliveryDTO) throws URISyntaxException {
        log.debug("REST request to save AlbanjarDelivery : {}", albanjarDeliveryDTO);
        if (albanjarDeliveryDTO.getId() != null) {
            throw new BadRequestAlertException("A new albanjarDelivery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlbanjarDeliveryDTO result = albanjarDeliveryService.save(albanjarDeliveryDTO);
        return ResponseEntity.created(new URI("/api/albanjar-deliveries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /albanjar-deliveries : Updates an existing albanjarDelivery.
     *
     * @param albanjarDeliveryDTO the albanjarDeliveryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated albanjarDeliveryDTO,
     * or with status 400 (Bad Request) if the albanjarDeliveryDTO is not valid,
     * or with status 500 (Internal Server Error) if the albanjarDeliveryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/albanjar-deliveries")
    @Timed
    public ResponseEntity<AlbanjarDeliveryDTO> updateAlbanjarDelivery(@Valid @RequestBody AlbanjarDeliveryDTO albanjarDeliveryDTO) throws URISyntaxException {
        log.debug("REST request to update AlbanjarDelivery : {}", albanjarDeliveryDTO);
        if (albanjarDeliveryDTO.getId() == null) {
            return createAlbanjarDelivery(albanjarDeliveryDTO);
        }
        AlbanjarDeliveryDTO result = albanjarDeliveryService.save(albanjarDeliveryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, albanjarDeliveryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /albanjar-deliveries : get all the albanjarDeliveries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of albanjarDeliveries in body
     */
    @GetMapping("/albanjar-deliveries")
    @Timed
    public ResponseEntity<List<AlbanjarDeliveryDTO>> getAllAlbanjarDeliveries(Pageable pageable) {
        log.debug("REST request to get a page of AlbanjarDeliveries");
        Page<AlbanjarDeliveryDTO> page = albanjarDeliveryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/albanjar-deliveries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /albanjar-deliveries/:id : get the "id" albanjarDelivery.
     *
     * @param id the id of the albanjarDeliveryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the albanjarDeliveryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/albanjar-deliveries/{id}")
    @Timed
    public ResponseEntity<AlbanjarDeliveryDTO> getAlbanjarDelivery(@PathVariable Long id) {
        log.debug("REST request to get AlbanjarDelivery : {}", id);
        AlbanjarDeliveryDTO albanjarDeliveryDTO = albanjarDeliveryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(albanjarDeliveryDTO));
    }

    /**
     * DELETE  /albanjar-deliveries/:id : delete the "id" albanjarDelivery.
     *
     * @param id the id of the albanjarDeliveryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/albanjar-deliveries/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlbanjarDelivery(@PathVariable Long id) {
        log.debug("REST request to delete AlbanjarDelivery : {}", id);
        albanjarDeliveryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
