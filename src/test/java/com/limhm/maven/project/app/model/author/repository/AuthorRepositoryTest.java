package com.limhm.maven.project.app.model.author.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import com.limhm.maven.project.app.model.author.entity.Author;
import com.limhm.maven.project.app.model.authorCourse.dto.AuthorCourseDto;
import com.limhm.maven.project.app.model.authorCourse.entity.AuthorCourse;
import com.limhm.maven.project.app.model.authorCourse.repository.AuthorCourseRepository;
import com.limhm.maven.project.app.model.course.entity.Course;
import com.limhm.maven.project.app.model.course.repository.CourseRepository;
import java.util.List;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorRepositoryTest extends ApplicationTests {

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private AuthorCourseRepository authorCourseRepository;

  private List<Course> givenCourse() {
    Course rapidSpringBootCourse = new Course("Rapid Spring Boot Application Development", "Spring",
        4, "Spring Boot gives all the power of the Spring Framework without all fo the complexity");
    Course springSecurityDslCourse = new Course("Getting Started with Spring Security DSL",
        "Spring", 5, "Learn Spring Security DsL in easy steps");
    Course scalable = new Course("Scalable, Cloud Native Data Applications", "Spring", 4,
        "Manage Cloud based applications with Spring Boot");
    Course fullyReactive = new Course(
        "Fully Reactive: Spring, Kotlin, and JavaFx Playing " + "Together", "Spring", 3,
        "Unleash the power of Reactive Spring with Kotlin and Spring Boot");
    Course springCloudKubernetesCourse = new Course("Getting Started with Spring Cloud Kubernetes",
        "Spring", 3, "Master Spring Boot application deployment with Kubernetes");
    return courseRepository.saveAll(
        List.of(rapidSpringBootCourse, springSecurityDslCourse, scalable, fullyReactive,
            springCloudKubernetesCourse));
  }

  private Iterable<Author> givenAuthor() {
    Author john = new Author("John Doe", "Author of several Spring Boot courses");
    Author steve = new Author("Steve Muller",
        "Author of several popular Spring and Python " + "courses");
    return authorRepository.saveAll(List.of(john, steve));
  }

  private long givenAuthorCourse() {
    List<Course> courses = givenCourse();
    Iterable<Author> entities = givenAuthor();
    List<Author> authors = StreamSupport.stream(entities.spliterator(), false).toList();

    long authorId = authors.get(1).getId();
    AuthorCourse john = new AuthorCourse(authors.get(0).getId(), courses.get(0).getId());
    AuthorCourse steve = new AuthorCourse(authors.get(1).getId(), courses.get(0).getId());
    authorCourseRepository.saveAll(List.of(john, steve));
    return authorId;
  }

  @Test
  public void whenCountAllCoursesThenExpectFiveCourses() {
    long authorId = givenAuthorCourse();

    Iterable<AuthorCourseDto> result = authorRepository.getAuthorCourseInfo(authorId);
    assertThat(result).hasSize(1);
  }
}
