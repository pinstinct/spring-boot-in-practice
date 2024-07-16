package com.limhm.maven.project.app.model.user.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class UserTest extends ApplicationTests {

  private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

  @Test
  public void validatePassword() {
    User user1 = new User("sbip01", "sbip");
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<User>> violations = validator.validate(user1);

    assertThat(violations).isNotNull();
    logger.error("Password for user1 do not adhere to the password policy");
    violations.forEach(userConstraintViolation -> logger.error("Violation details: [{}].",
        userConstraintViolation.getMessage()));

    User user2 = new User("sbip02", "Sbip01$4UDfg");
    violations = validator.validate(user2);
    assertThat(violations).isEmpty();
    logger.info("Password for user2 adhere to the password policy");

    User user3 = new User("sbip03", "Sbip01$4UDfgggg");
    violations = validator.validate(user3);
    assertThat(violations).isNotNull();
    logger.error("Password for user3 violates maximum repetitive rule");
    violations.forEach(userConstraintViolation -> logger.error("Violation details: [{}].",
        userConstraintViolation.getMessage()));

    User user4 = new User("sbip04", "Sbip014UDfgggg");
    violations = validator.validate(user4);
    assertThat(violations).isNotNull();
    logger.error("Password for user4 violates special character rule");
    violations.forEach(userConstraintViolation -> logger.error("Violation details: [{}].",
        userConstraintViolation.getMessage()));
  }
}