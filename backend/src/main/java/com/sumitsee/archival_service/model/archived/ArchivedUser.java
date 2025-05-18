package com.sumitsee.archival_service.model.archived;

import com.sumitsee.archival_service.model.archival.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "archived_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArchivedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String status;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    public ArchivedUser(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.archivedAt = LocalDateTime.now();
    }

}
