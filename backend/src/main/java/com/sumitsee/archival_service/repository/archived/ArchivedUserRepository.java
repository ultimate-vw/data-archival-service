package com.sumitsee.archival_service.repository.archived;

import com.sumitsee.archival_service.entity.archived.ArchivedUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ArchivedUserRepository extends JpaRepository<ArchivedUser, Long> {
    Page<ArchivedUser> findByCreatedAtBefore(LocalDateTime cutoffDate, Pageable pageable);
}
