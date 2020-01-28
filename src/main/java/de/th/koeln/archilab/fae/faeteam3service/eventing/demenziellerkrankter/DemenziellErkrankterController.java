package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankterRepository;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Log
@Tag(name = "DemenziellErkrankter", description = "DemenziellErkrankter API")
@RestController
public class DemenziellErkrankterController {

  @Autowired
  private DemenziellErkrankterRepository demenziellErkrankterRepository;

  @Operation(hidden = true)
  @GetMapping("/demenziellerkrankter")
  public Iterable<DemenziellErkrankter> getAllDemenziellErkrankte() {
    log.info("Hole alle Dementiell Erkrankten...");
    return demenziellErkrankterRepository.findAll();
  }

  @Operation(summary = "DemenziellErkrankter erstellen",
      description = "",
      tags = {"demenziellerkrankter"},
      hidden = true)
  @PostMapping(value = "/demenziellerkrankter",
      consumes = {"application/json"},
      produces = {"application/json"})
  public DemenziellErkrankter createDemenziellErkrankten() {

    DemenziellErkrankter demenziellErkrankter = new DemenziellErkrankter();
    demenziellErkrankter.setId("0a1ad64b-48ff-44e3-8260-9b584e875ac1");
    demenziellErkrankter.setName("Hilde");
    demenziellErkrankter.setVorname("Hilde");
    demenziellErkrankter.setZustimmung(true);

    Kontaktperson fritz = new Kontaktperson();
    fritz.setId("3e3d4fbd-1e38-46b2-8987-82dbcc1de23c");
    fritz.setName("Fritz");
    fritz.setVorname("Fritz");
    fritz.setTelefonnummer("12645981");
    fritz.setAktiv(true);

    Kontaktperson maria = new Kontaktperson();
    maria.setId("e037e7a4-41d5-4ec2-840e-99f5f8d88a2c");
    maria.setName("Maria");
    maria.setVorname("Maria");
    maria.setTelefonnummer("465165161");
    maria.setAktiv(true);

    Kontaktperson altersheim = new Kontaktperson();
    altersheim.setId("0704799f-8752-4b1b-81e5-56f166c539bd");
    altersheim.setName("Altersheim");
    altersheim.setVorname("Altersheim");
    altersheim.setTelefonnummer("5484655");
    altersheim.setAktiv(true);

    List<Kontaktperson> kontaktpersonen = new ArrayList<>();
    kontaktpersonen.add(fritz);
    kontaktpersonen.add(maria);
    kontaktpersonen.add(altersheim);
    demenziellErkrankter.setKontaktpersonen(kontaktpersonen);

    Positionssender positionssender = new Positionssender();
    positionssender.setPositionssenderId("0a1ad64b-48ff-44e3-8260-9b584e875ac1");
    List<Positionssender> positionssenderList = new ArrayList<>();
    positionssenderList.add(positionssender);
    demenziellErkrankter.setPositionssender(positionssenderList);

    demenziellErkrankter = demenziellErkrankterRepository.save(demenziellErkrankter);

    return demenziellErkrankter;
  }

  @Operation(hidden = true)
  @DeleteMapping("/demenziellerkrankter/all")
  public void deleteAllDemenziellerkrankte() {
    demenziellErkrankterRepository.deleteAll();
  }

}
