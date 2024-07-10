package com.limhm.maven.project.global.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:db.properties")
public class DataBaseConfiguration {

  @Autowired
  private Environment environment;

  @Override
  public String toString() {
    return "User: " + environment.getProperty("user") + ", Password: " + environment.getProperty(
        "password");
  }
}
