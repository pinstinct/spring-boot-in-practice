package com.limhm.maven.project;

import com.limhm.maven.project.app.model.releaseNote.ReleaseItem;
import com.limhm.maven.project.app.model.releaseNote.ReleaseNote;
import com.limhm.maven.project.global.properties.AppProperties;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @ConfigurationProperties 붙어 있는 클래스를 스프링 컨테이너에 등록
@EnableConfigurationProperties(AppProperties.class)
public class Application {

  private static Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    Properties properties = new Properties();
    // ConfigDataLocationNotFoundException 예외를 무시하고, 애플리케이션 시동 작업을 계속 진행
    properties.setProperty("spring.config.on-not-found", "ignore");

    //SpringApplication 인스턴스를 직접 생성해서 웹 애플리케이션 타입을 서블릿으로 지정
    SpringApplication application = new SpringApplication(Application.class);
    application.setWebApplicationType(WebApplicationType.SERVLET);
    application.setDefaultProperties(properties);
    application.run(args);
    logger.info("Application started successfully with Log4j2 configuration");
  }

  /**
   * 릴리스 노트 정보를 담고 있는 빈 정의
   * */
  @Bean(name = "releaseNotes")
  public Collection<ReleaseNote> loadReleaseNotes() {
    Set<ReleaseNote> releaseNotes = new LinkedHashSet<>();
    ReleaseNote releaseNote1 = ReleaseNote.builder()
        .version("v1.2.1")
        .releaseDate(LocalDate.of(2021, 12, 30))
        .commitTag("a7d2ea3")
        .bugFixes(Set.of(
            ReleaseItem.builder()
                .itemId("SBIP-123")
                .itemDescription(
                    "The name of the matching-strategy property is incorrect in the action message of the failure analysis for a PatternParseException #28839")
                .build(),
            ReleaseItem.builder()
                .itemId("SBIP-124")
                .itemDescription(
                    "ErrorPageSecurityFilter prevents deployment to a Servlet 3.1 compatible container #28790")
                .build()
        ))
        .build();

    ReleaseNote releaseNote2 = ReleaseNote.builder()
        .version("v1.2.0")
        .releaseDate(LocalDate.of(2021, 11, 20))
        .commitTag("4407f3")
        .newReleases(Set.of(
            ReleaseItem.builder()
                .itemId("SBIP-125")
                .itemDescription("Support both kebab-case and camelCase as Spring init CLI Options")
                .build(),
            ReleaseItem.builder()
                .itemId("SBIP-126")
                .itemDescription(
                    "Profiles added using @ActiveProfiles have different precedence #28724")
                .build()
        ))
        .build();

    releaseNotes.addAll(Set.of(releaseNote1, releaseNote2));
    return releaseNotes;
  }

}
