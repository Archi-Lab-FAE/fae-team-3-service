package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtDto;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class AusnahmesituationDto {
  @JsonProperty("ausnahmesituationId")
  private String entityId;
  @JsonUnwrapped
  private AusnahmesituationPositionssenderDto positionssender;
  private String text;
  private Set<NachrichtDto> nachrichten = new HashSet<>();
  private boolean hilfeUnterwegs = false;

}
