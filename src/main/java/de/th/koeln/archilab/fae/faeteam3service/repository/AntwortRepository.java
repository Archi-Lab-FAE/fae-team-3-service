package de.th.koeln.archilab.fae.faeteam3service.repository;

import de.th.koeln.archilab.fae.faeteam3service.entity.antwort.Antwort;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AntwortRepository extends CrudRepository<Antwort, String> {
}
