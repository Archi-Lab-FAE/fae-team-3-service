package de.th.koeln.archilab.fae.faeteam3service.antwort;

import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@Tag(name = "Antwort", description = "Antwort API")
@RestController
public class AntwortController {

    @Autowired
    private AntwortRepository antwortRepository;

    @Autowired
    private NachrichtRepository nachrichtRepository;

    @CrossOrigin(origins = "https://fae.aletutto.de")
    @Operation(summary = "Antwort für eine Nachricht erstellen", description = "", tags = { "Antwort" })
    @PostMapping(value = "/level-2/nachricht/{nachrichtId}/antwort", consumes = {"application/json"})
    public Antwort createAntwort(@PathVariable String nachrichtId, @RequestBody Antwort antwort) {
        log.info("Erstelle Antwort: " + antwort.toString());

        Antwort antw = antwortRepository.save(antwort);
        nachrichtRepository.findById(nachrichtId).ifPresent(it -> {
            it.setAntwort(antw);
        });

        return antwortRepository.save(antw);
    }

}
