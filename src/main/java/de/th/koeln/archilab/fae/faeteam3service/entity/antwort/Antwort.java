package de.th.koeln.archilab.fae.faeteam3service.entity.antwort;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@ToString(callSuper = true)
@AllArgsConstructor
public class Antwort extends AbstractEntity {

    @Id
    @Getter
    private String antwortId;

    @Setter
    @JsonUnwrapped
    private AntwortTyp antwortTyp;

    public Antwort() {
        this.antwortId = UUID.randomUUID().toString();
        this.antwortTyp = new AntwortTyp();
    }

}
