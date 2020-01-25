package de.th.koeln.archilab.fae.faeteam3service.antwort;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.service.NachrichtenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AusnahmesituationRepository ausnahmesituationRepository;
    @Autowired
    private NachrichtenService nachrichtenService;

    @Operation(summary = "Antwort für eine Nachricht erstellen", description = "", tags = {"Antwort"})
    @PostMapping(value = "/nachricht/{nachrichtId}/antwort", consumes = {"application/json"})
    public Antwort createAntwort(@PathVariable String nachrichtId, @RequestBody Antwort antwort) {
        log.info("Erstelle Antwort: " + antwort.toString());

        Antwort antw = antwortRepository.save(antwort);
        nachrichtRepository.findById(nachrichtId).ifPresent(nachricht -> {
            nachricht.setAntwort(antw);

            if (antwort.getAntwortTyp() == AntwortTyp.KANN_HELFEN) {
                nachricht.getAusnahmesituation().setIstAbgeschlossen(true);

                ausnahmesituationRepository.findById(nachricht.getAusnahmesituation().getEntityId())
                        .ifPresent(ausnahmesituation -> ausnahmesituation.setIstAbgeschlossen(true));

                log.info("Ausnahmesituation mit der ID "
                        + nachricht.getAusnahmesituation().getEntityId()
                        + " abgeschlossen...");
            }

            if (antwort.getAntwortTyp() == AntwortTyp.KANN_NICHT_HELFEN) {
                ausnahmesituationRepository.findById(nachricht.getAusnahmesituation().getEntityId())
                        .ifPresent(ausnahmesituation -> nachrichtenService.sendeNachrichtToKontaktperson(ausnahmesituation));
            }
        });

        return antwortRepository.save(antw);
    }

}
