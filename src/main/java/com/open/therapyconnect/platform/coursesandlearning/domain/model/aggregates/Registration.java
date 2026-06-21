package com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.CreateRegistrationCommand;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.valueobjects.RegistrationStatus;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

/**
 * Registration aggregate root.
 */
@Getter
public class Registration extends AbstractDomainAggregateRoot<Registration> {

    @Setter private Long id;
    @Setter private Long courseId;
    @Setter private Long studentId;
    @Setter private String registrationDate;
    @Setter private RegistrationStatus status;
    @Setter private Integer progress;

    public Registration() {
        this.status = RegistrationStatus.PENDING;
        this.progress = 0;
    }

    public Registration(CreateRegistrationCommand command) {
        this.courseId = command.courseId();
        this.studentId = command.studentId();
        this.registrationDate = command.registrationDate();
        this.status = command.status() != null
                ? RegistrationStatus.valueOf(command.status().toUpperCase())
                : RegistrationStatus.PENDING;
        this.progress = command.progress() != null ? command.progress() : 0;
    }

    public Registration updateInformation(String status, Integer progress) {
        if (status != null) this.status = RegistrationStatus.valueOf(status.toUpperCase());
        if (progress != null) this.progress = progress;
        return this;
    }
}
