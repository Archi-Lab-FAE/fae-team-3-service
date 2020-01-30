package de.th.koeln.archilab.fae.faeteam3service.antwort.persistance;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.EventPublishingEntityListener;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@EntityListeners(EventPublishingEntityListener.class)
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
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

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
