package de.th.koeln.archilab.fae.faeteam3service.repository;

import de.th.koeln.archilab.fae.faeteam3service.entity.ausnahmesituation.Ausnahmesituation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AusnahmesituationRepository extends CrudRepository<Ausnahmesituation, String> {
}
