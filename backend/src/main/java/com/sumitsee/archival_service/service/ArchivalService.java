package com.sumitsee.archival_service.service;

import com.sumitsee.archival_service.dto.ArchivalRequest;
import com.sumitsee.archival_service.model.archival.User;
import com.sumitsee.archival_service.model.archived.ArchivedUser;
import com.sumitsee.archival_service.repository.archival.UserRepository;
import com.sumitsee.archival_service.repository.archived.ArchivedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchivalService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArchivedUserRepository archivedUserRepository;

@Transactional("archivalTransactionManager")
    public String archiveData(ArchivalRequest archivalRequest){
        // TODO: logic

        System.out.println("Archiving from " + archivalRequest.getSourceTable());

        List<User> usersToArchive = userRepository.findUsersToArchive(archivalRequest.getCutoffDate().atStartOfDay());

        for(User user: usersToArchive){
            ArchivedUser archivedUser = new ArchivedUser(user);
            archivedUserRepository.save(archivedUser);
            userRepository.delete(user);
        }
    return "Archival job submitted for: " + archivalRequest.getSourceTable();

    }
}
