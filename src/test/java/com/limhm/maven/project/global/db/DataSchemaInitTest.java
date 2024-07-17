package com.limhm.maven.project.global.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DataSchemaInitTest extends ApplicationTests {

  @Autowired
  private DataSource dataSource;

  @Test
  public void whenCountAllCoursesThenExpectFiveCourses() throws SQLException {
    /*
    * JDBC 코드로 작성
    * */
    ResultSet rs = null;
    int noOfCourses = 0;

    try (PreparedStatement ps = dataSource.getConnection()
        .prepareStatement("SELECT COUNT(1) FROM" + " COURSES")) {
      rs = ps.executeQuery();
      while (rs.next()) {
        noOfCourses = rs.getInt(1);
      }
      assertThat(noOfCourses).isEqualTo(5);
    } finally {
      if (rs != null) {
        rs.close();
      }
    }
  }
}
