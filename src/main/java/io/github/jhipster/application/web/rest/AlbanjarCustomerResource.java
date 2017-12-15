package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.AlbanjarCustomerService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.AlbanjarCustomerDTO;
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
 * REST controller for managing AlbanjarCustomer.
 */
@RestController
@RequestMapping("/api")
public class AlbanjarCustomerResource {

    private final Logger log = LoggerFactory.getLogger(AlbanjarCustomerResource.class);

    private static final String ENTITY_NAME = "albanjarCustomer";

    private final AlbanjarCustomerService albanjarCustomerService;

    public AlbanjarCustomerResource(AlbanjarCustomerService albanjarCustomerService) {
        this.albanjarCustomerService = albanjarCustomerService;
    }

    /**
     * POST  /albanjar-customers : Create a new albanjarCustomer.
     *
     * @param albanjarCustomerDTO the albanjarCustomerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new albanjarCustomerDTO, or with status 400 (Bad Request) if the albanjarCustomer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/albanjar-customers")
    @Timed
    public ResponseEntity<AlbanjarCustomerDTO> createAlbanjarCustomer(@Valid @RequestBody AlbanjarCustomerDTO albanjarCustomerDTO) throws URISyntaxException {
        log.debug("REST request to save AlbanjarCustomer : {}", albanjarCustomerDTO);
        if (albanjarCustomerDTO.getId() != null) {
            throw new BadRequestAlertException("A new albanjarCustomer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlbanjarCustomerDTO result = albanjarCustomerService.save(albanjarCustomerDTO);
        return ResponseEntity.created(new URI("/api/albanjar-customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /albanjar-customers : Updates an existing albanjarCustomer.
     *
     * @param albanjarCustomerDTO the albanjarCustomerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated albanjarCustomerDTO,
     * or with status 400 (Bad Request) if the albanjarCustomerDTO is not valid,
     * or with status 500 (Internal Server Error) if the albanjarCustomerDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/albanjar-customers")
    @Timed
    public ResponseEntity<AlbanjarCustomerDTO> updateAlbanjarCustomer(@Valid @RequestBody AlbanjarCustomerDTO albanjarCustomerDTO) throws URISyntaxException {
        log.debug("REST request to update AlbanjarCustomer : {}", albanjarCustomerDTO);
        if (albanjarCustomerDTO.getId() == null) {
            return createAlbanjarCustomer(albanjarCustomerDTO);
        }
        AlbanjarCustomerDTO result = albanjarCustomerService.save(albanjarCustomerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, albanjarCustomerDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /albanjar-customers : get all the albanjarCustomers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of albanjarCustomers in body
     */
    @GetMapping("/albanjar-customers")
    @Timed
    public ResponseEntity<List<AlbanjarCustomerDTO>> getAllAlbanjarCustomers(Pageable pageable) {
        log.debug("REST request to get a page of AlbanjarCustomers");
        Page<AlbanjarCustomerDTO> page = albanjarCustomerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/albanjar-customers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /albanjar-customers/:id : get the "id" albanjarCustomer.
     *
     * @param id the id of the albanjarCustomerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the albanjarCustomerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/albanjar-customers/{id}")
    @Timed
    public ResponseEntity<AlbanjarCustomerDTO> getAlbanjarCustomer(@PathVariable Long id) {
        log.debug("REST request to get AlbanjarCustomer : {}", id);
        AlbanjarCustomerDTO albanjarCustomerDTO = albanjarCustomerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(albanjarCustomerDTO));
    }

    /**
     * DELETE  /albanjar-customers/:id : delete the "id" albanjarCustomer.
     *
     * @param id the id of the albanjarCustomerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/albanjar-customers/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlbanjarCustomer(@PathVariable Long id) {
        log.debug("REST request to delete AlbanjarCustomer : {}", id);
        albanjarCustomerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
