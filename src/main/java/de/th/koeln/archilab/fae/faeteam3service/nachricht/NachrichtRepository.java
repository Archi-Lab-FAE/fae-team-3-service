package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import org.springframework.data.repository.CrudRepository;

public interface NachrichtRepository extends CrudRepository<Nachricht, String> {

    Iterable<Nachricht> findByAusnahmesituation_aunamesituationId(String aunamesituationId);
}
