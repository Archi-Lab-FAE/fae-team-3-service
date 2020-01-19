package de.th.koeln.archilab.fae.faeteam3service.eventing.dementiellErkrankter;

import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.Positionssender;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "demenziellErkrankte")
public class DemenziellErkrankter implements Serializable {

    @Id
    private String id;
    private String name;
    private String vorname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "demenziellErkrankte_id")
    private List<Kontaktperson> kontaktpersonen;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "demenziellErkrankte_id")
    private List<Positionssender> positionssender;


    public DemenziellErkrankter() {
        this.id = UUID.randomUUID().toString();
    }
}