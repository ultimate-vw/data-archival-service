package com.sumitsee.archival_service.service;

import com.sumitsee.archival_service.model.ArchivalRequest;
import org.springframework.stereotype.Service;

@Service
public class ArchivalService {
    public String archiveData(ArchivalRequest archivalRequest){
        // TODO: logic

        System.out.println("Archiving from " + archivalRequest.getSourceTable());
        return "Archival job submitted for: " + archivalRequest.getSourceTable();
    }
}
