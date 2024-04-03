package com.reactive.courses.repository;

import com.reactive.courses.entity.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CourseRepository extends ReactiveCrudRepository<Course, UUID> {
}