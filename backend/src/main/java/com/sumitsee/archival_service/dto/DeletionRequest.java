package com.sumitsee.archival_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletionRequest {
    private String sourceTable;
    private LocalDate cutoffDate;
    private int pageSize;
}
