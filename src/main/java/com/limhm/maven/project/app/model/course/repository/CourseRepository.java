package com.limhm.maven.project.app.model.course.repository;

import com.limhm.maven.project.app.model.course.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository  // 스프링 리포지터리로 사용된다는 것을 알려줌, 예외 변환(JPA 구현체마다 달라지는 예외가 아니라 DataAccessException 만 처리하면 됨)
public interface CourseRepository extends CrudRepository<Course, Long> {
  // 비어 있더라도 스프링 데이터 JPA 가 런타임에 인터페이스 구현체를 자동으로 생성
}
