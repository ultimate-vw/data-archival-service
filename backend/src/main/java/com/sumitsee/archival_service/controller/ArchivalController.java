package com.sumitsee.archival_service.controller;

import com.sumitsee.archival_service.dto.DeletionRequest;
import com.sumitsee.archival_service.entity.archived.ArchivedUser;
import com.sumitsee.archival_service.entity.config.ArchivalJpaConfig;
import com.sumitsee.archival_service.service.ArchivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/archival")
@RequiredArgsConstructor
public class ArchivalController {
    private final ArchivalService archivalService;

    @PostMapping("/run")
    public ResponseEntity<String> runArchive(){
        archivalService.archiveData();
        return ResponseEntity.ok("Archival Complete!");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> runDelete(@RequestBody DeletionRequest deletionRequest){
        archivalService.deleteData(deletionRequest);
        return ResponseEntity.ok("Deletion Complete!");
    }

    @PostMapping("/config")
    public ResponseEntity<String> saveConfig(@RequestBody ArchivalJpaConfig archivalJpaConfig){
        archivalService.saveConfig(archivalJpaConfig);
        return ResponseEntity.ok("Configuration saved!");
    }

    @GetMapping("/config")
    public ResponseEntity<List<ArchivalJpaConfig>> getConfigs() {
        return ResponseEntity.ok(archivalService.getAllConfigs());
    }

    @GetMapping("/view/{table}")
    @PreAuthorize("hasRole(#table) or hasRole('Admin')")
    public ResponseEntity<List<ArchivedUser>> viewArchivedData(@PathVariable String table){
        return ResponseEntity.ok(archivalService.viewArchivedData(table));
    }

}
