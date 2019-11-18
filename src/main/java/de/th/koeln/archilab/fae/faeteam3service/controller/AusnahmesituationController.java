package de.th.koeln.archilab.fae.faeteam3service.controller;

import de.th.koeln.archilab.fae.faeteam3service.entity.ausnahmesituation.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.entity.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.repository.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.repository.NachrichtRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log
@RestController
public class AusnahmesituationController {

    @Autowired
    private AusnahmesituationRepository ausnahmesituationRepository;

    @Autowired
    private NachrichtRepository nachrichtRepository;

    @PostMapping("/level-2/ausnahmesituation")
    public Ausnahmesituation createAusnahmesituation(@Valid @RequestBody Ausnahmesituation ausnahmesituation) {

        ausnahmesituation = ausnahmesituationRepository.save(ausnahmesituation);

        Nachricht neueNachricht = nachrichtRepository.save(new Nachricht());
        ausnahmesituation.addNachricht(neueNachricht);

        nachrichtRepository.save(neueNachricht);

        log.info("Ausnahmesituation erstellt: " + ausnahmesituation.toString());
        return ausnahmesituation;
    }

    @GetMapping("/level-2/ausnahmesituation")
    public Iterable<Ausnahmesituation> getAllAusnahmesituationen() {
        log.info("Hole alle Ausnahmesituationen...");
        return ausnahmesituationRepository.findAll();
    }

    @DeleteMapping("/level-2/Ausnahmesituation/{ausnahmesituationId}")
    public void deleteAusnahmesituation(@PathVariable String ausnahmesituationId) {
        log.info("Löschen der Ausnahmesituation mit ID: " + ausnahmesituationId);
        ausnahmesituationRepository.deleteById(ausnahmesituationId);
    }
}
