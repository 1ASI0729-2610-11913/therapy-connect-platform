package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class CoursePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    private String instructorName;

    private String duration;

    private String level;

    private String imageUrl;
}
