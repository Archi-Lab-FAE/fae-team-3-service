package de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson;

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
@Tag(name = "Kontaktperson", description = "Kontaktperson API")
@RestController
public class KontaktpersonController {

  @Autowired
  private KontaktpersonRepository kontaktpersonRepository;

  @GetMapping("/kontaktperson")
  public Iterable<Kontaktperson> getAllKontaktpersonen() {
    log.info("Hole alle Kontaktpersonen...");
    return kontaktpersonRepository.findAll();
  }

  @Operation(summary = "Kontaktperson erstellen",
      description = "",
      tags = {"kontaktperson"})
  @PostMapping(value = "/kontaktperson",
      consumes = {"application/json"},
      produces = {"application/json"})
  public Kontaktperson createKontaktperson(
      @Valid @RequestBody Kontaktperson kontaktperson) {
    return kontaktpersonRepository.save(kontaktperson);
  }

  @DeleteMapping("/kontaktperson/all")
  public void deleteAllKontaktpersonen() {
    kontaktpersonRepository.deleteAll();
  }

}
