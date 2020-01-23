package de.th.koeln.archilab.fae.faeteam3service.eventing.demenziellerkrankter;

import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.Positionssender;
import org.springframework.data.repository.CrudRepository;

public interface DemenziellErkrankterRepository extends CrudRepository<DemenziellErkrankter, String> {
    DemenziellErkrankter findDemenziellErkrankterByPositionssenderContains(Positionssender positionssender);
}