package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.AlbanjarHipsterApp;

import io.github.jhipster.application.domain.AlbanjarProduct;
import io.github.jhipster.application.repository.AlbanjarProductRepository;
import io.github.jhipster.application.service.AlbanjarProductService;
import io.github.jhipster.application.service.dto.AlbanjarProductDTO;
import io.github.jhipster.application.service.mapper.AlbanjarProductMapper;
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
 * Test class for the AlbanjarProductResource REST controller.
 *
 * @see AlbanjarProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlbanjarHipsterApp.class)
public class AlbanjarProductResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_PU = 1D;
    private static final Double UPDATED_PU = 2D;

    @Autowired
    private AlbanjarProductRepository albanjarProductRepository;

    @Autowired
    private AlbanjarProductMapper albanjarProductMapper;

    @Autowired
    private AlbanjarProductService albanjarProductService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlbanjarProductMockMvc;

    private AlbanjarProduct albanjarProduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlbanjarProductResource albanjarProductResource = new AlbanjarProductResource(albanjarProductService);
        this.restAlbanjarProductMockMvc = MockMvcBuilders.standaloneSetup(albanjarProductResource)
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
    public static AlbanjarProduct createEntity(EntityManager em) {
        AlbanjarProduct albanjarProduct = new AlbanjarProduct()
            .name(DEFAULT_NAME)
            .pu(DEFAULT_PU);
        return albanjarProduct;
    }

    @Before
    public void initTest() {
        albanjarProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlbanjarProduct() throws Exception {
        int databaseSizeBeforeCreate = albanjarProductRepository.findAll().size();

        // Create the AlbanjarProduct
        AlbanjarProductDTO albanjarProductDTO = albanjarProductMapper.toDto(albanjarProduct);
        restAlbanjarProductMockMvc.perform(post("/api/albanjar-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarProductDTO)))
            .andExpect(status().isCreated());

        // Validate the AlbanjarProduct in the database
        List<AlbanjarProduct> albanjarProductList = albanjarProductRepository.findAll();
        assertThat(albanjarProductList).hasSize(databaseSizeBeforeCreate + 1);
        AlbanjarProduct testAlbanjarProduct = albanjarProductList.get(albanjarProductList.size() - 1);
        assertThat(testAlbanjarProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAlbanjarProduct.getPu()).isEqualTo(DEFAULT_PU);
    }

    @Test
    @Transactional
    public void createAlbanjarProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = albanjarProductRepository.findAll().size();

        // Create the AlbanjarProduct with an existing ID
        albanjarProduct.setId(1L);
        AlbanjarProductDTO albanjarProductDTO = albanjarProductMapper.toDto(albanjarProduct);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlbanjarProductMockMvc.perform(post("/api/albanjar-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarProductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AlbanjarProduct in the database
        List<AlbanjarProduct> albanjarProductList = albanjarProductRepository.findAll();
        assertThat(albanjarProductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = albanjarProductRepository.findAll().size();
        // set the field null
        albanjarProduct.setName(null);

        // Create the AlbanjarProduct, which fails.
        AlbanjarProductDTO albanjarProductDTO = albanjarProductMapper.toDto(albanjarProduct);

        restAlbanjarProductMockMvc.perform(post("/api/albanjar-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarProductDTO)))
            .andExpect(status().isBadRequest());

        List<AlbanjarProduct> albanjarProductList = albanjarProductRepository.findAll();
        assertThat(albanjarProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPuIsRequired() throws Exception {
        int databaseSizeBeforeTest = albanjarProductRepository.findAll().size();
        // set the field null
        albanjarProduct.setPu(null);

        // Create the AlbanjarProduct, which fails.
        AlbanjarProductDTO albanjarProductDTO = albanjarProductMapper.toDto(albanjarProduct);

        restAlbanjarProductMockMvc.perform(post("/api/albanjar-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarProductDTO)))
            .andExpect(status().isBadRequest());

        List<AlbanjarProduct> albanjarProductList = albanjarProductRepository.findAll();
        assertThat(albanjarProductList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAlbanjarProducts() throws Exception {
        // Initialize the database
        albanjarProductRepository.saveAndFlush(albanjarProduct);

        // Get all the albanjarProductList
        restAlbanjarProductMockMvc.perform(get("/api/albanjar-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(albanjarProduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].pu").value(hasItem(DEFAULT_PU.doubleValue())));
    }

    @Test
    @Transactional
    public void getAlbanjarProduct() throws Exception {
        // Initialize the database
        albanjarProductRepository.saveAndFlush(albanjarProduct);

        // Get the albanjarProduct
        restAlbanjarProductMockMvc.perform(get("/api/albanjar-products/{id}", albanjarProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(albanjarProduct.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.pu").value(DEFAULT_PU.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAlbanjarProduct() throws Exception {
        // Get the albanjarProduct
        restAlbanjarProductMockMvc.perform(get("/api/albanjar-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlbanjarProduct() throws Exception {
        // Initialize the database
        albanjarProductRepository.saveAndFlush(albanjarProduct);
        int databaseSizeBeforeUpdate = albanjarProductRepository.findAll().size();

        // Update the albanjarProduct
        AlbanjarProduct updatedAlbanjarProduct = albanjarProductRepository.findOne(albanjarProduct.getId());
        // Disconnect from session so that the updates on updatedAlbanjarProduct are not directly saved in db
        em.detach(updatedAlbanjarProduct);
        updatedAlbanjarProduct
            .name(UPDATED_NAME)
            .pu(UPDATED_PU);
        AlbanjarProductDTO albanjarProductDTO = albanjarProductMapper.toDto(updatedAlbanjarProduct);

        restAlbanjarProductMockMvc.perform(put("/api/albanjar-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarProductDTO)))
            .andExpect(status().isOk());

        // Validate the AlbanjarProduct in the database
        List<AlbanjarProduct> albanjarProductList = albanjarProductRepository.findAll();
        assertThat(albanjarProductList).hasSize(databaseSizeBeforeUpdate);
        AlbanjarProduct testAlbanjarProduct = albanjarProductList.get(albanjarProductList.size() - 1);
        assertThat(testAlbanjarProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAlbanjarProduct.getPu()).isEqualTo(UPDATED_PU);
    }

    @Test
    @Transactional
    public void updateNonExistingAlbanjarProduct() throws Exception {
        int databaseSizeBeforeUpdate = albanjarProductRepository.findAll().size();

        // Create the AlbanjarProduct
        AlbanjarProductDTO albanjarProductDTO = albanjarProductMapper.toDto(albanjarProduct);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAlbanjarProductMockMvc.perform(put("/api/albanjar-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(albanjarProductDTO)))
            .andExpect(status().isCreated());

        // Validate the AlbanjarProduct in the database
        List<AlbanjarProduct> albanjarProductList = albanjarProductRepository.findAll();
        assertThat(albanjarProductList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAlbanjarProduct() throws Exception {
        // Initialize the database
        albanjarProductRepository.saveAndFlush(albanjarProduct);
        int databaseSizeBeforeDelete = albanjarProductRepository.findAll().size();

        // Get the albanjarProduct
        restAlbanjarProductMockMvc.perform(delete("/api/albanjar-products/{id}", albanjarProduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AlbanjarProduct> albanjarProductList = albanjarProductRepository.findAll();
        assertThat(albanjarProductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlbanjarProduct.class);
        AlbanjarProduct albanjarProduct1 = new AlbanjarProduct();
        albanjarProduct1.setId(1L);
        AlbanjarProduct albanjarProduct2 = new AlbanjarProduct();
        albanjarProduct2.setId(albanjarProduct1.getId());
        assertThat(albanjarProduct1).isEqualTo(albanjarProduct2);
        albanjarProduct2.setId(2L);
        assertThat(albanjarProduct1).isNotEqualTo(albanjarProduct2);
        albanjarProduct1.setId(null);
        assertThat(albanjarProduct1).isNotEqualTo(albanjarProduct2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlbanjarProductDTO.class);
        AlbanjarProductDTO albanjarProductDTO1 = new AlbanjarProductDTO();
        albanjarProductDTO1.setId(1L);
        AlbanjarProductDTO albanjarProductDTO2 = new AlbanjarProductDTO();
        assertThat(albanjarProductDTO1).isNotEqualTo(albanjarProductDTO2);
        albanjarProductDTO2.setId(albanjarProductDTO1.getId());
        assertThat(albanjarProductDTO1).isEqualTo(albanjarProductDTO2);
        albanjarProductDTO2.setId(2L);
        assertThat(albanjarProductDTO1).isNotEqualTo(albanjarProductDTO2);
        albanjarProductDTO1.setId(null);
        assertThat(albanjarProductDTO1).isNotEqualTo(albanjarProductDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(albanjarProductMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(albanjarProductMapper.fromId(null)).isNull();
    }
}
