package com.limhm.maven.project.app.model.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import com.limhm.maven.project.app.model.course.entity.Course;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class CourseRepositoryTest extends ApplicationTests {

  @Autowired
  private CourseRepository courseRepository;

  @BeforeAll
  public static void setUp(@Autowired CourseRepository courseRepository) {
    courseRepository.deleteAll();
  }

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

  @Test
  public void givenCreateCoursesWhenLoadTheCourseThenExpectSameCourses() {
    courseRepository.saveAll(getCourses());
    assertThat(courseRepository.findAllByCategory("Spring")).hasSize(3);
    assertThat(courseRepository.existsByName("JavaScript for All")).isTrue();
    assertThat(courseRepository.existsByName("Mastering JavaScript")).isFalse();
    assertThat(courseRepository.countByCategory("Python")).isEqualTo(2);
    assertThat(courseRepository.findByNameStartsWith("Getting Started")).hasSize(3);
  }

  private List<Course> getCourses() {
    Course rapidSpringBootCourse = new Course("Rapid Spring Boot Application Development", "Spring",
        4, "Spring Boot gives all the power of the Spring Framework without all fo the complexity");

    Course springSecurityDslCourse = new Course("Getting Started with Spring Security DSL",
        "Spring", 5, "Learn Spring Security DsL in easy steps");

    Course springCloudKubernetesCourse = new Course("Getting Started with Spring Cloud Kubernetes",
        "Spring", 3, "Master Spring Boot application deployment with Kubernetes");

    Course rapidPythonCourse = new Course("Getting Started with Python", "Python", 5,
        "Learn Python concepts in easy steps");

    Course gameDevelopmentWithPython = new Course("Game Development with Python", "Python", 3,
        "Learn Python by developing 10 wonderful games");

    Course javascriptForAll = new Course("JavaScript for All", "JavaScript", 4,
        "Learn basic JavaScript syntax that can apply to anywhere");

    Course javascriptCompleteGuide = new Course("JavaScript Complete Guide", "JavaScript", 5,
        "Master JavaScript with Core Concepts and Web Development");

    return Arrays.asList(rapidSpringBootCourse, springSecurityDslCourse,
        springCloudKubernetesCourse, rapidPythonCourse, gameDevelopmentWithPython, javascriptForAll,
        javascriptCompleteGuide);
  }
}
