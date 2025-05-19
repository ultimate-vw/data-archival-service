package com.sumitsee.archival_service.entity.archived;

import com.sumitsee.archival_service.entity.archival.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "archived_teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchivedTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subject;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime archivedAt;

    public ArchivedTeacher(Teacher t) {
        this.name = t.getName();
        this.subject = t.getSubject();
        this.email = t.getEmail();
        this.createdAt = t.getCreatedAt();
        this.archivedAt = LocalDateTime.now();
    }
}