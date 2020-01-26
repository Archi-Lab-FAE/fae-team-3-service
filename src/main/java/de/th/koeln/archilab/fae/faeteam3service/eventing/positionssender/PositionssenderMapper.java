package de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender;

import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import org.mapstruct.Mapper;

@Mapper
public interface PositionssenderMapper {

  Positionssender convertToEntity(PositionssenderDto positionssenderDto);

  PositionssenderDto convertToDto(Positionssender positionssender);
}
