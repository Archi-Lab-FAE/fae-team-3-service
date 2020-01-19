package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.antwort.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class Nachricht extends AbstractEntity {

    @JsonUnwrapped
    private NachrichtText nachrichtText;

    @Setter
    @ManyToOne
    @JsonBackReference
    @ToString.Exclude
    private Ausnahmesituation ausnahmesituation;

    @OneToOne(cascade = CascadeType.ALL)
    private Antwort antwort;

    public Nachricht() {
        this.nachrichtText = new NachrichtText();
    }

    public Nachricht(NachrichtText nachrichtenText) {
        this.nachrichtText = nachrichtenText;
    }

    @Override
    @JsonProperty("antwortId")
    public String getEntityId() {
        return super.getEntityId();
    }
}
