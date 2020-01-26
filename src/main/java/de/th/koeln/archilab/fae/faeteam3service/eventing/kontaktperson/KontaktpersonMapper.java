package de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson;

import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import org.mapstruct.Mapper;

@Mapper
public interface KontaktpersonMapper {

  Kontaktperson convertToEntity(KontaktpersonDto kontaktpersonDto);

  KontaktpersonDto convertToDto(Kontaktperson kontaktperson);
}
