package de.th.koeln.archilab.fae.faeteam3service.antwort;

import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.AntwortRepository;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.AntwortTyp;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.service.NachrichtenService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class AntwortService {

  @Autowired
  private AntwortRepository antwortRepository;
  @Autowired
  private NachrichtRepository nachrichtRepository;
  @Autowired
  private AusnahmesituationRepository ausnahmesituationRepository;
  @Autowired
  private NachrichtenService nachrichtenService;

  Antwort saveAntwortAndCheckAntwortTyp(final String nachrichtId, final Antwort antwort) {
    log.info("Erstelle Antwort: " + antwort.toString());

    Antwort antw = antwortRepository.save(antwort);
    nachrichtRepository.findById(nachrichtId).ifPresent(nachricht -> {
      nachricht.setAntwort(antw);

      if (antwort.getAntwortTyp() == AntwortTyp.KANN_HELFEN) {
        nachricht.getAusnahmesituation().setIstAbgeschlossen(true);

        ausnahmesituationRepository.findById(nachricht.getAusnahmesituation().getEntityId())
            .ifPresent(ausnahmesituation -> ausnahmesituation.setIstAbgeschlossen(true));

        log.info("Ausnahmesituation mit der ID "
            + nachricht.getAusnahmesituation().getEntityId()
            + " abgeschlossen...");
      }

      if (antwort.getAntwortTyp() == AntwortTyp.KANN_NICHT_HELFEN) {
        ausnahmesituationRepository.findById(nachricht.getAusnahmesituation().getEntityId())
            .ifPresent(ausnahmesituation -> {
              nachrichtenService.sendeNachrichtToKontaktperson(ausnahmesituation);
            });
      }
    });

    return antwortRepository.save(antw);
  }
}
