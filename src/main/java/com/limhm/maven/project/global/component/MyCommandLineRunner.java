package com.limhm.maven.project.global.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)  // CommandLineRunner 구현체가 여러 개 있을 때, 실행 순서 지정
@Component
public class MyCommandLineRunner implements CommandLineRunner {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void run(String... args) throws Exception {
    logger.info("MyCommandLineRunner executed as a Spring Component");
  }
}
