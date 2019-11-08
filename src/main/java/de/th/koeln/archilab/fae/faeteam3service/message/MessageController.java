package de.th.koeln.archilab.fae.faeteam3service.message;

import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @GetMapping("/team3/level-2/messages")
    public Iterable<Message> messages(){
        return this.messageRepository.findAll();
    }

    @PostMapping("/team3/level-2/messages")
    public Message newMessage(@RequestBody Message newMessage){
        return this.messageRepository.save(newMessage);
    }

    @DeleteMapping("/team3/level-2/messages/{id}")
    public void deleteMessage(@PathVariable UUID id) {
        this.messageRepository.deleteById(id);
    }
}
