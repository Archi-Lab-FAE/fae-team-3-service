package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.EventPublishingEntityListener;
import de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender.persistance.Positionssender;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.NachrichtText;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(EventPublishingEntityListener.class)
public class Ausnahmesituation extends AbstractEntity {

  @OneToOne
  @Valid
  @Setter
  @JsonUnwrapped
  private Positionssender positionssender;

  @Getter
  @JsonUnwrapped
  private NachrichtText nachrichtText;

  @OneToMany(mappedBy = "ausnahmesituation",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<Nachricht> nachrichten = new HashSet<>();

  @Setter
  @Getter
  private Boolean hilfeUnterwegs = false;

  @Embedded
  private Position position;

  public Ausnahmesituation(String entityId) {
    super(entityId);
  }

  @Override
  @JsonProperty("ausnahmesituationId")
  public String getEntityId() {
    return super.getEntityId();
  }

  @Override
  public String getEventClass() {
    return "ausnahmesituation";
  }

  public void addNachricht(Nachricht nachricht) {
    if (!nachrichten.contains(nachricht)) {
      nachricht.setAusnahmesituation(this);
      this.nachrichten.add(nachricht);
    }
  }

}

