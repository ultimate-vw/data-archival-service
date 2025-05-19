package com.sumitsee.archival_service.entity.archived;

import com.sumitsee.archival_service.entity.archival.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "archived_students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchivedStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String grade;
    private LocalDateTime createdAt;
    private LocalDateTime archivedAt;

    public ArchivedStudent(Student s) {
        this.name = s.getName();
        this.email = s.getEmail();
        this.grade = s.getGrade();
        this.createdAt = s.getCreatedAt();
        this.archivedAt = LocalDateTime.now();
    }
}
