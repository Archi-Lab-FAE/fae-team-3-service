package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.KontaktpersonDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.PositionssenderDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;

import java.util.List;

import org.mapstruct.Mapper;


@Mapper
public interface DemenziellErkrankterMapper {

  DemenziellErkrankter convertToEntity(DemenziellErkrankterDto demenziellErkrankterDto);

  DemenziellErkrankterDto convertToDto(DemenziellErkrankter demenziellErkrankterDto);

  Positionssender convertToPositionssenderEntity(PositionssenderDto positionssenderDto);

  PositionssenderDto convertToPositionssenderDto(Positionssender positionssender);

  List<Positionssender> convertToPositionssenderEntityList(
      List<PositionssenderDto> positionssenderDtos);

  List<PositionssenderDto> convertToPositionssenderDtoList(List<Positionssender> positionssender);

  Kontaktperson convertToKontaktpersonEntity(KontaktpersonDto kontaktpersonDto);

  KontaktpersonDto convertToKontaktpersonDto(Kontaktperson kontaktperson);

  List<Kontaktperson> convertToKontaktpersonEntityList(List<KontaktpersonDto> kontaktpersonDtos);

  List<KontaktpersonDto> convertToKontaktpersonDtoList(List<Kontaktperson> kontaktpersonDtos);

}
