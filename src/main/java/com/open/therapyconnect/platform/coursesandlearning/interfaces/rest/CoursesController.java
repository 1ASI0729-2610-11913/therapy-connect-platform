package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest;

import com.open.therapyconnect.platform.coursesandlearning.application.commandservices.CourseCommandService;
import com.open.therapyconnect.platform.coursesandlearning.application.queryservices.CourseQueryService;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.DeleteCourseCommand;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.GetAllCoursesQuery;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.GetCourseByIdQuery;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.*;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import com.open.therapyconnect.platform.shared.interfaces.rest.resources.MessageResource;
import com.open.therapyconnect.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/courses", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "Course management endpoints")
public class CoursesController {

    private final CourseCommandService courseCommandService;
    private final CourseQueryService courseQueryService;

    public CoursesController(CourseCommandService courseCommandService, CourseQueryService courseQueryService) {
        this.courseCommandService = courseCommandService;
        this.courseQueryService = courseQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new course")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseResource resource) {
        var command = CreateCourseCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = courseCommandService.handle(command)
                .flatMap(courseId -> courseQueryService.handle(new GetCourseByIdQuery(courseId))
                        .<Result<com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Course, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Course", courseId.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                CourseResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{courseId}")
    @Operation(summary = "Get course by ID")
    public ResponseEntity<CourseResource> getCourseById(@PathVariable Long courseId) {
        var query = new GetCourseByIdQuery(courseId);
        var course = courseQueryService.handle(query);
        if (course.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(CourseResourceFromEntityAssembler.toResourceFromEntity(course.get()));
    }

    @GetMapping
    @Operation(summary = "Get all courses")
    public ResponseEntity<List<CourseResource>> getAllCourses() {
        var courses = courseQueryService.handle(new GetAllCoursesQuery());
        var resources = courses.stream()
                .map(CourseResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{courseId}")
    @Operation(summary = "Update course")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody UpdateCourseResource resource) {
        var command = UpdateCourseCommandFromResourceAssembler.toCommandFromResource(courseId, resource);
        var result = courseCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                CourseResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{courseId}")
    @Operation(summary = "Delete course")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        var command = new DeleteCourseCommand(courseId);
        var result = courseCommandService.handle(command)
                .map(deletedId -> new MessageResource("Course with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                message -> message,
                HttpStatus.OK
        );
    }
}
