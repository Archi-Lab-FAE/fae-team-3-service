package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@Log
@Embeddable
@Data
@Setter(AccessLevel.NONE)
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Tracker {

  @NotNull(message = "Should not be null!")
  @NotEmpty(message = "Should not be empty!")
  private String trackerId;

}
