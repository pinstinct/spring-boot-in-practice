package com.limhm.maven.project.app.api.course.service;


import com.limhm.maven.project.app.api.course.dto.CourseCreateRequestDto;
import com.limhm.maven.project.app.api.course.event.CourseCreate;
import com.limhm.maven.project.app.api.course.mapper.CourseMapper;
import com.limhm.maven.project.app.model.course.entity.Course;
import com.limhm.maven.project.app.model.course.repository.CourseRepository;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CourseCommandService {

  private final CourseRepository courseRepository;
  private final ApplicationEventPublisher publisher;

  @Autowired
  private Timer createCourseTimer;

  @SneakyThrows  // 검사 예외(checked exception)을 비검사 예외(unchecked exception)로 감싸서 던지도록 한다.
  public void createCourse(CourseCreateRequestDto params) {
    Course course = CourseMapper.toEntity(params);

    publisher.publishEvent(CourseCreate.builder()
        .rating(params.rating())
        .build());

    // recordCallable 메서드는 java.util.concurrent.Callable 타입을 인자로 맏으므로,
    // 생성된 과정(Course)을 데이터베이스에 저장하고 값을 반환하는 Callable 객체를 람다식을 사용해서 정의한다.
    // 타이머는 내부적으로 Callable 객체 안에서 과정 생성 시 소요되는 시간을 측정한다.
    createCourseTimer.recordCallable(() ->
        courseRepository.save(course)
    );
  }
}
