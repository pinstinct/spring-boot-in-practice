package com.limhm.maven.project.app.model.authorCourse.dto;

/**
 * Author 테이블과 Course 테이블에 저장된 정보 중에서 필요한 정보만을 골라낸 필드를 가지고 있다.
 */
public class AuthorCourseDto {

  private long id;
  private String authorName;
  private String courseName;
  private String description;

  public AuthorCourseDto(long id, String authorName, String courseName, String description) {
    this.id = id;
    this.authorName = authorName;
    this.courseName = courseName;
    this.description = description;
  }

  public AuthorCourseDto() {
  }

  @Override
  public String toString() {
    return "AuthorCourseDto{" + "id=" + id + ", authorName=" + authorName + ", courseName="
        + courseName + ", description=" + description + "}";
  }
}
