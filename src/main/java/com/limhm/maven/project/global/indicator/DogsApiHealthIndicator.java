package com.limhm.maven.project.global.indicator;

import java.util.Map;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component  // 스프링 부트 컴포넌트 스캔에 의해 감지되어 빈으로 등록
public class DogsApiHealthIndicator implements HealthIndicator {  // 애플리케이션 컴포넌트 상태를 알려주는 역할

  @Override
  public Health health() {
    try {
      ParameterizedTypeReference<Map<String, String>> reference = new ParameterizedTypeReference<Map<String, String>>() {
      };

      // RestTemplate 을 이용해서 외부 시스템 호출
      ResponseEntity<Map<String, String>> result = new RestTemplate().exchange(
          "https://dog.ceo/api/breeds/image/random", HttpMethod.GET, null, reference);

      if (result.getStatusCode().is2xxSuccessful() && result.getBody() != null) {
        return Health.up().withDetails(result.getBody()).build();
      } else {
        return Health.down().withDetail("status", result.getStatusCode()).build();
      }
    } catch (RestClientException exception) {
      return Health.down().withException(exception).build();
    }
  }
}
