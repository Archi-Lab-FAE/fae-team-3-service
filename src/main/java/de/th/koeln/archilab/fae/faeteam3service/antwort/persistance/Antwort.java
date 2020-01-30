package de.th.koeln.archilab.fae.faeteam3service.antwort.persistance;

import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.EventPublishingEntityListener;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

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
  public String getEventClass() {
    return "antwort";
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    Antwort antwort = (Antwort) o;
    return antwortTyp == antwort.antwortTyp;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), antwortTyp);
  }
}
