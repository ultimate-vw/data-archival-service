package com.sumitsee.archival_service.entity.config;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "archival_config")
@Builder
public class ArchivalJpaConfig {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int archiveAfterMonths;
    private int deleteAfterMonths;
    private int pageSize;
    private String tableName;
    private String criteriaColumn;

}
