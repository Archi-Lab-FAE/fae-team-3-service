package de.th.koeln.archilab.fae.faeteam3service.eventing.ausnahmesituation;

import java.io.IOException;

import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log
@Service
public class AntwortConsumer {

  // Für das Testen von Kafka
  @KafkaListener(topics = "antwort", groupId = "fae-team-3-service")
  public void consume(String input) throws IOException {
    log.info(String.format("#### -> Consumed message -> %s", input));

  }
}