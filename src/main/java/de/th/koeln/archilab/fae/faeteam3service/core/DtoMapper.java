package de.th.koeln.archilab.fae.faeteam3service.core;

import de.th.koeln.archilab.fae.faeteam3service.antwort.AntwortDto;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.AusnahmesituationDto;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.AusnahmesituationPositionssenderDto;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.PositionDto;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Position;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.DemenziellErkrankterDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankter;
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
public interface DtoMapper {

  // Ausnahmesituation
  @Mapping(source = "text", target = "nachrichtText")
  Ausnahmesituation convertToAusnahmesituationEntity(AusnahmesituationDto ausnahmesituationDto);

  @Mapping(source = "nachrichtText", target = "text")
  AusnahmesituationDto convertToAusnahmesituationDto(Ausnahmesituation ausnahmesituation);

  List<AusnahmesituationDto> convertToAusnahmesituationDtoList(
      List<Ausnahmesituation> ausnahmesituationen);

  // Position
  Position convertToPositionEntity(PositionDto positionDto);

  PositionDto convertToPositionDto(Position position);

  // Kontaktperson
  Kontaktperson convertToKontaktpersonEntity(KontaktpersonDto kontaktpersonDto);

  KontaktpersonDto convertToKontaktpersonDto(Kontaktperson kontaktperson);

  List<Kontaktperson> convertToKontaktpersonEntityList(List<KontaktpersonDto> kontaktpersonDtos);

  List<KontaktpersonDto> convertToKontaktpersonDtoList(List<Kontaktperson> kontaktpersonDtos);

  // Positionssender
  @Mapping(source = "id", target = "positionssenderId")
  Positionssender convertToPositionssenderEntity(PositionssenderDto positionssenderDto);

  @Mapping(source = "positionssenderId", target = "id")
  PositionssenderDto convertToPositionssenderDto(Positionssender positionssender);

  List<Positionssender> convertToPositionssenderEntityList(
      List<PositionssenderDto> positionssenderDtos);

  List<PositionssenderDto> convertToPositionssenderDtoList(List<Positionssender> positionssender);

  Positionssender convertToPositionssenderEntityFromAusnahmesituationPositionssenderDto(
      AusnahmesituationPositionssenderDto ausnahmesituationPositionssenderDto);

  AusnahmesituationPositionssenderDto convertToAusnahmesituationPositionssenderDto(
      Positionssender positionssender
  );

  // Demenziell Erkrankter
  DemenziellErkrankter convertToDemenziellErkrankterEntity(
      DemenziellErkrankterDto demenziellErkrankterDto);

  DemenziellErkrankterDto convertToDemenziellErkrankterDto(
      DemenziellErkrankter demenziellErkrankterDto);

  // Antwort
  AntwortDto convertToAntwortDto(Antwort antwort);

  Antwort convertToAntwortEntity(AntwortDto antwortDto);

  // Nachricht
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
