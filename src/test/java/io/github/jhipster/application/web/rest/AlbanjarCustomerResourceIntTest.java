package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.AlbanjarHipsterApp;

import io.github.jhipster.application.domain.AlbanjarCustomer;
import io.github.jhipster.application.repository.AlbanjarCustomerRepository;
import io.github.jhipster.application.service.AlbanjarCustomerService;
import io.github.jhipster.application.service.dto.AlbanjarCustomerDTO;
import io.github.jhipster.application.service.mapper.AlbanjarCustomerMapper;
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

import io.github.jhipster.application.domain.enumeration.Gender;
/**
 * Test class for the AlbanjarCustomerResource REST controller.
 *
 * @see AlbanjarCustomerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlbanjarHipsterApp.class)
public class AlbanjarCustomerResourceIntTest {

    private static final Gender DEFAULT_GENDER = Gender.MR;
    private static final Gender UPDATED_GENDER = Gender.MRS;

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CIN = "AAAAAAAAAA";
    private static final String UPDATED_CIN = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    @Autowired
    private AlbanjarCustomerRepository albanjarCustomerRepository;

    @Autowired
    private AlbanjarCustomerMapper albanjarCustomerMapper;

    @Autowired
    private AlbanjarCustomerService albanjarCustomerService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlbanjarCustomerMockMvc;

    private AlbanjarCustomer albanjarCustomer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlbanjarCustomerResource albanjarCustomerResource = new AlbanjarCustomerResource(albanjarCustomerService);
        this.restAlbanjarCustomerMockMvc = MockMvcBuilders.standaloneSetup(albanjarCustomerResource)
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
    public static AlbanjarCustomer createEntity(EntityManager em) {
        AlbanjarCustomer albanjarCustomer = new AlbanjarCustomer()
            .gender(DEFAULT_GENDER)
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .cin(DEFAULT_CIN)
            .adresse(DEFAULT_ADRESSE);
        return albanjarCustomer;
    }

    @Before
    public void initTest() {
        albanjarCustomer = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlbanjarCustomer() throws Exception {
        int databaseSizeBeforeCreate = albanjarCustomerRepository.findAll().size();

        // Create the AlbanjarCustomer
        AlbanjarCustomerDTO albanjarCustomerDTO = albanjarCustomerMapper.toDto(albanjarCustomer);
        restAlbanjarCustomerMockMvc.perform(post("/api/albanjar-customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarCustomerDTO)))
            .andExpect(status().isCreated());

        // Validate the AlbanjarCustomer in the database
        List<AlbanjarCustomer> albanjarCustomerList = albanjarCustomerRepository.findAll();
        assertThat(albanjarCustomerList).hasSize(databaseSizeBeforeCreate + 1);
        AlbanjarCustomer testAlbanjarCustomer = albanjarCustomerList.get(albanjarCustomerList.size() - 1);
        assertThat(testAlbanjarCustomer.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testAlbanjarCustomer.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testAlbanjarCustomer.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testAlbanjarCustomer.getCin()).isEqualTo(DEFAULT_CIN);
        assertThat(testAlbanjarCustomer.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
    }

    @Test
    @Transactional
    public void createAlbanjarCustomerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = albanjarCustomerRepository.findAll().size();

        // Create the AlbanjarCustomer with an existing ID
        albanjarCustomer.setId(1L);
        AlbanjarCustomerDTO albanjarCustomerDTO = albanjarCustomerMapper.toDto(albanjarCustomer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlbanjarCustomerMockMvc.perform(post("/api/albanjar-customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarCustomerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AlbanjarCustomer in the database
        List<AlbanjarCustomer> albanjarCustomerList = albanjarCustomerRepository.findAll();
        assertThat(albanjarCustomerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = albanjarCustomerRepository.findAll().size();
        // set the field null
        albanjarCustomer.setGender(null);

        // Create the AlbanjarCustomer, which fails.
        AlbanjarCustomerDTO albanjarCustomerDTO = albanjarCustomerMapper.toDto(albanjarCustomer);

        restAlbanjarCustomerMockMvc.perform(post("/api/albanjar-customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarCustomerDTO)))
            .andExpect(status().isBadRequest());

        List<AlbanjarCustomer> albanjarCustomerList = albanjarCustomerRepository.findAll();
        assertThat(albanjarCustomerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = albanjarCustomerRepository.findAll().size();
        // set the field null
        albanjarCustomer.setFirstname(null);

        // Create the AlbanjarCustomer, which fails.
        AlbanjarCustomerDTO albanjarCustomerDTO = albanjarCustomerMapper.toDto(albanjarCustomer);

        restAlbanjarCustomerMockMvc.perform(post("/api/albanjar-customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarCustomerDTO)))
            .andExpect(status().isBadRequest());

        List<AlbanjarCustomer> albanjarCustomerList = albanjarCustomerRepository.findAll();
        assertThat(albanjarCustomerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = albanjarCustomerRepository.findAll().size();
        // set the field null
        albanjarCustomer.setLastname(null);

        // Create the AlbanjarCustomer, which fails.
        AlbanjarCustomerDTO albanjarCustomerDTO = albanjarCustomerMapper.toDto(albanjarCustomer);

        restAlbanjarCustomerMockMvc.perform(post("/api/albanjar-customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarCustomerDTO)))
            .andExpect(status().isBadRequest());

        List<AlbanjarCustomer> albanjarCustomerList = albanjarCustomerRepository.findAll();
        assertThat(albanjarCustomerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCinIsRequired() throws Exception {
        int databaseSizeBeforeTest = albanjarCustomerRepository.findAll().size();
        // set the field null
        albanjarCustomer.setCin(null);

        // Create the AlbanjarCustomer, which fails.
        AlbanjarCustomerDTO albanjarCustomerDTO = albanjarCustomerMapper.toDto(albanjarCustomer);

        restAlbanjarCustomerMockMvc.perform(post("/api/albanjar-customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarCustomerDTO)))
            .andExpect(status().isBadRequest());

        List<AlbanjarCustomer> albanjarCustomerList = albanjarCustomerRepository.findAll();
        assertThat(albanjarCustomerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAlbanjarCustomers() throws Exception {
        // Initialize the database
        albanjarCustomerRepository.saveAndFlush(albanjarCustomer);

        // Get all the albanjarCustomerList
        restAlbanjarCustomerMockMvc.perform(get("/api/albanjar-customers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(albanjarCustomer.getId().intValue())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME.toString())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME.toString())))
            .andExpect(jsonPath("$.[*].cin").value(hasItem(DEFAULT_CIN.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())));
    }

    @Test
    @Transactional
    public void getAlbanjarCustomer() throws Exception {
        // Initialize the database
        albanjarCustomerRepository.saveAndFlush(albanjarCustomer);

        // Get the albanjarCustomer
        restAlbanjarCustomerMockMvc.perform(get("/api/albanjar-customers/{id}", albanjarCustomer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(albanjarCustomer.getId().intValue()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME.toString()))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME.toString()))
            .andExpect(jsonPath("$.cin").value(DEFAULT_CIN.toString()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAlbanjarCustomer() throws Exception {
        // Get the albanjarCustomer
        restAlbanjarCustomerMockMvc.perform(get("/api/albanjar-customers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlbanjarCustomer() throws Exception {
        // Initialize the database
        albanjarCustomerRepository.saveAndFlush(albanjarCustomer);
        int databaseSizeBeforeUpdate = albanjarCustomerRepository.findAll().size();

        // Update the albanjarCustomer
        AlbanjarCustomer updatedAlbanjarCustomer = albanjarCustomerRepository.findOne(albanjarCustomer.getId());
        // Disconnect from session so that the updates on updatedAlbanjarCustomer are not directly saved in db
        em.detach(updatedAlbanjarCustomer);
        updatedAlbanjarCustomer
            .gender(UPDATED_GENDER)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .cin(UPDATED_CIN)
            .adresse(UPDATED_ADRESSE);
        AlbanjarCustomerDTO albanjarCustomerDTO = albanjarCustomerMapper.toDto(updatedAlbanjarCustomer);

        restAlbanjarCustomerMockMvc.perform(put("/api/albanjar-customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarCustomerDTO)))
            .andExpect(status().isOk());

        // Validate the AlbanjarCustomer in the database
        List<AlbanjarCustomer> albanjarCustomerList = albanjarCustomerRepository.findAll();
        assertThat(albanjarCustomerList).hasSize(databaseSizeBeforeUpdate);
        AlbanjarCustomer testAlbanjarCustomer = albanjarCustomerList.get(albanjarCustomerList.size() - 1);
        assertThat(testAlbanjarCustomer.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testAlbanjarCustomer.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testAlbanjarCustomer.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testAlbanjarCustomer.getCin()).isEqualTo(UPDATED_CIN);
        assertThat(testAlbanjarCustomer.getAdresse()).isEqualTo(UPDATED_ADRESSE);
    }

    @Test
    @Transactional
    public void updateNonExistingAlbanjarCustomer() throws Exception {
        int databaseSizeBeforeUpdate = albanjarCustomerRepository.findAll().size();

        // Create the AlbanjarCustomer
        AlbanjarCustomerDTO albanjarCustomerDTO = albanjarCustomerMapper.toDto(albanjarCustomer);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAlbanjarCustomerMockMvc.perform(put("/api/albanjar-customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarCustomerDTO)))
            .andExpect(status().isCreated());

        // Validate the AlbanjarCustomer in the database
        List<AlbanjarCustomer> albanjarCustomerList = albanjarCustomerRepository.findAll();
        assertThat(albanjarCustomerList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAlbanjarCustomer() throws Exception {
        // Initialize the database
        albanjarCustomerRepository.saveAndFlush(albanjarCustomer);
        int databaseSizeBeforeDelete = albanjarCustomerRepository.findAll().size();

        // Get the albanjarCustomer
        restAlbanjarCustomerMockMvc.perform(delete("/api/albanjar-customers/{id}", albanjarCustomer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AlbanjarCustomer> albanjarCustomerList = albanjarCustomerRepository.findAll();
        assertThat(albanjarCustomerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlbanjarCustomer.class);
        AlbanjarCustomer albanjarCustomer1 = new AlbanjarCustomer();
        albanjarCustomer1.setId(1L);
        AlbanjarCustomer albanjarCustomer2 = new AlbanjarCustomer();
        albanjarCustomer2.setId(albanjarCustomer1.getId());
        assertThat(albanjarCustomer1).isEqualTo(albanjarCustomer2);
        albanjarCustomer2.setId(2L);
        assertThat(albanjarCustomer1).isNotEqualTo(albanjarCustomer2);
        albanjarCustomer1.setId(null);
        assertThat(albanjarCustomer1).isNotEqualTo(albanjarCustomer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlbanjarCustomerDTO.class);
        AlbanjarCustomerDTO albanjarCustomerDTO1 = new AlbanjarCustomerDTO();
        albanjarCustomerDTO1.setId(1L);
        AlbanjarCustomerDTO albanjarCustomerDTO2 = new AlbanjarCustomerDTO();
        assertThat(albanjarCustomerDTO1).isNotEqualTo(albanjarCustomerDTO2);
        albanjarCustomerDTO2.setId(albanjarCustomerDTO1.getId());
        assertThat(albanjarCustomerDTO1).isEqualTo(albanjarCustomerDTO2);
        albanjarCustomerDTO2.setId(2L);
        assertThat(albanjarCustomerDTO1).isNotEqualTo(albanjarCustomerDTO2);
        albanjarCustomerDTO1.setId(null);
        assertThat(albanjarCustomerDTO1).isNotEqualTo(albanjarCustomerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(albanjarCustomerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(albanjarCustomerMapper.fromId(null)).isNull();
    }
}
