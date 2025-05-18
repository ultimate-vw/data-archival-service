package com.sumitsee.archival_service.repository.archived;

import com.sumitsee.archival_service.model.archived.ArchivedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedUserRepository extends JpaRepository<ArchivedUser, Long> {
}
