package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.th.koeln.archilab.fae.faeteam3service.antwort.AntwortDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.KontaktpersonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NachrichtDto {

  @JsonProperty("nachrichtId")
  private String entityId;
  private String text;
  private AntwortDto antwort;
  private KontaktpersonDto kontaktperson;

}
