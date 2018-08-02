package com.icepoint.sample.application.repository;

import com.icepoint.sample.application.domain.AssetsLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetsLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetsLogRepository extends JpaRepository<AssetsLog, Long> {

}
