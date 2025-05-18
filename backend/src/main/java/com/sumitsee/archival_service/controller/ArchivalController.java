package com.sumitsee.archival_service.controller;

import com.sumitsee.archival_service.dto.ArchivalRequest;
import com.sumitsee.archival_service.service.ArchivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/archival")
@RequiredArgsConstructor
public class ArchivalController {
    private final ArchivalService archivalService;

    @PostMapping("/run")
    public String runArchive(@RequestBody ArchivalRequest archivalRequest){
        return archivalService.archiveData(archivalRequest);
    }
}
