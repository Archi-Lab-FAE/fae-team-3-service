package de.th.koeln.archilab.fae.faeteam3service.controller;

import de.th.koeln.archilab.fae.faeteam3service.entity.core.Emergency;
import de.th.koeln.archilab.fae.faeteam3service.entity.message.Message;
import de.th.koeln.archilab.fae.faeteam3service.repository.EmergencyRepository;
import de.th.koeln.archilab.fae.faeteam3service.repository.MessageRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Log
@RestController
public class EmergencyController {

    @Autowired
    private EmergencyRepository emergencyRepository;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/emergency")
    public Emergency createEmergency(@Valid @RequestBody Emergency emergency) {

        emergency = emergencyRepository.save(emergency);

        Message msg = messageRepository.save(new Message());
        emergency.addMessage(msg);

        messageRepository.save(msg);

        log.info("Created emergency: " + emergency.toString());
        return emergency;
    }

    @GetMapping("/emergency")
    public Iterable<Emergency> getAllEmergencies() {
        log.info("Getting all Emergencies...");
        return emergencyRepository.findAll();
    }

    @DeleteMapping("/emergency/{id}")
    public void deleteEmergency(@PathVariable UUID id) {
        log.info("Deleting Emergency with ID: " + id);
        emergencyRepository.deleteById(id);
    }
}
