package com.limhm.maven.project.app.api.course.mapper;

import com.limhm.maven.project.app.api.course.dto.CourseCreateRequestDto;
import com.limhm.maven.project.app.model.course.entity.Course;

public class CourseMapper {

  public static Course toEntity(CourseCreateRequestDto dto) {
    if (dto == null) {
      return null;
    }

    return new Course(
        dto.name(), dto.category(), dto.rating(), dto.description()
    );
  }
}
