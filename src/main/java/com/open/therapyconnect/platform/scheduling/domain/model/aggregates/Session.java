package com.open.therapyconnect.platform.scheduling.domain.model.aggregates;

import com.open.therapyconnect.platform.scheduling.domain.model.commands.CreateSessionCommand;
import com.open.therapyconnect.platform.scheduling.domain.model.valueobjects.SessionStatus;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Session aggregate root.
 */
@Getter
public class Session extends AbstractDomainAggregateRoot<Session> {

    @Setter private Long id;
    @Setter private String title;
    @Setter private String sessionDate;
    @Setter private String startTime;
    @Setter private String endTime;
    @Setter private String sessionType;
    @Setter private SessionStatus sessionStatus;
    @Setter private Long teacherId;
    @Setter private Long studentId;

    public Session() {
        this.title = Strings.EMPTY;
        this.sessionDate = Strings.EMPTY;
        this.startTime = Strings.EMPTY;
        this.endTime = Strings.EMPTY;
        this.sessionType = Strings.EMPTY;
        this.sessionStatus = SessionStatus.PENDING;
    }

    public Session(CreateSessionCommand command) {
        this.title = command.title();
        this.sessionDate = command.sessionDate();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.sessionType = command.sessionType();
        this.sessionStatus = SessionStatus.PENDING;
        this.teacherId = command.teacherId();
        this.studentId = command.studentId();
    }

    public Session updateInformation(String title, String sessionDate, String startTime, String endTime, String sessionType) {
        this.title = title;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sessionType = sessionType;
        return this;
    }

    public Session updateStatus(SessionStatus status) {
        this.sessionStatus = status;
        return this;
    }
}