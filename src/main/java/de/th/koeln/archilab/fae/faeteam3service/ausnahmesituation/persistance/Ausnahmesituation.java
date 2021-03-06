package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.Objects;
import java.util.Set;

@Entity
@EntityListeners(EventPublishingEntityListener.class)
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Ausnahmesituation extends AbstractEntity {

  @OneToOne
  @Valid
  @Setter
  @JsonUnwrapped
  private Positionssender positionssender;

  @Setter
  @JsonUnwrapped
  private NachrichtText nachrichtText;

  @Setter
  @OneToMany(mappedBy = "ausnahmesituation",
          cascade = CascadeType.ALL,
          fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<Nachricht> nachrichten = new HashSet<>();

  @Setter
  private Boolean hilfeUnterwegs = false;

  @Embedded
  @Setter
  private Position position;

  public Ausnahmesituation(String id) {
    super(id);
  }

  @Override
  public String getEventClass() {
    return "ausnahmesituation";
  }

  /**
   * Nachricht hinzufügen.
   * @param nachricht Die hinzuzufügende Nachricht
   */
  public void addNachricht(Nachricht nachricht) {
    if (!nachrichten.contains(nachricht)) {
      nachricht.setAusnahmesituation(this);
      this.nachrichten.add(nachricht);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    Ausnahmesituation that = (Ausnahmesituation) o;
    return nachrichtText.equals(that.nachrichtText)
        && hilfeUnterwegs.equals(that.hilfeUnterwegs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), nachrichtText, hilfeUnterwegs);
  }
}

