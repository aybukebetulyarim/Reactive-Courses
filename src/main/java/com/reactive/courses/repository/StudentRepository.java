package com.reactive.courses.repository;

import com.reactive.courses.entity.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface StudentRepository extends ReactiveCrudRepository<Student, UUID> {
}
