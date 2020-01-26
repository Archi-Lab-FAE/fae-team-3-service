package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import javax.validation.Valid;

import lombok.extern.java.Log;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

  private AusnahmesituationService ausnahmesituationService;
  private AusnahmesituationMapper ausnahmesituationMapper = Mappers
      .getMapper(AusnahmesituationMapper.class);

  @Autowired
  public AusnahmesituationController(AusnahmesituationService ausnahmesituationService) {
    this.ausnahmesituationService = ausnahmesituationService;
  }

  @Operation(summary = "Ausnahmesituation erstellen",
      tags = {"Ausnahmesituation"})
  @PostMapping(value = "/ausnahmesituation",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public AusnahmesituationDto createAusnahmesituation(
      @Valid @RequestBody AusnahmesituationDto ausnahmesituationDto) {

    Ausnahmesituation ausnahmesituation = ausnahmesituationMapper
        .toAusnahmesituation(ausnahmesituationDto);

    ausnahmesituation = ausnahmesituationService
        .saveAusnahmesituationAndSendMessage(ausnahmesituation);

    return ausnahmesituationMapper.toAusnahmesituationDto(ausnahmesituation);
  }

  @Operation(summary = "Alle Ausnahmesituationen als Liste",
      tags = {"Ausnahmesituation"})
  @GetMapping("/ausnahmesituation")
  public List<AusnahmesituationDto> getAllAusnahmesituationen() {

    return ausnahmesituationMapper
        .toAusnahmesituationDtos(ausnahmesituationService.getAllAusnahmesituationen());
  }

  @Operation(hidden = true)
  @GetMapping("/ausnahmesituation/{ausnahmesituationId}")
  public AusnahmesituationDto getAllAusnahmesituation(@PathVariable String ausnahmesituationId) {

    return ausnahmesituationMapper
        .toAusnahmesituationDto(ausnahmesituationService.getAusnahmesituation(ausnahmesituationId));
  }

  @Operation(summary = "Löschen einer Ausnahmesituationen",
      description = "",
      tags = {"ausnahmesituation"},
      hidden = true)
  @DeleteMapping("/ausnahmesituation/{ausnahmesituationId}")
  public void deleteAusnahmesituation(@PathVariable String ausnahmesituationId) {
    ausnahmesituationService.deleteAusnahmesituation(ausnahmesituationId);
  }

  @Operation(hidden = true)
  @DeleteMapping("/ausnahmesituation/all")
  public void deleteAllAusnahmesituation() {
    ausnahmesituationService.deleteAllAusnahmesituationen();
  }
}
