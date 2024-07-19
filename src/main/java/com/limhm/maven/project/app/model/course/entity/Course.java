package com.limhm.maven.project.app.model.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Objects;

@Entity  // JPA 로 관리되는 엔티티 클래스라는 것을 알려줌
@Table(name = "COURSES")
@NamedQueries({
    @NamedQuery(name = "Course.findAllByRating", query = "select c from Course c where c.category=?1"),
    @NamedQuery(name = "Course.findAllByCategoryAndRating", query = "select c from Course c where c.category=?1 and c.rating=?2")})
public class Course {

  @Id  // primary key
  @Column(name = "ID")  // 이름이 한 단어 이상인 카멜케이스 필드가 있다면 언더스코어를 사용해서 컬럼 생성
  /* *
   * GenerationType
   * - Table: JPA 구현체가 데이터베이스에 키 생성 전용 테이블을 만들고 이 테이블에서 키를 생성하고 기본 키로 사용
   * - Identity: JPA 구현체가 데이터베이스의 식별자 컬럼에서 생성된 값을 기본 키로 사용
   * - Sequence: JPA 구현체가 데이터베이스의 시퀀스를 사용해서 키를 생성하고 이를 기본키로 사용
   * - Auto: JPA 구현체가 기본 키 생성 방식을 스스로 결정
   * */
  @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 생성 전략
  private long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "CATEGORY")
  private String category;

  @Min(value = 1, message = "A course should have a minimum of 1 rating")
  @Max(value = 5, message = "A course should have a maximum of 5 rating")
  @Column(name = "RATING")
  private int rating;

  @Column(name = "DESCRIPTION")
  private String description;

  public Course() {
  }

  public Course(String name, String category, int rating, String description) {
    // ID는 JPA 에 의해 자동 생성되므로 생성자에 불포함
    this.name = name;
    this.category = category;
    this.rating = rating;
    this.description = description;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public int getRating() {
    return rating;
  }

  public String getDescription() {
    return description;
  }

  /**
   * isEqualTo 테스트를 위해 equals, hashCode 함수를 오버라이드
   * */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Course course = (Course) o;
    return Objects.equals(id, course.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public void setRating(int rating) {
    this.rating = rating;
  }
}
