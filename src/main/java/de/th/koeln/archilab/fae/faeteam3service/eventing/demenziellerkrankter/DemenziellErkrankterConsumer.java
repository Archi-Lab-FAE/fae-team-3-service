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

  private DemenziellErkrankterRepository demenziellErkrankterRepository;
  private ObjectMapper objectMapper;

  @Autowired
  public DemenziellErkrankterConsumer(DemenziellErkrankterRepository demenziellErkrankterRepository,
                                      ObjectMapper objectMapper) {
    this.demenziellErkrankterRepository = demenziellErkrankterRepository;
    this.objectMapper = objectMapper;
  }

  private final DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);

  private enum EventType { CREATED, UPDATED, DELETED }

  @KafkaListener(topics = "demenziellErkrankte", groupId = "fae-team-3-service")
  public void consume(String input) throws IOException {
    log.info(String.format("#### -> Consumed message -> %s", input));

    DemenziellErkrankterEvent demenziellErkrankterEvent = objectMapper
        .readValue(input, DemenziellErkrankterEvent.class);

    String eventType = demenziellErkrankterEvent.getType().toUpperCase();

    if (eventType.equals(EventType.CREATED.toString())
        || eventType.equals(EventType.UPDATED.toString())) {
      DemenziellErkrankterDto demenziellErkrankterDto = demenziellErkrankterEvent.getPayload();

      DemenziellErkrankter demenziellErkrankter = dtoMapper
          .convertToDemenziellErkrankterEntity(demenziellErkrankterDto);

      demenziellErkrankterRepository.save(demenziellErkrankter);
    }

    if (eventType.equals(EventType.DELETED.toString())) {
      demenziellErkrankterRepository.deleteById(demenziellErkrankterEvent.getKey());
    }
  }
}