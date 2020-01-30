package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import de.th.koeln.archilab.fae.faeteam3service.antwort.AntwortDto;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.AntwortTyp;
import de.th.koeln.archilab.fae.faeteam3service.core.DtoMapper;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.KontaktpersonDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;
import de.th.koeln.archilab.fae.faeteam3service.utils.KontaktpersonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.*;

public class NachrichtDtoTest {

  private DtoMapper dtoMapper;

  @Before
  public void initDtoMapper() {
    dtoMapper = Mappers.getMapper(DtoMapper.class);
  }

  @Test
  public void whenConvertNachrichtEntityToNachrichtDto_thenCorrect() {
    Nachricht nachricht = new Nachricht();
    nachricht.setNachrichtText(new NachrichtText("Hilfe"));
    nachricht.setAntwort(new Antwort(AntwortTyp.KANN_NICHT_HELFEN));
    nachricht.setKontaktperson(new KontaktpersonBuilder().build());

    NachrichtDto nachrichtDto = dtoMapper.convertToNachrichtDto(nachricht);

    assertEquals(nachricht.getNachrichtText().getText(), nachrichtDto.getText());
    assertEquals(nachricht.getAntwort().getAntwortTyp().toString(), nachrichtDto.getAntwort().getAntwortTyp());
    assertEquals(nachricht.getKontaktperson().getId(), nachrichtDto.getKontaktperson().getId());

  }

  @Test
  public void whenConvertNachrichtDtoToNachrichtEntity_thenCorrect() {
    NachrichtDto nachrichtDto = new NachrichtDto();
    nachrichtDto.setText("Hilfe");

    AntwortDto antwortDto = new AntwortDto();
    antwortDto.setAntwortTyp(AntwortTyp.KANN_NICHT_HELFEN.toString());
    nachrichtDto.setAntwort(antwortDto);

    Kontaktperson kontaktperson = new KontaktpersonBuilder().build();
    KontaktpersonDto kontaktpersonDto = dtoMapper.convertToKontaktpersonDto(kontaktperson);
    nachrichtDto.setKontaktperson(kontaktpersonDto);

    Nachricht nachricht = dtoMapper.convertToNachrichtEntity(nachrichtDto);

    assertEquals(nachricht.getNachrichtText().getText(), nachrichtDto.getText());
    assertEquals(nachricht.getAntwort().getAntwortTyp().toString(), nachrichtDto.getAntwort().getAntwortTyp());
    assertEquals(nachricht.getKontaktperson().getId(), nachrichtDto.getKontaktperson().getId());

  }

}