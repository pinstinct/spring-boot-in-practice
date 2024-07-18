package com.limhm.maven.project.global.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * CurdRepository 메서드 중에서 사용할 메서드만 선택해서 BaseRepository 에 동일하게 추가
 * BaseRepository 에 정의된 메서드는 CurdRepository 에 정의된 메서드와 시그니처(*)가 동일하므로,
 * 이 메서드가 호출되면 스프링 데이터 런타임에 실제 JPA 구현체의 메서드를 호출한다.
 * (*)시그니처: 메서드의 동일성을 판별하는 기준으로서 메서드의 이름과 파라미터타입으로 이루어져 있다.
 * */
@NoRepositoryBean  // 구현체가 자동으로 만들어지지 않도록 애너테이션 추가
public interface BaseRepository<T, ID> extends Repository<T, ID> {

  <S extends T> S save(S entity);

  Iterable<T> findAll();
}
