package com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.CreateCourseCommand;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Course aggregate root.
 */
@Getter
public class Course extends AbstractDomainAggregateRoot<Course> {

    @Setter private Long id;
    @Setter private String title;
    @Setter private String description;
    @Setter private String instructorName;
    @Setter private String duration;
    @Setter private String level;
    @Setter private String imageUrl;

    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.instructorName = Strings.EMPTY;
        this.duration = Strings.EMPTY;
        this.level = Strings.EMPTY;
        this.imageUrl = Strings.EMPTY;
    }

    public Course(CreateCourseCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.instructorName = command.instructorName();
        this.duration = command.duration();
        this.level = command.level();
        this.imageUrl = command.imageUrl();
    }

    public Course updateInformation(String title, String description, String instructorName,
                                    String duration, String level, String imageUrl) {
        this.title = title;
        this.description = description;
        this.instructorName = instructorName;
        this.duration = duration;
        this.level = level;
        this.imageUrl = imageUrl;
        return this;
    }
}
