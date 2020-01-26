package de.th.koeln.archilab.fae.faeteam3service.nachricht.service;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankterRepository;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log
@Service
public class NachrichtenService {

  @Autowired
  DemenziellErkrankterRepository demenziellErkrankterRepository;
  @Autowired
  NachrichtRepository nachrichtRepository;
  @Autowired
  TimeoutService timeoutService;
  @Autowired
  AusnahmesituationRepository ausnahmesituationRepository;

  public void sendeNachrichtToKontaktperson(Ausnahmesituation ausnahmesituation) {
    DemenziellErkrankter demenziellErkrankter = demenziellErkrankterRepository
        .findDemenziellErkrankterByPositionssenderContains(ausnahmesituation.getPositionssender());

    List<Kontaktperson> kontaktpersonen = new ArrayList<>(
        demenziellErkrankter.getKontaktpersonen());

    Set<Nachricht> nachrichten = ausnahmesituation.getNachrichten();

    kontaktpersonen.removeIf(kontaktperson -> !kontaktperson.getAktiv());

    for (Nachricht nachricht : nachrichten) {
      kontaktpersonen.removeIf(kontaktperson ->
          kontaktperson.getId().equals(nachricht.getKontaktperson().getId()));
    }

    if (kontaktpersonen.isEmpty()) {
      log.info("Es wurden bereits alle Kontaktpersonen benachrichtigt...");
    } else {
      Nachricht neueNachricht = nachrichtRepository.save(
          new Nachricht(ausnahmesituation.getNachrichtText(), kontaktpersonen.get(0)));
      ausnahmesituation.addNachricht(neueNachricht);
      neueNachricht = nachrichtRepository.save(neueNachricht);

      timeoutService.checkTimeout(ausnahmesituation.getEntityId(), neueNachricht);

      log.info("Nachricht an " + kontaktpersonen.get(0).getVorname() + " gesendet ...");
    }
  }

  public Nachricht addNachrichtToAusnahmesituation(String ausnahmesituationId,
                                                   Nachricht nachricht) {
    Optional<Ausnahmesituation> ausnahmesituation = ausnahmesituationRepository
        .findById(ausnahmesituationId);

    if (ausnahmesituation.isPresent()) {
      ausnahmesituation.get().addNachricht(nachricht);
      nachricht.setAusnahmesituation(ausnahmesituation.get());

      ausnahmesituationRepository.save(ausnahmesituation.get());
      nachrichtRepository.save(nachricht);
    }

    log.info("Füge der Ausnahme mit der ID: " + ausnahmesituationId + " eine Nachricht hinzu.");

    return nachricht;
  }

}

