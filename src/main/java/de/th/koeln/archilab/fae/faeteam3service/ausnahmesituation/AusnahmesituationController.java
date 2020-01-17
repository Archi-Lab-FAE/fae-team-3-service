package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.services.EmailService;
import de.th.koeln.archilab.fae.faeteam3service.externes.ausnahmesituation.Producer;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log
@Tag(name = "ausnahmesituation", description = "Ausnahmesituation API")
@RestController
public class AusnahmesituationController {

    @Autowired
    private AusnahmesituationRepository ausnahmesituationRepository;

    @Autowired
    private NachrichtRepository nachrichtRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Producer producer;

    @Operation(summary = "Ausnahmesituation erstellen", description = "", tags = { "ausnahmesituation" })
    @PostMapping(value = "/ausnahmesituation", consumes = {"application/json"}, produces = {"application/json"})
    public Ausnahmesituation createAusnahmesituation(@Valid @RequestBody Ausnahmesituation ausnahmesituation) throws JsonProcessingException {

        ausnahmesituation = ausnahmesituationRepository.save(ausnahmesituation);

        Nachricht neueNachricht = nachrichtRepository.save(new Nachricht(ausnahmesituation.getNachrichtText()));
        ausnahmesituation.addNachricht(neueNachricht);

        nachrichtRepository.save(neueNachricht);
        log.info("Ausnahmesituation erstellt: " + ausnahmesituation.toString());

        emailService.sendMessage("t.alessandro@web.de", neueNachricht);

        producer.publishErstellt(ausnahmesituation);
        return ausnahmesituation;
    }

    @GetMapping("/ausnahmesituation")
    public Iterable<Ausnahmesituation> getAllAusnahmesituationen() {
        log.info("Hole alle Ausnahmesituationen...");
        return ausnahmesituationRepository.findAll();
    }

    @Operation(summary = "Löschen einer Ausnahmesituationen", description = "", tags = { "ausnahmesituation" })
    @DeleteMapping("/ausnahmesituation/{ausnahmesituationId}")
    public void deleteAusnahmesituation(@PathVariable String ausnahmesituationId) {
        log.info("Löschen der Ausnahmesituation mit ID: " + ausnahmesituationId);
        ausnahmesituationRepository.deleteById(ausnahmesituationId);
    }
}
