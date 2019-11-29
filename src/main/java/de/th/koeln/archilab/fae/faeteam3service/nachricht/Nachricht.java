package de.th.koeln.archilab.fae.faeteam3service.nachricht;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.antwort.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.Ausnahmesituation;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class Nachricht extends AbstractEntity {

    @Id
    @Getter
    private String nachrichtId;

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
        this.nachrichtId = UUID.randomUUID().toString();
    }
}
