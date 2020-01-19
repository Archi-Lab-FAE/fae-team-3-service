package de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log
@Service
public class KontaktpersonConsumer {

  @Autowired
  private KontaktpersonRepository kontaktpersonRepository;

  @KafkaListener(topics = "kontaktperson", groupId = "fae-team-3-service")
  public void consume(String input) throws IOException {
    log.info(String.format("#### -> Consumed message -> %s", input));

    ObjectMapper mapper = new ObjectMapper();
    Kontaktperson kontaktperson = mapper.readValue(input, Kontaktperson.class);
    kontaktpersonRepository.save(kontaktperson);
  }
}