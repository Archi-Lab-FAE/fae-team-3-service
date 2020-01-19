package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtText;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true)
public class Ausnahmesituation extends AbstractEntity {

    @Valid
    @Setter
    @JsonUnwrapped
    private Tracker tracker;

    @Getter
    @JsonUnwrapped
    private NachrichtText nachrichtText;

    @OneToMany(mappedBy = "ausnahmesituation",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Nachricht> nachrichten = new HashSet<Nachricht>();

    NachrichtText getNachrichtText() {
        return nachrichtText;
    }

    public void addNachricht(Nachricht nachricht) {
        if (!nachrichten.contains(nachricht)) {
            this.nachrichten.add(nachricht);
            nachricht.setAusnahmesituation(this);
        }
    }

    @Override
    @JsonProperty("ausnamesituationId")
    public String getEntityId() {
        return super.getEntityId();
    }
}
