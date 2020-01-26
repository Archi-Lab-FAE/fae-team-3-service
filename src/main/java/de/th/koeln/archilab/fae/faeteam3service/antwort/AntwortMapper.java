package de.th.koeln.archilab.fae.faeteam3service.antwort;

import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import org.mapstruct.Mapper;

@Mapper
public interface AntwortMapper {
  AntwortDto convertToDto(Antwort antwort);

  Antwort convertToEntity(AntwortDto antwortDto);
}
