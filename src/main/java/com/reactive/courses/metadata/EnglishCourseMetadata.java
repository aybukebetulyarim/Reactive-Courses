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
public class EnglishCourseMetadata extends CourseMetadata{
    private String level;
    private List<String> books;
}
