package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.KontaktpersonDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.PositionssenderDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtDto;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface AusnahmesituationMapper {

  @Mapping(source = "text", target = "nachrichtText")
  Ausnahmesituation toAusnahmesituation(AusnahmesituationDto ausnahmesituationDto);

  @Mapping(source = "nachrichtText", target = "text")
  AusnahmesituationDto toAusnahmesituationDto(Ausnahmesituation ausnahmesituation);

  List<AusnahmesituationDto> toAusnahmesituationDtos(List<Ausnahmesituation> ausnahmesituationen);

  Kontaktperson convertToKontaktpersonEntity(KontaktpersonDto kontaktpersonDto);

  KontaktpersonDto convertToKontaktpersonDto(Kontaktperson kontaktperson);

  Positionssender convertToPositionssenderEntity(PositionssenderDto positionssenderDto);

  PositionssenderDto convertToPositionssenderDto(Positionssender positionssender);

  @Mapping(source = "text", target = "nachrichtText")
  Nachricht convertToNachrichtEntity(NachrichtDto nachrichtDto);

  @Mapping(source = "nachrichtText", target = "text")
  NachrichtDto convertToNachrichtDto(Nachricht nachricht);

  default String convertToTextString(NachrichtText nachrichtText) {
    return nachrichtText.getText();
  }

  default NachrichtText convertToNachrichtText(String text) {
    return new NachrichtText(text);
  }
}
