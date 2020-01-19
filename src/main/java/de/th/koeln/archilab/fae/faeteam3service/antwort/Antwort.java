package de.th.koeln.archilab.fae.faeteam3service.antwort;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@ToString(callSuper = true)
@AllArgsConstructor
public class Antwort extends AbstractEntity {

    @Setter
    @JsonUnwrapped
    private AntwortTyp antwortTyp;

    public Antwort() {
        this.antwortTyp = new AntwortTyp();
    }

    @Override
    @JsonProperty("antwortId")
    public String getEntityId() {
        return super.getEntityId();
    }

}
