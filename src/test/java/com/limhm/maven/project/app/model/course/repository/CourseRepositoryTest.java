package com.limhm.maven.project.app.model.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import com.limhm.maven.project.app.model.course.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class CourseRepositoryTest extends ApplicationTests {

  @Autowired
  private CourseRepository courseRepository;

  @Test
  public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
    Course course = new Course("Rapid Spring Boot Application Development", "spring", 4,
        "Spring Boot gives all the power of the Spring Framework without all of the complexities");

    Course savedCourse = courseRepository.save(course);

    // isEqualTo 함수로 객체의 참조 비교
    // 그러나 JPA 에서 반환된 엔티티 객체(savedCourse)와 생성한 객체(course)는 서로 다른 객체이므로 참조가 다름
    // 따라서 이 비교는 실패한다. 대신, 엔티티 객체의 동등성 비교를 위해 equals, hasCode 메서드를 id 기준으로 오버라이드 함
    assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
  }

  @Test
  public void givenUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse() {
    Course course = new Course("Rapid Spring Boot Application Development", "spring", 4,
        "Spring Boot gives all the power of the Spring Framework without all of the complexities");

    courseRepository.save(course);
    course.setRating(5);
    Course savedCourse = courseRepository.save(course);

    assertThat(courseRepository.findById(savedCourse.getId()).get().getRating()).isEqualTo(5);
  }

  @Test
  public void givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse() {
    Course course = new Course("Rapid Spring Boot Application Development", "spring", 4,
        "Spring Boot gives all the power of the Spring Framework without all of the complexities");

    Course savedCourse = courseRepository.save(course);

    assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
    courseRepository.delete(course);
    assertThat(courseRepository.findById(savedCourse.getId()).isPresent()).isFalse();
  }
}
