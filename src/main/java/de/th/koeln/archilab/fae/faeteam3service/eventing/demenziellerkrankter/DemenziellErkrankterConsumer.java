package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.th.koeln.archilab.fae.faeteam3service.core.DtoMapper;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter.persistance.DemenziellErkrankterRepository;

import java.io.IOException;

import lombok.extern.java.Log;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Log
@Service
public class DemenziellErkrankterConsumer {

  @Autowired
  private DemenziellErkrankterRepository demenziellErkrankterRepository;

  @Autowired
  ObjectMapper objectMapper;

  private final DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);

  @KafkaListener(topics = "demenziellErkrankte", groupId = "fae-team-3-service")
  public void consume(String input) throws IOException {
    log.info(String.format("#### -> Consumed message -> %s", input));

    input = input.substring(1, input.length() - 1)
        .replace("\\n", "")
        .replace("\\", "");

    DemenziellErkrankterEvent demenziellErkrankterEvent = objectMapper
        .readValue(input, DemenziellErkrankterEvent.class);

    DemenziellErkrankterDto demenziellErkrankterDto = demenziellErkrankterEvent.getPayload();

    DemenziellErkrankter demenziellErkrankter = dtoMapper
        .convertToDemenziellErkrankterEntity(demenziellErkrankterDto);

    demenziellErkrankterRepository.save(demenziellErkrankter);
  }
}