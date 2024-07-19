package com.limhm.maven.project.app.model.course.repository;

import com.limhm.maven.project.app.model.course.entity.Course;
import com.limhm.maven.project.app.model.course.entity.DescriptionOnly;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository  // 스프링 리포지터리로 사용된다는 것을 알려줌, 예외 변환(JPA 구현체마다 달라지는 예외가 아니라 DataAccessException 만 처리하면 됨)
public interface CourseRepository extends JpaRepository<Course, Long>,
    QuerydslPredicateExecutor<Course> {
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

  // @NamedQuery 애너테이션에서 name 속성으로 지정했더 메서드 이름과 같은 이름의 메서드를 추가
  Iterable<Course> findAllByCategoryAndRating(String category, int rating);

  // ?1, ?2 와 같은 위치 기반 파라미터 지정 방식 대신에 named 파라미터 방식을 사용
  @Query("select c from Course c where c.category=:category and c.rating > :rating")
  Iterable<Course> findAllByCategoryAndRatingGreaterThan(@Param("category") String category,
      @Param("rating") int rating);

  // JPQL 이 아닌 네이티브 쿼리문 사용, 데이터베이스 제품별 특화 기능 사용하려면 네이티브 쿼리를 사용
  @Query(value = "select * from COURSES where rating=?1", nativeQuery = true)
  Iterable<Course> findAllByRating(int rating);

  @Modifying  // @Query 어노테이션과 같이 사용할 때만 효력 발휘
  @Transactional
  @Query("update Course c set c.rating=:rating where c.name=:name")
  // int 혹은 Integer 반환 타입인 경우, 변경된 행의 개수를 반환
  int updateCourseRatingByName(@Param("rating") int rating, @Param("name") String name);

  Iterable<DescriptionOnly> getCourseByName(String name);
}
