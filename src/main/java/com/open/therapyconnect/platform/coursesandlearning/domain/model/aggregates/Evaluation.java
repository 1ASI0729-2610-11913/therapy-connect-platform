package com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.CreateEvaluationCommand;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

/**
 * Evaluation aggregate root.
 */
@Getter
public class Evaluation extends AbstractDomainAggregateRoot<Evaluation> {

    @Setter private Long id;
    @Setter private Long courseId;
    @Setter private Long studentId;
    @Setter private Integer score;
    @Setter private String feedback;
    @Setter private String evaluationDate;

    public Evaluation() {
        this.score = 0;
        this.feedback = "";
        this.evaluationDate = "";
    }

    public Evaluation(CreateEvaluationCommand command) {
        this.courseId = command.courseId();
        this.studentId = command.studentId();
        this.score = command.score();
        this.feedback = command.feedback();
        this.evaluationDate = command.evaluationDate();
    }

    public Evaluation updateInformation(Integer score, String feedback, String evaluationDate) {
        if (score != null) this.score = score;
        if (feedback != null) this.feedback = feedback;
        if (evaluationDate != null) this.evaluationDate = evaluationDate;
        return this;
    }
}
