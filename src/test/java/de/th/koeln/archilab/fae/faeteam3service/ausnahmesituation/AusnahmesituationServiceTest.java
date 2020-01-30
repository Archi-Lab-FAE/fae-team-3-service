package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.service.NachrichtenService;
import de.th.koeln.archilab.fae.faeteam3service.utils.AusnahmesituationBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AusnahmesituationServiceTest {

  private AusnahmesituationService mockAusnahmesituationService;
  private Ausnahmesituation ausnahmesituation;

  /** Test des AusnahmesituationService initialisieren. */
  @Before
  public void setUp() {
    AusnahmesituationRepository mockAusnahmesituationRepository =
        Mockito.mock(AusnahmesituationRepository.class);
    NachrichtenService mockNachrichtenService = Mockito.mock(NachrichtenService.class);

    ausnahmesituation = (new AusnahmesituationBuilder()).build();

    mockAusnahmesituationService =
        new AusnahmesituationService(mockAusnahmesituationRepository, mockNachrichtenService);

    when(mockAusnahmesituationRepository.saveAndFlush(ausnahmesituation))
        .thenReturn(ausnahmesituation);

    doAnswer(
        invocation -> {
          Nachricht nachrichtInAusnahmesituation = new Nachricht();
          nachrichtInAusnahmesituation.setNachrichtText(new NachrichtText("Hilfe"));
          ausnahmesituation.addNachricht(nachrichtInAusnahmesituation);
          return null;
        })
        .when(mockNachrichtenService)
        .sendeNachrichtToKontaktperson(any(Ausnahmesituation.class));

    Ausnahmesituation ausnahmesituation2 = (new AusnahmesituationBuilder()).build();
    ArrayList<Ausnahmesituation> ausnahmesituationen = new ArrayList<>();
    ausnahmesituationen.add(ausnahmesituation);
    ausnahmesituationen.add(ausnahmesituation2);
    when(mockAusnahmesituationRepository.findAll()).thenReturn(ausnahmesituationen);

    Optional<Ausnahmesituation> optionalAusnahmesituation = Optional.of(ausnahmesituation);

    when(mockAusnahmesituationRepository.findById(ausnahmesituation.getEntityId()))
        .thenReturn(optionalAusnahmesituation);

    doAnswer(invocationOnMock -> null)
        .when(mockAusnahmesituationRepository)
        .deleteById(any(String.class));
  }

  @Test
  public void saveAusnahmesituationAndSendMessage() {
    assertThat(ausnahmesituation.getNachrichten().size(), equalTo(0));
    assertThat(
        mockAusnahmesituationService.saveAusnahmesituationAndSendMessage(ausnahmesituation),
        instanceOf(Ausnahmesituation.class));
    assertThat(ausnahmesituation.getNachrichten().size(), equalTo(1));
  }

  @Test
  public void getAllAusnahmesituationen() {
    List<Ausnahmesituation> mockAusnahmesituationen =
        mockAusnahmesituationService.getAllAusnahmesituationen();
    assertThat(mockAusnahmesituationen, instanceOf(List.class));
    assertThat(mockAusnahmesituationen.size(), is(2));
  }

  @Test
  public void getAusnahmesituation() {
    Ausnahmesituation returnedAusnahmesituation =
        mockAusnahmesituationService.getAusnahmesituation(ausnahmesituation.getEntityId());

    assertNotNull(returnedAusnahmesituation);
    assertThat(returnedAusnahmesituation, instanceOf(Ausnahmesituation.class));
    assertThat(returnedAusnahmesituation.getEntityId(), equalTo(ausnahmesituation.getEntityId()));
  }

  @Test
  public void deleteAusnahmesituation() {
    try {
      mockAusnahmesituationService.deleteAusnahmesituation(ausnahmesituation.getEntityId());
    } catch (Exception e) {
      fail("Hier sollte keine Exception passieren.");
    }
  }

  @Test
  public void deleteAllAusnahmesituationen() {
    try {
      mockAusnahmesituationService.deleteAllAusnahmesituationen();
    } catch (Exception e) {
      fail("Hier sollte keine Exception passieren.");
    }
  }
}
