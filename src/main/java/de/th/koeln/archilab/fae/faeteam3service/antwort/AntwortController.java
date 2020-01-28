package de.th.koeln.archilab.fae.faeteam3service.antwort;

import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@Tag(name = "Antwort", description = "Antwort API")
@RestController
public class AntwortController {

  private final AntwortMapper antwortMapper = Mappers.getMapper(AntwortMapper.class);

  @Autowired
  AntwortService antwortService;

  @Operation(summary = "Antwort für eine Nachricht erstellen", description = "", tags = {"Antwort"})
  @PostMapping(value = "/nachricht/{nachrichtId}/antwort",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public AntwortDto createAntwort(@PathVariable String nachrichtId,
                                  @RequestBody AntwortDto antwortDto) {
    Antwort antwort = antwortMapper.convertToEntity(antwortDto);
    antwort = antwortService.saveAntwortAndCheckAntwortTyp(nachrichtId, antwort);

    if(antwort == null){
      throw new AntwortNotAllowedException();
    }

    return antwortMapper.convertToDto(antwort);
  }
}
