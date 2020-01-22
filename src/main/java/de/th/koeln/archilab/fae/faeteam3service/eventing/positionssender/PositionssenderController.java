package de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@Tag(name = "Positionssender", description = "Positionssender API")
@RestController
public class PositionssenderController {

  @Autowired
  private PositionssenderRepository positionssenderRepository;

  @GetMapping("/positionssender")
  public Iterable<Positionssender> getAllPositionssender() {
    log.info("Hole alle Positionssender...");
    return positionssenderRepository.findAll();
  }

  @Operation(summary = "Positionssender erstellen",
      description = "",
      tags = {"positionssender"})
  @PostMapping(value = "/positionssender",
      consumes = {"application/json"},
      produces = {"application/json"})
  public Positionssender createPositionssender(
      @Valid @RequestBody Positionssender positionssender) {
    return positionssenderRepository.save(positionssender);
  }

  @DeleteMapping("/positionssender/all")
  public void deleteAllPositionssender() {
    positionssenderRepository.deleteAll();
  }


}
