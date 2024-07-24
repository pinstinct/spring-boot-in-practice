package com.limhm.maven.project.app.api.course.event;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourseCreateListener {

  private final Counter createCourseCounter;
  private final DistributionSummary distributionSummary;

  @EventListener
  public void handleCourseCreate(CourseCreate courseCreate) {
    try {
      createCourseCounter.increment();
      distributionSummary.record(courseCreate.rating());
    } catch (Exception e) {
      log.error("Error handling CourseCreateListener:  {}", e.getMessage());
    }
  }
}
