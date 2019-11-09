package de.th.koeln.archilab.fae.faeteam3service.repository;

import de.th.koeln.archilab.fae.faeteam3service.entity.answer.Answer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AnswerRepository extends CrudRepository<Answer, UUID> {
}
