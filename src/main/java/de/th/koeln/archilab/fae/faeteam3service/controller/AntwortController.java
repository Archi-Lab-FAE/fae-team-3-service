package de.th.koeln.archilab.fae.faeteam3service.controller;

import de.th.koeln.archilab.fae.faeteam3service.entity.antwort.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.repository.AntwortRepository;
import de.th.koeln.archilab.fae.faeteam3service.repository.NachrichtRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Log
@RestController
public class AntwortController {

    @Autowired
    private AntwortRepository antwortRepository;

    @Autowired
    private NachrichtRepository nachrichtRepository;

    @PostMapping("/level-2/nachricht/{nachrichtId}/antwort")
    public Antwort createAntwort(@PathVariable String nachrichtId, @RequestBody Antwort antwort) {
        log.info("Erstelle Antwort: " + antwort.toString());

        Antwort antw = antwortRepository.save(antwort);
        nachrichtRepository.findById(nachrichtId).ifPresent(it -> {
            it.setAntwort(antw);
        });

        return antwortRepository.save(antw);
    }

}
