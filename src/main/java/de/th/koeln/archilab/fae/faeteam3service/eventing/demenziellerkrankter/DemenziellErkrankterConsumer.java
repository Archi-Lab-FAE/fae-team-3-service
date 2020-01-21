package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Log
@Service
public class DemenziellErkrankterConsumer {

  @Autowired
  private DemenziellErkrankterRepository demenziellErkrankterRepository;

  @KafkaListener(topics = "demenziellErkrankter", groupId = "fae-team-3-service")
  public void consume(String input) throws IOException {
    log.info(String.format("#### -> Consumed message -> %s", input));

    ObjectMapper mapper = new ObjectMapper();
    DemenziellErkrankter demenziellErkrankter = mapper.readValue(input, DemenziellErkrankter.class);
    demenziellErkrankterRepository.save(demenziellErkrankter);
  }
}