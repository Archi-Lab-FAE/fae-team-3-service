package de.th.koeln.archilab.fae.faeteam3service.repository;

import de.th.koeln.archilab.fae.faeteam3service.entity.core.Emergency;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EmergencyRepository extends CrudRepository<Emergency, UUID> {
}
