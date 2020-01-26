package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import de.th.koeln.archilab.fae.faeteam3service.antwort.AntwortDto;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.KontaktpersonDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NachrichtMapper {

  @Mapping(source = "text", target = "nachrichtText")
  Nachricht convertToEntity(NachrichtDto nachrichtDto);

  @Mapping(source = "nachrichtText", target = "text")
  NachrichtDto convertToDto(Nachricht nachricht);

  Kontaktperson convertToKontaktpersonEntity(KontaktpersonDto kontaktpersonDto);

  KontaktpersonDto convertToKontaktpersonDto(Kontaktperson kontaktperson);

  Antwort convertToAntwortEntity(AntwortDto antwortDto);

  AntwortDto convertToAntwortDto(Antwort antwort);

  default String convertToTextString(NachrichtText nachrichtText) {
    return nachrichtText.getText();
  }

  default NachrichtText convertToNachrichtText(String text) {
    return new NachrichtText(text);
  }

}
