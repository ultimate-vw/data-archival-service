package com.sumitsee.archival_service.service;

import com.sumitsee.archival_service.dto.ArchivalRequest;
import com.sumitsee.archival_service.repository.archival.UserRepository;
import com.sumitsee.archival_service.repository.archived.ArchivedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArchivalService {

    private final UserRepository userRepository;
    private final ArchivedUserRepository archivedUserRepository;


    public String archiveData(ArchivalRequest archivalRequest){
        // TODO: logic

        System.out.println("Archiving from " + archivalRequest.getSourceTable());
        return "Archival job submitted for: " + archivalRequest.getSourceTable();




    }
}
