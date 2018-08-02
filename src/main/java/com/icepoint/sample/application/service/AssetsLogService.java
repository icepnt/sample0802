package com.icepoint.sample.application.service;

import com.icepoint.sample.application.service.dto.AssetsLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AssetsLog.
 */
public interface AssetsLogService {

    /**
     * Save a assetsLog.
     *
     * @param assetsLogDTO the entity to save
     * @return the persisted entity
     */
    AssetsLogDTO save(AssetsLogDTO assetsLogDTO);

    /**
     * Get all the assetsLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AssetsLogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" assetsLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AssetsLogDTO> findOne(Long id);

    /**
     * Delete the "id" assetsLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
