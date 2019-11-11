package de.th.koeln.archilab.fae.faeteam3service.controller;

import de.th.koeln.archilab.fae.faeteam3service.entity.message.Message;
import de.th.koeln.archilab.fae.faeteam3service.repository.MessageRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log
@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/emergency/{emergency_id}/messages")
    public Iterable<Message> getAllMessagesByEmergency(@PathVariable UUID emergency_id) {
        log.info("Searching for messages from id: " + emergency_id);
        return messageRepository.findByEmergency_Id(emergency_id);
    }

    @GetMapping("/emergency/messages")
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
