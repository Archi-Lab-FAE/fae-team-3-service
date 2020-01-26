package de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance;

import org.springframework.data.repository.CrudRepository;

public interface NachrichtRepository extends CrudRepository<Nachricht, String> {

  Iterable<Nachricht>  findByAusnahmesituation_entityId(String aunamesituationId);
}
