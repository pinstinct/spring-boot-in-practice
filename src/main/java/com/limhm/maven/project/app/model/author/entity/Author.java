package com.limhm.maven.project.app.model.author.entity;

import com.limhm.maven.project.app.model.course.entity.Course;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "AUTHOR")
@Table(name = "AUTHORS")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String bio;
  @ManyToMany
  // 관계의 소유자 쪽에 @JoinTable 애너테이션 지정
  @JoinTable(name = "authors_courses",
      joinColumns = {  // 소유자쪽인 테이블의 식별자 컬럼을 가리키는 외래 키 지정
          // name: 컬럼 이름, referencedColumnName: 외래 키 컬럼에 의해 참조되는 대상 컬럼 이름 지정
          // updatable: 외래 키 컬럼값이 애플리케이션에 의한 수정 허용 여부
          @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false,
              updatable = false, insertable = false)},
      inverseJoinColumns = {  // 비소유자쪽인 테이블의 식별자 컬럼을 가리키는 외래 키 지정
          @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false,
              updatable = false, insertable = false)})
  private Set<Course> courses = new HashSet<>();

  public Author() {
  }

  public Author(String bio, String name) {
    this.bio = bio;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBio() {
    return bio;
  }

  public Set<Course> getCourses() {
    return courses;
  }

  @Override
  public String toString() {
    return "Author{" + "id=" + id + ", name=" + name + ", bio=" + bio + "}";
  }
}