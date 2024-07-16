package com.limhm.maven.project.global.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RepeatCharacterRegexRule;
import org.passay.Rule;
import org.passay.RuleResult;

public class PasswordRuleValidator implements ConstraintValidator<Password, String> {
  /**
   * ConstraintValidator 인터페이스는 Password, String 두 개의 타입 인자를 갖고 있다.
   * 첫 번째 타입 인자는 커스텀 밸리데이터 로직을 적용하게 해주는 애너테이션
   * 두 번째 타입 인자는 커스텀 애너테이션을 적용해야 하는 데이터 타입
   * */

  private static final int MIN_COMPLEX_RULES = 2;
  private static final int MAX_REPETITIVE_CHARS = 3;
  private static final int MIN_SPECIAL_CASE_CHARS = 1;
  private static final int MIN_UPPER_CASE_CHARS = 1;
  private static final int MIN_LOWER_CASE_CHARS = 1;
  private static final int MIN_DIGIT_CASE_CHARS = 1;

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    // 비밀번호 정책: 최소 8자, 최대 30자, 대소문자와 숫자 혼합, 동일한 문자는 3번까지 반복 허용
    List<Rule> passwordRules = new ArrayList<>();
    passwordRules.add(new LengthRule(8, 30));
    CharacterCharacteristicsRule characterCharacteristicsRule = new CharacterCharacteristicsRule(
        MIN_COMPLEX_RULES, new CharacterRule(EnglishCharacterData.Special, MIN_SPECIAL_CASE_CHARS),
        new CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
        new CharacterRule(EnglishCharacterData.LowerCase, MIN_LOWER_CASE_CHARS),
        new CharacterRule(EnglishCharacterData.Digit, MIN_DIGIT_CASE_CHARS));
    passwordRules.add(characterCharacteristicsRule);
    passwordRules.add(new RepeatCharacterRegexRule(MAX_REPETITIVE_CHARS));
    PasswordValidator passwordValidator = new PasswordValidator(passwordRules);
    PasswordData passwordData = new PasswordData(password);
    RuleResult ruleResult = passwordValidator.validate(passwordData);
    return ruleResult.isValid();
  }
}
