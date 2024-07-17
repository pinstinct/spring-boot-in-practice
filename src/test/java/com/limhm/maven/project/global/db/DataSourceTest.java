package com.limhm.maven.project.global.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DataSourceTest extends ApplicationTests {

  @Autowired
  private DataSource dataSource;

  @Test
  public void givenDatasourceAvailableWhenAccessDetailsThenExpectDetails() throws SQLException {
    assertThat(dataSource.getClass().getName()).isEqualTo("com.zaxxer.hikari.HikariDataSource");
    assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("H2");
  }
}
