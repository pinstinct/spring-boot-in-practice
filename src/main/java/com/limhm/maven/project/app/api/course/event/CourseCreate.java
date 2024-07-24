package com.limhm.maven.project.app.api.course.event;

import lombok.Builder;

@Builder
public record CourseCreate(
   Integer rating
) {
}
