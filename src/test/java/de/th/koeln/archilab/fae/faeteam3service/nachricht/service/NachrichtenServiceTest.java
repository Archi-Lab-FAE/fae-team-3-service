package de.th.koeln.archilab.fae.faeteam3service.nachricht.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.hamcrest.CoreMatchers.equalTo;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankterRepository;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class NachrichtenServiceTest {
  private final String POSITIONSSENDER_ID = "1";
  private final String NACHRICHT_TEXT = "HILFE";

  private NachrichtenService nachrichtenService;
  private DemenziellErkrankterRepository mockDemenziellErkrankterRepository;
  private NachrichtRepository mockNachrichtRepository;
  private Positionssender positionssender;
  private Nachricht nachricht;
  private Ausnahmesituation ausnahmesituation;

  @Before
  public void setUp() {
    mockDemenziellErkrankterRepository = Mockito.mock(DemenziellErkrankterRepository.class);
    mockNachrichtRepository = Mockito.mock(NachrichtRepository.class);
    positionssender = new Positionssender(POSITIONSSENDER_ID);
    ausnahmesituation = createAusnahmesituationOhneNachrichten("1");

    Kontaktperson kontaktperson1 = createKontaktperson("1");
    Kontaktperson kontaktperson2 = createKontaktperson("2");
    Kontaktperson kontaktperson3 = createKontaktperson("3");

    List<Kontaktperson> listOfKontaktpersonenInDemenziellErkrankter = new ArrayList<>();
    listOfKontaktpersonenInDemenziellErkrankter.add(kontaktperson1);
    listOfKontaktpersonenInDemenziellErkrankter.add(kontaktperson2);
    listOfKontaktpersonenInDemenziellErkrankter.add(kontaktperson3);

    List<Positionssender> listOfPositionssenderInDemeziellErkrankter = new ArrayList<>();
    listOfPositionssenderInDemeziellErkrankter.add(positionssender);

    DemenziellErkrankter demenziellErkrankter = new DemenziellErkrankter();
    demenziellErkrankter.setId("1");
    demenziellErkrankter.setPositionssender(listOfPositionssenderInDemeziellErkrankter);
    demenziellErkrankter.setKontaktpersonen(listOfKontaktpersonenInDemenziellErkrankter);

    when(mockDemenziellErkrankterRepository
        .findDemenziellErkrankterByPositionssenderContains(any(Positionssender.class)))
        .thenReturn(demenziellErkrankter);

    nachricht = new Nachricht("1");
    nachricht.setNachrichtText(new NachrichtText(NACHRICHT_TEXT));
    nachricht.setKontaktperson(kontaktperson1);
    nachricht.setAusnahmesituation(ausnahmesituation);

    when(mockNachrichtRepository.save(any(Nachricht.class))).thenReturn(nachricht);

    nachrichtenService = new NachrichtenService(mockDemenziellErkrankterRepository,
        mockNachrichtRepository,
        Mockito.mock(TimeoutService.class),
        Mockito.mock(AusnahmesituationRepository.class));
  }

  @Test
  public void sendeNachrichtToKontaktpersonTest() {
    nachrichtenService.sendeNachrichtToKontaktperson(ausnahmesituation);

    for (Nachricht nachrichtInAusnahmesituation : ausnahmesituation.getNachrichten()) {
      assertThat(nachrichtInAusnahmesituation.getNachrichtText(), equalTo(nachricht.getNachrichtText()));
      break;
    }
  }

  private Ausnahmesituation createAusnahmesituationOhneNachrichten(final String id) {
    Ausnahmesituation ausnahmesituation = new Ausnahmesituation(id);
    ausnahmesituation.setPositionssender(positionssender);
    ausnahmesituation.setHilfeUnterwegs(false);
    ausnahmesituation.setNachrichtText(new NachrichtText(NACHRICHT_TEXT));
    ausnahmesituation.setNachrichten(new HashSet<>());

    return ausnahmesituation;
  }

  private Kontaktperson createKontaktperson(final String id) {
    return new Kontaktperson(id, "Fritz", "Fritz", "12345789", true);
  }

}