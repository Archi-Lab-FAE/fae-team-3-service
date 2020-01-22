package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import de.th.koeln.archilab.fae.faeteam3service.nachricht.service.NachrichtenService;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.service.TimeoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Log
@Tag(name = "Ausnahmesituation", description = "Ausnahmesituation API")
@RestController
public class AusnahmesituationController {

  @Autowired
  private AusnahmesituationRepository ausnahmesituationRepository;

  @Autowired
  TimeoutService timeoutService;

  @Autowired
  NachrichtenService nachrichtenService;

  @Operation(summary = "Ausnahmesituation erstellen",
      description = "",
      tags = {"ausnahmesituation"})
  @PostMapping(value = "/ausnahmesituation",
      consumes = {"application/json"},
      produces = {"application/json"})
  public Ausnahmesituation createAusnahmesituation(
      @Valid @RequestBody Ausnahmesituation ausnahmesituation) throws InterruptedException {
    ausnahmesituation = ausnahmesituationRepository.save(ausnahmesituation);

    nachrichtenService.send(ausnahmesituation);
    timeoutService.checkTimeout(ausnahmesituation.getEntityId());

    return ausnahmesituation;
  }

  @GetMapping("/ausnahmesituation")
  public Iterable<Ausnahmesituation> getAllAusnahmesituationen() {
    log.info("Hole alle Ausnahmesituationen...");
    return ausnahmesituationRepository.findAll();
  }

  @Operation(summary = "Löschen einer Ausnahmesituationen",
      description = "",
      tags = {"ausnahmesituation"})
  @DeleteMapping("/ausnahmesituation/{ausnahmesituationId}")
  public void deleteAusnahmesituation(@PathVariable String ausnahmesituationId) {
    log.info("Löschen der Ausnahmesituation mit ID: " + ausnahmesituationId);
    ausnahmesituationRepository.deleteById(ausnahmesituationId);
  }


  @DeleteMapping("/ausnahmesituation/all")
  public void deleteAllAusnahmesituation() {
    ausnahmesituationRepository.deleteAll();
  }


}
