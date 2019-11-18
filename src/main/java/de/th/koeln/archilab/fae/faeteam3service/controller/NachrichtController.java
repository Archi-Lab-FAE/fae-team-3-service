package de.th.koeln.archilab.fae.faeteam3service.controller;

import de.th.koeln.archilab.fae.faeteam3service.entity.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.repository.NachrichtRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class NachrichtController {

    @Autowired
    private NachrichtRepository nachrichtRepository;

    @GetMapping("/level-2/ausnahmesituation/{ausnahmesituationId}/nachrichten")
    public Iterable<Nachricht> getAllNachrichtenByAusnahmesituation(@PathVariable String ausnahmesituationId) {
        log.info("Suche Nachrichten mit der Ausnahmesituation ID: " + ausnahmesituationId);
        return nachrichtRepository.findByAusnahmesituation_aunamesituationId(ausnahmesituationId);
    }

    @GetMapping("/level-2/ausnahmesituation/nachrichten")
    public Iterable<Nachricht> getAllNachrichten() {
        return nachrichtRepository.findAll();
    }
}
