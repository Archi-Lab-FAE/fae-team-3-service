package de.th.koeln.archilab.fae.faeteam3service.antwort;

import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.core.DtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@Tag(name = "Antwort", description = "Antwort API")
@RestController
public class AntwortController {

  private final DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);
  private AntwortService antwortService;

  @Autowired
  public AntwortController(AntwortService antwortService) {
    this.antwortService = antwortService;
  }

  @Operation(summary = "Antwort für eine Nachricht erstellen", description = "", tags = {"Antwort"})
  @PostMapping(value = "/nachricht/{nachrichtId}/antwort",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public AntwortDto createAntwort(@PathVariable String nachrichtId,
                                  @RequestBody AntwortDto antwortDto) {
    Antwort antwort = dtoMapper.convertToAntwortEntity(antwortDto);
    antwort = antwortService.saveAntwortAndCheckAntwortTyp(nachrichtId, antwort);

    if (antwort == null) {
      throw new AntwortNotAllowedException();
    }

    return dtoMapper.convertToAntwortDto(antwort);
  }
}
