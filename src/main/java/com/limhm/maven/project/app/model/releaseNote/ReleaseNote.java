package com.limhm.maven.project.app.model.releaseNote;

import java.time.LocalDate;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReleaseNote {
  private String version;
  private LocalDate releaseDate;
  private String commitTag;
  private Set<ReleaseItem> newReleases;
  private Set<ReleaseItem> bugFixes;
}


