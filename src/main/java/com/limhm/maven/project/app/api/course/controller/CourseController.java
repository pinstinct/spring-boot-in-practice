package com.limhm.maven.project.app.api.course.controller;

import com.limhm.maven.project.app.api.course.dto.CourseCreateRequestDto;
import com.limhm.maven.project.app.api.course.service.CourseCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

  private final CourseCommandService courseCommandService;

  @PostMapping
  public void createCourse(@RequestBody @Valid CourseCreateRequestDto params) {
    courseCommandService.createCourse(params);
    return;
  }
}
