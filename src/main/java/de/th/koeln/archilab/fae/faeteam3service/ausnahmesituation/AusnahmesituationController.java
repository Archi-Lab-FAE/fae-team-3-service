package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.core.DtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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

  private final DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);
  private AusnahmesituationService ausnahmesituationService;

  @Autowired
  public AusnahmesituationController(AusnahmesituationService ausnahmesituationService) {
    this.ausnahmesituationService = ausnahmesituationService;
  }

  @Operation(summary = "Ausnahmesituation erstellen",
      tags = {"Ausnahmesituation"},
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Test",
          required = true,
          content = @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(hidden = true),
              examples = {
                  @ExampleObject(name = "Ohne Position",
                      value = "{\n \"positionssenderId\" : "
                          + "\"0a1ad64b-48ff-44e3-8260-9b584e875ac1\", \n"
                          + "\"text\" : \"Hilde ben\\u00F6tigt dringend deine Hilfe!\"\n}",
                      description = "Um eine Ausnahmesituation erstellen zu können, wird die ID "
                          + "eines Positionssenders benötigt, sowie der Text den die zu"
                          + " übermittelnde Nachricht enthalten soll."),
                  @ExampleObject(name = "Mit Position",
                      value = "{\n\"positionssenderId\" : \"2a300cae-2b24-4e3a-b92b-cc61d905d831\","
                          + "\n\"text\" : \"Hilde ben\\u00F6tigt dringend deine Hilfe!\","
                          + "\n\"position\" : "
                          + "{\n\"laengengrad\" : 51.023630,\n\"breitengrad\" : 7.563658\n}\n}",
                      description = "Um eine Ausnahmesituation mit Position erstellen zu können,"
                          + " wird die ID eines Positionssenders, der Nachrichten Text, "
                          + "sowie der Breiten- und Längengrad der Position benötigt.")
              }
          )
      )
  )
  @PostMapping(value = "/ausnahmesituation",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public AusnahmesituationDto createAusnahmesituation(
      @Valid @RequestBody AusnahmesituationDto ausnahmesituationDto) {

    Ausnahmesituation ausnahmesituation = dtoMapper
        .convertToAusnahmesituationEntity(ausnahmesituationDto);

    ausnahmesituation = ausnahmesituationService
        .saveAusnahmesituationAndSendMessage(ausnahmesituation);

    return dtoMapper.convertToAusnahmesituationDto(ausnahmesituation);
  }

  @Operation(summary = "Alle Ausnahmesituationen als Liste",
      tags = {"Ausnahmesituation"})
  @GetMapping("/ausnahmesituation")
  public List<AusnahmesituationDto> getAllAusnahmesituationen() {

    return dtoMapper
        .convertToAusnahmesituationDtoList(ausnahmesituationService.getAllAusnahmesituationen());
  }

  @Operation(hidden = true)
  @GetMapping("/ausnahmesituation/{ausnahmesituationId}")
  public AusnahmesituationDto getAllAusnahmesituation(@PathVariable String ausnahmesituationId) {
    Ausnahmesituation ausnahmesituation = ausnahmesituationService
        .getAusnahmesituation(ausnahmesituationId);

    return dtoMapper
        .convertToAusnahmesituationDto(ausnahmesituation);
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
