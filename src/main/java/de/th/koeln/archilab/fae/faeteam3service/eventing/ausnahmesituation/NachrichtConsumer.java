package de.th.koeln.archilab.fae.faeteam3service.eventing.ausnahmesituation;

import java.io.IOException;

import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log
@Service
public class NachrichtConsumer {

  // Für das Testen von Kafka
  @KafkaListener(topics = "nachricht", groupId = "fae-team-3-service", autoStartup = "${enabled}")
  public void consume(String input) throws IOException {
    log.info(String.format("#### -> Consumed message -> %s", input));

  }
}