package de.th.koeln.archilab.fae.faeteam3service.antwort;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.EventPublishingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EntityListeners(EventPublishingEntityListener.class)
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

  @Override
  public String getEventClass() {
    return "antwort";
  }

}
