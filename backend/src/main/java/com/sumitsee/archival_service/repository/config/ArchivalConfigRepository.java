package com.sumitsee.archival_service.repository.config;

import com.sumitsee.archival_service.entity.config.ArchivalJpaConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivalConfigRepository extends JpaRepository<ArchivalJpaConfig, Long> {
    List<ArchivalJpaConfig> findAll();
}
