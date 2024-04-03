package com.reactive.courses.dto;

import com.reactive.courses.metadata.CourseMetadata;

public record CourseDto(String name, String description, Integer duration, String teacher,
                        CourseMetadata courseMetadata) {
}
