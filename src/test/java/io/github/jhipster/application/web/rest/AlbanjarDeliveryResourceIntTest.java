package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.AlbanjarHipsterApp;

import io.github.jhipster.application.domain.AlbanjarDelivery;
import io.github.jhipster.application.repository.AlbanjarDeliveryRepository;
import io.github.jhipster.application.service.AlbanjarDeliveryService;
import io.github.jhipster.application.service.dto.AlbanjarDeliveryDTO;
import io.github.jhipster.application.service.mapper.AlbanjarDeliveryMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AlbanjarDeliveryResource REST controller.
 *
 * @see AlbanjarDeliveryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlbanjarHipsterApp.class)
public class AlbanjarDeliveryResourceIntTest {

    private static final String DEFAULT_CZONE = "AAAAAAAAAA";
    private static final String UPDATED_CZONE = "BBBBBBBBBB";

    private static final String DEFAULT_CDA = "AAAAAAAAAA";
    private static final String UPDATED_CDA = "BBBBBBBBBB";

    private static final Double DEFAULT_QUANTITY = 1D;
    private static final Double UPDATED_QUANTITY = 2D;

    @Autowired
    private AlbanjarDeliveryRepository albanjarDeliveryRepository;

    @Autowired
    private AlbanjarDeliveryMapper albanjarDeliveryMapper;

    @Autowired
    private AlbanjarDeliveryService albanjarDeliveryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlbanjarDeliveryMockMvc;

