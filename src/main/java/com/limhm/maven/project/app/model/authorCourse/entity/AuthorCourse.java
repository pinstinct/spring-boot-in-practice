package com.limhm.maven.project.app.model.authorCourse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "AUTHOR_COURSE")
@Table(name = "AUTHORS_COURSES")
public class AuthorCourse {

  @Id
  @Column(name = "author_id")
  private long authorId;

  @Column(name = "course_id")
  private long courseId;

  public AuthorCourse() {
  }

  public AuthorCourse(long authorId, long courseId) {
    this.authorId = authorId;
    this.courseId = courseId;
  }

  public long getAuthorId() {
    return authorId;
  }

  public long getCourseId() {
    return courseId;
  }
}
