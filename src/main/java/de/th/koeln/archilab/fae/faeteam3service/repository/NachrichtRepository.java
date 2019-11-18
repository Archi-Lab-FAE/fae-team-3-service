package de.th.koeln.archilab.fae.faeteam3service.repository;

import de.th.koeln.archilab.fae.faeteam3service.entity.nachricht.Nachricht;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NachrichtRepository extends CrudRepository<Nachricht, String> {

    Iterable<Nachricht> findByAusnahmesituation_aunamesituationId(String aunamesituationId);
}
