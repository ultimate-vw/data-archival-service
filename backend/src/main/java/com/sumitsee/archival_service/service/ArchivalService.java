package com.sumitsee.archival_service.service;

import com.sumitsee.archival_service.dto.DeletionRequest;
import com.sumitsee.archival_service.entity.archival.User;
import com.sumitsee.archival_service.entity.archived.ArchivedUser;
import com.sumitsee.archival_service.entity.config.ArchivalJpaConfig;
import com.sumitsee.archival_service.repository.config.ArchivalConfigRepository;
import com.sumitsee.archival_service.repository.archival.UserRepository;
import com.sumitsee.archival_service.repository.archived.ArchivedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchivalService {
//    @Autowired
//    private UserRepository archivalRepo;
    private final UserRepository archivalRepo;
    private final ArchivedUserRepository archivedRepo;
    private final ArchivalConfigRepository configRepo;

//
//@Transactional("archivalTransactionManager")
//    public String archiveData(ArchivalRequest archivalRequest) {
//        // TODO: logic
//
//        System.out.println("Archiving from " + archivalRequest.getSourceTable());
//
//        List<User> usersToArchive = archivalRepo.findUsersToArchive(archivalRequest.getCutoffDate().atStartOfDay());
//
//        for(User user: usersToArchive){
//            ArchivedUser archivedUser = new ArchivedUser(user);
//            archivedUserRepository.save(archivedUser);
//            archivalRepo.delete(user);
//        }
//    return "Archival job submitted for: " + archivalRequest.getSourceTable();
//    Old Logic (Basic) !Keeping here for reference so that we can comeback to the starting point
//}
    @Transactional
    public void archiveData() {
        List<ArchivalJpaConfig> configs = configRepo.findAll();

        for (ArchivalJpaConfig config : configs) {
            int page = 0;
            Pageable pageable = PageRequest.of(page, config.getPageSize());
            LocalDateTime cutoff = LocalDate.now().minusMonths(config.getArchiveAfterMonths()).atStartOfDay();

            Page<User> result;
            do {
                result = archivalRepo.findByCreatedAtBefore(cutoff, pageable);
                List<ArchivedUser> archived = result.getContent().stream()
                        .map(this::mapToArchived)
                        .toList();
                archivedRepo.saveAll(archived);
                archivalRepo.deleteAll(result.getContent());
                page++;
                pageable = PageRequest.of(page, config.getPageSize());
            } while (!result.isEmpty());
        }
    }

    @Transactional
    public void deleteData(DeletionRequest deletionRequest) {
        List<ArchivalJpaConfig> configs = configRepo.findAll();

        for (ArchivalJpaConfig config : configs) {
            int page = 0;
            Pageable pageable = PageRequest.of(page, config.getPageSize());
            LocalDateTime cutoff = LocalDate.now().minusMonths(config.getDeleteAfterMonths()).atStartOfDay();

            Page<ArchivedUser> result;
            do {
                result = archivedRepo.findByCreatedAtBefore(cutoff, pageable);
                archivedRepo.deleteAll(result.getContent());
                page++;
                pageable = PageRequest.of(page, config.getPageSize());
            } while (!result.isEmpty());
        }
    }

    public void saveConfig(ArchivalJpaConfig config) {
        configRepo.save(config);
    }

    public List<ArchivalJpaConfig> getAllConfigs() {
        return configRepo.findAll();
    }

    public List<ArchivedUser> viewArchivedData(String table) {
        if ("archived_user".equalsIgnoreCase(table)) {
            return archivedRepo.findAll();
        }
        throw new UnsupportedOperationException("View only supported for archived_user table in current implementation.");
    }

    private ArchivedUser mapToArchived(User user) {
        return ArchivedUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .build();
    }


    public List<ArchivalJpaConfig>  getConfig() {
        return configRepo.findAll();
    }
}

