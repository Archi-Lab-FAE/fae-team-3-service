package de.th.koeln.archilab.fae.faeteam3service.nachricht.service;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TimeoutService {

    @Autowired
    private NachrichtenService nachrichtenService;

    @Async
    public void checkTimeout(Ausnahmesituation ausnahmesituation) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Nachricht nachricht : ausnahmesituation.getNachrichten()) {
            if (nachricht.getAntwort() == null) {
                nachrichtenService.sendeNachrichtToKontaktperson(ausnahmesituation);
            }
        }
    }
}
