package com.limhm.maven.project.app.model.releaseNote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseItem {

  private String itemId;
  private String itemDescription;
}
