package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Position;
import de.th.koeln.archilab.fae.faeteam3service.core.DtoMapper;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtDto;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AusnahmesituationDtoTest {

  private DtoMapper dtoMapper;

  @Before
  public void initDtoMapper() {
    dtoMapper = Mappers.getMapper(DtoMapper.class);
  }

  @Test
  public void whenConvertAusnahmesituationEntityToAusnahemsituationDto_thenCorrect() {
    Ausnahmesituation ausnahmesituation = new Ausnahmesituation();
    ausnahmesituation.setHilfeUnterwegs(false);
    ausnahmesituation.setNachrichtText(new NachrichtText("Hilfe"));
    ausnahmesituation.setPositionssender(new Positionssender("1"));

    Position position = new Position();
    position.setBreitengrad(1.1234);
    ausnahmesituation.setPosition(position);

    Set<Nachricht> nachrichten = new HashSet<>();
    Nachricht nachricht = new Nachricht();
    nachricht.setNachrichtText(new NachrichtText("Hilfe"));
    nachrichten.add(nachricht);
    ausnahmesituation.setNachrichten(nachrichten);

    AusnahmesituationDto ausnahmesituationDto = dtoMapper.convertToAusnahmesituationDto(ausnahmesituation);

    assertEquals(ausnahmesituation.getEntityId(), ausnahmesituationDto.getEntityId());
    assertEquals(ausnahmesituation.getHilfeUnterwegs(), ausnahmesituationDto.isHilfeUnterwegs());
    assertEquals(ausnahmesituation.getNachrichten().iterator().next().getNachrichtText().getText(),
        ausnahmesituationDto.getNachrichten().iterator().next().getText());
    assertEquals(ausnahmesituation.getPositionssender().getPositionssenderId(), ausnahmesituationDto.getPositionssender().getPositionssenderId());
    assertEquals(ausnahmesituation.getNachrichtText().getText(), ausnahmesituationDto.getText());
    assertEquals(ausnahmesituation.getPosition().getBreitengrad(), ausnahmesituationDto.getPosition().getBreitengrad());
  }

  @Test
  public void whenConvertAusnahmesituationDtoToAusnahemsituationEntity_thenCorrect() {
    AusnahmesituationDto ausnahmesituationDto = new AusnahmesituationDto();
    ausnahmesituationDto.setText("Hilfe");
    ausnahmesituationDto.setHilfeUnterwegs(false);

    AusnahmesituationPositionssenderDto ausnahmesituationPositionssenderDto = new AusnahmesituationPositionssenderDto();
    ausnahmesituationPositionssenderDto.setPositionssenderId("1");
    ausnahmesituationDto.setPositionssender(ausnahmesituationPositionssenderDto);

    PositionDto positionDto = new PositionDto();
    positionDto.setBreitengrad(1.2345);
    ausnahmesituationDto.setPosition(positionDto);

    Set<NachrichtDto> nachrichten = new HashSet<>();
    NachrichtDto nachricht = new NachrichtDto();
    nachricht.setText("Hilfe");
    nachrichten.add(nachricht);
    ausnahmesituationDto.setNachrichten(nachrichten);

    Ausnahmesituation ausnahmesituation = dtoMapper.convertToAusnahmesituationEntity(ausnahmesituationDto);

    assertEquals(ausnahmesituation.getNachrichtText().getText(), ausnahmesituationDto.getText());
    assertEquals(ausnahmesituation.getHilfeUnterwegs(), ausnahmesituationDto.isHilfeUnterwegs());
    assertEquals(ausnahmesituation.getPositionssender().getPositionssenderId(),
        ausnahmesituationDto.getPositionssender().getPositionssenderId());
    assertEquals(ausnahmesituation.getNachrichten().iterator().next().getNachrichtText().getText(),
        ausnahmesituationDto.getNachrichten().iterator().next().getText());
    assertEquals(ausnahmesituation.getPosition().getBreitengrad(), ausnahmesituationDto.getPosition().getBreitengrad());

  }

}