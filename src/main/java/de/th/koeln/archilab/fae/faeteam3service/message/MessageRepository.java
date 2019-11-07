package de.th.koeln.archilab.fae.faeteam3service.message;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MessageRepository extends CrudRepository<Message, UUID> {

}
