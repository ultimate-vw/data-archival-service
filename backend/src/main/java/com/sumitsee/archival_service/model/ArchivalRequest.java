package com.sumitsee.archival_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArchivalRequest {
    private String sourceTable;
    private String destinationTable;
    private String archivalCondition;
}
