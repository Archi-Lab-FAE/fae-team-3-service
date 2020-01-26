package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.service.NachrichtenService;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.service.TimeoutService;

import java.util.List;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log
@Service
public class AusnahmesituationService {

  private AusnahmesituationRepository ausnahmesituationRepository;
  private NachrichtenService nachrichtenService;

  @Autowired
  public AusnahmesituationService(AusnahmesituationRepository ausnahmesituationRepository,
                                  TimeoutService timeoutService,
                                  NachrichtenService nachrichtenService) {
    this.ausnahmesituationRepository = ausnahmesituationRepository;
    this.nachrichtenService = nachrichtenService;
  }

  Ausnahmesituation saveAusnahmesituationAndSendMessage(Ausnahmesituation ausnahmesituation) {
    log.info("Ausnahmesituation wird gespeichert und Nachrichten versendet...");
    ausnahmesituation = ausnahmesituationRepository.saveAndFlush(ausnahmesituation);
    nachrichtenService.sendeNachrichtToKontaktperson(ausnahmesituation);
    return ausnahmesituation;
  }

  List<Ausnahmesituation> getAllAusnahmesituationen() {
    log.info("Ausnahmesituationen werden abgefragt...");
    return ausnahmesituationRepository.findAll();
  }

  Ausnahmesituation getAusnahmesituation(String ausnahmesituationId) {
    log.info("Ausnahmesituationen mit ID " + ausnahmesituationId + " abgefragt...");
    return ausnahmesituationRepository.findById(ausnahmesituationId)
        .orElseThrow(AusnahmesituationNotFoundException::new);
  }

  void deleteAusnahmesituation(String ausnahmesituationId) {
    log.info("Lösche Ausnahmesituationen mit ID: " + ausnahmesituationId);
    ausnahmesituationRepository.deleteById(ausnahmesituationId);
  }

  void deleteAllAusnahmesituationen() {
    log.info("Löschen aller Ausnahmesituation wird durchgeführt...");
    ausnahmesituationRepository.deleteAll();
  }
}
