package com.reactive.courses.metadata;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class SpringCourseMetadata extends CourseMetadata{
    private List<String> prerequisites;
    private String language;
    private String github;
}
