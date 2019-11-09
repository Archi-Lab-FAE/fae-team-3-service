package de.th.koeln.archilab.fae.faeteam3service.controller;

import de.th.koeln.archilab.fae.faeteam3service.entity.core.Emergency;
import de.th.koeln.archilab.fae.faeteam3service.entity.message.Message;
import de.th.koeln.archilab.fae.faeteam3service.repository.EmergencyRepository;
import de.th.koeln.archilab.fae.faeteam3service.repository.MessageRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
        log.info("Trying to create emergency: " + emergency.toString());

        //Not really nice. There should be a better solution
        UUID newEmergency = emergencyRepository.save(emergency).getId();
        return emergencyRepository.findById(newEmergency).map(emergency1 -> {
            Message newMessage = new Message();
            newMessage.setEmergency(emergency1);
            messageRepository.save(newMessage);
            return emergency1;
        }).orElseThrow(() -> new ResourceNotFoundException("Something went wrong!"));
    }

    @GetMapping("/emergency")
    public Iterable<Emergency> getAllEmergencies() {
        log.info("Getting all Emergencies...");
        return emergencyRepository.findAll();
    }

    @DeleteMapping("/emergency/{id}")
    public void deleteEmergency(@RequestParam UUID id) {
        log.info("Deleting Emergency with ID: " + id);
        emergencyRepository.deleteById(id);
    }
}
