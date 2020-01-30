package de.th.koeln.archilab.fae.faeteam3service.antwort.persistance;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.EventPublishingEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(EventPublishingEntityListener.class)
public class Antwort extends AbstractEntity {

  @Enumerated(EnumType.STRING)
  private AntwortTyp antwortTyp;

  @Override
  @JsonProperty("antwortId")
  public String getEntityId() {
    return super.getEntityId();
  }

  @Override
  public String getEventClass() {
    return "antwort";
  }

}
