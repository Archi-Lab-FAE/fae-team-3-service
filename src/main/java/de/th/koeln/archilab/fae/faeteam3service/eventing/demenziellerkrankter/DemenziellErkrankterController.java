package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

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
@Tag(name = "DemenziellErkrankter", description = "DemenziellErkrankter API")
@RestController
public class DemenziellErkrankterController {

  @Autowired
  private DemenziellErkrankterRepository demenziellErkrankterRepository;

  @GetMapping("/demenziellerkrankter")
  public Iterable<DemenziellErkrankter> getAllDemenziellErkrankte() {
    log.info("Hole alle Dementiell Erkrankten...");
    return demenziellErkrankterRepository.findAll();
  }

  @Operation(summary = "DemenziellErkrankter erstellen",
      description = "",
      tags = {"demenziellerkrankter"})
  @PostMapping(value = "/demenziellerkrankter",
      consumes = {"application/json"},
      produces = {"application/json"})
  public DemenziellErkrankter createDemenziellErkrankten(
      @Valid @RequestBody DemenziellErkrankter demenziellErkrankter) {
    return demenziellErkrankterRepository.save(demenziellErkrankter);
  }

  @DeleteMapping("/demenziellerkrankter/all")
  public void deleteAllDemenziellerkrankte() {
    demenziellErkrankterRepository.deleteAll();
  }


}
