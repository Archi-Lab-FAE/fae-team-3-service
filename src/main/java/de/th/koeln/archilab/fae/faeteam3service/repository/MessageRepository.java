package de.th.koeln.archilab.fae.faeteam3service.repository;

import de.th.koeln.archilab.fae.faeteam3service.entity.message.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MessageRepository extends CrudRepository<Message, UUID> {

    Iterable<Message> findByEmergency_Id(UUID emergency_id);
}
