package com.limhm.maven.project.app.model.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import com.limhm.maven.project.app.model.course.entity.Course;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomizedCourseRepositoryTest extends ApplicationTests {

  @Autowired
  private CustomizedCourseRepository customizedCourseRepository;

  @BeforeAll
  static void setUp(@Autowired CourseRepository courseRepository) {
    courseRepository.deleteAll();
  }

  @Test
  public void givenCreateCourseWhenFindAllCoursesThenExpectOneCourse() {
    Course course = new Course("Rapid Spring Boot Application Development", "spring", 4,
        "Spring Boot gives all the power of the Spring Framework without all of the complexities");

    customizedCourseRepository.save(course);
    assertThat(customizedCourseRepository.findAll()).hasSize(1);
  }
}
