package com.sumitsee.archival_service.repository.archival;

import com.sumitsee.archival_service.entity.archival.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
    @Query("SELECT u FROM User u WHERE u.createdAt < :cutoffDate")
    List<User> findUsersToArchive(@Param("cutoffDate") LocalDateTime cutoffDate);

    Page<User> findByCreatedAtBefore(LocalDateTime cutoffDate, Pageable pageable);
}
