package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

import de.th.koeln.archilab.fae.faeteam3service.core.DtoMapper;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.KontaktpersonDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.PositionssenderDto;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.utils.DementiellErkrankterBuilder;
import de.th.koeln.archilab.fae.faeteam3service.utils.KontaktpersonBuilder;
import de.th.koeln.archilab.fae.faeteam3service.utils.PositionssenderBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DemenziellErkrankterDtoTest {

  private DtoMapper dtoMapper;

  @Before
  public void initDtoMapper() {
    dtoMapper = Mappers.getMapper(DtoMapper.class);
  }

  @Test
  public void whenConvertDemenziellErkrankterEntityToDemenziellErkrankterDto_thenCorrect() {
    DemenziellErkrankter demenziellErkrankter = new DementiellErkrankterBuilder().build();

    List<Kontaktperson> kontaktpersonList = new ArrayList<>();
    kontaktpersonList.add(new KontaktpersonBuilder().build());
    demenziellErkrankter.setKontaktpersonen(kontaktpersonList);

    List<Positionssender> positionssenderList = new ArrayList<>();
    positionssenderList.add(new PositionssenderBuilder().build());
    demenziellErkrankter.setPositionssender(positionssenderList);

    DemenziellErkrankterDto demenziellErkrankterDto = dtoMapper.convertToDemenziellErkrankterDto(demenziellErkrankter);

    assertEquals(demenziellErkrankter.getId(), demenziellErkrankterDto.getId());
    assertEquals(demenziellErkrankter.getName(), demenziellErkrankterDto.getName());
    assertEquals(demenziellErkrankter.getVorname(), demenziellErkrankterDto.getVorname());
    assertEquals(demenziellErkrankter.getZustimmung(), demenziellErkrankterDto.getZustimmung());
    assertEquals(demenziellErkrankter.getKontaktpersonen().iterator().next().getId(),
        demenziellErkrankterDto.getKontaktpersonen().iterator().next().getId());
    assertEquals(demenziellErkrankter.getPositionssender().iterator().next().getPositionssenderId(),
        demenziellErkrankterDto.getPositionssender().iterator().next().getId());
  }

  @Test
  public void whenConvertDemenziellErkrankterDtoToDemenziellErkrankterEntity_thenCorrect() {
    DemenziellErkrankterDto demenziellErkrankterDto = new DemenziellErkrankterDto();
    demenziellErkrankterDto.setName("Hilde");
    demenziellErkrankterDto.setVorname("HildeHilde");
    demenziellErkrankterDto.setZustimmung(true);

    List<KontaktpersonDto> kontaktpersonDtoList = new ArrayList<>();
    KontaktpersonDto kontaktpersonDto = dtoMapper.
        convertToKontaktpersonDto(new KontaktpersonBuilder().build());
    kontaktpersonDtoList.add(kontaktpersonDto);
    demenziellErkrankterDto.setKontaktpersonen(kontaktpersonDtoList);

    List<PositionssenderDto> positionssenderDtoList = new ArrayList<>();
    PositionssenderDto positionssenderDto = dtoMapper.
        convertToPositionssenderDto(new PositionssenderBuilder().build());
    positionssenderDtoList.add(positionssenderDto);
    demenziellErkrankterDto.setPositionssender(positionssenderDtoList);

    DemenziellErkrankter demenziellErkrankter = dtoMapper.convertToDemenziellErkrankterEntity(demenziellErkrankterDto);

    assertEquals(demenziellErkrankter.getId(), demenziellErkrankterDto.getId());
    assertEquals(demenziellErkrankter.getName(), demenziellErkrankterDto.getName());
    assertEquals(demenziellErkrankter.getVorname(), demenziellErkrankterDto.getVorname());
    assertEquals(demenziellErkrankter.getZustimmung(), demenziellErkrankterDto.getZustimmung());
    assertEquals(demenziellErkrankter.getKontaktpersonen().iterator().next().getId(),
        demenziellErkrankterDto.getKontaktpersonen().iterator().next().getId());
    assertEquals(demenziellErkrankter.getPositionssender().iterator().next().getPositionssenderId(),
        demenziellErkrankterDto.getPositionssender().iterator().next().getId());

  }

}