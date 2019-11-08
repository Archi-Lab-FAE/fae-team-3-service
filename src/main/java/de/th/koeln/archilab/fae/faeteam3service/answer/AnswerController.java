package de.th.koeln.archilab.fae.faeteam3service.answer;

import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public class AnswerController {

    private final AnswerRepository answerRepository;

    public AnswerController(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    @GetMapping("/team3/level-2/answers")
    public Iterable<Answer> answers(){
        return this.answerRepository.findAll();
    }

    @PostMapping("/team3/level-2/answers")
    public Answer newAnswer(@RequestBody Answer newAnswer){
        return this.answerRepository.save(newAnswer);
    }

    @DeleteMapping("/team3/level-2/answers/{id}")
    public void deleteAnswer(@PathVariable UUID id){
        this.answerRepository.deleteById(id);
    }
}
