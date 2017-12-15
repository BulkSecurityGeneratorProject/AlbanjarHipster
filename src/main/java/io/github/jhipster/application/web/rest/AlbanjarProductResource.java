package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.AlbanjarProductService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.AlbanjarProductDTO;
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
 * REST controller for managing AlbanjarProduct.
 */
@RestController
@RequestMapping("/api")
public class AlbanjarProductResource {

    private final Logger log = LoggerFactory.getLogger(AlbanjarProductResource.class);

    private static final String ENTITY_NAME = "albanjarProduct";

    private final AlbanjarProductService albanjarProductService;

    public AlbanjarProductResource(AlbanjarProductService albanjarProductService) {
        this.albanjarProductService = albanjarProductService;
    }

    /**
     * POST  /albanjar-products : Create a new albanjarProduct.
     *
     * @param albanjarProductDTO the albanjarProductDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new albanjarProductDTO, or with status 400 (Bad Request) if the albanjarProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/albanjar-products")
    @Timed
    public ResponseEntity<AlbanjarProductDTO> createAlbanjarProduct(@Valid @RequestBody AlbanjarProductDTO albanjarProductDTO) throws URISyntaxException {
        log.debug("REST request to save AlbanjarProduct : {}", albanjarProductDTO);
        if (albanjarProductDTO.getId() != null) {
            throw new BadRequestAlertException("A new albanjarProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlbanjarProductDTO result = albanjarProductService.save(albanjarProductDTO);
        return ResponseEntity.created(new URI("/api/albanjar-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /albanjar-products : Updates an existing albanjarProduct.
     *
     * @param albanjarProductDTO the albanjarProductDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated albanjarProductDTO,
     * or with status 400 (Bad Request) if the albanjarProductDTO is not valid,
     * or with status 500 (Internal Server Error) if the albanjarProductDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/albanjar-products")
    @Timed
    public ResponseEntity<AlbanjarProductDTO> updateAlbanjarProduct(@Valid @RequestBody AlbanjarProductDTO albanjarProductDTO) throws URISyntaxException {
        log.debug("REST request to update AlbanjarProduct : {}", albanjarProductDTO);
        if (albanjarProductDTO.getId() == null) {
            return createAlbanjarProduct(albanjarProductDTO);
        }
        AlbanjarProductDTO result = albanjarProductService.save(albanjarProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, albanjarProductDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /albanjar-products : get all the albanjarProducts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of albanjarProducts in body
     */
    @GetMapping("/albanjar-products")
    @Timed
    public ResponseEntity<List<AlbanjarProductDTO>> getAllAlbanjarProducts(Pageable pageable) {
        log.debug("REST request to get a page of AlbanjarProducts");
        Page<AlbanjarProductDTO> page = albanjarProductService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/albanjar-products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /albanjar-products/:id : get the "id" albanjarProduct.
     *
     * @param id the id of the albanjarProductDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the albanjarProductDTO, or with status 404 (Not Found)
     */
    @GetMapping("/albanjar-products/{id}")
    @Timed
    public ResponseEntity<AlbanjarProductDTO> getAlbanjarProduct(@PathVariable Long id) {
        log.debug("REST request to get AlbanjarProduct : {}", id);
        AlbanjarProductDTO albanjarProductDTO = albanjarProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(albanjarProductDTO));
    }

    /**
     * DELETE  /albanjar-products/:id : delete the "id" albanjarProduct.
     *
     * @param id the id of the albanjarProductDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/albanjar-products/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlbanjarProduct(@PathVariable Long id) {
        log.debug("REST request to delete AlbanjarProduct : {}", id);
        albanjarProductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
