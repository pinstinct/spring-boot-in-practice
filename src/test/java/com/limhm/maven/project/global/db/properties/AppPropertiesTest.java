package com.limhm.maven.project.global.db.properties;

import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import com.limhm.maven.project.global.properties.AppProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AppPropertiesTest extends ApplicationTests {

  @Autowired
  private AppProperties appProperties;

  @Test
  void getAppProperties() throws Exception {
    assertThat(appProperties).isNotNull();
    System.out.println(appProperties.toString());
  }
}
