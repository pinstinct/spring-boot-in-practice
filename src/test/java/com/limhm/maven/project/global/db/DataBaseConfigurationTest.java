package com.limhm.maven.project.global.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DataBaseConfigurationTest extends ApplicationTests {

  @Autowired
  private DataBaseConfiguration db;

  @Test
  public void toStringTest() throws Exception {
    String result = db.toString();
    assertThat(result).isNotNull();
    System.out.println(result);
  }
}
