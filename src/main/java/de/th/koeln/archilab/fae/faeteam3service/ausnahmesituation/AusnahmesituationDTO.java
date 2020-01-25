package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtText;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class AusnahmesituationDTO {

    private String entityId;

    @JsonUnwrapped
    private Positionssender positionssender;

    @JsonUnwrapped
    private NachrichtText nachrichtText;

    private Set<Nachricht> nachrichten = new HashSet<>();

    private Boolean istAbgeschlossen = false;

    AusnahmesituationDTO() {
        this.entityId = UUID.randomUUID().toString();
    }

    @JsonProperty("ausnahmesituationId")
    public String getEntityId() {
        return this.entityId;
    }

}
