package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.EventPublishingEntityListener;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.Nachricht;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.NachrichtText;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EntityListeners(EventPublishingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true)
public class Ausnahmesituation extends AbstractEntity {

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

  NachrichtText getNachrichtText() {
    return nachrichtText;
  }

  @Setter
  private Boolean istAbgeschlossen = false;

  public void addNachricht(Nachricht nachricht) {
    if (!nachrichten.contains(nachricht)) {
      this.nachrichten.add(nachricht);
      nachricht.setAusnahmesituation(this);
    }
  }

  @Override
  @JsonProperty("ausnamesituationId")
  public String getEntityId() {
    return super.getEntityId();
  }
}

