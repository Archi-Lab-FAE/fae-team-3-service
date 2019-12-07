package de.th.koeln.archilab.fae.faeteam3service.kafka;

import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log
@Service
public class Consumer {

    @KafkaListener(topics = "dementiellErkrankter", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info(String.format("#### -> Consumed message -> %s", message));
    }
}
