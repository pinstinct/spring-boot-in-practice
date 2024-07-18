package com.limhm.maven.project.app.model.course.repository;

import com.limhm.maven.project.app.model.course.entity.Course;
import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository  // 스프링 리포지터리로 사용된다는 것을 알려줌, 예외 변환(JPA 구현체마다 달라지는 예외가 아니라 DataAccessException 만 처리하면 됨)
public interface CourseRepository extends CrudRepository<Course, Long> {
  // 비어 있더라도 스프링 데이터 JPA 가 런타임에 인터페이스 구현체를 자동으로 생성

  // 카테고리 기준으로 모든 과정 정보 목록 조회
  Iterable<Course> findAllByCategory(String category);

  // 카테고리 기준으로 모든 과정 정보 목록 조회
  Stream<Course> streamAllByCategory(String category);

  // 카테고리 기준으로 모든 과정 정보 목록을 조회해서 이름 기준으로 오름차순 정렬
  Iterable<Course> findAllByCategoryOrderByName(String category);

  // 이름으로 과정을 조회해서 있으면 true, 없으면 false 반환
  boolean existsByName(String name);

  // 카테고리 기준으로 조회한 과정 정보의 개수 반환
  long countByCategory(String category);

  // 이름이 같거나 카테고리가 같은 과정 목록 조회
  Iterable<Course> findByNameOrCategory(String name, String category);

  // 이름이 주어진 문자열로 시작하는 과정 목록 조회
  Iterable<Course> findByNameStartsWith(String name);
}
