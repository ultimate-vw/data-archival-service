package com.sumitsee.archival_service.entity.archived;

import com.sumitsee.archival_service.entity.archival.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "archived_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchivedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    public ArchivedUser(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.archivedAt = LocalDateTime.now();
    }

}
