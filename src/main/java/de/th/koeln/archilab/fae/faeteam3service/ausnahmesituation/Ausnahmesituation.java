package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@ToString(callSuper = true)
public class Ausnahmesituation extends AbstractEntity {

    @Id
    @Getter
    private String aunamesituationId;

    @Valid
    @Setter
    @JsonUnwrapped
    private DementiellErkrankter dementiellErkrankter;

    @OneToMany(mappedBy = "ausnahmesituation",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Nachricht> nachrichten = new HashSet<Nachricht>();

    public void addNachricht(Nachricht nachricht) {
        if(!nachrichten.contains(nachricht)){
            this.nachrichten.add(nachricht);
            nachricht.setAusnahmesituation(this);
        }
    }

    public Ausnahmesituation() {
        this.aunamesituationId = UUID.randomUUID().toString();
    }
}
