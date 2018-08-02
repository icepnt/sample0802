package com.icepoint.sample.application.web.rest;

import com.icepoint.sample.application.Sample0802App;

import com.icepoint.sample.application.domain.AssetsLog;
import com.icepoint.sample.application.repository.AssetsLogRepository;
import com.icepoint.sample.application.service.AssetsLogService;
import com.icepoint.sample.application.service.dto.AssetsLogDTO;
import com.icepoint.sample.application.service.mapper.AssetsLogMapper;
import com.icepoint.sample.application.web.rest.errors.ExceptionTranslator;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.icepoint.sample.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AssetsLogResource REST controller.
 *
 * @see AssetsLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Sample0802App.class)
public class AssetsLogResourceIntTest {

    private static final String DEFAULT_DIGITAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DIGITAL_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_CUST_ID = 1L;
    private static final Long UPDATED_CUST_ID = 2L;

    private static final String DEFAULT_SECURITY_ACCOUNT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SECURITY_ACCOUNT_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ASSETS_TYPE = 1;
    private static final Integer UPDATED_ASSETS_TYPE = 2;

    private static final String DEFAULT_ASSETS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ASSETS_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ASSETS_SUBTYPE = 1;
    private static final Integer UPDATED_ASSETS_SUBTYPE = 2;

    private static final Instant DEFAULT_TRANS_DATETIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRANS_DATETIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_TRADE_AMT = 1F;
    private static final Float UPDATED_TRADE_AMT = 2F;

    private static final Float DEFAULT_FEE_AMT = 1F;
    private static final Float UPDATED_FEE_AMT = 2F;

    private static final Float DEFAULT_START_BAL = 1F;
    private static final Float UPDATED_START_BAL = 2F;

    private static final Float DEFAULT_END_BAL = 1F;
    private static final Float UPDATED_END_BAL = 2F;

    private static final Integer DEFAULT_TRADE_OBJ_TYPE = 1;
    private static final Integer UPDATED_TRADE_OBJ_TYPE = 2;

    private static final Long DEFAULT_TRADE_OBJ_ID = 1L;
    private static final Long UPDATED_TRADE_OBJ_ID = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private AssetsLogRepository assetsLogRepository;


    @Autowired
    private AssetsLogMapper assetsLogMapper;
    

    @Autowired
    private AssetsLogService assetsLogService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAssetsLogMockMvc;

    private AssetsLog assetsLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssetsLogResource assetsLogResource = new AssetsLogResource(assetsLogService);
        this.restAssetsLogMockMvc = MockMvcBuilders.standaloneSetup(assetsLogResource)
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
    public static AssetsLog createEntity(EntityManager em) {
        AssetsLog assetsLog = new AssetsLog()
            .digitalCode(DEFAULT_DIGITAL_CODE)
            .custId(DEFAULT_CUST_ID)
            .securityAccountCode(DEFAULT_SECURITY_ACCOUNT_CODE)
            .assetsType(DEFAULT_ASSETS_TYPE)
            .assetsCode(DEFAULT_ASSETS_CODE)
            .assetsSubtype(DEFAULT_ASSETS_SUBTYPE)
            .transDatetime(DEFAULT_TRANS_DATETIME)
            .tradeAmt(DEFAULT_TRADE_AMT)
            .feeAmt(DEFAULT_FEE_AMT)
            .startBal(DEFAULT_START_BAL)
            .endBal(DEFAULT_END_BAL)
            .tradeObjType(DEFAULT_TRADE_OBJ_TYPE)
            .tradeObjId(DEFAULT_TRADE_OBJ_ID)
            .description(DEFAULT_DESCRIPTION)
            .remark(DEFAULT_REMARK);
        return assetsLog;
    }

