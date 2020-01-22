package de.th.koeln.archilab.fae.faeteam3service.nachricht.service;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.DemenziellErkrankterRepository;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtRepository;

import java.util.ArrayList;
import java.util.List;
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

  public void send(Ausnahmesituation ausnahmesituation) {
    String positionssenderId = ausnahmesituation.getPositionssender().getPositionssenderId();
    DemenziellErkrankter demenziellErkrankter = getDemeziellErkrankterByPositionssenderId(
        positionssenderId);

    List<Kontaktperson> kontaktpersonen = new ArrayList<>(
        demenziellErkrankter.getKontaktpersonen());
    Set<Nachricht> nachrichten = ausnahmesituation.getNachrichten();

    for (Nachricht nachricht : nachrichten) {
      if (kontaktpersonen.contains(nachricht.getKontaktperson())) {
        kontaktpersonen.remove(nachricht.getKontaktperson());
      }
    }

    kontaktpersonen.removeIf(kontaktperson -> !kontaktperson.getAktiv());
    kontaktpersonen.sort((o1, o2) -> o1.getPrioritaet() - o2.getPrioritaet());

    // TODO: Mit Team 1 absprechen ob das Altersheim als Kontaktperson mit letzter Priorität hat
    if (kontaktpersonen.isEmpty()) {
      log.info("Es wurden bereits alle Kontaktpersonen benachrichtigt...");
    } else {
      Nachricht neueNachricht = nachrichtRepository.save(
          new Nachricht(ausnahmesituation.getNachrichtText(), kontaktpersonen.get(0)));
      ausnahmesituation.addNachricht(neueNachricht);

      nachrichtRepository.save(neueNachricht);

      log.info("Nachricht an " + kontaktpersonen.get(0).getVorname()
          + " mit Prioritaet " + kontaktpersonen.get(0).getPrioritaet() + " gesendet ...");
    }

  }

  // TODO: In DemenziellErkrankterRepository implementieren
  private DemenziellErkrankter getDemeziellErkrankterByPositionssenderId(String positionssenderId) {
    for (DemenziellErkrankter demenziellErkrankter : demenziellErkrankterRepository.findAll()) {
      for (Positionssender positionssender : demenziellErkrankter.getPositionssender()) {
        if (positionssender.getPositionssenderId().equals(positionssenderId)) {
          return demenziellErkrankter;
        }
      }
    }
    return new DemenziellErkrankter();
  }
}
