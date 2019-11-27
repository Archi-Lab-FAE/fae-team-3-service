package de.th.koeln.archilab.fae.faeteam3service.controller;

import de.th.koeln.archilab.fae.faeteam3service.entity.ausnahmesituation.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.entity.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.repository.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.repository.NachrichtRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Log
@RestController
public class NachrichtController {

    @Autowired
    private NachrichtRepository nachrichtRepository;

    @Autowired
    private AusnahmesituationRepository ausnahmesituationRepository;

    // TODO: Nur für Testzwecke, löschen wenn nich mehr benötigt
    @GetMapping("/level-2/ausnahmesituation/nachrichten")
    public Iterable<Nachricht> getAllNachrichten() {
        return nachrichtRepository.findAll();
    }

    @GetMapping("/level-2/ausnahmesituation/{ausnahmesituationId}/nachrichten")
    public Iterable<Nachricht> getAllNachrichtenByAusnahmesituation(@PathVariable String ausnahmesituationId) {
        log.info("Suche Nachrichten mit der Ausnahmesituation ID: " + ausnahmesituationId);
        return nachrichtRepository.findByAusnahmesituation_aunamesituationId(ausnahmesituationId);
    }

    @PostMapping("/level-2/ausnahmesituation/{ausnahmesituationId}/nachrichten")
    public Nachricht addNachrichtToAusnahmesituation(@PathVariable String ausnahmesituationId, @Valid @RequestBody Nachricht nachricht) {
        Optional<Ausnahmesituation> ausnahmesituation = ausnahmesituationRepository.findById(ausnahmesituationId);

        if(ausnahmesituation.isPresent()) {
            ausnahmesituation.get().addNachricht(nachricht);
            nachricht.setAusnahmesituation(ausnahmesituation.get());

            ausnahmesituationRepository.save(ausnahmesituation.get());
            nachrichtRepository.save(nachricht);
        }

        log.info("Füge der Ausnahme mit der ID: " + ausnahmesituationId + " eine Nachricht hinzu.");

        return nachricht;
    }
}
