package com.limhm.maven.project.app.api.releaseNote.controller;

import com.limhm.maven.project.app.model.releaseNote.ReleaseNote;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@Component  // 스프링 부트 컴포넌트 스캔으로 감지하고 빈으로 생성되게 함
@Endpoint(id = "releaseNotes")  // 액추에이터 엔드포인트로 사용
public class ReleaseNoteEndpoint {

  private final Collection<ReleaseNote> releaseNotes;

  @Autowired
  public ReleaseNoteEndpoint(Collection<ReleaseNote> releaseNotes) {
    this.releaseNotes = releaseNotes;
  }

  /**
   * <a href="http://localhost:8081/actuator/releaseNotes">모든 버전의 릴리스 상세 정보</a>
   */
  @ReadOperation
  public Collection<ReleaseNote> releaseNotes() {
    return releaseNotes;
  }

  /**
   * <a href="http://localhost:8081/actuator/releaseNotes/v1.2.1">버전별 릴리스 상세 정보</a>
   */
  @ReadOperation
  public Object selectCourse(@Selector String version) {
    Optional<ReleaseNote> releaseNoteOptional = releaseNotes
        .stream()
        .filter(releaseNote -> version.equals(releaseNote.getVersion()))
        .findFirst();

    if (releaseNoteOptional.isPresent()) {
      return releaseNoteOptional.get();
    }
    return String.format("No such release version exists : %s", version);
  }
}
