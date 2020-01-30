package de.th.koeln.archilab.fae.faeteam3service.antwort;

import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.AntwortRepository;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.AntwortTyp;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.service.NachrichtenService;

import java.util.Optional;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log
@Service
public class AntwortService {

  private AntwortRepository antwortRepository;
  private NachrichtRepository nachrichtRepository;
  private AusnahmesituationRepository ausnahmesituationRepository;
  private NachrichtenService nachrichtenService;

  @Autowired
  public AntwortService(AntwortRepository antwortRepository,
                        NachrichtRepository nachrichtRepository,
                        AusnahmesituationRepository ausnahmesituationRepository,
                        NachrichtenService nachrichtenService) {
    this.antwortRepository = antwortRepository;
    this.nachrichtRepository = nachrichtRepository;
    this.ausnahmesituationRepository = ausnahmesituationRepository;
    this.nachrichtenService = nachrichtenService;
  }

  public Antwort saveAntwortAndCheckAntwortTyp(final String nachrichtId, final Antwort antwort) {
    log.info("Erstelle Antwort: " + antwort.toString());

    Optional<Nachricht> optionaleNachricht = nachrichtRepository.findById(nachrichtId);

    //Dieser Fall sollte niemals passieren!
    if (!optionaleNachricht.isPresent() || optionaleNachricht.get().getAusnahmesituation().getHilfeUnterwegs().booleanValue()) {
        throw new AntwortNotAllowedException();
    }

    //Wenn nicht speicher sie und fülle den Rest
    Antwort antw = antwortRepository.save(antwort);
    Nachricht nachricht = optionaleNachricht.get();
    nachricht.setAntwort(antw);

    Optional<Ausnahmesituation> ausnahmesituation = ausnahmesituationRepository.findById(nachricht.getAusnahmesituation().getEntityId());
    if (antwort.getAntwortTyp() == AntwortTyp.KANN_HELFEN) {
      ausnahmesituation.ifPresent( it -> it.setHilfeUnterwegs(true));
      log.info("Für Ausnahmesituation mit der ID "+ nachricht.getAusnahmesituation().getEntityId() + " ist Hilfe unterwegs...");
    }

    //Nächste Kontaktperson kontaktieren
    if (antwort.getAntwortTyp() == AntwortTyp.KANN_NICHT_HELFEN) {
      ausnahmesituation.ifPresent( it -> nachrichtenService.sendeNachrichtToKontaktperson(it));
      log.info("Für Ausnahmesituation mit der ID "+ nachricht.getAusnahmesituation().getEntityId() +
          " wurde eine weiter Kontaktperson kontaktiert");
    }

    return antwortRepository.save(antw);
  }
}
