package com.open.therapyconnect.platform.marketplace.domain.model.aggregates;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateDependentCommand;
import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.*;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dependent extends AbstractDomainAggregateRoot {
    private Long id;
    private String dependentName;
    private String dependentCondition;
    private String needLevel;
    private ProgressStates progressState;

    public Dependent() {}

    public Dependent(CreateDependentCommand command) {
        this.dependentName = command.dependentName();
        this.dependentCondition = command.dependentCondition();
        this.needLevel = command.needLevel();
        this.progressState = ProgressStates.valueOf(command.progressState());
    }
}
