package de.th.koeln.archilab.fae.faeteam3service.eventing.ausnahmesituation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.Ausnahmesituation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log
@Service
public class Producer {

    private static final String AUSNAHMESITUATION_ERSTELLT = "ausnahmesituationErstellt";
    private static final String AUSNAHMESITUATION_ABGESCHLOSSEN = "ausnahmesituationAbgeschlossen";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishErstellt(Ausnahmesituation ausnahmesituation) throws JsonProcessingException {
        log.info(String.format("#### -> Producing message -> %s", ausnahmesituation));

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(ausnahmesituation);
        this.kafkaTemplate.send(AUSNAHMESITUATION_ERSTELLT, jsonString);
    }

    public void publishAbgeschlossen(Ausnahmesituation ausnahmesituation) throws JsonProcessingException {
        log.info(String.format("#### -> Producing message -> %s", ausnahmesituation));

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(ausnahmesituation);
        this.kafkaTemplate.send(AUSNAHMESITUATION_ABGESCHLOSSEN, jsonString);
    }
}