package com.limhm.maven.project.global.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfiguration {

  /**
   * 생성된 과정의 수를 Counter 를 사용해서 확인 /actuator/metrics/api.course.created.count
   */
  @Bean
  public Counter createCourseCounter(MeterRegistry meterRegistry) {
    return Counter.builder("api.course.created.count")
        .description("Total number of courses created")
        .register(meterRegistry);  // MeterRegistry 에 등록
  }

  /**
   * 과정 생성에 소요된 시간을 Timer 를 사용해서 확인 /actuator/metrics/api.course.creation.time
   */
  @Bean
  public Timer createCourseTimer(MeterRegistry meterRegistry) {
    return Timer.builder("api.course.creation.time")
        .description("Course creation time")
        .register(meterRegistry);
  }

  /**
   * 과정 평점에 대한 분포 요약을 DistributionSummary 를 사용해서 확인
   * /actuator/metrics/api.course.rating.distribution.summary
   */
  @Bean
  public DistributionSummary createDistributionSummary(MeterRegistry meterRegistry) {
    return DistributionSummary.builder("api.course.rating.distribution.summary")
        .description("Rating distribution summary")
        .register(meterRegistry);
  }
}
