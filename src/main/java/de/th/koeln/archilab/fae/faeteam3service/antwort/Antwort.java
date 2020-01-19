package de.th.koeln.archilab.fae.faeteam3service.antwort;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Antwort extends AbstractEntity {

    @Setter
    @Enumerated(EnumType.STRING)
    private AntwortTyp antwortTyp;

    @Override
    @JsonProperty("antwortId")
    public String getEntityId() {
        return super.getEntityId();
    }

}
