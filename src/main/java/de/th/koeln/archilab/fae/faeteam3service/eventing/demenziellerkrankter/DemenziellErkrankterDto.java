package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.KontaktpersonDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.PositionssenderDto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class DemenziellErkrankterDto implements Serializable {

  private String id;
  private String name;
  private String vorname;
  private Boolean zustimmung;
  private List<KontaktpersonDto> kontaktpersonen;
  private List<PositionssenderDto> positionssender;

}