    @Before
    public void initTest() {
        assetsLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssetsLog() throws Exception {
        int databaseSizeBeforeCreate = assetsLogRepository.findAll().size();

        // Create the AssetsLog
        AssetsLogDTO assetsLogDTO = assetsLogMapper.toDto(assetsLog);
        restAssetsLogMockMvc.perform(post("/api/assets-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetsLogDTO)))
            .andExpect(status().isCreated());

        // Validate the AssetsLog in the database
        List<AssetsLog> assetsLogList = assetsLogRepository.findAll();
        assertThat(assetsLogList).hasSize(databaseSizeBeforeCreate + 1);
        AssetsLog testAssetsLog = assetsLogList.get(assetsLogList.size() - 1);
        assertThat(testAssetsLog.getDigitalCode()).isEqualTo(DEFAULT_DIGITAL_CODE);
        assertThat(testAssetsLog.getCustId()).isEqualTo(DEFAULT_CUST_ID);
        assertThat(testAssetsLog.getSecurityAccountCode()).isEqualTo(DEFAULT_SECURITY_ACCOUNT_CODE);
        assertThat(testAssetsLog.getAssetsType()).isEqualTo(DEFAULT_ASSETS_TYPE);
        assertThat(testAssetsLog.getAssetsCode()).isEqualTo(DEFAULT_ASSETS_CODE);
        assertThat(testAssetsLog.getAssetsSubtype()).isEqualTo(DEFAULT_ASSETS_SUBTYPE);
        assertThat(testAssetsLog.getTransDatetime()).isEqualTo(DEFAULT_TRANS_DATETIME);
        assertThat(testAssetsLog.getTradeAmt()).isEqualTo(DEFAULT_TRADE_AMT);
        assertThat(testAssetsLog.getFeeAmt()).isEqualTo(DEFAULT_FEE_AMT);
        assertThat(testAssetsLog.getStartBal()).isEqualTo(DEFAULT_START_BAL);
        assertThat(testAssetsLog.getEndBal()).isEqualTo(DEFAULT_END_BAL);
        assertThat(testAssetsLog.getTradeObjType()).isEqualTo(DEFAULT_TRADE_OBJ_TYPE);
        assertThat(testAssetsLog.getTradeObjId()).isEqualTo(DEFAULT_TRADE_OBJ_ID);
        assertThat(testAssetsLog.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAssetsLog.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createAssetsLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assetsLogRepository.findAll().size();

        // Create the AssetsLog with an existing ID
        assetsLog.setId(1L);
        AssetsLogDTO assetsLogDTO = assetsLogMapper.toDto(assetsLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssetsLogMockMvc.perform(post("/api/assets-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetsLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AssetsLog in the database
        List<AssetsLog> assetsLogList = assetsLogRepository.findAll();
        assertThat(assetsLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAssetsLogs() throws Exception {
        // Initialize the database
        assetsLogRepository.saveAndFlush(assetsLog);

        // Get all the assetsLogList
        restAssetsLogMockMvc.perform(get("/api/assets-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assetsLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].digitalCode").value(hasItem(DEFAULT_DIGITAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].custId").value(hasItem(DEFAULT_CUST_ID.intValue())))
            .andExpect(jsonPath("$.[*].securityAccountCode").value(hasItem(DEFAULT_SECURITY_ACCOUNT_CODE.toString())))
            .andExpect(jsonPath("$.[*].assetsType").value(hasItem(DEFAULT_ASSETS_TYPE)))
            .andExpect(jsonPath("$.[*].assetsCode").value(hasItem(DEFAULT_ASSETS_CODE.toString())))
            .andExpect(jsonPath("$.[*].assetsSubtype").value(hasItem(DEFAULT_ASSETS_SUBTYPE)))
            .andExpect(jsonPath("$.[*].transDatetime").value(hasItem(DEFAULT_TRANS_DATETIME.toString())))
            .andExpect(jsonPath("$.[*].tradeAmt").value(hasItem(DEFAULT_TRADE_AMT.doubleValue())))
            .andExpect(jsonPath("$.[*].feeAmt").value(hasItem(DEFAULT_FEE_AMT.doubleValue())))
            .andExpect(jsonPath("$.[*].startBal").value(hasItem(DEFAULT_START_BAL.doubleValue())))
            .andExpect(jsonPath("$.[*].endBal").value(hasItem(DEFAULT_END_BAL.doubleValue())))
            .andExpect(jsonPath("$.[*].tradeObjType").value(hasItem(DEFAULT_TRADE_OBJ_TYPE)))
            .andExpect(jsonPath("$.[*].tradeObjId").value(hasItem(DEFAULT_TRADE_OBJ_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }
    

    @Test
    @Transactional
    public void getAssetsLog() throws Exception {
        // Initialize the database
        assetsLogRepository.saveAndFlush(assetsLog);

        // Get the assetsLog
        restAssetsLogMockMvc.perform(get("/api/assets-logs/{id}", assetsLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(assetsLog.getId().intValue()))
            .andExpect(jsonPath("$.digitalCode").value(DEFAULT_DIGITAL_CODE.toString()))
            .andExpect(jsonPath("$.custId").value(DEFAULT_CUST_ID.intValue()))
            .andExpect(jsonPath("$.securityAccountCode").value(DEFAULT_SECURITY_ACCOUNT_CODE.toString()))
            .andExpect(jsonPath("$.assetsType").value(DEFAULT_ASSETS_TYPE))
            .andExpect(jsonPath("$.assetsCode").value(DEFAULT_ASSETS_CODE.toString()))
            .andExpect(jsonPath("$.assetsSubtype").value(DEFAULT_ASSETS_SUBTYPE))
            .andExpect(jsonPath("$.transDatetime").value(DEFAULT_TRANS_DATETIME.toString()))
            .andExpect(jsonPath("$.tradeAmt").value(DEFAULT_TRADE_AMT.doubleValue()))
            .andExpect(jsonPath("$.feeAmt").value(DEFAULT_FEE_AMT.doubleValue()))
            .andExpect(jsonPath("$.startBal").value(DEFAULT_START_BAL.doubleValue()))
            .andExpect(jsonPath("$.endBal").value(DEFAULT_END_BAL.doubleValue()))
            .andExpect(jsonPath("$.tradeObjType").value(DEFAULT_TRADE_OBJ_TYPE))
            .andExpect(jsonPath("$.tradeObjId").value(DEFAULT_TRADE_OBJ_ID.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAssetsLog() throws Exception {
        // Get the assetsLog
        restAssetsLogMockMvc.perform(get("/api/assets-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssetsLog() throws Exception {
        // Initialize the database
        assetsLogRepository.saveAndFlush(assetsLog);

        int databaseSizeBeforeUpdate = assetsLogRepository.findAll().size();

        // Update the assetsLog
        AssetsLog updatedAssetsLog = assetsLogRepository.findById(assetsLog.getId()).get();
        // Disconnect from session so that the updates on updatedAssetsLog are not directly saved in db
        em.detach(updatedAssetsLog);
        updatedAssetsLog
            .digitalCode(UPDATED_DIGITAL_CODE)
            .custId(UPDATED_CUST_ID)
            .securityAccountCode(UPDATED_SECURITY_ACCOUNT_CODE)
            .assetsType(UPDATED_ASSETS_TYPE)
            .assetsCode(UPDATED_ASSETS_CODE)
            .assetsSubtype(UPDATED_ASSETS_SUBTYPE)
            .transDatetime(UPDATED_TRANS_DATETIME)
            .tradeAmt(UPDATED_TRADE_AMT)
            .feeAmt(UPDATED_FEE_AMT)
            .startBal(UPDATED_START_BAL)
            .endBal(UPDATED_END_BAL)
            .tradeObjType(UPDATED_TRADE_OBJ_TYPE)
            .tradeObjId(UPDATED_TRADE_OBJ_ID)
            .description(UPDATED_DESCRIPTION)
            .remark(UPDATED_REMARK);
        AssetsLogDTO assetsLogDTO = assetsLogMapper.toDto(updatedAssetsLog);

        restAssetsLogMockMvc.perform(put("/api/assets-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetsLogDTO)))
            .andExpect(status().isOk());

        // Validate the AssetsLog in the database
        List<AssetsLog> assetsLogList = assetsLogRepository.findAll();
        assertThat(assetsLogList).hasSize(databaseSizeBeforeUpdate);
        AssetsLog testAssetsLog = assetsLogList.get(assetsLogList.size() - 1);
        assertThat(testAssetsLog.getDigitalCode()).isEqualTo(UPDATED_DIGITAL_CODE);
        assertThat(testAssetsLog.getCustId()).isEqualTo(UPDATED_CUST_ID);
        assertThat(testAssetsLog.getSecurityAccountCode()).isEqualTo(UPDATED_SECURITY_ACCOUNT_CODE);
        assertThat(testAssetsLog.getAssetsType()).isEqualTo(UPDATED_ASSETS_TYPE);
        assertThat(testAssetsLog.getAssetsCode()).isEqualTo(UPDATED_ASSETS_CODE);
        assertThat(testAssetsLog.getAssetsSubtype()).isEqualTo(UPDATED_ASSETS_SUBTYPE);
        assertThat(testAssetsLog.getTransDatetime()).isEqualTo(UPDATED_TRANS_DATETIME);
        assertThat(testAssetsLog.getTradeAmt()).isEqualTo(UPDATED_TRADE_AMT);
        assertThat(testAssetsLog.getFeeAmt()).isEqualTo(UPDATED_FEE_AMT);
        assertThat(testAssetsLog.getStartBal()).isEqualTo(UPDATED_START_BAL);
        assertThat(testAssetsLog.getEndBal()).isEqualTo(UPDATED_END_BAL);
        assertThat(testAssetsLog.getTradeObjType()).isEqualTo(UPDATED_TRADE_OBJ_TYPE);
        assertThat(testAssetsLog.getTradeObjId()).isEqualTo(UPDATED_TRADE_OBJ_ID);
        assertThat(testAssetsLog.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAssetsLog.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingAssetsLog() throws Exception {
        int databaseSizeBeforeUpdate = assetsLogRepository.findAll().size();

        // Create the AssetsLog
        AssetsLogDTO assetsLogDTO = assetsLogMapper.toDto(assetsLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAssetsLogMockMvc.perform(put("/api/assets-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetsLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AssetsLog in the database
        List<AssetsLog> assetsLogList = assetsLogRepository.findAll();
        assertThat(assetsLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAssetsLog() throws Exception {
        // Initialize the database
        assetsLogRepository.saveAndFlush(assetsLog);

        int databaseSizeBeforeDelete = assetsLogRepository.findAll().size();

        // Get the assetsLog
        restAssetsLogMockMvc.perform(delete("/api/assets-logs/{id}", assetsLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AssetsLog> assetsLogList = assetsLogRepository.findAll();
        assertThat(assetsLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssetsLog.class);
        AssetsLog assetsLog1 = new AssetsLog();
        assetsLog1.setId(1L);
        AssetsLog assetsLog2 = new AssetsLog();
        assetsLog2.setId(assetsLog1.getId());
        assertThat(assetsLog1).isEqualTo(assetsLog2);
        assetsLog2.setId(2L);
        assertThat(assetsLog1).isNotEqualTo(assetsLog2);
        assetsLog1.setId(null);
        assertThat(assetsLog1).isNotEqualTo(assetsLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssetsLogDTO.class);
        AssetsLogDTO assetsLogDTO1 = new AssetsLogDTO();
        assetsLogDTO1.setId(1L);
        AssetsLogDTO assetsLogDTO2 = new AssetsLogDTO();
        assertThat(assetsLogDTO1).isNotEqualTo(assetsLogDTO2);
        assetsLogDTO2.setId(assetsLogDTO1.getId());
        assertThat(assetsLogDTO1).isEqualTo(assetsLogDTO2);
        assetsLogDTO2.setId(2L);
        assertThat(assetsLogDTO1).isNotEqualTo(assetsLogDTO2);
        assetsLogDTO1.setId(null);
        assertThat(assetsLogDTO1).isNotEqualTo(assetsLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(assetsLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(assetsLogMapper.fromId(null)).isNull();
    }
}
