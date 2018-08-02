package com.icepoint.sample.application.service.impl;

import com.icepoint.sample.application.service.AssetsLogService;
import com.icepoint.sample.application.domain.AssetsLog;
import com.icepoint.sample.application.repository.AssetsLogRepository;
import com.icepoint.sample.application.service.dto.AssetsLogDTO;
import com.icepoint.sample.application.service.mapper.AssetsLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing AssetsLog.
 */
@Service
@Transactional
public class AssetsLogServiceImpl implements AssetsLogService {

    private final Logger log = LoggerFactory.getLogger(AssetsLogServiceImpl.class);

    private final AssetsLogRepository assetsLogRepository;

    private final AssetsLogMapper assetsLogMapper;

    public AssetsLogServiceImpl(AssetsLogRepository assetsLogRepository, AssetsLogMapper assetsLogMapper) {
        this.assetsLogRepository = assetsLogRepository;
        this.assetsLogMapper = assetsLogMapper;
    }

    /**
     * Save a assetsLog.
     *
     * @param assetsLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AssetsLogDTO save(AssetsLogDTO assetsLogDTO) {
        log.debug("Request to save AssetsLog : {}", assetsLogDTO);
        AssetsLog assetsLog = assetsLogMapper.toEntity(assetsLogDTO);
        assetsLog = assetsLogRepository.save(assetsLog);
        return assetsLogMapper.toDto(assetsLog);
    }

    /**
     * Get all the assetsLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AssetsLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AssetsLogs");
        return assetsLogRepository.findAll(pageable)
            .map(assetsLogMapper::toDto);
    }


    /**
     * Get one assetsLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AssetsLogDTO> findOne(Long id) {
        log.debug("Request to get AssetsLog : {}", id);
        return assetsLogRepository.findById(id)
            .map(assetsLogMapper::toDto);
    }

    /**
     * Delete the assetsLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AssetsLog : {}", id);
        assetsLogRepository.deleteById(id);
    }
}
