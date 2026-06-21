package com.open.therapyconnect.platform.profiles.domain.model.aggregates;

import com.open.therapyconnect.platform.profiles.domain.model.commands.CreateDependentCommand;
import com.open.therapyconnect.platform.profiles.domain.model.valueobjects.DependentCondition;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Dependent aggregate root.
 */
@Getter
public class Dependent extends AbstractDomainAggregateRoot<Dependent> {

    @Setter private Long id;
    @Setter private String name;
    @Setter private Integer age;
    @Setter private DependentCondition condition;
    @Setter private Long parentId;

    public Dependent() {
        this.name = Strings.EMPTY;
        this.age = 0;
        this.condition = DependentCondition.OTHER;
    }

    public Dependent(CreateDependentCommand command) {
        this.name = command.name();
        this.age = command.age();
        this.condition = command.condition() != null
                ? DependentCondition.valueOf(command.condition().toUpperCase())
                : DependentCondition.OTHER;
        this.parentId = command.parentId();
    }

    public Dependent updateInformation(String name, Integer age, String condition) {
        if (name != null) this.name = name;
        if (age != null) this.age = age;
        if (condition != null) this.condition = DependentCondition.valueOf(condition.toUpperCase());
        return this;
    }
}
