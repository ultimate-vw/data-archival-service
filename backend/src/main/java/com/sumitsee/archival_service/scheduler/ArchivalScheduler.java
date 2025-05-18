package com.sumitsee.archival_service.scheduler;

import com.sumitsee.archival_service.dto.ArchivalRequest;
import com.sumitsee.archival_service.service.ArchivalService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArchivalScheduler {

    private final ArchivalService archivalService;

    public ArchivalScheduler(ArchivalService archivalService){
        this.archivalService = archivalService;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void runArchival(){
        ArchivalRequest request = new ArchivalRequest();
        request.setSourceTable("users");
       archivalService.archiveData(request);
    }
}
