package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log
@Tag(name = "Ausnahmesituation", description = "Ausnahmesituation API")
@RestController
public class AusnahmesituationController {

  private final AusnahmesituationMapper ausnahmesituationMapper = Mappers.getMapper(AusnahmesituationMapper.class);
  private AusnahmesituationService ausnahmesituationService;

  @Autowired
  public AusnahmesituationController(AusnahmesituationService ausnahmesituationService) {
    this.ausnahmesituationService = ausnahmesituationService;
  }

  @Operation(summary = "Ausnahmesituation erstellen",
          description = "",
          tags = {"ausnahmesituation"})
  @PostMapping(value = "/ausnahmesituation",
          consumes = {"application/json"},
          produces = {"application/json"})
  public AusnahmesituationDTO createAusnahmesituation(@Valid @RequestBody AusnahmesituationDTO ausnahmesituationDTO) throws InterruptedException {
    Ausnahmesituation ausnahmesituation = ausnahmesituationMapper.toAusnahmesituation(ausnahmesituationDTO);
    ausnahmesituation = ausnahmesituationService.saveAusnahmesituationAndSendMessage(ausnahmesituation);
    return ausnahmesituationMapper.toAusnahmesituationDTO(ausnahmesituation);
  }

  @GetMapping("/ausnahmesituation")
  public List<AusnahmesituationDTO> getAllAusnahmesituationen() {
    return ausnahmesituationMapper.toAusnahmesituationDTOs(ausnahmesituationService.holeAlleAusnahmesituationen());
  }

  @GetMapping("/ausnahmesituation/{ausnahmesituationId}")
  public AusnahmesituationDTO getAllAusnahmesituation(@PathVariable String ausnahmesituationId) {
    return ausnahmesituationMapper.toAusnahmesituationDTO(ausnahmesituationService.holeAusnahmesituation(ausnahmesituationId));
  }


  @Operation(summary = "Löschen einer Ausnahmesituationen",
          description = "",
          tags = {"ausnahmesituation"})
  @DeleteMapping("/ausnahmesituation/{ausnahmesituationId}")
  public void deleteAusnahmesituation(@PathVariable String ausnahmesituationId) {
    ausnahmesituationService.löscheAusnahmesiutation(ausnahmesituationId);
  }

  @DeleteMapping("/ausnahmesituation/all")
  public void deleteAllAusnahmesituation() {
    ausnahmesituationService.löscheAlleAusnahmesituationen();
  }
}
