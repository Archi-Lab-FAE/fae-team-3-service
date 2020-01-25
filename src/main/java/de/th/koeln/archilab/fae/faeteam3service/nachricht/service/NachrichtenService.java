package de.th.koeln.archilab.fae.faeteam3service.nachricht.service;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.DemenziellErkrankterRepository;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Log
@Service
public class NachrichtenService {

    @Autowired
    DemenziellErkrankterRepository demenziellErkrankterRepository;
    @Autowired
    NachrichtRepository nachrichtRepository;
    @Autowired
    TimeoutService timeoutService;

    public void sendeNachrichtToKontaktperson(Ausnahmesituation ausnahmesituation) {
        DemenziellErkrankter demenziellErkrankter = demenziellErkrankterRepository.findDemenziellErkrankterByPositionssenderContains(ausnahmesituation.getPositionssender());
        List<Kontaktperson> kontaktpersonen = new ArrayList<>(demenziellErkrankter.getKontaktpersonen());
        Set<Nachricht> nachrichten = ausnahmesituation.getNachrichten();

        for (Nachricht nachricht : nachrichten) {
            if (kontaktpersonen.contains(nachricht.getKontaktperson())) {
                kontaktpersonen.remove(nachricht.getKontaktperson());
            }
        }

        if (kontaktpersonen.isEmpty()) {
            log.info("Es wurden bereits alle Kontaktpersonen benachrichtigt...");
        } else {
            Nachricht neueNachricht = nachrichtRepository.save(
                    new Nachricht(ausnahmesituation.getNachrichtText(), kontaktpersonen.get(0)));
            ausnahmesituation.addNachricht(neueNachricht);
            nachrichtRepository.save(neueNachricht);

            timeoutService.checkTimeout(ausnahmesituation);

            log.info("Nachricht an " + kontaktpersonen.get(0).getVorname()
                    + " mit Prioritaet " + kontaktpersonen.get(0).getPrioritaet() + " gesendet ...");
        }
    }
}

