package de.th.koeln.archilab.fae.faeteam3service.nachricht.service;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.DemenziellErkrankterRepository;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Log
@Service
public class NachrichtenService implements ApplicationContextAware {
  private static ApplicationContext applicationContext;

  @Autowired
  DemenziellErkrankterRepository demenziellErkrankterRepository;

  @Autowired
  NachrichtRepository nachrichtRepository;

  public static NachrichtenService lookup() {
    return applicationContext.getBean(NachrichtenService.class);
  }

  public void send(Ausnahmesituation ausnahmesituation, int kontaktpersonPrioritaet) {

    boolean alleKontaktpersonenBenachrichtigt = false;

    for (Nachricht nachricht : ausnahmesituation.getNachrichten()) {
      if (nachricht.getKontaktperson().getPrioritaet() == kontaktpersonPrioritaet) {
        alleKontaktpersonenBenachrichtigt = true;
      }
    }

    if (alleKontaktpersonenBenachrichtigt == false) {
      String positionssenderId = ausnahmesituation.getPositionssender().getPositionssenderId();
      DemenziellErkrankter demenziellErkrankter = getDemeziellErkrankterByPositionssenderId(
          positionssenderId);
      Kontaktperson kontaktperson = getKontaktpersonByPrioritaet(
          demenziellErkrankter, kontaktpersonPrioritaet);

      Nachricht neueNachricht = nachrichtRepository.save(
          new Nachricht(ausnahmesituation.getNachrichtText(), kontaktperson));
      ausnahmesituation.addNachricht(neueNachricht);

      nachrichtRepository.save(neueNachricht);

      log.info("Nachricht an " + kontaktperson.getVorname()
          + " mit Prioritaet " + kontaktperson.getPrioritaet() + " gesendet ...");
    }
  }

  private DemenziellErkrankter getDemeziellErkrankterByPositionssenderId(String positionssenderId) {
    for (DemenziellErkrankter demenziellErkrankter : demenziellErkrankterRepository.findAll()) {
      for (Positionssender positionssender : demenziellErkrankter.getPositionssender()) {
        if (positionssender.getPositionssenderId().equals(positionssenderId)) {
          return demenziellErkrankter;
        }
      }
    }
    return null;
  }

  private Kontaktperson getKontaktpersonByPrioritaet(
      DemenziellErkrankter demenziellErkrankter, int prioritaet) {
    for (Kontaktperson kontaktperson : demenziellErkrankter.getKontaktpersonen()) {
      if (kontaktperson.getPrioritaet() == prioritaet) {
        return kontaktperson;
      }
    }
    return null;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) {
    applicationContext = context;
  }
}
