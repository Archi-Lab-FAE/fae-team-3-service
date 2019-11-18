package de.th.koeln.archilab.fae.faeteam3service.controller;

import de.th.koeln.archilab.fae.faeteam3service.entity.answer.Answer;
import de.th.koeln.archilab.fae.faeteam3service.repository.AnswerRepository;
import de.th.koeln.archilab.fae.faeteam3service.repository.MessageRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Log
@RestController
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/messages/{message_id}/answer")
    public Answer createAnswer(@PathVariable UUID message_id, @RequestBody Answer answer) {
        log.info("Trying to add answer: " + answer.toString());

        Answer answ = answerRepository.save(answer);
        messageRepository.findById(message_id).ifPresent( it -> {
            it.setAnswer(answ);
        });

        return answerRepository.save(answ);
    }

}
