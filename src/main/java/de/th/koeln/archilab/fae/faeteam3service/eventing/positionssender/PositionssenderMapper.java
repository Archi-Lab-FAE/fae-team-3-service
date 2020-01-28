package de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender;

import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PositionssenderMapper {

  @Mapping(source = "id", target = "positionssenderId")
  Positionssender convertToEntity(PositionssenderDto positionssenderDto);

  @Mapping(source = "positionssenderId", target = "id")
  PositionssenderDto convertToDto(Positionssender positionssender);
}
