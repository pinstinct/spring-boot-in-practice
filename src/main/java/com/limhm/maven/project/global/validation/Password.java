package com.limhm.maven.project.global.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 애너테이션을 적용할 대상 타입 지정: 메서드, 필드
@Target({ElementType.METHOD, ElementType.FIELD})

// 애너테이션의 효력 유지 기간 지정: 런타임
// SOURCE: 소스 코드 수준에서만 효력을 발휘하고 컴파일된 바이너리 결과물에는 포함되지 않아 효력을 잃는다.
// CLASS: 컴파일된 바이너리 결과물에 포함되어 효력을 발휘하지만, 런타임에는 포함되지 않아 효력을 잃는다.
// RUNTIME: 런타임까지 살아남아 효력을 유지한다.
@Retention(RetentionPolicy.RUNTIME)

// 빈 밸리데이션 제약 사항을 포함하는 애너테이션임을 의미하며,
// validateBy 속성을 사용해서 제약 사항이 구현된 클래스를 지정할 수 있다.
@Constraint(validatedBy = PasswordRuleValidator.class)
public @interface Password {

  // 유효성 검증에 실패할 때 표시해야 하는 문자열 지정
  String message() default "Password do not adhere to the specified rule";

  // 그룹을 지정하면 밸리데이션을 그룹별로 구분해서 적용 가능
  Class<?>[] groups() default {};

  // 밸리데이션 클라이언트가 사용하는 메타데이터를 전달하기 위해 사용
  Class<? extends Payload>[] payload() default {};
}
