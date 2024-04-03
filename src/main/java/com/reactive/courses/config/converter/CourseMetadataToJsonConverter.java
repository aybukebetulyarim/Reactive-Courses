package com.reactive.courses.config.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.courses.metadata.CourseMetadata;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.io.IOException;

@WritingConverter
@Slf4j
public class CourseMetadataToJsonConverter implements Converter<CourseMetadata, Json> {
    private final ObjectMapper mapper;

    public CourseMetadataToJsonConverter(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    public Json convert(CourseMetadata courseMetadata) {
        try {
            return Json.of(mapper.writeValueAsBytes(courseMetadata));
        } catch (IOException e) {
            log.error("Error while converting CourseMetadata to JSON", e);
            throw new RuntimeException(e);
        }
    }
}
