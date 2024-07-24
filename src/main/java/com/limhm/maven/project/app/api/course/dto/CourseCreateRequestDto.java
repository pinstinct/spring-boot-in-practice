package com.limhm.maven.project.app.api.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseCreateRequestDto(
    @NotBlank
    String name,

    @NotBlank
    String category,

    @NotNull
    Integer rating,

    @NotBlank
    String description
) {
}
