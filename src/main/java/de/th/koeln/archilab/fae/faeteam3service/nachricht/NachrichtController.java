package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.AusnahmesituationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;
import javax.validation.Valid;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Log
@Tag(name = "Nachricht", description = "Nachricht API")
@RestController
public class NachrichtController {

  @Autowired
  private NachrichtRepository nachrichtRepository;

  @Autowired
  private AusnahmesituationRepository ausnahmesituationRepository;

  @Operation(summary = "Alle Nachrichten einer Ausnahmesituationen abfragen",
      description = "",
      tags = {"Nachricht"})
  @GetMapping(value = "/ausnahmesituation/{ausnahmesituationId}/nachrichten",
      produces = {"application/json"})
  public Iterable<Nachricht> getAllNachrichtenByAusnahmesituation(
      @PathVariable String ausnahmesituationId) {
    log.info("Suche Nachrichten mit der Ausnahmesituation ID: " + ausnahmesituationId);
    return nachrichtRepository.findByAusnahmesituation_entityId(ausnahmesituationId);
  }

  @Operation(summary = "Nachricht für eine Ausnahmesituation erstellen",
      description = "",
      tags = {"Nachricht"})
  @PostMapping(value = "/ausnahmesituation/{ausnahmesituationId}/nachrichten",
      consumes = {"application/json"},
      produces = {"application/json"})
  public Nachricht addNachrichtToAusnahmesituation(
      @PathVariable String ausnahmesituationId,
      @Valid @RequestBody Nachricht nachricht) {
    Optional<Ausnahmesituation> ausnahmesituation = ausnahmesituationRepository
        .findById(ausnahmesituationId);

    if (ausnahmesituation.isPresent()) {
      ausnahmesituation.get().addNachricht(nachricht);
      nachricht.setAusnahmesituation(ausnahmesituation.get());

      ausnahmesituationRepository.save(ausnahmesituation.get());
      nachrichtRepository.save(nachricht);
    }

    log.info("Füge der Ausnahme mit der ID: " + ausnahmesituationId + " eine Nachricht hinzu.");

    return nachricht;
  }
}
