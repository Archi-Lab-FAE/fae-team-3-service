package de.th.koeln.archilab.fae.faeteam3service.antwort;

import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.AntwortTyp;
import de.th.koeln.archilab.fae.faeteam3service.core.DtoMapper;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.*;

public class AntwortDtoTest {

  private DtoMapper dtoMapper;

  @Before
  public void initDtoMapper() {
    dtoMapper = Mappers.getMapper(DtoMapper.class);
  }

  @Test
  public void whenConvertAntwortEntityToAntwortDto_thenCorrect() {
    Antwort antwort = new Antwort(AntwortTyp.KANN_NICHT_HELFEN);

    AntwortDto antwortDto = dtoMapper.convertToAntwortDto(antwort);

    assertEquals(antwort.getAntwortTyp().toString(), antwortDto.getAntwortTyp());
  }

  @Test
  public void whenConvertAntwortDtoToAntwortEntity_thenCorrect() {
    AntwortDto antwortDto = new AntwortDto();
    antwortDto.setAntwortTyp(AntwortTyp.KANN_NICHT_HELFEN.toString());

    Antwort antwort = dtoMapper.convertToAntwortEntity(antwortDto);

    assertEquals(antwort.getAntwortTyp().toString(), antwortDto.getAntwortTyp());
  }

}