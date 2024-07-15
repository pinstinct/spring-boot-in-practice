package com.limhm.maven.project.app.model.course.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CourseTest extends ApplicationTests {

  private static final Logger logger = LoggerFactory.getLogger(CourseTest.class);

  @Test
  public void validateRating() {
    Course course = new Course(1, "CS50", "Computer", 0, "컴퓨터 기초 수업");
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    // Course 객체에 정의된 모든 제약 사항 준수 여부를 검증하고 위반 사항을 모아서 반환
    Set<ConstraintViolation<Course>> violations = validator.validate(course);
    violations.forEach(courseConstraintViolation -> logger.error(
        "A constraint violation has occurred. Violation details: [{}].",
        courseConstraintViolation));

    assertThat(violations).isNotNull();
  }
}