    private AlbanjarDelivery albanjarDelivery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlbanjarDeliveryResource albanjarDeliveryResource = new AlbanjarDeliveryResource(albanjarDeliveryService);
        this.restAlbanjarDeliveryMockMvc = MockMvcBuilders.standaloneSetup(albanjarDeliveryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlbanjarDelivery createEntity(EntityManager em) {
        AlbanjarDelivery albanjarDelivery = new AlbanjarDelivery()
            .czone(DEFAULT_CZONE)
            .cda(DEFAULT_CDA)
            .quantity(DEFAULT_QUANTITY);
        return albanjarDelivery;
    }

    @Before
    public void initTest() {
        albanjarDelivery = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlbanjarDelivery() throws Exception {
        int databaseSizeBeforeCreate = albanjarDeliveryRepository.findAll().size();

        // Create the AlbanjarDelivery
        AlbanjarDeliveryDTO albanjarDeliveryDTO = albanjarDeliveryMapper.toDto(albanjarDelivery);
        restAlbanjarDeliveryMockMvc.perform(post("/api/albanjar-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarDeliveryDTO)))
            .andExpect(status().isCreated());

        // Validate the AlbanjarDelivery in the database
        List<AlbanjarDelivery> albanjarDeliveryList = albanjarDeliveryRepository.findAll();
        assertThat(albanjarDeliveryList).hasSize(databaseSizeBeforeCreate + 1);
        AlbanjarDelivery testAlbanjarDelivery = albanjarDeliveryList.get(albanjarDeliveryList.size() - 1);
        assertThat(testAlbanjarDelivery.getCzone()).isEqualTo(DEFAULT_CZONE);
        assertThat(testAlbanjarDelivery.getCda()).isEqualTo(DEFAULT_CDA);
        assertThat(testAlbanjarDelivery.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    public void createAlbanjarDeliveryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = albanjarDeliveryRepository.findAll().size();

        // Create the AlbanjarDelivery with an existing ID
        albanjarDelivery.setId(1L);
        AlbanjarDeliveryDTO albanjarDeliveryDTO = albanjarDeliveryMapper.toDto(albanjarDelivery);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlbanjarDeliveryMockMvc.perform(post("/api/albanjar-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarDeliveryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AlbanjarDelivery in the database
        List<AlbanjarDelivery> albanjarDeliveryList = albanjarDeliveryRepository.findAll();
        assertThat(albanjarDeliveryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCzoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = albanjarDeliveryRepository.findAll().size();
        // set the field null
        albanjarDelivery.setCzone(null);

        // Create the AlbanjarDelivery, which fails.
        AlbanjarDeliveryDTO albanjarDeliveryDTO = albanjarDeliveryMapper.toDto(albanjarDelivery);

        restAlbanjarDeliveryMockMvc.perform(post("/api/albanjar-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarDeliveryDTO)))
            .andExpect(status().isBadRequest());

        List<AlbanjarDelivery> albanjarDeliveryList = albanjarDeliveryRepository.findAll();
        assertThat(albanjarDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCdaIsRequired() throws Exception {
        int databaseSizeBeforeTest = albanjarDeliveryRepository.findAll().size();
        // set the field null
        albanjarDelivery.setCda(null);

        // Create the AlbanjarDelivery, which fails.
        AlbanjarDeliveryDTO albanjarDeliveryDTO = albanjarDeliveryMapper.toDto(albanjarDelivery);

        restAlbanjarDeliveryMockMvc.perform(post("/api/albanjar-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarDeliveryDTO)))
            .andExpect(status().isBadRequest());

        List<AlbanjarDelivery> albanjarDeliveryList = albanjarDeliveryRepository.findAll();
        assertThat(albanjarDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = albanjarDeliveryRepository.findAll().size();
        // set the field null
        albanjarDelivery.setQuantity(null);

        // Create the AlbanjarDelivery, which fails.
        AlbanjarDeliveryDTO albanjarDeliveryDTO = albanjarDeliveryMapper.toDto(albanjarDelivery);

        restAlbanjarDeliveryMockMvc.perform(post("/api/albanjar-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarDeliveryDTO)))
            .andExpect(status().isBadRequest());

        List<AlbanjarDelivery> albanjarDeliveryList = albanjarDeliveryRepository.findAll();
        assertThat(albanjarDeliveryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAlbanjarDeliveries() throws Exception {
        // Initialize the database
        albanjarDeliveryRepository.saveAndFlush(albanjarDelivery);

        // Get all the albanjarDeliveryList
        restAlbanjarDeliveryMockMvc.perform(get("/api/albanjar-deliveries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(albanjarDelivery.getId().intValue())))
            .andExpect(jsonPath("$.[*].czone").value(hasItem(DEFAULT_CZONE.toString())))
            .andExpect(jsonPath("$.[*].cda").value(hasItem(DEFAULT_CDA.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())));
    }

    @Test
    @Transactional
    public void getAlbanjarDelivery() throws Exception {
        // Initialize the database
        albanjarDeliveryRepository.saveAndFlush(albanjarDelivery);

        // Get the albanjarDelivery
        restAlbanjarDeliveryMockMvc.perform(get("/api/albanjar-deliveries/{id}", albanjarDelivery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(albanjarDelivery.getId().intValue()))
            .andExpect(jsonPath("$.czone").value(DEFAULT_CZONE.toString()))
            .andExpect(jsonPath("$.cda").value(DEFAULT_CDA.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAlbanjarDelivery() throws Exception {
        // Get the albanjarDelivery
        restAlbanjarDeliveryMockMvc.perform(get("/api/albanjar-deliveries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlbanjarDelivery() throws Exception {
        // Initialize the database
        albanjarDeliveryRepository.saveAndFlush(albanjarDelivery);
        int databaseSizeBeforeUpdate = albanjarDeliveryRepository.findAll().size();

        // Update the albanjarDelivery
        AlbanjarDelivery updatedAlbanjarDelivery = albanjarDeliveryRepository.findOne(albanjarDelivery.getId());
        // Disconnect from session so that the updates on updatedAlbanjarDelivery are not directly saved in db
        em.detach(updatedAlbanjarDelivery);
        updatedAlbanjarDelivery
            .czone(UPDATED_CZONE)
            .cda(UPDATED_CDA)
            .quantity(UPDATED_QUANTITY);
        AlbanjarDeliveryDTO albanjarDeliveryDTO = albanjarDeliveryMapper.toDto(updatedAlbanjarDelivery);

        restAlbanjarDeliveryMockMvc.perform(put("/api/albanjar-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarDeliveryDTO)))
            .andExpect(status().isOk());

        // Validate the AlbanjarDelivery in the database
        List<AlbanjarDelivery> albanjarDeliveryList = albanjarDeliveryRepository.findAll();
        assertThat(albanjarDeliveryList).hasSize(databaseSizeBeforeUpdate);
        AlbanjarDelivery testAlbanjarDelivery = albanjarDeliveryList.get(albanjarDeliveryList.size() - 1);
        assertThat(testAlbanjarDelivery.getCzone()).isEqualTo(UPDATED_CZONE);
        assertThat(testAlbanjarDelivery.getCda()).isEqualTo(UPDATED_CDA);
        assertThat(testAlbanjarDelivery.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingAlbanjarDelivery() throws Exception {
        int databaseSizeBeforeUpdate = albanjarDeliveryRepository.findAll().size();

        // Create the AlbanjarDelivery
        AlbanjarDeliveryDTO albanjarDeliveryDTO = albanjarDeliveryMapper.toDto(albanjarDelivery);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAlbanjarDeliveryMockMvc.perform(put("/api/albanjar-deliveries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarDeliveryDTO)))
            .andExpect(status().isCreated());

        // Validate the AlbanjarDelivery in the database
        List<AlbanjarDelivery> albanjarDeliveryList = albanjarDeliveryRepository.findAll();
        assertThat(albanjarDeliveryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAlbanjarDelivery() throws Exception {
        // Initialize the database
        albanjarDeliveryRepository.saveAndFlush(albanjarDelivery);
        int databaseSizeBeforeDelete = albanjarDeliveryRepository.findAll().size();

        // Get the albanjarDelivery
        restAlbanjarDeliveryMockMvc.perform(delete("/api/albanjar-deliveries/{id}", albanjarDelivery.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AlbanjarDelivery> albanjarDeliveryList = albanjarDeliveryRepository.findAll();
        assertThat(albanjarDeliveryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlbanjarDelivery.class);
        AlbanjarDelivery albanjarDelivery1 = new AlbanjarDelivery();
        albanjarDelivery1.setId(1L);
        AlbanjarDelivery albanjarDelivery2 = new AlbanjarDelivery();
        albanjarDelivery2.setId(albanjarDelivery1.getId());
        assertThat(albanjarDelivery1).isEqualTo(albanjarDelivery2);
        albanjarDelivery2.setId(2L);
        assertThat(albanjarDelivery1).isNotEqualTo(albanjarDelivery2);
        albanjarDelivery1.setId(null);
        assertThat(albanjarDelivery1).isNotEqualTo(albanjarDelivery2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlbanjarDeliveryDTO.class);
        AlbanjarDeliveryDTO albanjarDeliveryDTO1 = new AlbanjarDeliveryDTO();
        albanjarDeliveryDTO1.setId(1L);
        AlbanjarDeliveryDTO albanjarDeliveryDTO2 = new AlbanjarDeliveryDTO();
        assertThat(albanjarDeliveryDTO1).isNotEqualTo(albanjarDeliveryDTO2);
        albanjarDeliveryDTO2.setId(albanjarDeliveryDTO1.getId());
        assertThat(albanjarDeliveryDTO1).isEqualTo(albanjarDeliveryDTO2);
        albanjarDeliveryDTO2.setId(2L);
        assertThat(albanjarDeliveryDTO1).isNotEqualTo(albanjarDeliveryDTO2);
        albanjarDeliveryDTO1.setId(null);
        assertThat(albanjarDeliveryDTO1).isNotEqualTo(albanjarDeliveryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(albanjarDeliveryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(albanjarDeliveryMapper.fromId(null)).isNull();
    }
}
