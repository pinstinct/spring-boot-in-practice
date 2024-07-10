package com.limhm.maven.project;

import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    Properties properties = new Properties();
    // ConfigDataLocationNotFoundException 예외를 무시하고, 애플리케이션 시동 작업을 계속 진행
    properties.setProperty("spring.config.on-not-found", "ignore");

    //SpringApplication 인스턴스를 직접 생성해서 웹 애플리케이션 타입을 서블릿으로 지정
    SpringApplication application = new SpringApplication(Application.class);
    application.setWebApplicationType(WebApplicationType.SERVLET);
    application.setDefaultProperties(properties);
    application.run(args);
  }

}
