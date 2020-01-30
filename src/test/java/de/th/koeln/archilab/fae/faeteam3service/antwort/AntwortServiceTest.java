package de.th.koeln.archilab.fae.faeteam3service.antwort;

import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.AntwortRepository;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.AntwortTyp;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.service.NachrichtenService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;


public class AntwortServiceTest {

  private AntwortService mockAntwortService;
  private AntwortRepository mockAntwortRepository;
  private NachrichtRepository mockNachrichtenRepository;
  private AusnahmesituationRepository mockAusnahmesituationRepository;
  private NachrichtenService mockNachrichtenService;

  private Antwort kannHelfenAntwort;
  private Antwort kannNichtHelfenAntwort;
  private Nachricht nachrichtKannHelfen;
  private Nachricht nachrichtKannNichtHelfen;
  private Ausnahmesituation ausnahmesituation;

  @Before
  public void setUp() {
    mockAntwortRepository = Mockito.mock(AntwortRepository.class);
    mockNachrichtenRepository = Mockito.mock(NachrichtRepository.class);
    mockAusnahmesituationRepository = Mockito.mock(AusnahmesituationRepository.class);
    mockNachrichtenService = Mockito.mock(NachrichtenService.class);

    ausnahmesituation = new Ausnahmesituation("1");
    ausnahmesituation.setHilfeUnterwegs(false);
    Nachricht nachrichtInAusnahmesituation = new Nachricht("1");
    nachrichtInAusnahmesituation.setNachrichtText(new NachrichtText("Hilfe"));
    Set<Nachricht> nachrichtenInAusnahmesituation = new HashSet<>();
    nachrichtenInAusnahmesituation.add(nachrichtInAusnahmesituation);
    ausnahmesituation.setNachrichten(nachrichtenInAusnahmesituation);

    nachrichtKannHelfen = new Nachricht("1");
    nachrichtKannHelfen.setNachrichtText(new NachrichtText("Hilfe"));
    nachrichtKannHelfen.setAntwort(kannHelfenAntwort);
    nachrichtKannHelfen.setAusnahmesituation(ausnahmesituation);

    nachrichtKannNichtHelfen = new Nachricht("1");
    nachrichtKannNichtHelfen.setNachrichtText(new NachrichtText("Hilfe"));
    nachrichtKannNichtHelfen.setAntwort(kannHelfenAntwort);
    nachrichtKannNichtHelfen.setAusnahmesituation(ausnahmesituation);

    Optional<Nachricht> optionalNachricht = Optional.ofNullable(nachrichtKannHelfen);

    when(mockNachrichtenRepository.findById(any(String.class))).thenReturn(optionalNachricht);

    kannHelfenAntwort = new Antwort(AntwortTyp.KANN_HELFEN);
    kannNichtHelfenAntwort = new Antwort(AntwortTyp.KANN_NICHT_HELFEN);

    when(mockAntwortRepository.save(any(Antwort.class))).thenReturn(kannHelfenAntwort);

    Optional<Ausnahmesituation> optionalAusnahmesituation = Optional.ofNullable(ausnahmesituation);

    when(mockAusnahmesituationRepository.findById(any(String.class))).thenReturn(optionalAusnahmesituation);

    mockAntwortService = new AntwortService(mockAntwortRepository,
        mockNachrichtenRepository,
        mockAusnahmesituationRepository,
        mockNachrichtenService);
  }

  @Test
  public void ifAntwortIsKannHelfen_thenSetHilfeUnterwegsInAusnahmesituationToTrue() {
    mockAntwortService.saveAntwortAndCheckAntwortTyp("1", kannHelfenAntwort);

    assertThat(ausnahmesituation.getHilfeUnterwegs().booleanValue(), equalTo(true));
  }

  @Test
  public void ifAntwortIsKannNichtHelfen_thenSendMessageToNextKontaktperson() {

    doAnswer(invocation -> {
      Nachricht neueNachricht = new Nachricht();
      neueNachricht.setNachrichtText(new NachrichtText("Hilf mir bitte"));
      ausnahmesituation.addNachricht(neueNachricht);
      return null;
    }).when(mockNachrichtenService).sendeNachrichtToKontaktperson(any(Ausnahmesituation.class));

    mockAntwortService.saveAntwortAndCheckAntwortTyp("1", kannNichtHelfenAntwort);

    assertThat(ausnahmesituation.getNachrichten().size(), equalTo(2));
  }

}