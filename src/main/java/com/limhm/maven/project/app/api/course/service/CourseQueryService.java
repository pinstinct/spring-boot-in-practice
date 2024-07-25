package com.limhm.maven.project.app.api.course.service;

import com.limhm.maven.project.app.model.course.entity.Course;
import com.limhm.maven.project.app.model.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseQueryService {

  private final CourseRepository courseRepository;

  public Iterable<Course> findAllCourses() {
    return courseRepository.findAll();
  }
}
