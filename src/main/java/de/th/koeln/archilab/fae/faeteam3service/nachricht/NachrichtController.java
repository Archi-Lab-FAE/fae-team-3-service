package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.service.NachrichtenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

import lombok.extern.java.Log;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@Tag(name = "Nachricht", description = "Nachricht API")
@RestController
public class NachrichtController {

  @Autowired
  NachrichtenService nachrichtenService;

  NachrichtMapper nachrichtMapper = Mappers.getMapper(NachrichtMapper.class);

  @Operation(summary = "Nachricht für eine Ausnahmesituation erstellen",
      description = "",
      tags = {"Nachricht"})
  @PostMapping(value = "/ausnahmesituation/{ausnahmesituationId}/nachrichten",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public NachrichtDto addNachrichtToAusnahmesituation(
      @PathVariable String ausnahmesituationId,
      @Valid @RequestBody NachrichtDto nachrichtDto) {
    Nachricht nachricht = nachrichtMapper.convertToEntity(nachrichtDto);
    nachricht = nachrichtenService.addNachrichtToAusnahmesituation(ausnahmesituationId, nachricht);
    return nachrichtMapper.convertToDto(nachricht);
  }
}
